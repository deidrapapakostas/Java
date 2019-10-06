/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas. */
public class Entry {
	private String key;
	private Integer value;

	public Entry(String key, Integer value){
		this.key = key;
		this.value = value;
	}

	/** Accessor method for the key */
	public String getKey() {return key;}

	/** Accessor method for the value */
	public int getValue() {return value;}

	/** Mutator method for the value */
	public void setValue(int value) {this.value = value;}

	/** Produces a string representation of the entry */
	@Override
	public String toString(){
		return "[" + key + " : " + value + "]";
	}
}