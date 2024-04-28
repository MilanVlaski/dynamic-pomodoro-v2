package model;

import main.Director;
import model.Work.SessionTooLong;

public interface Counter
{
	void count(Work work, Director director) throws SessionTooLong;
	void count(Rest rest, Director director);
	void stop();
	boolean isWorking();
}
