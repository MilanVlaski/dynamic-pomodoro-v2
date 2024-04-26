package main;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import main.Work.SessionTooLong;

public class TestView
{

	@Test
	void IncrementsTime_While_Working() throws SessionTooLong
	{
		var timer = new Timer();
		Counter counter = new CountsSeconds(1);
		FakeView view = new FakeView(timer, counter);
		view.startWorking();

		assertThat(view.seconds).isEqualTo(1);
	}

	@Test
	void Shows_Correct_Time_When_Rests_After_Work() throws SessionTooLong
	{
		var timer = new Timer();
		Counter counter = new CountsSeconds(25);
		FakeView view = new FakeView(timer, counter);
		view.startWorking();
		view.startResting(); 
		
		assertThat(view.seconds).isEqualTo(5);
	}

	public class FakeView implements View
	{

		private final Timer timer;
		private final Counter counter;
		private Work work;

		public long seconds;

		public FakeView(Timer timer, Counter counter)
		{
			this.timer = timer;
			this.counter = counter;
		}

		@Override
		public void startWorking() throws SessionTooLong
		{
			this.work = timer.start();
			counter.count(work, this);
		}

		@Override
		public void setTime(long seconds)
		{ this.seconds = seconds; }

		@Override
		public void startResting()
		{}

	}

	class CountsSeconds implements Counter
	{
		private final int seconds;

		public CountsSeconds(int seconds)
		{ this.seconds = seconds; }

		@Override
		public void count(Work work, View view) throws SessionTooLong
		{
			for (int i = 0; i < seconds; i++)
				view.setTime(work.incrementSeconds());
		}
	}
}
