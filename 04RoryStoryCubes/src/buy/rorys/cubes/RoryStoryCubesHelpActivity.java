package buy.rorys.cubes;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RoryStoryCubesHelpActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        
        // Así es como cargamos un fichero que tenemos en 
        // la carpeta RAW de los resources
        InputStream iFile = getResources().openRawResource(R.raw.help);
        
        // Cargamos el textview donde pondremos la chapa
        TextView helpText = (TextView) findViewById(R.id.texto_help);
        
        // Cargamos el contenido del fichero
        String strFile = leerFichero(iFile);
        
        // Y ponemos ese contenido en el TextView
        helpText.setText(strFile);
        
        // Si pinchamos en el botón para volver al menú
        // lo que hacemos es cerrar la activity actual.
        Button botonmenu = (Button) findViewById(R.id.botonmenu);
       botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	RoryStoryCubesHelpActivity.this.finish();
    	    }
    	});
    }
    
    /**
     * leerFichero
     * Le pasamos un inputStream y se encarga en devolver todo el contenido
     * en forma de un único String.
     * Versión impresionante y rápida  tal y como se cuenta en:
     * http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
     *  reading files like a pro
     * @param iFile
     * @return String
     */
	private String leerFichero(InputStream iFile) {
	    	    try {
	    	        return new java.util.Scanner(iFile,"UTF-8").useDelimiter("\\A").next();
	    	    } catch (java.util.NoSuchElementException e) {
	    	        return "";
	    	    } 
	      
	  }
}