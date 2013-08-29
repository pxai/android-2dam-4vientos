package info.pello.android.contentproviderclient;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textViewResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewResult = (TextView) findViewById(R.id.textViewResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * called when button is clicked
	 * It will try to get data from Content Provider.
	 * @param v
	 */
	public void getData(View v) {
		Log.d("PELLODEBUG","Client> button pressed.");
		String result = "";
		String uriString = "content://info.pello.android.contentprovider.provider.Students/students/1";
		Uri uri = Uri.parse(uriString);
		
		// We finally make the request to the content provider
		Cursor cursor = getContentResolver().query(
			    uri,   // The content URI of the words table
			    new String[]{""},
			    "",                        // The columns to return for each row
			    new String[]{""},                     // Selection criteria
			    "");
		


		// We get results in the cursor instance.
		while (cursor.moveToNext()) {
			result += cursor.getLong(0) + ", ";
			result += cursor.getString(1) + ", ";
			result += cursor.getString(2) + "\n";
		}
		
		textViewResult.setText(result);
	}
	
	/**
	 * called when second button is clicked
	 * It will try to get data from Content Provider.
	 * @param v
	 */
	public void insertData (View v) {
		Log.d("PELLODEBUG","Client> button pressed.");
		String result = "";
		String uriString = "content://info.pello.android.contentprovider.provider.Students/students/insert";
		Uri uri = Uri.parse(uriString);
		ContentValues contentValues = new ContentValues();
		contentValues.put("_id", System.currentTimeMillis());
		contentValues.put("name","Example name");
		contentValues.put("description","Example Description");
		
		// We finally make the request to the content provider
		Uri resultUri = getContentResolver().insert(
			    uri,   // The content URI
			    contentValues
			    );
		Log.d("PELLODEBUG","Result Uri after insert: " + uri.toString());
		textViewResult.setText("Result uri created: " + uri.toString());
	}


}
