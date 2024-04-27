package main;

public class Rest
{
	private long seconds;
	private Counter counter;

	public Rest(long workDuration)
	{ this.seconds = workDuration / 5; }

	public long seconds()
	{ return seconds; }

	public long decrementSeconds()
	{
		if (seconds > 0)
			seconds--;
		
		if (seconds == 0 && counter != null)
			counter.stop();

		return seconds;
	}

	public void count(Counter counter)
	{
		this.counter = counter;
		counter.count(this);
	}

}
