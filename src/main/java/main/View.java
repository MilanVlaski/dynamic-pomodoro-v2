package main;

import main.Work.SessionTooLong;

public interface View
{

	void startWorking() throws SessionTooLong;
	void setTime(long seconds);
	void startResting();

}
