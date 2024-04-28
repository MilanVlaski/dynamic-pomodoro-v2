package model;

import main.IViewModel;
import model.Work.SessionTooLong;

public class SingleCounter implements Counter
{

	private boolean working;

	@Override
	public void count(Work work, IViewModel viewModel) throws SessionTooLong
	{
		try
		{
			viewModel.setSeconds(work.incrementSeconds());
		} catch (SessionTooLong e)
		{
			stop();
			throw e;
		}
	}

	@Override
	public void stop()
	{ this.working = false; }

	@Override
	public void count(Rest rest)
	{
		var seconds = rest.decrementSeconds();
		if (seconds == 0)
			stop();
	}

	@Override
	public boolean isWorking()
	{ return working; }

}
