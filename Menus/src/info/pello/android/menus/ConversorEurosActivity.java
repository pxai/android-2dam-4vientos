package info.pello.android.menus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ConversorEurosActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor_euros);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_conversor_euros, menu);
        return true;
    }
    
    public void volver(View v) {
        startActivity(
         			  new Intent(getApplicationContext(), 
         					 MainActivity.class));
        this.finish();
     }

}
