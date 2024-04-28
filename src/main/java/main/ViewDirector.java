package main;

import model.*;
import model.Work.SessionTooLong;

public class ViewDirector implements Director
{

	private final View view;
	private final Counter counter;
	private final Timer timer;

	public int seconds;
	public Work work;

	public ViewDirector(View view, Timer timer, Counter counter)
	{
		this.view = view;
		this.timer = timer;
		this.counter = counter;
	}

	@Override
	public void startWorking()
	{
		Work work = timer.start();
		this.work = work;
		try
		{
			counter.count(work, this);
		} catch (SessionTooLong e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void startResting()
	{
		var rest = work.rest();
		counter.count(rest, this);
	}

	@Override
	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
		view.seconds = seconds;
	}


}
