package info.pello.android.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * DBAdapter
 * Esta es una clase intermediaria entre nuestro Activity y 
 * la BBDD. Aquí meteremos todas las operaciones CRUD sobre
 * los datos
 * @author Pello Altadill
 *
 */
public class DbAdapter {

	// Este objeto nos permite meterle mano a SQLite
	private SQLiteDatabase db;

	// Aquí tenemos nuestro SqliteHelper
	// que se encarga de crear y actualizar
	private SqLiteHelper dbHelper;
	
	// El contexto nos servirá para referirnos a la Activity
	// en la que estamos
	private final Context contexto;


	/**
	 * DbAdapter
	 * Constructor de la clase
	 * @param contexto Será la activity que usa esta clase
	 */
	public DbAdapter(Context contexto) {
		this.contexto = contexto;
	}


	/**
	 * open
	 * Usa el SqLiteHelper para encargase de abrir la conexión.
	 * El SqLiteHelper lo primero que hará es crear la BD si no existía.
	 * @return Devuelve un objeto de clase SQLiteDatabase para gestionar la BD
	 * @throws SQLException
	 */
	public SQLiteDatabase open() throws SQLException {
		// Crea un objeto asistente de base de datos de clase SqLiteHelper.
		dbHelper = new SqLiteHelper(contexto);
		
		// Abre la base de datos en modo escritura (lectura permitida).
		db = dbHelper.getWritableDatabase();
		
		Log.d("DEBUG","BD obtenida: " + db.toString());
		
		// Devuelve el objeto de tipo SQLiteDatabase.
		return db;
	}

	/**
	 * close
	 * Cierra la base de datos mediante el dbHelper
	 */
	public void close() {
		dbHelper.close();
	}

	/**
	 * insertarTarea
	 * Inserta un registro con los campos titulo y cuerpo en la base de datos.
	 * 
	 * @param String tarea
	 * @return Devuelve el número de registro insertado 0 -1 en caso de error
	 */
	public long insertarTarea(String tarea) {
		// Creamos un registro
		ContentValues registro = new ContentValues();

		// Agrega los datos.
		registro.put("tarea", tarea);

		// Inserta el registro y devuelve el resultado.
		return db.insert("todo", null, registro);
	}

	/**
	 * borrarTarea
	 * Borra el registro que tiene el id especificado.
	 * 
	 * @param idRegistro id del registro a borrar
	 * @return Devuelve el nº de registros afectados.
	 */
	public int borrarTarea(long idRegistro) {
		return db.delete("todo",  "_id = "
				+ idRegistro, null);
	}

	/**
	 * obtenerTareas
	 * Obtiene todos los registros de la tabla todo.
	 * 
	 * @return Cursor Devuelve un cursor con los registros obtenidos.
	 */
	public Cursor obtenerTareas() {
		return db.query("todo", new String[] {"_id","tarea"}, null, null, null, null, null);
	}

	/**
	 * obtenerTarea
	 * Obtiene el registro que tiene el id especificado.
	 * 
	 * @param idRegistro id del registro que se quiere obtener.
	 * @return Cursor un cursor con el registro obtenido.
	 * @throws SQLException
	 */
	public Cursor obtenerTarea (long idRegistro) throws SQLException {
		Cursor registro = db.query(true, "todo",new String[] { "_id","tarea"}, 
									"_id =" + idRegistro, null, null, null, null, null);

		// Si lo ha encontrado, apunta al inicio del cursor.
		if (registro != null) {
			registro.moveToFirst();
		}
		return registro;
	}

	/**
	 * actualizarTarea
	 * Hace un UPDATE de los valores del registro cuyo id es idRegistro.
	 * 
	 * @param int idRegistro id del registro que se quiere modificar.
	 * @param String tarea
	 * @return int cantidad registros han sido afectados.
	 */
	public int actualizarTarea(long idRegistro, String tarea) {
		// Creamos un registro
		ContentValues registro = new ContentValues();

		// Agrega los datos.
		registro.put("tarea", tarea);

		// Inserta el registro y devuelve el resultado.
		return db.update("to", registro,
				 "_id=" + idRegistro, null);
	}
	
}