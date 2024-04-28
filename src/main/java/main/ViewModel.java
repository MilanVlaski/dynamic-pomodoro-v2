package main;

import model.*;
import model.Work.SessionTooLong;

public class ViewModel implements IViewModel
{

	private View view;
	private Counter counter;
	private Timer timer;

	public long seconds;
	public Work work;

	public ViewModel(View view, Timer timer, Counter counter)
	{
		this.view = view;
		this.timer = timer;
		this.counter = counter;
	}

	@Override
	public void startWorking()
	{
		Work work = timer.start();

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
		counter.count(rest, null);
	}

	@Override
	public void setSeconds(long seconds)
	{
		this.seconds = seconds;
		view.seconds = seconds;
	}


}
