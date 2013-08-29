package info.pello.android.listadapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

/**
 * The main activity that holds de ListView
 * @author Pello Xabier Altadill Izura
 * @greetz quality RSS contents
 */
public class MainActivity extends Activity {

	private RssListItemAdapter rssListItem;
	private RssReaderAsyncTask rssReaderAsyncTask;
	private ListView listNews;
	private static final String RSS_URL = "http://www.pello.info/index.php/rss2";//"http://eugeniaperez.es/wordpress/?feed=rss2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listNews = (ListView) findViewById(R.id.listNews);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * method called when refresh button is pressed
	 * It Starts AsyncTask
	 * @param v
	 */
	public void refreshNews(View v) {
		Log.d("PELLODEBUG","MainActivity> refresh news...");
		rssReaderAsyncTask = new RssReaderAsyncTask(this);
		rssReaderAsyncTask.execute(RSS_URL);

	}
	
	/**
	 * refresh the List with parsed data from AsyncTask
	 * @param rssItems
	 */
	public void refreshList (ArrayList<RssItem> rssItems) {
		Log.d("PELLODEBUG","MainActivity> Async task finished...");
		rssListItem = new RssListItemAdapter(this, rssItems);
		listNews.setAdapter(rssListItem);
	}
	
}
