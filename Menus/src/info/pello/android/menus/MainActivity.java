package info.pello.android.menus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void abrirSumador(View v) {
        startActivity(
         			  new Intent(getApplicationContext(), 
         					  SumadorActivity.class));
        this.finish();
     }
    
    public void abrirConversorEuros(View v) {
        startActivity(
         			  new Intent(getApplicationContext(), 
         					  ConversorEurosActivity.class));
        this.finish();
     }

    public void abrirConversorCelsius(View v) {
        startActivity(
         			  new Intent(getApplicationContext(), 
         					 ConversorCelsiusActivity.class));
        this.finish();
     }

    public void abrirLista(View v) {
        startActivity(
         			  new Intent(getApplicationContext(), 
         					 EjemploListActivity.class));
        this.finish();
     }
}
