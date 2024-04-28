package model;

import main.Director;
import model.Work.SessionTooLong;

public class SingleCounter implements Counter
{

	private boolean working;

	@Override
	public void count(Work work, Director viewModel) throws SessionTooLong
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
	public void count(Rest rest, Director viewModel)
	{
		var seconds = rest.decrementSeconds();
		viewModel.setSeconds(seconds);
		if (seconds == 0)
		{
			viewModel.finishRest();
			stop();
		}
	}

	@Override
	public boolean isWorking()
	{ return working; }

}
