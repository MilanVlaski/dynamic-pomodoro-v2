package main;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestView
{

	@Test
	void Shows_Zero_On_Initialize()
	{
		Timer timer = new Timer();
		View view = new View();
		Counter counter = new Counter();
		ViewModel vm = new ViewModel(view, counter, timer);
		
		assertThat(vm.seconds).isEqualTo(0);
	}

//	@Test
//	void IncrementsTime_While_Working() throws SessionTooLong
//	{
//		var timer = new Timer();
//		Counter counter = new CountsSeconds(1);
//		FakeView view = new FakeView(timer, counter);
//		view.startWorking();
//
//		assertThat(view.seconds).isEqualTo(1);
//	}
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
