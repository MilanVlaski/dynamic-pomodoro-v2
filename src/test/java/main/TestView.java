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
	void Work_starts_and_Increases_time_by_one_second()
	{
		var view = new View(new Timer(), new SingleCounter());
		view.startWorking();
		assertThat(view.time()).isEqualTo(LocalTime.of(0, 0, 1));
	}

	@Test
	void Works_then_rests()
	{
		var view = new View(new Timer(),
		                    new MultiCounter(new Counts().times(25), new NullCounter()));

		view.startWorking();
		view.startResting();

		assertThat(view.time()).isEqualTo(LocalTime.of(0, 0, 5));
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

	// TODO inject a View to check whether our view gets alerted
	@Test
	void Works_then_rests_then_rest_runs_out()
	{
		
		View view = new View(new Timer(), new MultiCounter(new Counts().times(25),
		                                                   new Counts().times(5)));

		view.startWorking();
		view.startResting();

		assertThat(view.time()).isEqualTo(LocalTime.of(0, 0));
	}

	@Test
	void When_Works_then_rests_then_works_Then_time_is_reset()
	{

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

}
