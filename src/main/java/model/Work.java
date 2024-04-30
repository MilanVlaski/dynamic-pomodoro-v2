package model;

public class Work
{
	private int seconds;
	private Counter counter = new NullCounter();

	public int seconds()
	{ return seconds; }

	public void incrementSeconds()
	{
		seconds++;
		if (seconds >= 60 * 60 * 4)
			counter.stop();
	}

	public Rest rest()
	{ return new Rest(seconds); }

	public void registerCounter(Counter counter)
	{ this.counter = counter; }

	class NullCounter implements Counter
	{

		@Override
		public void count(Work work)
		{}

		@Override
		public void count(Rest rest)
		{}

		@Override
		public void stop()
		{}

		@Override
		public boolean isWorking()
		{ return false; }

	}
}
