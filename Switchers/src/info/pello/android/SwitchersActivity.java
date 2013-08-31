package info.pello.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;

public class SwitchersActivity extends Activity {
	private Button btnAvanzar;
	private ImageSwitcher imageSwitcher;
	private TextSwitcher textSwitcher;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnAvanzar = (Button) findViewById(R.id.button1);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher1);
        
        btnAvanzar.setOnClickListener(new View.OnClickListener() {
     	    public void onClick(View view) {

     	    }
     	});
 
    }
}