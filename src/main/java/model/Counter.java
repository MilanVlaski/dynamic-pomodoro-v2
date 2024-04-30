package model;

import main.Director;

public interface Counter
{
	void count(Work work);
	void count(Rest rest, Director director);
	void stop();
	boolean isWorking();
}
