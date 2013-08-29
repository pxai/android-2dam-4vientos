package info.pello.android.contentprovider;

/**
 * POJO class to represent a Student
 * @author Pello Xabier Altadill Izura	
 * @greetz 4vientos students
 */
public class Student {
	private long _id;
	private String name;
	private String description;
	
	/**
	 * Constructor where _id is set automagically
	 * @param name
	 * @param description
	 */
	public Student(String name, String description) {
		this._id = System.currentTimeMillis();
		this.name = name;
		this.description = description;
	}
	
	/**
	 * @param _id
	 * @param name
	 * @param description
	 */
	public Student(long _id, String name, String description) {
		this._id = _id;
		this.name = name;
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [_id=" + _id + ", "
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description : "")
				+ "]";
	}
	/**
	 * @return the _id
	 */
	public long get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(long _id) {
		this._id = _id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
