package main;

import java.time.Duration;
import java.time.LocalTime;

public class Rest
{

	private long seconds;

	public Rest(LocalTime now, Duration workDuration)
	{ this.seconds = workDuration.toSeconds() / 5; }

	public long seconds()
	{ return seconds; }

	public long incrementSeconds()
	{
		if (seconds != 0)
			seconds--;

		return seconds;
	}

}
