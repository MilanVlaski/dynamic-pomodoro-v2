package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestTimer
{

	@Test
	void TimerBeginsWork()
	{
		var timer = new Timer();
		Work work = timer.start();
		assertThat(work.seconds()).isEqualTo(0);
	}
}
