package model;

import main.IViewModel;
import model.Work.SessionTooLong;

public interface Counter
{
	void count(Work work, IViewModel viewModel) throws SessionTooLong;
	void count(Rest rest, IViewModel newParam);
	void stop();
	boolean isWorking();
}
