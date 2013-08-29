package info.pello.android.contentprovider;

import java.util.Hashtable;

/**
 * Hashtable to contain Student class instances.
 * @author Pello Xabier Altadill Izura
 * @greet DAM
 */
public class StudentList extends Hashtable<Long,Student>{

	
	/**
	 * returns Student list in String format
	 */
	public String toString () {
		String result = "";
		for (Long key : this.keySet()) {
			result += this.get(key).toString() + "\n";
		}
		return result;
	}
}
