package main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
				Counter once = new SingleCounter();
				Counter twice = new CountsTimes(once, 2);
				work.count(twice);

				assertThat(work.seconds()).isEqualTo(2);
			}

			@Test
			void Seconds_Increase_Only_Up_To_Four_Hours() throws SessionTooLong
			{
				int fourHours = 60 * 60 * 4;

				Counter fourHour = new CountsTimes(new SingleCounter(), fourHours);
				
				assertThatThrownBy(() -> work.count(fourHour))
				        .isInstanceOf(SessionTooLong.class);
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

		CountsTimes(Counter counter, int times)
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
