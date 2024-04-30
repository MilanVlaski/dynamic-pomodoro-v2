package main;

import java.time.LocalTime;

import model.Counter;
import model.Timer;

public class View
{
	private final Timer timer;
	private final Counter counter;

	private LocalTime time = LocalTime.MIN;

	public View(Timer timer, Counter counter)
	{
		this.timer = timer;
		this.counter = counter;
	}

	public LocalTime time()
	{ return time; }

	public void incrementByOneSecond()
	{ time = time.plusSeconds(1); }

	public void startWorking()
	{
		var work = timer.start();
		work.registerCounter(counter);
		work.registerView(this);
		counter.count(work);
	}


}
