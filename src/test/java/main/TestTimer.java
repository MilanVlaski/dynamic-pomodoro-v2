package main;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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
		void Begin_sWork()
		{
			Work work = timer.start(anyTime);
			assertThat(work.seconds()).isEqualTo(0);
		}

		@Test
		void Work_Moves_Seconds_Forward()
		{
			Work work = timer.start(anyTime);
			assertThat(work.incrementSeconds()).isEqualTo(1);
		}

		@Nested
		class _Rest
		{
			Duration twentyFiveSeconds = Duration.of(25, ChronoUnit.SECONDS);

			@Test
			void Lasts_Five_Seconds_After_Works_For_Twenty_Five()
			{
				LocalTime twentyFiveSecLater = anyTime.plus(twentyFiveSeconds);

				Work work = timer.start(anyTime);
				Rest rest = work.rest(twentyFiveSecLater);
				assertThat(rest.seconds()).isEqualTo(5);
			}

			@Test
			void Moves_Seconds_Down()
			{
				Rest rest = new Rest(anyTime, twentyFiveSeconds);
				assertThat(rest.incrementSeconds()).isEqualTo(4);
			}

		}

	}

}
