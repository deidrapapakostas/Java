/*THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas. */
public class HashSeparateChaining {

	private class Node {
		Entry entry;
		Node next;

		Node(Entry entry) { this.entry = entry; }
	}

	Node[] hashTable;
	int arraySize;
	int tableSize;

	public HashSeparateChaining(){
		hashTable = new Node[10];
		arraySize = 10;
	}

	/** This method resizes the table */
	private void resize() {
		//store previous values in temp array of original size
		Node[] temp = new Node[arraySize];
		//updates arraySize
		arraySize *= 2;
		for (int j = 0; j < temp.length; j++){
			temp[j] = hashTable[j];
		}
		//change hashTable's reference to new array of 2x previous size
		hashTable = new Node[arraySize];
		tableSize = 0; //reset tableSize
		//put all elements previously hashed into new array with new hash
		for (int i = 0; i < temp.length; i++){
			if(temp[i] != null){ //as long as it's not null
				if(temp[i].next == null){ //puts element if next element is null
					put(temp[i].entry.getKey(), temp[i].entry.getValue());    
				}else{
					while(temp[i].next != null){//loops through all next elements to put them
						put(temp[i].entry.getKey(), temp[i].entry.getValue());
						temp[i] = temp[i].next;
					}
					put(temp[i].entry.getKey(), temp[i].entry.getValue()); //last element forgotten
				}
			}	
		}
	}

	/** Computes the index in the hash table from a given key */
	private int hash(String key) {
		int hashCode = key.hashCode();
		return (hashCode & 0x7fffffff) % arraySize;
	}

	/** Returns the number of entries in the hash table. */
	public int size() { return tableSize; }

	/** Checks whether the hash table is empty */
	public boolean isEmpty() { return tableSize == 0; }

	/** Returns the node containing the given key value if it exists in the table.
	    Otherwise, it returns a null value. */
	private Node findEntry(String key) {
		int index = hash(key);

		Node currentNode = hashTable[index];
		while (currentNode != null && !currentNode.entry.getKey().equals(key))
			currentNode = currentNode.next;

		return currentNode;

	}

	/** Returns the integer value paired with the given key, if the key is in the table.
		Otherwise, it returns null. */
	public Integer get(String key) {
		Node searchResult = findEntry(key);

		if (searchResult != null)
			return searchResult.entry.getValue();
		else
			return null;

	}

	/** If the given key is not in the table, creates a new entry and adds it to the table.
		Otherwise, it updates the value associated with the given key. */
	public void put(String key, Integer value) {
		//checks if needs to be resized before putting a new element in
		if((size() / arraySize) > 5){
			resize();
		}else{
			Node searchResult = findEntry(key);


			if (searchResult != null){
				searchResult.entry.setValue(value);
				return;
			}

			Entry newEntry = new Entry(key, value);
			Node newNode = new Node(newEntry);

			int index = hash(key);
			if (hashTable[index] != null)
				newNode.next = hashTable[index];

			hashTable[index] = newNode;

			tableSize++; //update tableSize +1 because we just added an element
		}

	}

	/** Removes the entry containing the given key from the table, if the key exists in the table. */
	public void delete(String key) {
		Node searchResult = findEntry(key);
		if (searchResult == null)
			return;

		int index = hash(key);
		if (hashTable[index] == searchResult)
			hashTable[index] = searchResult.next;
		else{
			Node currentNode = hashTable[index];
			while (currentNode.next != searchResult)
				currentNode = currentNode.next;
			currentNode.next = searchResult.next;
		}

		tableSize--; //update tableSize -1 because we just deleted an element
	}

	/** Produces a string representation of the table. */
	@Override
	public String toString(){
		String output = "";
		for (int i = 0; i < arraySize; i++){
			output += "(" + i + "): ";
			Node currentNode = hashTable[i];
			if (currentNode == null)
				output += currentNode + "\n";
			else{
				while (currentNode != null){
					output += " -> " + currentNode.entry;
					currentNode = currentNode.next;
				}
				output += "\n";
			}
		}

		return output;

	}
}