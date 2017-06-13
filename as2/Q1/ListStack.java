// ListStack.java
// Dev By __HUO_YU__

class Elem<T> {
	public T val;
	public Elem<T> next;
	public Elem (T val, Elem<T> next) {
		this.val = val;
		this.next = next;
	}
}

public class ListStack<T> implements Stack<T> {
	private int size;
	private int cap;
	private Elem<T> bottom;
	private Elem<T> top;
	public ListStack (int cap) {
		this.bottom = null;
		this.top = null;
		this.size = 0;
		this.cap = cap;
	}
	public boolean isEmpty () {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isFull() {
		if (this.size == this.cap) {
			return true;
		} else {
			return false;
		}
	}
	public T peek () {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.top.val;
		}
	}
	public T pop () {
		if (this.isEmpty()) return null;
		Elem<T> old_top;
		Elem<T> new_top;
		if (this.size == 1){
			old_top = this.bottom;
			this.top = null;
			this.bottom = null;
		} else {
			old_top = this.top;
			new_top = this.bottom;
			while (new_top.next != old_top) {
				new_top = new_top.next;
			}
			new_top.next = null;
			this.top = new_top;
		}
		this.size --;
		return old_top.val;
	}
	public void push (T new_el_val) {
		Elem<T> new_top = new Elem(new_el_val, null);
		if (this.isEmpty()) {
			this.bottom = new_top;
			this.top = new_top;
		} else {
			this.top.next = new_top;
			this.top = new_top;
		}
		this.size ++;
	}
	public void clear () {
		// pay attention to the machanism of garbage collection in Java.
		// The most straight-foreward way to implement this is to pop again and again.
		while (this.pop() == null) ;
	}
	public Elem<T> getBottom () {
		return this.bottom;
	}
	public Elem<T> getTop () {
		return this.top;
	}
	public void reverse () {
		ListStack<T> rev_stack = new ListStack<T>(this.cap);
		int original_size = this.size;
		while (!this.isEmpty()) {
			rev_stack.push(this.pop());
		}
		this.bottom = rev_stack.getBottom();
		this.top = rev_stack.getTop();
		this.size = original_size;
	}
	public String toString() {
		String str = "";
		Elem<T> current_el = this.bottom;
		for (int i = 0; i < this.size; i++) {
			str += (current_el.val + ", ");
			current_el = current_el.next;
		}
		return str;
	}
	public void display () {
		if (isEmpty()) {
			System.out.println("...empty...");
		} else {
			System.out.println(this.toString() + " | STACK_BOTTOM");
		}
	}
}

