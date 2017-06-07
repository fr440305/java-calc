import java.util.Vector;
import java.util.Collections;

class vectorGenerics {
	private Vector<Integer> vect1;
	private Vector<String>  vect2;
	private Vector<Integer> vect3;
	public vectorGenerics () {
		this.vect1 = new Vector<Integer>();
		this.vect2 = new Vector<String>();
	}
	public void add (Integer new_el) {
		this.vect1.add(new_el);
	}
	public void add (String new_el) {
		this.vect2.add(new_el);
	}
	public void max () {
		System.out.println("max of vect1: " + Collections.max(this.vect1));
		System.out.println("max of vect2: " + Collections.max(this.vect2));
	}
	public void min () {
		System.out.println("max of vect1: " + Collections.min(this.vect1));
		System.out.println("max of vect2: " + Collections.min(this.vect2));
	}
	public void addAll (Vector<Integer> vect3) {
		this.vect3 = vect3;
		this.vect1.addAll(this.vect3);
	}
	public void reverse () {
		Collections.reverse(this.vect3);
	}
	public void shuffle () {
		Collections.shuffle(this.vect1);
	}
	public void rotate () {
		// ???
	}
	public void clear () {
		this.vect1.clear();
	}
}

class App {
	public static void main (String[] args) {
		vectorGenerics vg = new vectorGenerics();
		vg.add(45);
		vg.add(3);
		vg.add("hello world");
		vg.max();
		System.out.println("Asd");
	}
}
