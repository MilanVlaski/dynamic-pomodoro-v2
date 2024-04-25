package main;

import java.time.Duration;
import java.time.LocalTime;

public class Work
{

	private long seconds;
	private final LocalTime start;

	Work(LocalTime now)
	{this.start = now;}

	public long seconds()
	{ return seconds; }

	public long incrementSeconds()
	{ return ++seconds;}

	public Rest rest(LocalTime now)
	{ return new Rest(now, Duration.between(start, now)); }

}
