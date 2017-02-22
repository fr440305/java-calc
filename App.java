import java.util.*;

class ExpString {
	private byte[] exp_string;
	ExpString (String str) {
		this.exp_string = new byte[str.length() + 2];
		this.exp_string[0] = 0; // 0 for start.
		this.exp_string[str.length() + 1] = -1; // -1 for end.
		System.arraycopy(str.getBytes(), 0, this.exp_string, 1, str.length());
	}
	/**
	 * Print() is just a testing method.
	 */
	public void Print() {
		for (byte a : this.exp_string) {
			if (a == 0) {
				System.out.print("00H [");
			} else if (a == -1) {
				System.out.print("] FFH");
			} else {
				System.out.print((char)a + ", ");
			}
		}
	}
	/**
	 * ToList() is the main method of class ExpString. It can
	 * return a ExpList type Object of this.exp_string.
	 */
	public ExpList ToList() {
		ArrayList<ExpNode> result = new ArrayList<ExpNode> ();
		ArrayList<Integer> dig_stack = new ArrayList<Integer> ();
		ExpNode new_node = null;
		int op_depth = 0;
		int op_order;
		for (int i = 0; i < this.exp_string.length; i++) {
			char ptr = (char)this.exp_string[i];
			//System.out.print(op_depth + ", ");
			if (ptr == '(') {
				op_depth ++;
			} else if (ptr == ')') {
				op_depth --;
			} else if (ptr == '+' || ptr == '-' || ptr == '*' || ptr == '/') {
				//operators
				new_node = new ExpNode((int)ptr, op_depth, i);
				//new_node.Print();
				result.add(new_node);
			} else if (ptr >= '0' && ptr <= '9') {
				new_node = new ExpNode((int)ptr);
				//new_node.Print();
				result.add(new_node);
			} else if (ptr == (char)(-1)){
				// Do Nothing
				break;
			}
		}
		return new ExpList(result);
	}
}

class ExpNode {
	private ExpNode lc, rc, parent;
	private int literal_val;
	private float express_val;
	private char type; // 'n' for number, and 'o' for operator.
	private int[] priority; //{depth, meta_priority, order}
	/** Use this constructor when you wanna construct a
	 * operator-type ExpNode.
	 */
	ExpNode (int op, int depth, int order) {
		this.type = 'o';
		this.literal_val = op;
		this.priority = new int[3];
		this.priority[0] = depth;
		this.priority[2] = order;
	}
	/** Use this constructor when you wanna construct a
	 *  numeric-type ExpNode.
	 */
	ExpNode (int number) {
		this.type = 'n';
		this.literal_val = number;
	}
	/**
	 * You can use SetBranches() to set the left child, right child, and 
	 * parent node of the current node.
	 */
	public void SetBranches (ExpNode lc, ExpNode rc, ExpNode parent) {
		this.lc = lc;
		this.rc = rc;
		this.parent = parent;
	}
	/** Has() can be used to check if the current node has left child,
	 * right child, or parent node.
	 */
	public boolean Has(String has_what) {
		if (has_what == "parent") {
			if (this.parent != null) {return true;} else {return false;}
		} else if (has_what == "lc") {
			if (this.lc != null) {return true;} else {return false;}
		} else {
			//rc
			if (this.rc != null) {return true;} else {return false;}
		}
	}
	/**
	 * Just a testing method.
	 */
	public void Print() {
		//System.out.println("ExpNode - Print");
		String prio;
		if (this.type == 'n') {
			System.out.println("{"+this.type+", "+(char)this.literal_val+"}");
		} else if (this.type == 'o') {
			System.out.println("{"+this.type+", "+this.priority[0]+(char)this.literal_val+this.priority[2]+"}");
		}
		//System.out.println("{" + (char)this.literal_val +", "+ this.priority[0] + "}");
	}
}

class ExpList {
	private ArrayList<ExpNode> exp_list;
	ExpList (ArrayList<ExpNode> list) {
		System.out.println("ExpList::ExpList");
		this.exp_list = list;
	}
	public void Print() {
		Iterator<ExpNode> I = this.exp_list.iterator();
		while (I.hasNext()) {
			I.next().Print();
		}
	}
	public ExpTree ToTree() {
		//enode_arr = this.exp_list.toArray(enode_arr);
		ExpNode[] enode_arr = new ExpNode[this.exp_list.size()];
		Iterator<ExpNode> I = this.exp_list.iterator();
		int i = 0;
		while (I.hasNext()){
			enode_arr[i] = I.next();
			i++;
		}
		// now enode_arr is ExpNode[] equivalent to this.exp_list; -?-?
		/* TODO */	
		return null;
	}
}

class ExpTree {
	private ExpNode root;
}

public class App {
	public static void main (String[] $) {
		ExpString S = new ExpString("(1+2)*3");
		S.Print();
		System.out.println("---");
		S.ToList().ToTree();

	}
}
