package info.pello.android.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * This activity uses java Threads to generate Random names.
 * To communicate with them it makes us of a Handler
 * @author Pello Xabier Altadill Izura
 * @greetz bizgen project
 */
public class MainActivity extends Activity {
	
	private TextView generatedResult;
	private NameGenerator nameGenerator;
	private NameFetcher nameFetcher;
    BlockingQueue<String> generatedNames = new SynchronousQueue<String>();
    private Handler handler;
    private static final int NAMES_TO_GENERATE = 10;
    ProgressBar progressBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		generatedResult = (TextView) findViewById(R.id.generatedResult);
		progressBar = (ProgressBar) findViewById(R.id.progressBarThreads);
		progressBar.setMax(NAMES_TO_GENERATE);
		
		// The handler used to communicate between Activity and a Thread
		handler =new Handler() {
	    				@Override
	    				public void handleMessage(Message msg) {
	    					Log.d("Received message from thread","PELLODEBUG");
	    					Bundle bundle;
	    					bundle = msg.getData();
	    					generatedResult.append(bundle.getString("name") + "\n");
	    					// We update progress bar:
	    					progressBar.incrementProgressBy(1);
	    				}
	    		};
	    		
		nameGenerator = new NameGenerator("Generator", 3, generatedNames,NAMES_TO_GENERATE);
		nameFetcher = new NameFetcher("Fetcher", generatedNames, NAMES_TO_GENERATE);
		// We set a handler to comunicate with android activity
		nameFetcher.setHandler(handler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * invoked when button is pressed, this method start
	 * some java threads.
	 * NOTE: this thread will keep on working even if we click home
	 * or back buttons in the application
	 * @param v
	 */
	public void startThreads (View v) {
		progressBar.setProgress(0);
		Log.d("Starting threads","PELLODEBUG");
		// This thread generates names and puts them
		// in a queue
		nameGenerator.start();
		
		// This one will get freshly created names from
		// the queue
		nameFetcher.start();
	}
	
}
