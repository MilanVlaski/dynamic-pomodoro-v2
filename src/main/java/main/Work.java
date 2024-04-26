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

	public long incrementSeconds()
	{ return ++seconds; }

	public Rest rest(LocalTime now)
	{
		var workDuration = Duration.between(start, now);
		return new Rest(workDuration);
	}

}
