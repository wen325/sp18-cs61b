public class LinkedListDeque<T> {
    private Deque sentinel_front;
    private Deque sentinel_back;
    private int size;

    private class Deque {
        public T item;
        public Deque next;

        public Deque(T i, Deque n) {
            item = i;
            next = n;
        }
    }

    /** Initializ the null Deque */
    public LinkedListDeque() {
        sentinel_front = new Deque(null, null);
        sentinel_back = new Deque(null, null);
        size = 0;
    }

    /** Initialize the Deque with x */
    public LinkedListDeque(T x) {
        sentinel_front = new Deque(null, null);
        sentinel_front.next = new Deque(x, null);
        sentinel_back.next = new Deque(x, null);
        size = 1;
    }

    /**
     * gets the item at the given index, where 0 is the front, 1 is the next item
     */
    public T get(int index) {
        Deque p = sentinel_front;
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
        sentinel_front.next = new Deque(x, sentinel_front.next);
        size += 1;
    }

    /** remove and return the item at the front; if no item exists, return null */
    public T removeFirst() {
        T item = sentinel_front.next.item;
        sentinel_front.next = sentinel_front.next.next;
        size -= 1;
        return item;
    }

    /** add x to the Last */
    public void addLast(T x) {
        sentinel_back.next = new Deque(x, null);
        sentinel_back = sentinel_back.next;
        size += 1;
    }

    /** remove and return the item at the back; if no item exists, return null */
    public T removeLast(){
        return null;
    }
    /** return Deque length */
    public int size(){
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
//        if(sentinel_front.next == null){
//            return false;
//        }
        return (sentinel_front.next == null);
//        return true;
    }

    /** Prints the items in the deque from first to last, separated by space. */
    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(get(i)+" ");
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  use resursion;
     */
    public T getRecursive(int index){
        if(index == 0){
            return sentinel_front.item;
        }else{
            Deque p = sentinel_front.next;
            return p.item;
//            return p.getRecursive(index - 1);

        }
    }
}
