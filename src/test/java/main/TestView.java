package main;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.mocks.Counts;
import model.*;

public class TestView
{


	@Test
	void View_Time_Is_Zero_On_Init()
	{
		var view = new View(null, null);
		assertThat(view.time()).isEqualTo(LocalTime.MIN);
	}

	@Test
	void Work_starts_and_Increases_time_by_one_econd()
	{
		var view = new View(new Timer(), new SingleCounter());
		view.startWorking();
		assertThat(view.time()).isEqualTo(LocalTime.of(0, 0, 1));
	}

	@Test
	void Works_then_rests_Then_time_decreases()
	{
		var view = new View(new Timer(), new MultiCounter(new Counts().times(25),
		                                                  new SingleCounter()));

		view.startWorking();
		view.startResting();

		assertThat(view.time()).isEqualTo(LocalTime.of(0, 0, 4));
	}

	public class MultiCounter implements Counter
	{

		private List<Counter> counters;
		private int i;

		public MultiCounter(Counter... counters)
		{ this.counters = Arrays.asList(counters); }

		@Override
		public void count(Work work)
		{ counters.get(i++).count(work); }

		@Override
		public void count(Rest rest)
		{ counters.get(i++).count(rest); }

		@Override
		public void stop()
		{ counters.get(i).stop(); }

		@Override
		public boolean isWorking()
		{ return counters.get(i).isWorking(); }

	}
//
//	@Test
//	void Increments_Seconds_While_Working() throws SessionTooLong
//	{
//		var viewModel = new ViewDirector(new View(), new Timer(), new SingleCounter());
//
//		viewModel.startWorking();
//		assertThat(viewModel.seconds).isEqualTo(1);
//	}
//
//	@Test
//	void Decrements_Seconds_While_Resting() throws SessionTooLong
//	{
//		var workThenRest = new WorkThenRest(new Counts().times(25), new SingleCounter());
//
//		var director = new ViewDirector(new View(), new Timer(), workThenRest);
//
//		director.startWorking();
//		director.startResting();
//
//		assertThat(director.seconds).isEqualTo(4);
//	}
//
//	@Test
//	void When_Work_Goes_Over_Four_Hours_Alerts_View_And_Stops_Counting()
//	{
//		var view = new View();
//		var longCounter = new Counts().times(fourHours + 123);
//		var director = new ViewDirector(view, new Timer(), longCounter);
//		director.startWorking();
//
//		assertThat(view.alerted).isTrue();
//		assertThat(longCounter.isWorking()).isFalse();
//	}
//
//	@Test
//	void When_Rest_Time_Runs_Out_Alerts_View_And_Stops_Counting()
//	{
//		var view = new View();
//		var counter = new WorkThenRest(new Counts().times(25), new Counts().times(6));
//		var director = new ViewDirector(view, new Timer(), counter);
//		
//		director.startWorking();
//		director.startResting();
//
//		assertThat(director.restFinished).isTrue();
//		assertThat(counter.isWorking()).isFalse();
//	}
//
//	public class WorkThenRest implements Counter
//	{
//
//		private final Counter workCounter;
//		private final Counter restCounter;
//
//		public WorkThenRest(Counter work, Counter rest)
//		{
//			this.workCounter = work;
//			this.restCounter = rest;
//		}
//
//		@Override
//		public void count(Work work) throws SessionTooLong
//		{ workCounter.count(work); }
//
//		@Override
//		public void count(Rest rest, Director viewModel)
//		{ restCounter.count(rest, viewModel); }
//
//		@Override
//		public void stop()
//		{}
//
//		@Override
//		public boolean isWorking()
//		{ return false; }
//
}
