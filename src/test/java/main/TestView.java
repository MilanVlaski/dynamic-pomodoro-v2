package main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import model.SingleCounter;
import model.Timer;
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
	{
		
	}

}
