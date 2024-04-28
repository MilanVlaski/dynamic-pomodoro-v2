package main.mocks;

import main.Director;

public class NullDirector implements Director
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

	@Override
	public void finishRest()
	{}

}