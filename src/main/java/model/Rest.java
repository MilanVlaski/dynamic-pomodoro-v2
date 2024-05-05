package model;

import java.time.LocalTime;

import main.View;

public class Rest
{
	private int seconds;
	private Counter counter = new NullCounter();
	private View view = new NullView(null, null);

	public Rest(int workDuration)
	{ this.seconds = workDuration / 5; }

	public int seconds()
	{ return seconds; }

	public void decrementSeconds()
	{
		if (seconds > 0) {
			seconds--;
			view.setTime(time());
		}
		else {
			view.finishRest();
			counter.stop();
		}
	}

	public void registerCounter(Counter counter)
	{ this.counter = counter; }

	public void registerView(View view)
	{ this.view = view; }

	public LocalTime time()
	{ return LocalTime.ofSecondOfDay(seconds); }
}
