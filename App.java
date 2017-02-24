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
				new_node = new ExpNode((int)ptr, op_depth);
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
	private int depth; // appeard in how many levels of bracket-pairs?
	private int priority;
	/** 
	 * Use this constructor when you wanna construct a
	 * operator-type ExpNode.
	 */
	ExpNode (int op, int depth) {
		this.type = 'o';
		this.literal_val = op;
		this.depth = depth;
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
	 *
	 */
	public int GetLitVal () {
		return this.literal_val;
	}
	/**
	 *
	 */
	public int GetType () {
		return this.type;
	}
	/**
	 *
	 */
	public int GetDepth () {
		return this.depth;
	}
	/**
	 *
	 */
	public int GetPriority () {
		return this.priority;
	}
	public void SetPriority (int priority) {
		this.priority = priority;
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
	private int max_priority;
	/**
	 * TODO
	 */
	private void setPriority () {
		boolean add_sub = false, mul_div = false;
		int depth = 0;
		int magnitude = 0;
		ExpNode ptr_node;
		do {
			add_sub = false;
			mul_div = false;
			//search for add_sub;
			for (int i = 0; i < this.exp_list.length; i++) {
				ptr_node = this.exp_list[i];
				if (ptr_node.GetType() == 'o' && ptr_node.GetDepth() == depth) {
					if (ptr_node.GetLitVal() == (int)'+' || ptr_node.GetLitVal() == (int)'-') {
						add_sub = true;
						ptr_node.SetPriority(magnitude);
						magnitude++;
					}
				}
			}
			//mul_div
			for (int i = 0; i < this.exp_list.length; i++) {
				ptr_node = this.exp_list[i];
				if (ptr_node.GetType() == 'o' && ptr_node.GetDepth() == depth) {
					if (ptr_node.GetLitVal() == (int)'*' || ptr_node.GetLitVal() == (int)'/') {
						mul_div = true;
						ptr_node.SetPriority(magnitude);
						magnitude++;
					}
				}
			}
			depth++;
		} while (add_sub == true || mul_div == true);
		this.max_priority = magnitude - 1;
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
		this.setPriority();
	}
	public void Print() {
		System.out.println("max-priority = " + this.max_priority);
		for (int i = 0; i < this.exp_list.length; i++) {
			this.exp_list[i].Print();
		}
	}
	/**
	 * a recurision func.
	 */
	private ExpNode conNode (int priority) {
		ExpNode prio_op = null;
		ExpNode op_lc = null;
		ExpNode op_rc = null;
		int prio_op_index = 0;
		// seek for prio_op:
		for (int i = 0; i < this.exp_list.length; i++) {
			if (this.exp_list[i].GetPriority() == priority) {
				prio_op = this.exp_list[i];
				prio_op_index = i;
			}
		}
		for (int i = prio_op_index - 1; i >= 0; i--) {
			if (!this.exp_list[i].Has("parent")) {
				op_lc = this.exp_list[i];
			}
		}
		for (int i = prio_op_index + 1; i < this.exp_list.length; i++) {
			if (!this.exp_list[i].Has("parent")) {
				op_rc = this.exp_list[i];
			}
		}
		prio_op.SetBranches(op_lc, op_rc, null);
		op_lc.SetBranches(null, null, prio_op);
		op_rc.SetBranches(null, null, prio_op);
		//System.out.println((char)prio_op.GetLitVal() + ", " + (char));
		if (priority != 0) {
			this.conNode(priority - 1);
		}
		return prio_op;
	}
	public ExpTree ToTree() {
		//enode_arr = this.exp_list.toArray(enode_arr);
		// now enode_arr is ExpNode[] equivalent to this.exp_list; -?-?
		return new ExpTree(this.conNode(this.max_priority));
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
			ori_str = "(1+(5*6)+2)*3";
		}
		ExpString S = new ExpString(ori_str);
		S.Print();
		System.out.println("---");
		ExpList L = S.ToList();
		L.Print();
		L.ToTree();
	}
}
