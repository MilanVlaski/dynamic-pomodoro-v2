package main;

import main.Work.SessionTooLong;

public interface Counter
{
	void count(Work work) throws SessionTooLong;
	void count(Rest rest);
	void stop();
}
