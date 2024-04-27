package main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.*;

import main.Work.SessionTooLong;

public class TestTimer
{
	@Nested
	class _Timer
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
			void Seconds_Passed_Increase() throws SessionTooLong
			{ assertThat(work.incrementSeconds()).isEqualTo(1); }

			@Test
			void Counts_Up_Once() throws SessionTooLong
			{
				Counter singleCounter = new SingleCounter();
				work.count(singleCounter);

				assertThat(work.seconds()).isEqualTo(1);
			}

			@Test
			void Counts_Up_Twice() throws SessionTooLong
			{
				Counter twice = new CountsTimes(2, new SingleCounter());
				work.count(twice);

				assertThat(work.seconds()).isEqualTo(2);
			}

			@Nested
			class Timeout
			{
				int fourHours = 60 * 60 * 4;

				@Test
				void After_Four_Hours() throws SessionTooLong
				{
					Counter fourHourCounter = new CountsTimes(fourHours,
					                                          new SingleCounter());
					assertThatExceptionOfType(SessionTooLong.class)
					        .isThrownBy(() -> work.count(fourHourCounter));
				}

				@Test
				void Stops_Seconds_From_Increasing_Past_Four_Hours()
				{
					int fiveHours = 60 * 60 * 5;
					CountsTimes fiveHourCounter = new CountsTimes(fiveHours,
					                                          new SingleCounter());
					try
					{
						work.count(fiveHourCounter);
					} catch (SessionTooLong e)
					{
						e.printStackTrace();
					}
					assertThat(work.seconds()).isEqualTo(fourHours);
					assertThat(fiveHourCounter.wasStopped()).isTrue();
				}

			}


		}


		@Nested
		class _Rest
		{
			@Test
			void Lasts_Five_Seconds_After_Works_For_Twenty_Five() throws SessionTooLong
			{
				Work work = timer.start();
				Work workForTwentyFive = new WorkFromTime(work).of(25);
				Rest rest = workForTwentyFive.rest();
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

		}

	}

	class CountsTimes implements Counter
	{

		private final int times;
		private Counter counter;
		private boolean wasStopped;

		CountsTimes(int times, Counter counter)
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
		public void stop()
		{ this.wasStopped = true; }

		public boolean wasStopped()
		{ return wasStopped; }

	}


	class WorkFromTime
	{
		private final Work work;

		WorkFromTime(Work work)
		{ this.work = work; }

		Work of(long seconds) throws SessionTooLong
		{
			for (int i = 0; i < seconds; i++)
				work.incrementSeconds();
			return work;
		}

	}
}
