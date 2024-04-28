package main;

import model.*;
import model.Work.SessionTooLong;

public class ViewModel implements IViewModel
{

	private View view;
	private Counter counter;
	private Timer timer;

	public long seconds;

	public ViewModel(View view, Timer timer, Counter counter)
	{
		this.view = view;
		this.timer = timer;
		this.counter = counter;
	}

	public ViewModel(View view, Timer timer)
	{

		this.view = view;
		this.timer = timer;
	}

	@Override
	public void startWorking()
	{
		Work work = timer.start();

		try
		{
			work.count(counter, null);
		} catch (SessionTooLong e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setSeconds(long seconds)
	{}

}
