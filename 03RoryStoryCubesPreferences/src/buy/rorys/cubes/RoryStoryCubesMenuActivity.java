package buy.rorys.cubes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/*
 * RoryStoryCubesMenuActivity
 * Activity para mostrar un menú al usuario
 * @author Pello
 * @version 0.2
 */
public class RoryStoryCubesMenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Un array con los elementos del menú
        String[] items = { getResources().getString(R.string.game_title),
                getResources().getString(R.string.setup_title),
                getResources().getString(R.string.help_title),getResources().getString(R.string.exit) };

        // Cargamos un ListView
        ListView menuLista = (ListView) findViewById(R.id.ListView_Menu);
        
        // Creamos un ArrayAdapter con los Strings del array
        // y de paso lo formateamos con un layout por item
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);
        
        // Le asociamos el ArrayAdapter con los elementos del menú
        // a nuestro ListView
        menuLista.setAdapter(adaptador);

        // Definimos un Listener para controlar qué pasa
        // cuando hacemos click en cada elemento de la lista
        menuLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {

            	// Según la posición del menú seleccionada
            	// hacemos determinada cosa
                switch (position) {
                	case 0:
                			/*startActivity(new Intent(RoryStoryCubesMenuActivity.this,
                			RoryStoryCubesGameActivity.class));*/
                			break;
                	case 1:
                			startActivity(new Intent(RoryStoryCubesMenuActivity.this,
                			RoryStoryCubesPreferencesActivity.class));
                			Log.d("MSG","cerrando");
                			break;
                	case 2:
                			/*startActivity(new Intent(RoryStoryCubesMenuActivity.this,
            				RoryStoryCubesHelpActivity.class));*/
                			break;
                	case 3:
                			finishApp();
                			
                	default:
                }
                
              }
          });

                    
    }
    
    
    /**
     * finishApp
     * Termina la app
     * No es necesario según los pro:
     * http://blog.radioactiveyak.com/2010/05/when-to-include-exit-button-in-android.html
     */
    private void finishApp() {
    	// A lo killer, headshot al proceso
    	//android.os.Process.killProcess(android.os.Process.myPid());
    	
    	// A lo blandengue, realmente no detiene la app
    	this.finish();
    	
    }
}