import java.util.Vector;
import java.util.Collections;

class vectorGenerics {
	Vector<Integer> vect1;
	Vector<String>  vect2;
	Vector<Integer> vect3;
	public vectorGenerics () {
		this.vect1 = new Vector<Integer>();
		this.vect2 = new Vector<String>();
		this.vect3 = new Vector<Integer>();
	}
	public void add (Integer new_el) {
		this.vect1.add(new_el);
	}
	public void add (String new_el) {
		this.vect2.add(new_el);
	}
	public void max () {
		if (this.vect1.size() == 0) {
			System.out.println("vect1 is empty.");
		} else {
			System.out.println("max of vect1: " + Collections.max(this.vect1));
		}
		if (this.vect2.size() == 0) {
			System.out.println("vect2 is empty.");
		} else {
			System.out.println("max of vect2: " + Collections.max(this.vect2));
		}
	}
	public void min () {
		if (this.vect1.size() == 0) {
			System.out.println("vect1 is empty.");
		} else {
			System.out.println("min of vect1: " + Collections.min(this.vect1));
		}
		if (this.vect2.size() == 0) {
			System.out.println("vect2 is empty.");
		} else {
			System.out.println("min of vect2: " + Collections.min(this.vect2));
		}
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
		Collections.rotate(this.vect1, 3);
	}
	public void clear () {
		//only clear vect1.
		this.vect1.clear();
	}
	public void print() {
		System.out.println("Vect1: ");
		System.out.println(this.vect1);
		System.out.println("Vect2: ");
		System.out.println(this.vect2);
		System.out.println("Vect3: ");
		System.out.println(this.vect3);
	}
}

class App {
	public static void main (String[] args) {
		vectorGenerics vg = new vectorGenerics();
		// test code goes here:
		// use vg.print to check the result:
		vg.add(1);
		vg.add(45);
		vg.add("asdasd");
		vg.print();
		vg.rotate();
		vg.print();
	}
}
