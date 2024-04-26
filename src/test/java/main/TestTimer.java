package main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalTime;

import org.junit.jupiter.api.*;

import main.Work.SessionTooLong;

public class TestTimer
{

	@Nested
	class _Timer
	{
		Timer timer;

		LocalTime anyTime = LocalTime.MIN;

		@BeforeEach
		void setup()
		{ timer = new Timer(); }

		@Test
		void Begins_Work()
		{
			Work work = timer.start();
			assertThat(work.seconds()).isEqualTo(0);
		}

		@Test
		void Seconds_Passed_Increase_While_Working() throws SessionTooLong
		{
			Work work = timer.start();
			assertThat(work.incrementSeconds()).isEqualTo(1);
		}

		@Test
		void Seconds_Increase_Only_Up_To_Four_Hours_While_Working() throws SessionTooLong
		{
			Work work = timer.start();
			long fourHours = 60 * 60 * 4;

			var fourHourWork = new WorkFromTime(work).of(fourHours);

			assertThatThrownBy(() -> fourHourWork.incrementSeconds())
			        .isInstanceOf(SessionTooLong.class);
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
