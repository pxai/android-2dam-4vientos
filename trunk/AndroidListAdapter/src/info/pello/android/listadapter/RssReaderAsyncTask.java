package info.pello.android.listadapter;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


/**
 * Extends AsyncTask to get RSS contents and parse the to populate
 * a ListAdapter
 * @author Pello Xabier Altadill Izura
 * @greetz any
 */
public class RssReaderAsyncTask extends AsyncTask<String,String,Void> {
	
	// We well keep a reference to our caller activity
	// so we can attach/detach in case of activity is destroyed in a rotation
	private MainActivity mainActivity;
	private ArrayList<RssItem> rssItems;

	/**
	 * Default constructor
	 * @param mainActivity
	 */
	public RssReaderAsyncTask (MainActivity mainActivity) {
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
		Log.d("PELLODEBUG","AT> URL passed to AsyncTask: " + url[0]);
		String sampleRSS ="";
		try {
			rssItems = this.parseXML(url[0]);
		} catch (Exception e) {
			Log.d("PELLODEBUG","AT> Exception processing RSS: " + e.getMessage());
		}
		// With this call we notify to progressUpdate
		Log.d("PELLODEBUG","AT> doInBackbround publishes progress");

		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * This method is called when we call this.publishProgress
	 * and can be used to update contents,progress bars,... in the Activity
	 */
	@Override
	protected void onProgressUpdate(String... item) {
		Log.d("PELLODEBUG","AT> onProgressUpdate> on progress... ");

	}
	
	/**
	 * called when task is finished.
	 */
	@Override
	protected void onPostExecute(Void unused) {
		Toast.makeText(this.mainActivity, "Finished.", Toast.LENGTH_SHORT).show();
		Log.d("PELLODEBUG","AT> onPostExecute was called: ");
		this.mainActivity.refreshList(rssItems);
		//this.mainActivity.getProgressBar1().setVisibility(ProgressBar.INVISIBLE);

	}

	/**
     * parseXML
	 * parses XML content from a given URL
     * @return ArrayList with parsed data
     */
    private ArrayList<RssItem> parseXML (String rssContent) {
		ArrayList<RssItem> rssItemsArray = new ArrayList<RssItem>();

		// If we do it using a String
		//Reader xml = new StringReader("<?xml..>..");
		//Document doc = builder.parse(new InputSource(xml));
		
    	DocumentBuilder builder;
    	DocumentBuilderFactory builderFactory;
		try {
			builderFactory = DocumentBuilderFactory.newInstance();
			
			// I tried this features to relax the parser but with no effect
			// See: http://xerces.apache.org/xerces2-j/features.html
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			//builderFactory.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
			
			builder = builderFactory.newDocumentBuilder();

    	Document doc=builder.parse(rssContent);
    	NodeList items =doc.getElementsByTagName("item");
    	
    	for (int i=0;i<items.getLength();i++) {
    		Element item = (Element)items.item(i);
    		NodeList titles = item.getElementsByTagName("title");
    		NodeList links = item.getElementsByTagName("link");
    		NodeList descriptions = item.getElementsByTagName("description");
 
    		Element title = (Element) titles.item(0);
    		Element link = (Element) links.item(0);
    		Element description = (Element) descriptions.item(0);
    		
    		// NOTE: description tag seems to be special due to CDATA
    		
    		RssItem rssItem = new RssItem(title.getFirstChild().getNodeValue(),
    				description.getChildNodes().item(1).getTextContent().substring(0, 30),
    				link.getFirstChild().getNodeValue());
    		rssItemsArray.add(rssItem);

    	}
    	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("PELLODEBUG","Parser exception: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("PELLODEBUG","Parser General exception: " + e.getMessage());			
		}
		return rssItemsArray;
    }


    /**
     * Makes a GET request to an URL
     * @param url to be requested
     * @return contents from url
     */
    public String get (String url) {
    	// It makes use of apache's HttpClient, available on Android
    	HttpGet request = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		String result = "";
		request.setHeader("Accept", "application/xml");
		
    	try {
			ResponseHandler<String> response = new BasicResponseHandler();
			
			String responseContents = httpClient.execute(request,response);

			if (responseContents != null && responseContents.length() > 0) {
				result += responseContents;
			} else {
				result += "Error\n"+responseContents;
			}

		} catch (ClientProtocolException e) {
			result += "Unexpected ClientProtocolException" + e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			result += "Unexpected IOException" + e.getMessage();
		}
		Log.d("PELLODEBUG","RssReader> Result: "+ result);
		return result;
    }

}
