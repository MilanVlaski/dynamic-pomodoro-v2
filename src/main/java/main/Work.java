package main;

public class Work
{
	private long seconds;
	private Counter counter;

	public long seconds()
	{ return seconds; }

	public long incrementSeconds() throws SessionTooLong
	{
		seconds++;
		if (seconds == 60 * 60 * 4)
		{
			counter.stop();
			throw new SessionTooLong();
		}
		else
			return seconds;
	}

	public void count(Counter counter) throws SessionTooLong
	{
		this.counter = counter;
		counter.count(this);
	}

	public Rest rest()
	{ return new Rest(seconds); }

	public class SessionTooLong extends Exception
	{
		private static final long serialVersionUID = -5023485537050739302L;

		SessionTooLong()
		{ super("Session can't last more than four hours!"); }
	}

}
