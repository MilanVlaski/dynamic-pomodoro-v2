package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import main.mocks.Counts;
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
	void Decrements_Seconds_While_Resting() throws SessionTooLong
	{
		WorkThenRest workThenRest = new WorkThenRest(new Counts(new SingleCounter())
		        .times(25), new SingleCounter());

		var viewModel = new ViewModel(new View(), new Timer(), workThenRest);

		viewModel.startWorking();
		viewModel.startResting();

		assertThat(viewModel.seconds).isEqualTo(4);
	}

	public class WorkThenRest implements Counter
	{

		private final Counter workCounter;
		private final Counter restCounter;

		public WorkThenRest(Counter work, Counter rest)
		{
			this.workCounter = work;
			this.restCounter = rest;
		}

		@Override
		public void count(Work work, IViewModel viewModel) throws SessionTooLong
		{ workCounter.count(work, viewModel); }

		@Override
		public void count(Rest rest, IViewModel viewModel)
		{ restCounter.count(rest, viewModel); }

		@Override
		public void stop()
		{}

		@Override
		public boolean isWorking()
		{ return false; }

	}
}
