public class ArrayDeque<T> {

	private T[] items;
	private int size;
	private int sentinel_front;
	private int sentinel_back;
	private int start = 4;

	/** Creates an empty list. */
	public ArrayDeque() {
		items = (T[]) new Object[8];
		sentinel_front = start;
		sentinel_back = start;
		size = 0;
	}

	/** Creates a list with X. */
	public ArrayDeque(T x){
		items = (T[]) new Object[8];
		items[start] = x;
		sentinel_front = start -1;
		sentinel_back = start + 1;
		size = 1;
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty(){
		if (items == null){
			return true;
		}
		return false;
	}

	/** Prints the items in the deque from first to last, separated by a space. */
	public void printDeque(){
		for(int i = sentinel_front + 1; i< sentinel_back; i++){
			System.out.print(get(i) + " ");
		}
	}

	/** Inserts X into the front of the list. */
	public void addFirst(T x){
		items[sentinel_front] = x;
		sentinel_front -= 1;
		size += 1;
	}

	/** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
	public T removeFirst(){
		T x = get(0);
		sentinel_front += 1;
		items[sentinel_front - 1] = null;
		return x;
	}

	/** Inserts X into the back of the list. */
	public void addLast(T x) {
		items[sentinel_back] = x;
		sentinel_back -= 1;
		size += 1;
	}

	/** Returns the item from the back of the list. */
	public T getLast() {
		return items[sentinel_back - 1];
	}

	/** Deletes item from back of the list and
	 * returns deleted item. */
	public T removeLast() {
		T x = items[sentinel_back - 1];
		items[sentinel_back - 1] = null;
		size = size - 1;
		return x;
	}

	/** Gets the ith item in the list (0 is the front). */
	public T get(int i) {
		return items[i + sentinel_front + 1];
	}

	/** Returns the number of items in the list. */
	public int size() {
		return size;
	}

}
