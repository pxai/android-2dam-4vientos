package info.pello.android.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * Simple Activity to observe Android App lifecycle.
 * @author Pello Xabier Altadill Izura
 *
 */
public class MainActivity extends Activity {
	
	private static int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		log("App created");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (null != savedInstanceState) {
			Log.d("Restored value1: " + savedInstanceState.getString("Something") , "PELLODEBUG");
			Log.d("Restored value2: " + savedInstanceState.getString("Another") , "PELLODEBUG");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		log("Options menu created");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * called from the button and tells Android that 
	 * this should be closed.
	 * @param v
	 */
	public void stopApp (View v) {
		this.finish();
	}

	/**
	 * Suposed to be called when restoring app when:
	 * 1. Screen orientation changes
	 * 2. App was killed by android
	 * But:
	 *     "Most implementations will simply use onCreate(Bundle) to restore their state, but it is sometimes convenient to do it here after all of the initialization has been done or to allow subclasses to decide whether to use your default implementation. The default implementation of this method performs a restore of any view state that had previously been frozen by onSaveInstanceState(Bundle)."
	 * There is no reason to override onRestoreInstanceState() unless you are subclassing Activity and it is expected that someone will subclass your subclass. 
	 * @param bundle data to restore app state
	 */
	@Override
	protected void onRestoreInstanceState(Bundle bundle) {
		super.onRestoreInstanceState(bundle);
		log("Restoring Instance State");
		Log.d("Restored value1: " + bundle.getString("Something") , "PELLODEBUG");
		Log.d("Restored value2: " + bundle.getString("Another") , "PELLODEBUG");
	} 

	/**
	 * when user press home button this is
	 * called before app is paused
	 */
	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		// Save some values AFTER!! invoking super method
		bundle.putString("Something", "Saved for later");
		bundle.putString("Another", "Another Saved for later");
		log("Saving instance State...");
	} 

	/**
	 * called after the app is Created
	 * or after stop > restart
	 */
	@Override
	protected void onStart () {
		super.onStart();
		log("App Started");
	}
	
	/**
	 * called when app is resumed (from paused) and also
	 * the first time is executed (after start)
	 */
	@Override
	protected void onResume () {
		super.onResume();
		log("App Resumed");
	}
	
	/**
	 * called when app is paused
	 */
	@Override
	protected void onPause () {
		super.onPause();
		// We can control if the App is about to finish
		if (this.isFinishing()) {
			log("App is finishing");
		} else {
			log("App is NOT finishing");
		}
		
		log("App paused");
	}
	
	/**
	 * called when app is restarted from stop state
	 */
	@Override
	protected void onRestart () {
		super.onRestart();
		log("App Restarted");
	}
	
	/**
	 * called when app is stoped
	 */
	@Override
	protected void onStop () {
		super.onStop();
		log("App Stopped");
	}
	
	/**
	 * called when app is stoped is destroyed by Android
	 */
	@Override
	protected void onDestroy () {
		super.onDestroy();
		log("APP DESTROYED");
	}
	
	/**
	 * logs messages using Android log
	 * @param msg
	 */
	private void log (String msg) {
		Log.d("LifeCycle["+(counter++)+"]> "+msg,"PELLODEBUG");
	}
}
