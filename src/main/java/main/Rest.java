package main;

import java.time.Duration;

public class Rest
{
	private long seconds;

	public Rest(Duration workDuration)
	{ this.seconds = workDuration.toSeconds() / 5; }

	public long seconds()
	{ return seconds; }

	public long decrementSeconds()
	{
		if (seconds != 0)
			seconds--;

		return seconds;
	}

}
