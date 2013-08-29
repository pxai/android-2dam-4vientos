package info.pello.android.broadcastreceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.util.Log;

/**
 * MainActivity that simply register a BroadcastReceiver.
 * It's important to unregister on Stop event or we will get an Exception.
 * @author Pello Xabier Altadill Izura
 * @greetz For those who dare to mix Android and Threads
 */
public class MainActivity extends Activity {
	PhoneCallReceiver myPhoneCallReceiver = new PhoneCallReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("PELLODEBUG","MainActivity> unregistering Receiver");
	}
	
	@Override
	protected void onStart() {
		myPhoneCallReceiver = new PhoneCallReceiver();
		IntentFilter myFilter = new IntentFilter();
		myFilter.addAction("android.intent.action.HEADSET_PLUG");
		myFilter.addAction("android.intent.action.PHONE_STATE");
		myFilter.addAction("android.intent.action.BOOT_COMPLETED");
		myFilter.addAction("android.intent.action.QUICKBOOT_POWERON");

		// We register the receiver...
		// and off we go...
		this.registerReceiver(myPhoneCallReceiver, myFilter);
		Log.d("PELLODEBUG","Activity> receiver registered");
		super.onStart();
	}
	
	/**
	 *  We must override this to avoid 
	 *  Are you missing a call to unRegisterReceiver()
	 *  exception	 
	 *  */
	@Override
	protected void onStop() {
		Log.d("PELLODEBUG","MainActivity> unregistering Receiver");
		if (null != myPhoneCallReceiver)
			this.unregisterReceiver(myPhoneCallReceiver);
		else
			Log.d("PELLODEBUG","MainActivity> is null already");
		super.onStop();
	}
	

}
