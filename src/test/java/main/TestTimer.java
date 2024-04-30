package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;

import main.mocks.Counts;
import model.*;

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
		void Seconds_Increase()
		{
			work.incrementSeconds();
			assertThat(work.seconds()).isEqualTo(1);
		}

		@Test
		void Counts_Up_Once()
		{
			new SingleCounter().count(work);
			assertThat(work.seconds()).isEqualTo(1);
		}

		@Test
		void Counts_Up_Twice()
		{
			Counter twiceCounter = new Counts().times(2);
			twiceCounter.count(work);
			assertThat(work.seconds()).isEqualTo(2);
		}

		@Nested
		class Timeout
		{
			public static int fourHours = 60 * 60 * 4;

			@Test
			void Stops_Seconds_From_Increasing_Past_Four_Hours()
			{
				int fiveHours = 60 * 60 * 5;
				Counter fiveHourCounter = new Counts().times(fiveHours);

				fiveHourCounter.count(work);

				assertThat(work.seconds()).isEqualTo(fourHours);
			}
		}
	}

	@Nested
	class _Rest
	{
		@Test
		void Lasts_Five_Seconds_After_Works_For_Twenty_Five()
		{
			Counter twentyFiveCounter = new Counts().times(25);

			Work work = new Timer().start();
			twentyFiveCounter.count(work);
			Rest rest = work.rest();
			assertThat(rest.seconds()).isEqualTo(5);
		}

		@Test
		void Seconds_Decrease()
		{
			Rest rest = new Rest(25);
			rest.decrementSeconds();
			assertThat(rest.seconds()).isEqualTo(4);
		}

		@Test
		void Seconds_Dont_Decrease_Past_Zero()
		{
			Rest rest = new Rest(0);
			rest.decrementSeconds();
			assertThat(rest.seconds()).isEqualTo(0);
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
			Counter counter = new Counts().times(2);
			counter.count(rest);
			assertThat(rest.seconds()).isEqualTo(0);
		}
	}


}
