package info.pello.android.service.password;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

/**
 * MainActivity needed to start the service.
 * @author Pello Xabier Altadill Izura 
 * @greetz cuatrovientos
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("Password Activity started...","PELLODEBUG");
		// To make the service available we need to start it
		// from an Activity
		Intent serviceIntent = new Intent(this,PasswordService.class);
		startService(serviceIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
