package model;

import main.Director;

public class SingleCounter implements Counter
{

	private boolean working = true;

	@Override
	public void count(Work work)
	{ work.incrementSeconds(); }

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
