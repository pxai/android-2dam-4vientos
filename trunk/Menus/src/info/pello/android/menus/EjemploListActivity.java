package info.pello.android.menus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EjemploListActivity extends ListActivity {
	    String tests[] = { "SumadorActivity",
					"ConversorEurosActivity",
					"ConversorCelsiusActivity"};

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setListAdapter(new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, tests));
	    }

	    @Override
	    protected void onListItemClick(ListView list, View view, int position,
	            long id) {
	        super.onListItemClick(list, view, position, id);
	        String testName = tests[position];
	        try {
	            Class clase = Class
	                    .forName("info.pello.android.menus." + testName);
	            Intent intent = new Intent(this, clase);
	            startActivity(intent);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}

