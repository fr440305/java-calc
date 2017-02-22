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
		int op_depth;
		int op_order;
		for (int i = 0; i < this.exp_string.length; i++) {
						/* TODO */
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
}

class ExpList {
	private ArrayList<ExpNode> exp_list;
	ExpList (ArrayList<ExpNode> list) {
		this.exp_list = list;
	}
}

class ExpTree {
}

public class App {
	public static void main (String[] $) {
		ExpString S = new ExpString("13");
		S.Print();
	}
}
