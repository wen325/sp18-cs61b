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
		sentinel_back = start + 1;
		size = 0;
	}

	/** Resizes the underlying array to the target capacity. */
	private void resize(int capacity){
		if (capacity == size * 2) {
			T[] a = (T[])new Object[capacity];
//			int front_end_length = items.length - sentinel_front;
//			int start_back_length = sentinel_back;
//			System.arraycopy(items, sentinel_front + 1, a, size - front_end_length + 1, items.length  - sentinel_front -1); // arraycopy is too complex;
//			System.arraycopy(items, 0, a, size , sentinel_back );
//			items = a;
//			sentinel_front = size - front_end_length;
//			sentinel_back = size + sentinel_back;
			for (int i = 0; i < size; i++) {
				a[capacity/4 + i] = this.get(i);
			}
			items = a;
			sentinel_front = capacity/4 - 1;
			sentinel_back = capacity * 3/4;
		}else{
			T[] a = (T[])new Object[capacity];
			for (int i = 0; i < size; i++) {
				a[i] = this.get(i);
			}
			items = a;
			sentinel_front = 1;
			sentinel_back = 0;
		}
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty() {
		if (size() == 0) {
            return true;
        }
        return false;
	}

	/** Prints the items in the deque from first to last, separated by a space. */
	public void printDeque() {
		for(int i = 0; i< size; i++) {
			System.out.print(get(i) + " ");
		}
	}

	/** Inserts X into the front of the list. */
	public void addFirst(T x) {
		if(size == items.length) {
			resize(size * 2);
		}
		items[sentinel_front] = x;
		if (sentinel_front == 0) {
			sentinel_front = items.length -1;
		}else{
			sentinel_front -= 1;
		}
		size += 1;
	}

  /**
   * Removes and returns the item at the front of the deque. If no such item exists, returns null.
   */
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        T x = get(0);
        if (sentinel_front == items.length - 1) {
            sentinel_front = 0;
            }else{
            sentinel_front += 1;
		}
          size = size - 1;
          return x;
	}

	/** Inserts X into the back of the list. */
	public void addLast(T x) {
		if (size == items.length) {
			resize(size * 2);
		}
		items[sentinel_back] = x;
		if (sentinel_back == items.length - 1) {
			sentinel_back = 0;
		}else{
			sentinel_back += 1;
		}
		size += 1;
	}

	/** Deletes item from back of the list and
	 * returns deleted item. */
	public T removeLast() {
		if (this.size == 0) {
			return null;
		}
		T x = get(this.size - 1);
		if (sentinel_back == 0) {
			sentinel_back = items.length - 1;
        }else{
            sentinel_back -= 1;
			}
		size = size - 1;

		if (size == items.length/2) {        //deal with resize down
			resize(size/2);
		}
		return x;
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 * If no such item exists, returns null.  */
	public T get(int i) {
        if (i + sentinel_front + 1 < items.length) {
            return items[i + sentinel_front + 1];
		}else{
			return items[i + sentinel_front + 1 - items.length];
        }
	}

	/** Returns the number of items in the list. */
	public int size() {
		return size;
	}

}
