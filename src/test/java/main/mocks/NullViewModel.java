package main.mocks;

import main.Director;

public class NullViewModel implements Director
{

	@Override
	public void startWorking()
	{}

	@Override
	public void setSeconds(int seconds)
	{}

	@Override
	public void startResting()
	{}

}