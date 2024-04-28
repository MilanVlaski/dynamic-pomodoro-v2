package main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.*;

import main.mocks.Counts;
import main.mocks.NullViewModel;
import model.*;
import model.Work.SessionTooLong;

public class TestTimer
{
	@Nested
	class _Work
	{
		Work work;

		@BeforeEach
		void setup()
		{ work = new Timer().start(); }

		@Test
		void Begins_Work()
		{ assertThat(work.seconds()).isEqualTo(0); }

		@Test
		void Seconds_Increase() throws SessionTooLong
		{ assertThat(work.incrementSeconds()).isEqualTo(1); }

		@Test
		void Counts_Up_Once() throws SessionTooLong
		{
			new SingleCounter().count(work, new NullViewModel());
			assertThat(work.seconds()).isEqualTo(1);
		}

		@Test
		void Counts_Up_Twice() throws SessionTooLong
		{
			Counter twiceCounter = new Counts(new SingleCounter()).times(2);
			twiceCounter.count(work, new NullViewModel());
			assertThat(work.seconds()).isEqualTo(2);
		}

		@Nested
		class Timeout
		{
			int fourHours = 60 * 60 * 4;

			@Test
			void After_Four_Hours() throws SessionTooLong
			{
				Counter fourHourCounter = new Counts(new SingleCounter())
				        .times(fourHours);
				assertThatExceptionOfType(SessionTooLong.class).isThrownBy(
				        () -> fourHourCounter.count(work, new NullViewModel()));
			}

			@Test
			void Stops_Seconds_From_Increasing_Past_Four_Hours()
			{
				int fiveHours = 60 * 60 * 5;
				Counter fiveHourCounter = new Counts(new SingleCounter())
				        .times(fiveHours);
				try
				{
					fiveHourCounter.count(work, new NullViewModel());
				} catch (SessionTooLong e)
				{
					e.printStackTrace();
				}
				assertThat(work.seconds()).isEqualTo(fourHours);
				assertThat(fiveHourCounter.isWorking()).isFalse();
			}
		}
	}

	@Nested
	class _Rest
	{
		@Test
		void Lasts_Five_Seconds_After_Works_For_Twenty_Five() throws SessionTooLong
		{
			Counter twentyFiveCounter = new Counts(new SingleCounter()).times(25);

			Work work = new Timer().start();
			twentyFiveCounter.count(work, new NullViewModel());
			Rest rest = work.rest();
			assertThat(rest.seconds()).isEqualTo(5);
		}

		@Test
		void Seconds_Decrease()
		{
			Rest rest = new Rest(25);
			assertThat(rest.decrementSeconds()).isEqualTo(4);
		}

		@Test
		void Seconds_Dont_Decrease_Past_Zero()
		{
			Rest rest = new Rest(0);
			assertThat(rest.decrementSeconds()).isEqualTo(0);
		}

		@Test
		void Lasts_Zero_Seconds_If_Has_Not_Worked()
		{
			Rest rest = new Rest(0);
			assertThat(rest.seconds()).isEqualTo(0);
		}

		@Test
		void Stops_Counting_When_Zero()
		{
			Rest rest = new Rest(5);
			Counter counter = new Counts(new SingleCounter()).times(2);
			counter.count(rest, new NullViewModel());
			assertThat(counter.isWorking()).isFalse();
		}
	}


}
