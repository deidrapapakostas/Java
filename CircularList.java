/*
      THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
      CODE WRITTEN BY OTHER STUDENTS. Deidra Papakostas
*/
public class CircularList {
	
	private ListElem tail; // pointer to the last element in list
	private int size; // size of the list
	
	public CircularList(){
		/*
		 * build an empty CircularList
		 */
		 
		tail = null;
		size = 0;
	}

	//This method returns true if the list is empty, false otherwise
    public boolean isEmpty(){ 
    	if(size == 0){ 
    		return true;
    	}else{
    		return false;
    	}
    }

    //This method returns the size of the circular list
    public int getSize(){
    	return size; 
    }

    //This method removes an element from the head of list. It returns the value of the element removed, or null if the list is empty
    public Integer remove(){
    	if(size == 0){ //nothing to remove
    		return null;
    	}else{
    		ListElem x = tail.next;
    		tail.next = x.next;
    		size--; //update size to be one less
    		return x.value; //return value removed
    	}
    }

    //This method inserts the newElem at the head of the circular list, and it has no returning value
    public void insert(ListElem newElem){
    	if(tail == null){ //accounts for tail being null
    		tail = newElem;
    		tail.next = null;
    		size++; //increase size by 1
    	}else if(tail.next == null){ //accounts for tail.next being null
    		tail.next = newElem;
    		newElem.next = tail;
    		size++; //increases size by 1
    	}
    	else{ //other cases
    		ListElem x = tail.next;
    		tail.next = newElem;
    		newElem.next = x;
    		size++; //increases size by 1
    	}
    }

    //This method rotates the circular list: the first element in the list is moved to the end of the list, and all other elements in the list are moved forward one position
    public void rotate(){
    	if(tail != null){ //makes sure tail isn't null
    		ListElem oldTail = tail;
    		tail = oldTail.next;
    	}
    	

    }

    //This method prints the value of each element in the list starting from the head
    public void printList(){
    	ListElem x = tail.next; //holder for first element in list
    	for (int i = 0 ; i < size; i++){ //loops through size 
    		System.out.println(x.value);
    		x = x.next; //updates x to be next item
    	}
    }

}
