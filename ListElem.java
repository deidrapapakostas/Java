public class ListElem {
	public int value;	// data item (key)
	public ListElem next;	// pointer to next element in list

	public ListElem() {
		value = 0;
		next = null;
	}

	public ListElem(int x) {
		value = x;
		next = null;
	}
}
