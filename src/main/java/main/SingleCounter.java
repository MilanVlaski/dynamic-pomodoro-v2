package main;

import main.Work.SessionTooLong;

public class SingleCounter implements Counter
{

	@Override
	public void count(Work work) throws SessionTooLong
	{work.incrementSeconds();}

}
