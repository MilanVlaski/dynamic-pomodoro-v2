package model;

import model.Work.SessionTooLong;

public interface Counter
{
	void count(Work work) throws SessionTooLong;
	void count(Rest rest);
	void stop();
	boolean isWorking();
}
