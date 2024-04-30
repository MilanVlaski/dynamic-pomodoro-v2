package main.mocks;

import main.Director;
import model.*;

public class Counts implements Counter
{

	private int times;
	private Counter counter;

	public Counts()
	{ this.counter = new SingleCounter(); }

	public Counter times(int times)
	{
		this.times = times;
		return this;
	}

	@Override
	public void count(Work work)
	{
		work.registerCounter(this);
		for (int i = 0; i < times && isWorking(); i++)
			counter.count(work);
	}

	@Override
	public void count(Rest rest, Director viewModel)
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
