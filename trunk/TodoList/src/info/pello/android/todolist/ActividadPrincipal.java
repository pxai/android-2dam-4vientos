package info.pello.android.todolist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/*
 * ActividadPrincipal
 * @author Pello Altadill
 */
public class ActividadPrincipal extends Activity {


	// Variable para manejar la base de datos.
	private DbAdapter db;
	private EditText nuevaNota;
	private TextView tvSeleccionado;
	private ListView listaDatos;
    private Cursor cursor = null;
    private int seleccionado = -1;

	/**
	 * onCreate
	 * Método que se ejecuta al iniciar la Activity por 1ª vez
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Ponemos el layout
		setContentView(R.layout.main);

		// En esta caja de texto meteremos nuevas tareas
		nuevaNota = (EditText) findViewById(R.id.etNuevaTarea);

		// Un textview para sacar info
		tvSeleccionado = (TextView) findViewById(R.id.txtseleccionado);

		// Y la lista donde veremos todos los registros de la tabla
		listaDatos = (ListView) findViewById(R.id.lista_datos);
		
		// Crea un objeto manipulador de base de datos y abre una conexi�n.
		db = new DbAdapter(this);
		db.open();

		// Rellena el elemento ListView con los registros de la tabla notas.
		this.rellenarListView();
	}


	/**
	 * rellenarListView
	 * Método con el que rellenamos el ListView con los registros
	 * de la BD
	 */
	void rellenarListView() {
		// Hacemos la consulta y obtenemos el cursor
		// que apunta a los datos
		cursor = db.obtenerTareas();

		// Prepara el cursor para su uso.
		startManagingCursor(cursor);

		// Decimos qué campos queremos sacar
		String[] campos = new String[] {"_id","tarea"};

		// Decimos dónde cargaremos los datos en cada item de la lista
		int[] dondeMostrarCampos = new int[] {R.id.item_id, R.id.item_tarea };

		// Crea un adaptador para poder mostrar los datos en el ListView.
		SimpleCursorAdapter tareas = new SimpleCursorAdapter(this,R.layout.item, cursor, campos,dondeMostrarCampos);

		// Asigna el adaptador al ListView.
		listaDatos.setAdapter(tareas);
		
		// Le asociamos un listener para saber cuál clickamos
		listaDatos.setOnItemClickListener(new OnItemClickListener() {

		    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    	// 	Sacamos el registro de la posición que han seleccionado (arg2)
		    	Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(arg2);

		    	// Nos guardamos el ID del registro
		    	seleccionado = elementoSeleccionado.getInt(0);
		    	
		    	// Sacamos info por el textview
		    	tvSeleccionado.setText("Has seleccionado: " + seleccionado);
		    	
		    	Log.d("DEBUG","Clickado el elemento con el identificador: " + seleccionado);
		    }
		});
	
		Log.d("DEBUG","Lista cargada desde BD");
	}


	/**
	 * insertarRegistro
	 * Toma la información de la caja de texto y la inserta
	 * como nuevo registro
	 * @param v
	 */
	public void insertarRegistro (View v) {
		String texto = nuevaNota.getText().toString();
		// Inserta los valores de las cajas de texto en la tabla notas.
		db.insertarTarea(texto);

		// Actualiza los datos del elemento ListView.
		actualizarLista();

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Registro insertado: " + texto, Toast.LENGTH_SHORT).show();
		
		// Vacíamos la caja de texto
		nuevaNota.setText("");
	}
	
	/**
	 * eliminarRegistro
	 * Recupera el id del registro que habiamos seleccionado 
	 * y el manda a la BD que lo elimine
	 * @param v
	 */
	public void eliminarRegistro (View v) {
		
		// Si no tenemos nada seleccionado nos vamos.
		if (seleccionado == -1) {return;}
		
		// nos cargamos el registro de la BD
		db.borrarTarea(seleccionado);

		// Hasta que no vuelvan a seleccionar algún elemento
		// de la lista no podremos entrar aquí
		seleccionado = -1;
		
    	// Sacamos info por el textview
    	tvSeleccionado.setText("Elemento eliminado");

		
		// Actualiza los datos del elemento ListView.
		actualizarLista();

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Registro eliminado: " + seleccionado, Toast.LENGTH_SHORT).show();

	}
	
	/**
	 * actualizarLista
	 * La lista tipo ListView tiene asociado un Cursor,
	 * nos basta con hacer un requery para que se refresque
	 */
	private void actualizarLista () {
		cursor.requery();
	}

}