package info.pello.android.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
* Android activity that shows how to deal with AsyncTasks
* @author Pello Xabier Altadill Izura
* @greetz my async friends
*/
public class MainActivity extends Activity {
	
	private JsonGetterTask jsonGetterTask;
	private EditText editText1;
	private ProgressBar progressBarInnerTask;
	private ProgressBar progressBar1;
	private TextView resultTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText1 = (EditText) findViewById(R.id.editText1);
		progressBarInnerTask = (ProgressBar) findViewById(R.id.progressBarInnerTask);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		resultTextView = (TextView) findViewById(R.id.resultTextView);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * @return the progressBar1
	 */
	public ProgressBar getProgressBar1() {
		return progressBar1;
	}

	/**
	 * @return the resultTextView
	 */
	public TextView getResultTextView() {
		return resultTextView;
	}

	/**
	 * this method is called when button is clicked
	 * It starts an AsyncTask to retrieve a Json source
	 * You've to create a new instance of the task or an exception like this will be thrown
	 * 08-12 23:32:45.698: E/AndroidRuntime(2041): Caused by: java.lang.IllegalStateException: Cannot execute task: the task has already been executed (a task can be executed only once)

	 * @param v
	 */
	public void getJson (View v) {
		Log.d("Button clicked, let's grab some JSON data...","PELLODEBUG");
		jsonGetterTask = new JsonGetterTask(this);
		jsonGetterTask.execute(editText1.getText().toString());
	}
	
	/**
	 * this method is called when first button is clicked
	 * It starts an inner AsyncTask defined here
	 * @param v
	 */
	public void startInnerTask (View v) {
		Log.d("InnerTask button clicked...","PELLODEBUG");
		// We pass 20 to set the work to be done.
		// this param will go to doInBackground
		new InnerTask().execute(20);
	}
	
	/**
	 * Inner class for a symple AsyncTask
	 * @author Pello Xabier Altadill Izura
	 * @greetz any
	 */
	public class InnerTask extends AsyncTask<Integer,Void,Void> {

		/**
		 * This is called before doInBackground and is a perfect place
		 * to prepare the progress Bar.
		 */
		@Override
		protected void onPreExecute () {
			Toast.makeText(MainActivity.this, "Let's start task", Toast.LENGTH_SHORT).show();
			progressBarInnerTask.setProgress(0);
		};
		
		/**
		 * This is where the task begins and runs
		 */
		@Override
		protected Void doInBackground(Integer... work) {
			Log.d("InnerTask doInBackground " + work[0],"PELLODEBUG");
			progressBarInnerTask.setMax(work[0]);

			Log.d("InnerTask doInBackground " + work[0] + ":"+ progressBarInnerTask.getMax(),"PELLODEBUG");
			
			// We just sleep and publish progress
			for (int i = 0; i<work[0]; i++) {
				SystemClock.sleep(1000);
				this.publishProgress();
			}
			
			return null;
		}
		
		/**
		 * This method is called when we call this.publishProgress
		 * and can be used to update contents,progress bars,... in the Activity
		 */
		@Override
		protected void onProgressUpdate(Void... unused) {
			Log.d("InnerTask> onProgressUpdate was called: ","PELLODEBUG");
			progressBarInnerTask.incrementProgressBy(1);
		}
		
		/**
		 * called when task is finished.
		 */
		@Override
		protected void onPostExecute(Void unused) {
			Log.d("InnerTask> onPostExecute was called: ","PELLODEBUG");
			Toast.makeText(MainActivity.this, "Task finished", Toast.LENGTH_SHORT).show();

		}


	}

}
