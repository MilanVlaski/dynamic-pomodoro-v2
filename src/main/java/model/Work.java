package model;

public class Work
{
	private int seconds;
	private Counter counter;

	public int seconds()
	{ return seconds; }

	public void incrementSeconds()
	{
		seconds++;
		if (seconds == 60 * 60 * 4)
		{
			if(counter != null)
				counter.stop();
		}
	}

	public Rest rest()
	{ return new Rest(seconds); }


	public class SessionTooLong extends Exception
	{
		private static final long serialVersionUID = -5023485537050739302L;

		SessionTooLong()
		{ super("Session can't last more than four hours!"); }
	}


	public void registerCounter(Counter counter)
	{ this.counter = counter; }

}
