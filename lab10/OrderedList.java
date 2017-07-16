public class OrderedList implements OrderedStructure {
	private static class Node {
		public Comparable val;
		public Node prev;
		public Node next;
		public Node(Comparable obj) {
			this.val = obj;
			this.prev = null;
			this.next = null;
		}
	};
	private Node head;
	private Node tail;
	private int capacity;
	public OrderedList (int cap) {
		this.capacity = capacity;
		this.head = null;
		this.tail = null;
	}
	public int size () {
		int i = 0;
		Node pointer = this.head;
		while (pointer != null) {
			pointer = pointer.next;
			i++;
		}
		return i;
	}
	public boolean add (Comparable obj) throws IllegalArgumentException {
		//load obj into Elem...
		//XXX
		if (obj == null) {
			throw new IllegalArgumentException("null");
		}
		if (head == null) { //empty list..
			this.head = new Node(obj);
			this.tail = this.head;
		} else { //non-empty list..
			Node new_node = new Node(obj);
			new_node.prev = this.tail;
			new_node.prev.next = new_node;
			this.tail = new_node;
		}
		return true;
	}
	public Object get (int pos) throws IndexOutOfBoundsException {
		int i = 0;
		Node pointer = this.head;
		while (i != pos) {
			if (pointer == null) {
				throw new IndexOutOfBoundsException();
			} else {
				i += 1;
				pointer = pointer.next;
			}
		}
		// now i == 0, we reached, but now, pointer can probobably still be null..
		if (pointer == null) {
			throw new IndexOutOfBoundsException();
		} else {
			return (Object)(pointer.val); //type-unsafty.
		}
	}
	public void remove (int pos) throws IndexOutOfBoundsException {
		int i = 0;
		Node pointer = head;
		while (i != pos) {
			if (pointer == null) {
				throw new IndexOutOfBoundsException();
			} else {
				i += 1;
				pointer = pointer.next;
			}
		}
		//now, i reached pos..
		if (pointer == null) {
			throw new IndexOutOfBoundsException();
		} else {
			//remove it..
			if (pointer.prev == null) {
				//extreme-left
				this.head = this.head.next;
				pointer.next.prev = null;
				pointer = null; //gc
			} else if (pointer.next == null) {
				//extrem-right.
				this.tail = this.tail.prev;
				pointer.prev.next = null;
				pointer = null;
			} else {
				pointer.prev.next = pointer.next;
				pointer.next.prev = pointer.prev;
				pointer = null;
			}
		}

	}

	public void print () {
		Node pointer = head;
		while (pointer != null) {
			System.out.println(pointer.val);
			pointer = pointer.next;
		}
	}
	/* merge sort... */
	public void merge (OrderedList other) {
		//XXX
	}
}

