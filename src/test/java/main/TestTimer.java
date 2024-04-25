package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;

public class TestTimer
{

	@Nested
	class _Timer
	{
		Timer timer;

		@BeforeEach
		void setup()
		{ timer = new Timer(); }

		@Test
		void TimerBeginsWork()
		{
			Work work = timer.start();
			assertThat(work.seconds()).isEqualTo(0);
		}

		@Test
		void TimerMovesSecondsForward()
		{
			Work work = timer.start();
			assertThat(work.incrementSeconds()).isEqualTo(1);
		}
	}

}
