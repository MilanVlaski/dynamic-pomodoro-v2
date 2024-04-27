package model;

import model.Work.SessionTooLong;

public class SingleCounter implements Counter
{

	private boolean working;

	@Override
	public void count(Work work) throws SessionTooLong
	{
		try
		{
			work.incrementSeconds();
		} catch (SessionTooLong e)
		{
			stop();
			e.printStackTrace();
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
