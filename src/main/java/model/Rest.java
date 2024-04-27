package model;

public class Rest
{
	private long seconds;

	public Rest(long workDuration)
	{ this.seconds = workDuration / 5; }

	public long seconds()
	{ return seconds; }

	public long decrementSeconds()
	{
		if (seconds > 0)
			seconds--;
			
		return seconds;
	}

	public void count(Counter counter)
	{ counter.count(this); }

}
