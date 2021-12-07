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

	/** Resizes the underlying array to the target capacity. */
	private void resize(int capacity){
		T[] a = (T[])new Object[capacity];

		int front_end_length = items.length - sentinel_front;
		int start_back_length = sentinel_back;
		System.arraycopy(items, sentinel_front, a, front_end_length , items.length  - sentinel_front);
		System.arraycopy(items, 0, a, start_back_length, sentinel_back );
		items = a;
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
		if(size == items.length){
			resize((size * 2));
		}
		items[sentinel_front] = x;
		sentinel_front -= 1;
		size += 1;
	}

	/** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
	public T removeFirst(){
		T x = items[sentinel_front + 1];
		sentinel_front += 1;
		items[sentinel_front] = null;
		size = size - 1;
		return x;
	}

	/** Inserts X into the back of the list. */
	public void addLast(T x) {
		if(size == items.length){
			resize((size * 2));
		}
		items[sentinel_back] = x;
		sentinel_back += 1;
		size += 1;
	}

	/** Deletes item from back of the list and
	 * returns deleted item. */
	public T removeLast() {
		T x = items[sentinel_back - 1];
		sentinel_back -= 1;
		items[sentinel_back] = null;
		size = size - 1;
		return x;
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 * If no such item exists, returns null.  */
	public T get(int i) {
		return items[i + sentinel_front + 1];
	}

	/** Returns the number of items in the list. */
	public int size() {
		return size;
	}

}
