package info.pello.android.asynctask;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Extends AsyncTask to get the contents from an URL
 * It provides a way to pass some values through generics:
 * AsyncTask<Class1,Class2,Class3> where:
 * -Class1: type of information for the task (e.g. the URL to get), passed
 * 			through execute method
 * -Class2: type of information to indicate progress, must be the same
 *          class in onProgressUpdate method
 * -Class3: type of information for the postTask
 * @author Pello Xabier Altadill Izura
 * @greetz any
 */
public class JsonGetterTask extends AsyncTask<String,String,Void> {
	
	// We well keep a reference to our caller activity
	// so we can attach/detach in case of activity is destroyed in a rotation
	private MainActivity mainActivity;

	/**
	 * Default constructor
	 * @param mainActivity
	 */
	public JsonGetterTask (MainActivity mainActivity) {
		attach(mainActivity);
	}
	
	/**
	 * sets mainActivity reference
	 * @param mainActivity
	 */
	public void attach(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	/**
	 * when task is finished this reference is not needed any longer
	 */
	public void detach () {
		this.mainActivity = null;
	}
	
	/**
	 * This is called before doInBackground and is a perfect place
	 * to prepare the progress Bar.
	 */
	@Override
	protected void onPreExecute () {
		Toast.makeText(this.mainActivity, "Starting Async Task", Toast.LENGTH_SHORT).show();
		this.mainActivity.getProgressBar1().setVisibility(1);
		this.mainActivity.getResultTextView().setText("");
	}
	
	/**
	 * This is where the task begins and runs
	 * String... url declares variable arguments list,
	 * and we can get values using indexes: url[0], url[1],...
	 * Whenever we consider that we make som progress we can
	 * notify through publishProgress()
	 */
	@Override
	protected Void doInBackground(String... url) {
		Log.d("URL passed to AsyncTask: " + url[0],"PELLODEBUG");
		String sampleJSON ="{'data': {'technologies':[{name : 'Backbone', description : 'JS MVC Framework' , difficulty: 6}, {name : 'Angular', description: 'JS MVC Framework' ,difficulty: 8}, {name : 'CouchDB', description: 'noSQL Database' ,dificultty: 9} ]}, 'responseDetails': null, 'responseStatus': 200} ";
		try {
			this.parseJson(sampleJSON);
		} catch (Exception e) {
			Log.d("Exception processing JSON: " + e.getMessage(),"PELLODEBUG");
		}
		// With this call we notify to progressUpdate
		Log.d("doInBackbround publishes progress","PELLODEBUG");

		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method is called when we call this.publishProgress
	 * and can be used to update contents,progress bars,... in the Activity
	 */
	@Override
	protected void onProgressUpdate(String... item) {
		Log.d("onProgressUpdate> json data: " + item[0] + " and " + item[1],"PELLODEBUG");
		this.mainActivity.getResultTextView().append(item[0] + " and " + item[1]+"\n");
	}
	
	/**
	 * called when task is finished.
	 */
	@Override
	protected void onPostExecute(Void unused) {
		Toast.makeText(this.mainActivity, "Finished Async Task", Toast.LENGTH_SHORT).show();
		Log.d("onPostExecute was called: ","PELLODEBUG");
		this.mainActivity.getProgressBar1().setVisibility(ProgressBar.INVISIBLE);

	}

	/**
	 * 
	 * @param cadenaJSON
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public void parseJson (String cadenaJSON) throws IllegalStateException,
	IOException, JSONException, NoSuchAlgorithmException {


		String name = "";
		String description = "";

	 // We create an instance with the JSON string
	 JSONObject mResponseObject = new JSONObject(cadenaJSON);

	 // We get one object
	 JSONObject responObject = mResponseObject.getJSONObject("data");

	 // and from that object we get the array of data we need
	 JSONArray registros = responObject.getJSONArray("technologies");

	 Log.d("JSON parser> # records:" + registros.length(),"PELLODEBUG");

	 // and now, for each record we process data.
	 for (int i = 0; i < registros.length(); i++) {
		 Log.d("JSON parser> json record data: " + i + "] " + registros.get(i).toString(),"PELLODEBUG");
		 // We get name and description
		 name = registros.getJSONObject(i).getString("name");
		 description = registros.getJSONObject(i).getString("description");
		 
		 this.publishProgress(name, description);

	 }

	 Log.d("JSON parser> finished","PELLODEBUG");

	}

}
