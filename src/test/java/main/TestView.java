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
		ViewModel viewModel = new ViewModel(new View(), new Timer(), new SingleCounter());

		viewModel.startWorking();
		assertThat(viewModel.seconds).isEqualTo(1);
	}
//
//	@Test
//	void Shows_Correct_Time_When_Rests_After_Work() throws SessionTooLong
//	{
//		var timer = new Timer();
//		Counter counter = new CountsSeconds(25);
//		FakeView view = new FakeView(timer, counter);
//		view.startWorking();
//		view.startResting(); 
//		
//		assertThat(view.seconds).isEqualTo(5);
//	}

}
