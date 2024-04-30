package model;

public interface Counter
{
	void count(Work work);
	void count(Rest rest);
	void stop();
	boolean isWorking();
}
