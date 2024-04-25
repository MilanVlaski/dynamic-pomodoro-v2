package main;

import java.time.Duration;
import java.time.LocalTime;

public class Rest
{

	private final LocalTime start;
	private long seconds;

	public Rest(LocalTime now, Duration workDuration)
	{
		this.start = now;
		this.seconds = workDuration.toSeconds() / 5;
	}

	public long seconds()
	{ return seconds; }

	public long incrementSeconds()
	{ return --seconds; }

}
