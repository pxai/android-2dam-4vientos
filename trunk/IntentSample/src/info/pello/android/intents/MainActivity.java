package info.pello.android.intents;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MainActivity shows how to start other activities with intent
 * parameters and waiting for results.
 * @author Pello Xabier Altadill Izura
 * @greetz for the usual people
 */
public class MainActivity extends Activity {

	private EditText editText1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText1 = (EditText) this.findViewById(R.id.editText1);
	}
	
	/**
	 * Method invoked when button is clicked
	 * configured in the layout
	 * @param v
	 */
	public void openActivity (View v) {
		Log.d("Opening another activity. Or trying to", "PELLODEBUG");
		Intent intent = new Intent(this, AnotherActivity.class);
		intent.putExtra("extraValue", editText1.getText().toString());
		//intent.putExtra("extraValue","Seventh son of a seventh son");
		startActivity(intent);
	}
	
	/**
	 * Method invoked when second button is clicked
	 * configured in the layout
	 * @param v
	 */
	public void openActivityResult (View v) {
		Log.d("Opening activity wait for result. Or trying to", "PELLODEBUG");
		Intent intent = new Intent(this, ChildActivity.class);
		startActivityForResult(intent,666);
	}
	
	/**
	 * callback method when returning from activity started withResult
	 */
	// @Override
	//protected void onActivityResult () {}
	
	/**
	 * callback method when returning from activity started withResult
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("Callback when child activity finishes","PELLODEBUG");
		Log.d("Req code: " + requestCode + ". Result code: " + resultCode,"PELLODEBUG");
		Log.d("Extra data: " + data.getStringExtra("someData"),"PELLODEBUG");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
