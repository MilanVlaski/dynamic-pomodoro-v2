package model;

public class NullCounter implements Counter
{
	@Override
	public void count(Work work)
	{}

	@Override
	public void count(Rest rest)
	{}

	@Override
	public void stop()
	{}

	@Override
	public boolean isWorking()
	{ return false; }

}
