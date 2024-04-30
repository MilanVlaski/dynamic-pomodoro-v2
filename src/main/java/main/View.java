package main;

import java.time.LocalTime;

import model.*;

public class View
{
	private final Timer timer;
	private final Counter counter;
	private Work work;

	private LocalTime time = LocalTime.MIN;

	public View(Timer timer, Counter counter)
	{
		this.timer = timer;
		this.counter = counter;
	}

	public LocalTime time()
	{ return time; }

	public void startWorking()
	{
		this.work = timer.start();
		work.registerCounter(counter);
		work.registerView(this);
		counter.count(work);
	}

	public void startResting()
	{
		var rest = work.rest();
		time = rest.time();
		rest.registerView(this);
		counter.count(rest);
	}

	public void setTime(LocalTime time)
	{ this.time = time; }

	public void finishRest()
	{}


}
