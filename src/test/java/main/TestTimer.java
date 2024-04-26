package main;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
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
			Work work = timer.start(anyTime);
			assertThat(work.seconds()).isEqualTo(0);
		}

		@Test
		void Seconds_Passed_Increase_While_Working() throws SessionTooLong
		{
			Work work = timer.start(anyTime);
			assertThat(work.incrementSeconds()).isEqualTo(1);
		}

		@Test
		void Seconds_Increase_Only_Up_To_Four_Hours_While_Working() throws SessionTooLong
		{
			Work work = timer.start(anyTime);
			long fourHours = 60 * 60 * 4;

			var fourHourWork = new WorkFromTime(work).of(fourHours);

			assertThatThrownBy(() -> fourHourWork.incrementSeconds())
			        .isInstanceOf(SessionTooLong.class);
		}
		
		@Nested
		class _Rest
		{
			Duration twentyFiveSeconds = Duration.of(25, SECONDS);

			@Test
			void Lasts_Five_Seconds_After_Works_For_Twenty_Five()
			{
				LocalTime twentyFiveSecLater = anyTime.plus(twentyFiveSeconds);

				Work work = timer.start(anyTime);
				Rest rest = work.rest(twentyFiveSecLater);
				assertThat(rest.seconds()).isEqualTo(5);
			}

			@Test
			void Seconds_Decrease()
			{
				Rest rest = new Rest(twentyFiveSeconds);
				assertThat(rest.decrementSeconds()).isEqualTo(4);
			}

			@Test
			void Seconds_Dont_Decrease_Past_Zero()
			{
				Rest rest = new Rest(Duration.ZERO);
				assertThat(rest.decrementSeconds()).isEqualTo(0);
			}

			@Test
			void Lasts_Zero_Seconds_If_Has_Not_Worked()
			{
				Rest rest = new Rest(Duration.ZERO);
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
