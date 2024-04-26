package main;

import java.time.Duration;
import java.time.LocalTime;

public class Work
{
	private final LocalTime start;
	private long seconds;

	Work(LocalTime now)
	{ this.start = now; }

	public long seconds()
	{ return seconds; }

	public long incrementSeconds() throws SessionTooLong
	{
		if (seconds >= 60 * 60 * 4)
			throw new SessionTooLong();
		return ++seconds;
	}

	public Rest rest(LocalTime now)
	{
		var workDuration = Duration.between(start, now);
		return new Rest(workDuration);
	}

	public class SessionTooLong extends Exception
	{
		SessionTooLong()
		{ super("Session can't last more than four hours!"); }
	}
}
