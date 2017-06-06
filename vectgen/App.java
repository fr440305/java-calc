import java.util.*;

class vectorGenerics {
	private Vector<Integer> vect1;
	private Vector<String>  vect2;
	private Vector<Integer> vect3;
	public vectorGenerics () {
		this.vect1 = new Vector<Integer>();
		this.vect2 = new Vector<String>();
	}
	public void add (Object new_el) {
		System.out.println(new_el);
		if (new_el instanceof Integer) {
			this.vect1.add((Integer)new_el);
		} else if (new_el instanceof String) {
			this.vect2.add((String)new_el);
		} else {
			System.out.println("Err.");
		}
		System.out.println(this.vect1);
		System.out.println(this.vect2);
	}
	public void max () {
	}
	public void min () {
	}
	public void addAll () {
	}
	public void reverse () {
	}
	public void shuffle () {
	}
	public void rotate () {
	}
	public void clear () {
	}
}

class App {
	public static void main (String[] args) {
		vectorGenerics vg = new vectorGenerics();
		vg.add(new Integer(3));
		vg.add(45);
		System.out.println("Asd");
	}
}
