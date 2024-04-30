package model;

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
	public void count(Rest rest)
	{ rest.decrementSeconds(); }

	@Override
	public boolean isWorking()
	{ return working; }

}
