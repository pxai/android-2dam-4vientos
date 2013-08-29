package info.pello.android.serviceclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Sample Android -remote- Service Client. The service is not part of this App.
 * Uses Messenger/Handler to communicate.
 * @author Pello Xabier Altadill Izura
 * @greetz Txapela
 */
public class MainActivity extends Activity {

	private Handler handler;
	private TextView textViewResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewResult = (TextView) findViewById(R.id.textViewResult);
		
		Log.d("Client Started. ","PELLODEBUG");
		
		// The handler used to communicate between Activity and the service
		handler =new Handler() {
	    				@Override
	    				public void handleMessage(Message msg) {
	    					Log.d("Received message from Service","PELLODEBUG");
	    					Bundle bundle;
	    					bundle = msg.getData();
	    					Log.d("Received Message: " + bundle.getString("result"),"PELLODEBUG");
	    					textViewResult.setText("Generated password: \n"+bundle.getString("result"));

	    				}
	    		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 * called when button is pressed. We use a Service
	 * using startService
	 * @param view
	 */
	public void startService (View v) {
		Log.d("Starting service fuck yeah ","PELLODEBUG");
		//Intent serviceIntent = new Intent("info.pello.android.services.START_SERVICE");
		Intent serviceIntent = new Intent();
		
		// We set the messenger to let the service notify us.
		serviceIntent.putExtra("PasswordServiceMessenger", new Messenger(handler));
		// And by the way the password length
		serviceIntent.putExtra("passwordLength",8);
		
		// We get the service through package and class name.
		serviceIntent.setClassName("info.pello.android.service.password", "info.pello.android.service.password.PasswordService");
		startService(serviceIntent);
		Log.d("Client> Service was started. ","PELLODEBUG");
	}

	/**
	 * called when second buttons is pressed. We try to bind
	 * to a service.
	 * @param view
	 */
	public void startServiceBind (View v) {
		Log.d("Binding to service. ","PELLODEBUG");

		Intent serviceIntent = new Intent();
		serviceIntent.setAction("info.pello.android.service.RssService.START_SERVICE");
		//bindService(serviceIntent);
		Log.d("Client> Service was started. ","PELLODEBUG");
		
	}

}
