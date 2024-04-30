//package main;
//
//import model.*;
//import model.Work.SessionTooLong;
//
//public class ViewDirector implements Director
//{
//
//	private final View view;
//	private final Counter counter;
//	private final Timer timer;
//
//	public int seconds;
//	private Work work;
//	public boolean restFinished;
//
//	public ViewDirector(View view, Timer timer, Counter counter)
//	{
//		this.view = view;
//		this.timer = timer;
//		this.counter = counter;
//	}
//
//	@Override
//	public void startWorking()
//	{
//		Work work = timer.start();
//		this.work = work;
//		try
//		{
//			counter.count(work);
//		} catch (SessionTooLong e)
//		{
//			view.alert(e);
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void startResting()
//	{
//		var rest = work.rest();
//		counter.count(rest, this);
//	}
//
//	@Override
//	public void setSeconds(int seconds)
//	{
//		this.seconds = seconds;
//		view.seconds = seconds;
//	}
//
//	@Override
//	public void finishRest()
//	{
//		this.restFinished = true;
//		view.finishRest();
//	}
//
//
//}
