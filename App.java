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
				System.out.print("[ 0, ");
			} else if (a == -1) {
				System.out.print("-1 ]");
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
		/* TODO */
		ArrayList<ExpNode> result = new ArrayList<ExpNode> ();
		ArrayList<Integer> dig_stack = new ArrayList<Integer> ();
		ExpNode new_node = null;
		int op_depth = 0;
		for (int i = 0; i < this.exp_string.length; i++) {
			char ptr = (char)this.exp_string[i];
			//System.out.print(op_depth + ", ");
			if (ptr == '(') {
				op_depth ++;
			} else if (ptr == ')') {
				op_depth --;
			} else if (ptr == '+' || ptr == '-' || ptr == '*' || ptr == '/') {
				//operators
				/* TODO - modified the priority here !!!*/
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

/* This class needs to be refactored later because it is not elegant enough. */
class ExpNode { 
	private ExpNode lc, rc, parent;
	private int literal_val;
	private float express_val;
	private char type; // 'n' for number, and 'o' for operator.
	private int priority;
	/** 
	 * Use this constructor when you wanna construct a
	 * operator-type ExpNode.
	 */
	ExpNode (int op, int priority) {
		this.type = 'o';
		this.literal_val = op;
		this.priority = priority;
	}
	/** 
	 * Use this constructor when you wanna construct a
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
	/** 
	 * Has() can be used to check if the current node has left child,
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
			System.out.println( "{" + this.type + ", " + this.priority + "}");
		}
		//System.out.println("{" + (char)this.literal_val +", "+ this.priority[0] + "}");
	}
}

class ExpList {
	//private ArrayList<ExpNode> exp_list;
	private ExpNode[] exp_list;
	/**
	 * TODO
	 */
	private void prio () {
		boolean add_sub, mul_div;
		int depth = 0;
		int magnitude = 0;
		do {
			//search for mul_div;
			for (int i = 0; i < this.exp_list.length; i++) {
				if (this.exp_list[i].GetPrio() != null) {
					this.
				}
			}
			//search for add_sub;
			for (int i = 0; i < this.exp_list.length; i++) {
			}
			depth++;
		} while (add_sub == 0 && mul_div == 0);
	}
	ExpList (ArrayList<ExpNode> list) {
		//System.out.println("ExpList::ExpList");
		//this.exp_list = list;
		this.exp_list = new ExpNode[list.size()];
		Iterator<ExpNode> I = list.iterator();
		int i = 0;
		while (I.hasNext()){
			this.exp_list[i] = I.next();
			i++;
		}
		//this.prio();
	}
	public void Print() {
		for (int i = 0; i < this.exp_list.length; i++) {
			this.exp_list[i].Print();
		}
	}
	public ExpTree ToTree() {
		//enode_arr = this.exp_list.toArray(enode_arr);
		// now enode_arr is ExpNode[] equivalent to this.exp_list; -?-?
		/* TODO */	
		return null;
	}
}

class ExpTree {
	private ExpNode root;
	ExpTree (ExpNode root) {
		this.root = root;
	}
}

public class App {
	public static void main (String[] $) {
		String ori_str;
		if ($.length != 0) {
			ori_str = $[0];
		} else {
			ori_str = "(1+2)*3";
		}
		ExpString S = new ExpString(ori_str);
		S.Print();
		System.out.println("---");
		ExpList L = S.ToList();
		L.Print();
	}
}
