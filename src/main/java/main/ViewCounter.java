package main;

import model.*;
import model.Work.SessionTooLong;

public class ViewCounter implements Counter
{

	private final Counter counter;
	private IViewModel viewModel;

	public ViewCounter(Counter counter)
	{ this.counter = counter; }

	@Override
	public void count(Work work, IViewModel viewModel) throws SessionTooLong
	{ counter.count(work, null); }

	@Override
	public void count(Rest rest)
	{ counter.count(rest); }

	@Override
	public void stop()
	{ counter.stop(); }

	@Override
	public boolean isWorking()
	{ return counter.isWorking(); }

	public void init(IViewModel viewModel)
	{ this.viewModel = viewModel; }

}
