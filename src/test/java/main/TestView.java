package main;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import main.Work.SessionTooLong;

public class TestView
{

	@Test
	void IncrementsTime_While_Working() throws SessionTooLong
	{
		var timer = new Timer();
		Counter counter = new CountsOnce();
		FakeView view = new FakeView(timer, counter);
		view.startWorking();

		assertThat(view.seconds).isEqualTo(1);
	}

	public class FakeView implements View
	{

		private final Timer timer;
		private final Counter counter;
		
		public long seconds;

		public FakeView(Timer timer, Counter counter)
		{
			this.timer = timer;
			this.counter = counter;
		}

		@Override
		public void startWorking() throws SessionTooLong
		{
			Work work = timer.start(LocalTime.now());
			counter.count(work, this);
		}

		@Override
		public void setTime(long seconds)
		{ this.seconds = seconds; }

	}

	class CountsOnce implements Counter
	{
		@Override
		public void count(Work work, View view) throws SessionTooLong
		{ view.setTime(work.incrementSeconds()); }
	}
}
