package info.pello.android.intents;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * ChildActivity started with startActivityForResult
 * @author Pello Xabier Altadill Izura
 * @greetz for the usual people
 */
public class ChildActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_child);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.child, menu);
		return true;
	}
	
	/**
	 * method called when button is clicked
	 * @param v
	 */
	public void goBack (View v) {
		Log.d("Going back","PELLODEBUG");
		Intent intent = new Intent();
		intent.putExtra("someData","Kill kill kill");
        setResult(RESULT_OK,intent);
        finish();
	}

}
