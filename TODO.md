@Override
	public void count(Rest rest, Director viewModel)
	{
		var seconds = rest.decrementSeconds();
		viewModel.setSeconds(seconds);
		if (seconds == 0)
		{
			viewModel.finishRest();
			stop();
		}
	}
	
- The above tells me that, when seconds decrease to zero, they are impossible to decrease further. Also, the viewModel and the counter are notified.
- The exception passing around also clearly shows that both the Counter and ViewModel need to be notified of changes.
- Perhaps Work should provide a current LocalTime, and not just seconds, although seconds is easier to test, since it's just an int, and it's small.
- Set seconds is the wrong way to approach displaying the time. We should be incrementing/decrementing the time when necessary, and setting it in special cases (i.e. when Rest)