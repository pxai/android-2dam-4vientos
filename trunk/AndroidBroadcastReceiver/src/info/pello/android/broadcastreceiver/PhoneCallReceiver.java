package info.pello.android.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Sample of BroadcastReceiver, every time the phone get a phone
 * this Receiver should be called. It'll just log a message but It
 * could save info in a database, to a file, somewhere on the cloud...
 * @author Pello Xabier Altadill Izura
 * @greetz Remírez
 */
public class PhoneCallReceiver extends BroadcastReceiver {
	
	/**
	 * default constructor
	 */
	public PhoneCallReceiver() {
		Log.d("PELLODEBUG","BR> Instance creaed");
	}

	/**
	 * this handler will be fired when w receive an Intent for us
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		 Bundle intentExtras = intent.getExtras();
		 
		// is intent a REBOOT?
		if (intent.getAction().equals(Intent.ACTION_REBOOT)) {
			Log.d("PELLODEBUG","BR> Received intent: system rebooted");
		}

		// or maybe a headphone plug/unplug?
		if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
			Log.d("PELLODEBUG","BR> Received intent: headsetPlugged");
		}
		
		// or maybe phone is ringing?
		if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
			Log.d("PELLODEBUG","BR> Received intent: RING RING!!");
			// Now we extract number from intent extras

	
		    if (null != intentExtras) {
			      String state = intentExtras.getString(TelephonyManager.EXTRA_STATE);
			      if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			        String callingNumber = intentExtras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

					saveInformation("BR> Call received: " + state + " ," + callingNumber);
			      }
			    }
		}


		Log.d("PELLODEBUG","BR> Received intent: " + intent.getAction());

	}
	
	/**
	 * method called from onReceive to store information
	 * @param info
	 * @return
	 */
	private boolean saveInformation (String info) {
		Log.d("PELLODEBUG","BR> saving info: " + info);
		return true;
	}
	
	
}
