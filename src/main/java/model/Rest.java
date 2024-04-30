package model;

public class Rest implements Countable
{
	private int seconds;
	private Counter counter = new NullCounter();

	public Rest(int workDuration)
	{ this.seconds = workDuration / 5; }

	public int seconds()
	{ return seconds; }

	public void decrementSeconds()
	{
		if (seconds > 0)
			seconds--;
		else
			counter.stop();
	}

	@Override
	public void registerCounter(Counter counter)
	{ this.counter = counter; }
}
