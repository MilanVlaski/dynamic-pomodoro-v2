package model;

public class Rest
{
	private int seconds;

	public Rest(int workDuration)
	{ this.seconds = workDuration / 5; }

	public int seconds()
	{ return seconds; }

	public int decrementSeconds()
	{
		if (seconds > 0)
			seconds--;
			
		return seconds;
	}

}
