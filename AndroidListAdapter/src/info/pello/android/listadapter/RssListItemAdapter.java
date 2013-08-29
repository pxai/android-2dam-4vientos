package info.pello.android.listadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * extends a base adapter to create a customized ListAdapter
 * @author Pello Xabier Altadill Izura
 * @greetz 4vientos students
 */
class RssListItemAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<RssItem> rssItems;
	

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param layoutId
	 * @param rssItems
	 */
	public RssListItemAdapter(Activity activity,ArrayList<RssItem> rssItems) {
		super();
		this.activity = activity;
		this.rssItems = rssItems;
	}
	
	
	/**
	 * return number of items
	 * @return int
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return rssItems.size();
	}

	/**
	 * returns one object in a given position
	 * @param position
	 * @return Object
	 */
	public Object getItem(int position) {
		return rssItems.get(position);
	}

	/**
	 * returns id for the item(position)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * for each list item it call this method to render it in the ListView
	 * @param position
	 * @param converView
	 * @param parent
	 * @result View
	 */
	public View getView(int position, View convertview, ViewGroup parent) {
		View view = convertview;
		if(convertview == null){
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item,null);
		}
		
		RssItem item = rssItems.get(position);
		Log.d("PELLODEBUG", item.toString());
		
		TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
		textViewTitle.setText(item.getTitle());
		
		TextView textViewText = (TextView) view.findViewById(R.id.textViewText);
		textViewText.setText(item.getText());
	
		
		return view;
		
		
	}



	

}

