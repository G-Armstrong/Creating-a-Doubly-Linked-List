import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ListIterator;


public class Test {

	public static void main(String[] args) {
		DoublyLinkedList<String> countryArrayList = new DoublyLinkedList<>();
		
		File file = new File("countries.txt");
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		while(readFile.hasNext()) {
			String country = readFile.nextLine();
			countryArrayList.add(country);
		}
		
		printForward(countryArrayList);
		printBackward(countryArrayList);
		

	}
	/**
	 * Prints forward
	 * @param <E> fluid object type  to be assigned to String object
	 * @param list passed filled with countries
	 */
	public static <E> void printForward(DoublyLinkedList<E> list) {
		ListIterator<String> iter = (ListIterator<String>) list.iterator(); //current points to head
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	/**
	 * Prints backward
	 * @param <E> fluid object type  to be assigned to String object
	 * @param list passed filled with countries
	 */
	public static <E> void printBackward(DoublyLinkedList<E> list) {
		ListIterator<String> iter = (ListIterator<String>) list.iterator(list.size()-1); //current points to head
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}
	}

}
