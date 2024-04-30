package model;

public class Work implements Countable
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

	@Override
	public void registerCounter(Counter counter)
	{ this.counter = counter; }

}
