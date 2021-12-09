public class LinkedListDeque<T> {
    private Deque sentinel;
    private int size;

    private class Deque {
        public Deque prev;
        public T item;
        public Deque next;

        public Deque(Deque m, T i,Deque n) {
            prev = m;
            item = i;
            next = n;
        }
    }

    /** Initialize the null Deque */
    public LinkedListDeque() {
        sentinel = new Deque(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * gets the item at the given index, where 0 is the front, 1 is the next item
     */
    public T get(int index) {
        Deque p = sentinel.next;
        while (index > 0) {
            p = p.next;
            if(p == null){
                return null;
            }
            index -= 1;
        }
        return p.item;
    }

    /** add x to the First */
    public void addFirst(T x) {
      Deque p = new Deque(sentinel, x, sentinel.next);
      sentinel.next.prev = p;
      sentinel.next = p;
        size += 1;
    }

    /** remove and return the item at the front; if no item exists, return null */
    public T removeFirst() {
        if (sentinel.next == null){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    /** add x to the Last */
    public void addLast(T x) {
        Deque p = new Deque(sentinel.prev, x, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    /** remove and return the item at the back; if no item exists, return null */
    public T removeLast(){
        if (sentinel.prev == null){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }
    /** return Deque length */
    public int size(){
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (sentinel.next == null);
    }

    /** Prints the items in the deque from first to last, separated by space. */
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i)+" ");
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  use resursion;
     */
    public T getRecursive(int index){
        if(index == 0){
            return sentinel.next.item;
            }
//        T p = new T(sentinel.next, sentinel.next.item, sentinel.next.next);
//        return p.getRecuresive(index - 1);
        return null;
    }
}
