package main;

import model.Work.SessionTooLong;

public class View
{

	public long seconds;
	public boolean alerted;

	public void alert(SessionTooLong e)
	{alerted = true;}
}
