package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import model.*;
import model.Work.SessionTooLong;

public class TestView
{


	@Test
	void Increments_Seconds_While_Working() throws SessionTooLong
	{
		var viewModel = new ViewModel(new View(), new Timer(), new SingleCounter());

		viewModel.startWorking();
		assertThat(viewModel.seconds).isEqualTo(1);
	}

	@Test
	void Rests_Then_Works() throws SessionTooLong
	{ var viewModel = new ViewModel(new View(), new Timer(), new MultiCounter()); }

	public class MultiCounter implements Counter
	{

		@Override
		public void count(Work work, IViewModel viewModel) throws SessionTooLong
		{}

		@Override
		public void count(Rest rest, IViewModel newParam)
		{}

		@Override
		public void stop()
		{}

		@Override
		public boolean isWorking()
		{ return false; }

	}
}
