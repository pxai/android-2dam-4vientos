package info.pello.android.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * NameFetcher shares a queue with a name producer.
 * When a new name is generated it send a message to Activity through a Handler
 * @author Pello Xabier Altadill Izura
 */
public class NameFetcher extends Thread {
	private BlockingQueue<String> generatedNames;	
	private static final int SLEEP_TIME = 2000;
	private Handler handler;
	private int total = 100;
	
	/**
	 * default constructor
	 * @param thread name
	 */
	public NameFetcher (String name) {
		super(name);
	}
	
	/**
	 * Constructor with size parameter
	 * @param size
	 */
	public NameFetcher (String name, BlockingQueue<String> generatedNames, int total) {
		super(name);
		this.generatedNames = generatedNames;
		this.total = total;
	}

	/**
	 * method executed when thread is started
	 */
	public void run () {
		String name = "";
		Message msg = null;
		Bundle bundle = null;
		
		while (true) {
			try {
				if (total == 0) {
					System.err.println(this.getName() + " > My work is done here. Bye!");
					return; 
				}
				name = generatedNames.take().toString();
				System.out.println("Fetching name: " + name);
				// We prepare message, and we send it.
				msg = new Message();
				bundle= new Bundle();
				bundle.putString("name",name);
				msg.setData(bundle);
				handler.sendMessage(msg);
				total--;
				sleep(SLEEP_TIME);
			} catch (InterruptedException ie) {
				System.err.println("Exception in generation thread: " + ie.getMessage());
			}
		}
	}


	/**
	 * we need to set this handler to send messages to
	 * Main app thread
	 * @param handler
	 */
	public void setHandler(Handler handler) {
		// TODO Auto-generated method stub
		this.handler = handler;
		
	}


	
}
