package buy.rorys.cubes;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;

public class RoryStoryCubesPreferencesActivity extends PreferenceActivity {
	Button botonmenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.preferences_layout);
        botonmenu = (Button) this.findViewById(R.id.botonmenu);
        botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(RoryStoryCubesPreferencesActivity.this, RoryStoryCubesMenuActivity.class);
    	    	startActivity(miIntent);
    	    	RoryStoryCubesPreferencesActivity.this.finish();
    	    }
    	});
    }
}