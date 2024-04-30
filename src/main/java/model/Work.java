package model;

import main.View;

public class Work
{
	private int seconds;
	private Counter counter = new NullCounter();
	private View view = new NullView(null, null);

	public int seconds()
	{ return seconds; }

	public void incrementSeconds()
	{
		seconds++;
		view.incrementByOneSecond();
		if (seconds >= 60 * 60 * 4)
			counter.stop();
	}

	public Rest rest()
	{ return new Rest(seconds); }

	public void registerCounter(Counter counter)
	{ this.counter = counter; }

	public void registerView(View view)
	{ this.view = view; }

}
