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
		void BeginsWork()
		{
			Work work = timer.start(anyTime);
			assertThat(work.seconds()).isEqualTo(0);
		}

		@Test
		void WorkMovesSecondsForward()
		{
			Work work = timer.start(anyTime);
			assertThat(work.incrementSeconds()).isEqualTo(1);
		}

		@Test
		void RestLastsFiveSecondsAfterWorksForTwentyFive()
		{
			LocalTime twentyFiveSecLater = anyTime.plusSeconds(25);

			Work work = timer.start(anyTime);
			Rest rest = work.rest(twentyFiveSecLater);
			assertThat(rest.seconds()).isEqualTo(5);
		}

		@Test
		void RestMovesSecondsDown()
		{
			Duration twentyFiveSeconds = Duration.of(25, ChronoUnit.SECONDS);
			Rest rest = new Rest(anyTime, twentyFiveSeconds);
			assertThat(rest.incrementSeconds()).isEqualTo(4);
		}
	}

}
