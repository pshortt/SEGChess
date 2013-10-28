package textEditor.core.structures;

/**
 * Implementation of the ArrayList 
 */
public class ArrayList<E extends Comparable<? super E>>{

	private final static int INITIAL_SIZE = 500;
	
	private int arrayListSize=0;
	private Comparable<E> [] array;
	private double alpha;
	
	/**
	 * Constructor
	 */
	public ArrayList(){
		// Originally, create an array of size 5000
		array = new Comparable[INITIAL_SIZE];
		alpha = 2.0;
	}
	
	public ArrayList(int initialSize){
		// Originally, create an array of size 5000
		array = new Comparable[initialSize];
		alpha = 2.0;
	}
	

	/**
	 * Returns the size of the array list
	 * @return size of array list
	 */
	public int size() {
		return arrayListSize;
	}


	/**
	 * Check whether the list is empty
	 * @return true if the list is empty and false otherwise
	 */
	public boolean isEmpty() {
		return arrayListSize==0;
	}

	/**
	 * Searches for a particular element by applying the binary search
	 * @param o we are searching for
	 * @return true if the element is found and false otherwise
	 */
	public boolean contains(Object o) {
		int lowerbound = 0;
		int upperbound = arrayListSize-1;
		int position;
		
		// To start, find the subscript of the middle position.
		position = ( lowerbound + upperbound) / 2;
		
		
		while((!array[position].equals(o)) && (lowerbound <= upperbound)){
		
			if (array[position].compareTo(o)>0){             // If the number is > key, ..
			                                       // decrease position by one. 
			upperbound = position - 1;   
			}                                                             
			else{                                                        
				lowerbound = position + 1;    // Else, increase position by one. 
			}
			
			position = (lowerbound + upperbound) / 2;
			
		}
		if (lowerbound <= upperbound){
			return true;
		}
		else{
			return false;
		}
	}

	
	/**
	 * Transforms the array list into a simple array
	 * @return the array containing all the elements of the array list
	 */
	public Object[] toArray() {
		return array;
	}

	/**
	 * Returns the element at the specified index
	 * @param index of the element we wish to retrieve
	 * @return the element at the specified index
	 */
	public E get(int index) {
		E temp = null;
		if (index < array.length){
			temp = (E)array[index]; 
		}
		return temp;
	}

	/**
	 * Sets the element at the specified index
	 * @param index of the element we wish to set
	 * @return the previous content of the array postion before the set operation was performed
	 */
	public E set(int index, E element) {
		E temp = null;
		if (index < array.length){
			temp = (E)array[index]; 
			array[index] = (Comparable) element;
		}
		return temp;
	}

	/**
	 * Adds an element to the array list
	 * @param e element to be added
	 * @return true
	 */
	public boolean addLast(E e) {
		if (arrayListSize == array.length){
			growArray ();
		}
		
		
		
		array[arrayListSize] = (Comparable) e;
		arrayListSize++;
		
		return true;
	}


	/**
	 * Adds an element at the specified index
	 * @param index where the element should be added
	 * @param element we wish to add to the array list
	 */
	public void add(int index, E element) {
		if (index > arrayListSize){
			return;
		}
		
		if (arrayListSize == array.length){
			growArray ();
		}
		
		for (int i = arrayListSize-1; i >= index; i--){
			array[i+1]=array[i];
		}
		
		array [index] = (Comparable) element;
		arrayListSize++;
	}


	/**
	 * Removes the element stored in the array list at the specified index
	 * @param index of element we wish to delete
	 * @return the element we removed form the list
	 */
	public E remove(int index) {
		E element = null;
		
		if (!isEmpty() && index < arrayListSize){
			element = (E)array[index];
		
			for (int i = index; i < array.length-1; i++){
				array[i]=array[i+1];
			}
			arrayListSize--;
		}
		return element;
	}
	
	/**
	 * Move the elements of the array to a bigger array and redirect the original reference
	 */
	private void growArray (){
		int newLength = growthFunction();
		
		Comparable [] tempArray = new Comparable[newLength];
		
		for (int i = 0; i < array.length; i++){
			tempArray[i] = array[i];
		}
		
		// redirect reference to array
		array = tempArray;
	}
	
	/**
	 * Calculate the next size of the array
	 * @return
	 */
	private int growthFunction(){
		return (int)(array.length*alpha);
	}
	
	public void merge(ArrayList<E> b){
		Comparable<E>[] temp = new Comparable[size() + b.size()];

		while (!isEmpty() && !b.isEmpty()){
			
			int comparasion = this.get(0).compareTo(b.get(0));
			int i = 0;
			
			if (comparasion < 0){
				temp[i] = (Comparable)remove(0);
				i++;
				
			} else if (comparasion > 0){
				temp[i] = (Comparable) b.remove(0);
				i++;
			} else {
				temp[i] = (Comparable) remove(0);
				b.remove(0);
				i++;
			}
			
			while (!isEmpty()){
				temp[i] = (Comparable) remove(0);
				i++;
			}
			
			while (!b.isEmpty()){
				temp[i] = (Comparable) remove(0);
				i++;
			}
			
			array = temp;
		}
	}



	
}
