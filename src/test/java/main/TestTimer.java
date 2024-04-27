package main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.*;

import model.*;
import model.Work.SessionTooLong;

public class TestTimer
{
	Timer timer;

	@BeforeEach
	void setup()
	{ timer = new Timer(); }

	@Nested
	class _Work
	{
		Work work;

		@BeforeEach
		void setup()
		{ work = timer.start(); }

		@Test
		void Begins_Work()
		{ assertThat(work.seconds()).isEqualTo(0); }

		@Test
		void Seconds_Increase() throws SessionTooLong
		{ assertThat(work.incrementSeconds()).isEqualTo(1); }

		@Test
		void Counts_Up_Once() throws SessionTooLong
		{
			work.count(new SingleCounter());
			assertThat(work.seconds()).isEqualTo(1);
		}

		@Test
		void Counts_Up_Twice() throws SessionTooLong
		{
			work.count(new Counts(2, new SingleCounter()));
			assertThat(work.seconds()).isEqualTo(2);
		}

		@Nested
		class Timeout
		{
			int fourHours = 60 * 60 * 4;

			@Test
			void After_Four_Hours() throws SessionTooLong
			{
				Counter fourHourCounter = new Counts(fourHours, new SingleCounter());
				assertThatExceptionOfType(SessionTooLong.class)
				        .isThrownBy(() -> work.count(fourHourCounter));
			}

			@Test
			void Stops_Seconds_From_Increasing_Past_Four_Hours()
			{
				int fiveHours = 60 * 60 * 5;
				Counts fiveHourCounter = new Counts(fiveHours, new SingleCounter());
				try
				{
					work.count(fiveHourCounter);
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
			Counter twentyFiveCounter = new Counts(25, new SingleCounter());

			Work work = timer.start();
			work.count(twentyFiveCounter);
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
		void Counts_Backwards()
		{
			Rest rest = new Rest(5);
			rest.count(new SingleCounter());
			assertThat(rest.seconds()).isEqualTo(0);
		}

		@Test
		void Stops_Counting_When_Zero()
		{
			Rest rest = new Rest(5);
			Counts counter = new Counts(2, new SingleCounter());
			rest.count(counter);
			assertThat(counter.isWorking()).isFalse();
		}
	}

	class Counts implements Counter
	{

		private final int times;
		private Counter counter;

		Counts(int times, Counter counter)
		{
			this.counter = counter;
			this.times = times;
		}

		@Override
		public void count(Work work) throws SessionTooLong
		{
			for (int i = 0; i < times; i++)
				counter.count(work);
		}

		@Override
		public void count(Rest rest)
		{
			for (int i = 0; i < times; i++)
				counter.count(rest);
		}

		@Override
		public void stop()
		{ counter.stop(); }

		@Override
		public boolean isWorking()
		{ return counter.isWorking(); }

	}

}
