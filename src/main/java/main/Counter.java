package main;

import main.Work.SessionTooLong;

public interface Counter
{
	void count(Work work, View view) throws SessionTooLong;
}
