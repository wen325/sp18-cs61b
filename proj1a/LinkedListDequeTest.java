/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//		/*
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
//		*/
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//		/*
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
//		*/
	}

	//** addFirst and addLast Test */
	public static void addTest(){
		LinkedListDeque<Integer> dll = new LinkedListDeque<Integer>();
		dll.addFirst(1);
		dll.addFirst(2);
		dll.addFirst(3);
		dll.addFirst(4);
		dll.addFirst(5);
		dll.addLast(6);
		dll.addLast(7);
		dll.printDeque();
	}

	//** get item and print Deque test */
	public static void getTest(){
		LinkedListDeque<Integer> dll = new LinkedListDeque<Integer>();
		dll.addFirst(1);
		dll.addFirst(2);
		dll.addFirst(3);
		dll.addLast(4);
		dll.addLast(5);
		dll.addLast(6);
		System.out.println(dll.get(6));
	}

	//** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
	public static void removeFirstTest(){
		LinkedListDeque<Integer> dll = new LinkedListDeque<Integer>();
		dll.addFirst(1);
		dll.addFirst(2);
		dll.addFirst(3);
		dll.removeFirst();
		dll.removeFirst();
		int k = dll.removeFirst();
		System.out.println(k);
	}

	//** test isEmpty */
	public static void isEmptyTest(){
		LinkedListDeque<Integer> dll = new LinkedListDeque<Integer>();
		dll.addFirst(1);
		dll.addFirst(0);
		dll.isEmpty();
	}

	//** get item RecursiveTest */
	public static void getRecursiveTest(){
		LinkedListDeque<Integer> dll = new LinkedListDeque<Integer>();
		dll.addFirst(1);
		dll.removeLast();
		dll.addLast(3);
		dll.addFirst(4);
		dll.getRecursive(0);
		dll.removeLast();
		dll.addLast(7);
		dll.addFirst(8);
		dll.addLast(9);
		int k =dll.getRecursive(3);
	}

	public static void main(String[] args) {
//		System.out.println("Running tests.\n");
//		addIsEmptySizeTest();
//		addRemoveTest();
//		addTest();
//		getTest();
//		removeFirstTest();
//		isEmptyTest();
		getRecursiveTest();
	}
} 