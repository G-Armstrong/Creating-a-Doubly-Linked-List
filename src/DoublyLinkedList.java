import java.util.ListIterator;
import java.util.NoSuchElementException;



public class DoublyLinkedList<E> {
	// Data members
		private Node head, tail;
		int size;
		// Inner class Node
		//O(1) = constant
		private class Node{
			E value;
			Node next, previous;
			Node(E initialValue){ 
				value = initialValue; 
				next = null;
				previous =null;
			}
		}
		// Constructor
		//O(1) = constant
		public DoublyLinkedList() {
			head = tail = null;
			size = 0;
		}
		/**
		 *  Adding an item to the list
		 * @param item to be added
		 * @return boolean result of operation 
		 */
		//O(1) = constant
		public boolean addFirst(E item) {
			Node newNode = new Node(item);
			if(head == null) { 
				head = tail = newNode; 
//				newNode.previous = null;
			}
			else { 
				newNode.next = head;  
				head.previous = newNode;
	//			newNode.previous = null;
				head = newNode;
			}
			size++; 
			return true;
		}
		/**
		 * Adds item to end of list
		 * @param item to be added
		 * @return boolean result of operation 
		 */
		//O(1) = constant
		public boolean addLast(E item) {
			Node newNode = new Node(item);
			if(head == null) { 
				head = tail = newNode; 
			}
			else { 
				tail.next = newNode; 
				newNode.previous=tail;
				tail = newNode; 
			}
			size++; 
			return true;
		}
		/**
		 * Calls addFirst
		 * @param item
		 * @return boolean result of operation 
		 */
		//O(1) = constant
		public boolean add(E item) {
			return addFirst(item);
		}
		/**
		 *  Retrieving an item from the list
		 * @return E object
		 */
		//O(1) = constant
		public E getFirst() {
			if (head == null) {
				throw new NoSuchElementException();
			}
			return head.value;
		}
		/**
		 * Gets value held at tail
		 * @return E object
		 */
		//O(1) = constant
		public E getLast() {
			if (head == null) {
				throw new NoSuchElementException();
			}
			return tail.value;
		}
		/**
		 *  Removing an item from the list
		 * @return boolean result of operation 
		 */
		//O(1) = constant
		public boolean removeFirst() {
			if (head == null) {
				throw new NoSuchElementException();
			}
			head = head.next;
			head.previous = null;
			if(head == null) {
				tail=null;
			}
			
			size--; 
			return true;
		}
		/**
		 * removes last object and maintains double links
		 * @return boolean result of operation 
		 */
		//O(n) = linear
		public boolean removeLast() {
			if (head == null) 
				throw new NoSuchElementException();
			if(size == 1) {
				return removeFirst();
			}
			Node current = head;
			Node previous = null;
			Node before_previous = null;
			while(current.next != null) {
				before_previous = current.previous;
				previous = current; 
				current = current.next;
			}
			previous.next = null; previous.previous = before_previous;
			tail = previous;
			size--; return true;
		}
		/**
		 *  toString() method
		 */
		//O(n) = linear
		public String toString() {
			String output = "[";
			Node current = head;
			while(current != null) {
				output += current.value + " ";
				current = current.next;
			}
			output += "]";
			return output;
		}
		/**
		 * Returns index of node passed
		 * @param node to be indexed
		 * @return int index of Node
		 */
		//O(n) = linear
		private int indexOf(Node node) {
			if (head == null) 
				throw new NoSuchElementException();
			if(size == 1) {
				return 0;
			}
			int index = 0;
			Node current = head;
			while(current.next != null) {
				if(current.value == node.value) {
					return index;
				}
				index++;
				current = current.next;
				
			}
			return -1;
		}
		
		
		// clear, check if empty, and size of the list
		//O(1) = constant
		public void clear() { head = tail= null; size = 0; }
		//O(1) = constant
		public boolean isEmpty() { return (size == 0); }
		//O(1) = constant
		public int size() { return size; }
		
		/**
		 *  Generating an iterator for the list
		 * @return constructor call
		 */
		//O(1) = constant
		public ListIterator<E> iterator(){  
			return new DoublyLinkedListIterator();
		}
		/**
		 * Generating an iterator for the list
		 * @param index specified to start the iteration
		 * @return constructor call with parameter 'index'
		 */
		//O(1) = constant
		public ListIterator<E> iterator(int index){
			return new DoublyLinkedListIterator(index);
		
		}
		
		private class DoublyLinkedListIterator implements ListIterator<E>{
			private Node current;
			/**
			 * Constructor with 0 parameters
			 */
			public DoublyLinkedListIterator() {
				current = tail;
			}
			/**
			 * Constructor
			 * @param index parameter to start iteration
			 */
			//O(1) = constant at best
			//O(n) = linear at worst
			public DoublyLinkedListIterator(int index) {
				if(index<0 || index>size) {
					throw new IndexOutOfBoundsException();
				}
				if(index == size) { 
					current = null;
				}
				int count =0;
				System.out.println(head.value);
				do {
					current = head.next;
					count++;	
				} while(count<index);
		
			}
			/**
			 * Checks for current value
			 */
			//O(1) = constant
			public boolean hasNext() {
				return (current != null);
			}
			/**
			 * Checks for current value
			 */
			//O(1) = constant
			public boolean hasPrevious() {
				return (current != null);
			}
			/**
			 * Returns current value which iterates to previous
			 */
			//O(1) = constant
			public E next() {
				if(current == null)  {
					throw new NoSuchElementException();
				}
				E value = current.value;
				current = current.previous; 
				return value;
			}	
			/**
			 * Returns current value which iterates to next
			 */
			//O(1) = constant
			public E previous() {
				if(current == null)  {
					throw new NoSuchElementException();
				}
				E value = current.value;
				current = current.next; 
				return value;
			}
			/**
			 * Calls indexOf
			 */
			//O(n) = linear
			public int nextIndex() {
				return indexOf(current);
			}
			/**
			 * Calls indexOf
			 */
			//O(n) = linear
			public int previousIndex() {
				return indexOf(current);
			}
			/**
			 * Sets a value at current
			 */
			//O(1) = constant
			public void set(E e) {
				if(current == null) {
					throw new NoSuchElementException();
				}
				else {
					current.value = e;
				}
				
			}
			/**
			 * Throws error
			 */
			//O(1) = constant
			public void add(E e) {
				throw new UnsupportedOperationException();
			}
			/**
			 * Throws error
			 */
			//O(1) = constant
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		/**
		 * Returns the number of iterations it takes the algo to find the item
		 * @param item to be found
		 * @return int number of iterations 
		 */
		//O(n) = linear
		public int searchIterations(E item) {
			int iterations = 0;
			Node current = head;
			while(current != null) {
				iterations++;
				if(current.value.equals(item)) {
					return iterations;
				}
				current = current.next;
			}
			return iterations;
		}
}
