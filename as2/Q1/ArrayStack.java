// ArrayStack.java
// The implementation of interface::Stack<T> by using array..
// Dev By __HUO_YU__

public class ArrayStack<T> implements Stack<T> {
	int cap;
	int size;
	Object[] arr;
	public ArrayStack (int cap) {
		this.cap = cap;
		this.size = 0;
		//unsafe: use arraylist to substitude.
		this.arr = new Object[cap];
	}
	public boolean isEmpty () {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isFull () {
		if(this.size == this.cap){
			return true;
		} else {
			return false;
		}
	}
	public T peek () {
		if (this.size != 0) {
			return (T)this.arr[this.size - 1];
		} else {
			return null;
		}
	}
	public T pop () {
		if (this.size != 0) {
			this.size --;
			return (T)this.arr[this.size];
		} 
		return null;
	}
	public void push (T new_el) {
		if (isFull()) {
			//Ok, I will do nothing...
			return;
		}
		this.arr[this.size] = (Object)new_el;
		this.size ++;
	}
	public void clear () {
		this.size = 0;
	}
	public int size () {
		return this.size;
	}
	public void reverse () {
		Object temper;
		for (int i = 0; i < this.size / 2; i++) {
			// swap arr[i] and arr[size - i];
			temper = this.arr[i];
			this.arr[i] = this.arr[this.size - i - 1];
			this.arr[this.size - i - 1] = temper;
		}
	}
	public String toString () {
		String str = "";
		for (int i = 0; i < this.size; i++) {
			str += (this.arr[i]/*.toString()*/ + ", ");
		}
		return str;
	}
	public void display () {
		if (isEmpty()) {
			System.out.println("..empty..");
		} else {
			System.out.println(this.toString());
		}
	}
}
