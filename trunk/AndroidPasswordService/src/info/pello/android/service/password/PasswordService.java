package info.pello.android.service.password;


import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * Creates an Android Service to generate random password
 * I've override all the methods just to see how Android calls them
 * @author Pello Xabier Altadill Izura
 * @greetz Web Workers
 */
public class PasswordService extends Service {

	// Needed to pass messages to caller
	private Messenger messenger = null;
	private Random random = new Random();
	
	/**
	 *  default constructor
	 */
	
	public PasswordService() {
	}

	/**
	 * Called when the service is first called.
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Logger.getInstance().log("onCreate method was called");

	}

	/**
	 * this method will be called
	 * each time the service receives a command through startService call 
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int passwordLenght = 8;
		Logger.getInstance().log("onStartCommand method was called");
		
		// Getting messenger to notify
		getMessenger(intent);
		sendMessage("note","Starting service...");
		
		// We get the length requested by client or give default value
		passwordLenght = intent.getIntExtra("passwordLength", passwordLenght);
		String result = generatePassword(passwordLenght);
		
		sendMessage("result", result);

		Logger.getInstance().log("OK, result sent: " + result);
		
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * can you guess what this is? ;)
	 * @param length
	 * @return
	 */
	private String generatePassword(int length) {
		String password = "";
		String [] chars = { ".",",",";",":","?","!","-","_",
							"0","1","2","3","4","5","6","7","8","9", 
							"a","e","i","o","u","b","c","d","f","g","h","j","k","l",
							"m","n","p","q","r","s","t","v","w","x","y","z"};
		
		for (int i = 0; i < length;i++) {
			if (random.nextInt(2) == 0 ) 
				password += chars[random.nextInt(chars.length)];
			else
				password += chars[random.nextInt(chars.length)].toUpperCase();
		}
		
		return password;
	}

	/**
	 * sends message to client through the Messenger
	 * @param key
	 * @param msg
	 */
	private void sendMessage(String key, String msg) {
		Message message = new Message();
		Bundle bundle = new Bundle();
		bundle.putString(key, msg);
		message.setData(bundle);
		try {
			messenger.send(message);
		} catch (Exception e) {
			Log.d("Error sending message","PELLODEBUG");
		}
		
	}

	/**
	 * get Messenger from calling intent 
	 * @param intent
	 */
	private void getMessenger(Intent intent) {
		Bundle extras = intent.getExtras();
		if (null != extras) {
			 messenger=(Messenger)extras.get("PasswordServiceMessenger");
		} 
	}

	/**
	 * services somehow are exposing an API. Other Activities or sources
	 * can call bindService() with an intent to bind with this service in order to access that API,
	 * When the service is binded it must return an IBinder instance to communicate
	 * with the client of the service
	 */
	@Override
	public IBinder onBind(Intent intent) {
		Logger.getInstance().log("onBind method was called");
		// TODO: Return the communication channel to the service.
		return null;
		
	}

	/**
	 * called when service configuration is changed
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Logger.getInstance().log("onConfigurationChanged method was called");

	}

	/**
	 * called when Android destroys the service
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Logger.getInstance().log("onDestroy method was called");

	}

	/**
	 * In cases of low memory Android could do something with the service
	 */
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Logger.getInstance().log("onLowMemory method was called");

	}

	/* (non-Javadoc)
	 * @see android.app.Service#onRebind(android.content.Intent)
	 */
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Logger.getInstance().log("onRebind method was called");

	}

	/* 
	 * onStart is deprecated, use onStartCommand instead
	 * */
	/*@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Logger.getInstance().log("onStart deprecated method was called");
	}*/


	/* (non-Javadoc)
	 * @see android.app.Service#onTaskRemoved(android.content.Intent)
	 */
	@Override
	public void onTaskRemoved(Intent rootIntent) {
		// TODO Auto-generated method stub
		super.onTaskRemoved(rootIntent);
		Logger.getInstance().log("onTaskRemoved method was called");

	}

	/* (non-Javadoc)
	 * @see android.app.Service#onTrimMemory(int)
	 */
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
		Logger.getInstance().log("onTrimMemory method was called");
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Logger.getInstance().log("onUnbind method was called");
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
	
}
