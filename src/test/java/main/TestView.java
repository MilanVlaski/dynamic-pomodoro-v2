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
		var viewModel = new ViewDirector(new View(), new Timer(), new SingleCounter());

		viewModel.startWorking();
		assertThat(viewModel.seconds).isEqualTo(1);
	}

	@Test
	void Decrements_Seconds_While_Resting() throws SessionTooLong
	{
		Counter countsTwentyFive = new Counts().times(25);
		var workThenRest = new WorkThenRest(countsTwentyFive, new SingleCounter());

		var viewModel = new ViewDirector(new View(), new Timer(), workThenRest);

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
		public void count(Work work, Director viewModel) throws SessionTooLong
		{ workCounter.count(work, viewModel); }

		@Override
		public void count(Rest rest, Director viewModel)
		{ restCounter.count(rest, viewModel); }

		@Override
		public void stop()
		{}

		@Override
		public boolean isWorking()
		{ return false; }

	}
}
