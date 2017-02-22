import java.util.*;

class ExpString {
	private byte[] exp_string;
	ExpString (String str) {
		this.exp_string = new byte[str.length() + 2];
		this.exp_string[0] = 0; // 0 for start.
		this.exp_string[str.length() + 1] = -1; // -1 for end.
		System.arraycopy(str.getBytes(), 0, this.exp_string, 1, str.length());
	}
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
				new_node.Print();
				result.add(new_node);
			} else if (ptr >= '0' && ptr <= '9') {
				//digits
				new_node = new ExpNode((int)ptr);
				new_node.Print();
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
	ExpNode (int op, int depth, int order) {
		this.type = 'o';
		this.literal_val = op;
		this.priority = new int[3];
		this.priority[0] = depth;
		this.priority[2] = order;
	}
	ExpNode (int number) {
		this.type = 'n';
		this.literal_val = number;
	}
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
}

class ExpTree {
}

public class App {
	public static void main (String[] $) {
		ExpString S = new ExpString("(1+2)*3");
		S.Print();
		System.out.println("---\n");
		S.ToList().Print();

	}
}
