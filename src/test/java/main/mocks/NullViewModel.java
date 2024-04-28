package main.mocks;

import main.IViewModel;

public class NullViewModel implements IViewModel
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