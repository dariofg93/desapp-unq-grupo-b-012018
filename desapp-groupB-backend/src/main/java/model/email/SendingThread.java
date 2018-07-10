package model.email;

public class SendingThread extends Thread{

	Runnable processSending;
	
	public SendingThread(Runnable runnable) {

		this.processSending= runnable;
	}
	
	@Override
	public void run() {
		this.processSending.run();
	}

}
