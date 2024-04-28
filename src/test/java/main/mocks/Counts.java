package main.mocks;

import main.IViewModel;
import model.*;
import model.Work.SessionTooLong;

public class Counts implements Counter
{

	private int times;
	private Counter counter;

	public Counts(Counter counter)
	{ this.counter = counter; }

	public Counter times(int times)
	{
		this.times = times;
		return this;
	}

	@Override
	public void count(Work work, IViewModel viewModel) throws SessionTooLong
	{
		for (int i = 0; i < times; i++)
			counter.count(work, viewModel);
	}

	@Override
	public void count(Rest rest, IViewModel viewModel)
	{
		for (int i = 0; i < times; i++)
			counter.count(rest, viewModel);
	}

	@Override
	public void stop()
	{ counter.stop(); }

	@Override
	public boolean isWorking()
	{ return counter.isWorking(); }

}
