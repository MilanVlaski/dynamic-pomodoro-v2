package main;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.jupiter.api.*;

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
		void Seconds_Passed_Increase_While_Working()
		{
			Work work = timer.start(anyTime);
			assertThat(work.incrementSeconds()).isEqualTo(1);
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

}
