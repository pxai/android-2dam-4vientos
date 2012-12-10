package info.pello.android.accesoficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AccesoFicherosActivity extends Activity {

	private String miFichero = "fichero.txt";
	private TextView contenidoFichero;
	
    /**
     * onCreate
     * Método invocado cuando se crea la Activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // textView donde mostraremos el contenido
        contenidoFichero = (TextView) findViewById(R.id.textoFichero);
        
        // Comprobamos si podemos escribir, y lo ahcemos.
        if (hayAlmacenamiento()) {
        	this.guardarAlgo();
        }
    }
    
    

    
    /**
     * hayAlmacenamiento
     * Comprueba si hay posibilidad de almacenamiento.
     * @return boolean
     */
    private boolean hayAlmacenamiento () {
    	
    	// Del entorno tomamos info de almacenamiento
    	String estadoAlmacenamiento = Environment.getExternalStorageState();

    	// Tenemos permiso para todo
    	if (Environment.MEDIA_MOUNTED.equals(estadoAlmacenamiento)) {
    		Log.d("DEBUG","Puedo leer y escribir");
    		return true;
    		// o quizá tenemos permiso de solo lectura?
    	}else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(estadoAlmacenamiento)) {
    		Log.d("DEBUG","Puedo leer pero no escribir");
    		return false;
    	}else{ 
    		// cualquier otro caso, no podemos hacer nada
    		Log.d("DEBUG","No podemos ni leer ni escribir");
    		return false;
    	}

    }
    
    
    /**
     * guardarAlgo
     * Guardamos un fichero de texto en el almacenamiento
     */
    private void guardarAlgo () {
    	try{
    		
    		File ruta_sd = Environment.getExternalStorageDirectory();
    		File f = new File(ruta_sd.getAbsolutePath(), this.miFichero);
    		OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
    		
    		// Escribimos algo..
    		fout.write("Vamooos esa peña ahí to cheta como lo parte");
    		
    		// Y cerramos.
    		fout.close();
    		
    		Log.d("DEBUG","Ok, fichero guardado: " + ruta_sd.getAbsolutePath() + "/" + this.miFichero);
    	}catch(Exception e){
    		Log.e(this.getClass().getName(), "Error al escribir fichero en almacenamiento: " + e.getMessage());
    	}

    }
    
    
    /**
     * leerFichero
     * Lee un fichero del almacenamiento
     */
    public void leerFichero (View w) {
    	String contenido = "";
    	
    	try{
    		File ruta_sd = Environment.getExternalStorageDirectory();
    		File f = new File(ruta_sd.getAbsolutePath(), this.miFichero);
    		BufferedReader lectorFichero = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    		
    		// Vamos a leer una línea a ver:
    		contenido = lectorFichero.readLine();
    		
    		this.contenidoFichero.setText(contenido);
    		lectorFichero.close();
    		Log.d("DEBUG","Ok, fichero leído, ruta: " + ruta_sd.getAbsolutePath()+ "/" + this.miFichero);
    	}catch(Exception e){
    		Log.e(this.getClass().getName(), "Error al leer Fichero: " + e.getMessage());
    	}
    }

}