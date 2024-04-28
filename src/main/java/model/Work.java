package model;

public class Work
{
	private long seconds;

	public long seconds()
	{ return seconds; }

	public long incrementSeconds() throws SessionTooLong
	{
		seconds++;
		if (seconds == 60 * 60 * 4)
			throw new SessionTooLong();
		else
			return seconds;
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
