/** Performs some basic linked list tests. */
public class ArrayDequeTest {

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


	//** Test Array Resize function.* //
	public static void resizeTest() {
		System.out.println("Running resize test.");
		ArrayDeque<Integer> ad_1 = new ArrayDeque<Integer>();
		ad_1.addFirst(1);
		ad_1.addFirst(2);
		ad_1.addFirst(3);
		ad_1.addFirst(4);
		ad_1.addFirst(5);
		ad_1.addFirst(6);
		ad_1.addFirst(7);
		ad_1.addFirst(8);
		ad_1.addFirst(9);
		ad_1.addLast(10);
		for (int i = 0; i< ad_1.size(); i++){
			System.out.print(ad_1.get(i));
		}

		ArrayDeque<Integer> ad_2 = new ArrayDeque<Integer>();
		ad_2.addLast(1);
		ad_2.addLast(2);
		ad_2.addLast(3);
		ad_2.addLast(4);
		ad_2.addLast(5);
		ad_2.addLast(6);
		ad_2.addLast(7);
		ad_2.addLast(8);
		ad_2.addLast(9);
		ad_2.addFirst(10);
		for (int i = 0; i< ad_2.size(); i++){
			System.out.print(ad_2.get(i));
		}
	}

	//** test get items at index i */
	public static void getTest(){
		System.out.println("Running resize test.");
		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		ad.addLast(0);
		ad.addLast(1);
		ad.addLast(2);
		ad.addLast(3);
		ad.addLast(4);
		ad.addLast(5);
		ad.addLast(6);
		ad.addLast(7);
		for(int i = 0; i < 8; i++){
		System.out.print(ad.get(i));
		}
	}

	public static void isEmptyTest() {
		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		System.out.println(ad.isEmpty());
		ad.addLast(1);
		System.out.println(ad.isEmpty());
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
//		resizeTest();
//		getTest();
		isEmptyTest();
	}
} 