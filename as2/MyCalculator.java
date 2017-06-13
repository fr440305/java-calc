class Parenthesis {
	char p;
	public Parenthesis (char ch) {
		this.p = ch;
	}
	public char val() {
		return this.p;
	}
	public boolean matchWith (char another) {
		char a = this.p;
		char b = another;
		if (
			(a == '<' && b == '>') ||
			(a == '(' && b == ')') ||
			(a == '[' && b == ']') ||
			(a == '{' && b == '}') ||
			(a == '>' && b == '<') ||
			(a == ')' && b == '(') ||
			(a == ']' && b == '[') ||
			(a == '}' && b == '{')
		) return true; else return false;
	}
}

class Operand {
	public double val;
	public Operand (char ch) {
		this.val = (double)(ch - '0');
	}
	public Operand (double val) {
		this.val = val;
	}
}

class Operator {
	public char o;    // + - * /
	public int index; // the index of this operator in a complete infix expression.
	public int priority; // the priority of the operator itself. add and sub are 1, mul and div are 2.
	public int parenthesis_layer; //NOT NESSESARY.
	public Operator (char c) {
		this.o = c;
	}
	public Operator (char opt, int index, int parenthesis_layer) {
		this.o = opt;
		this.index = index;
		this.parenthesis_layer = parenthesis_layer;
		if (this.o == '+' || this.o == '-') {
			this.priority = 1;
		} else if (this.o == '*' || this.o == '/') {
			this.priority = 2;
		}
	}
	public int compareTo (Operator another) {
		if (this.parenthesis_layer == -1 || this.index == -1) return -1; //cannot compare.
		if (this.parenthesis_layer > another.parenthesis_layer) {
			return 1; //pos num.
		} else if (this.parenthesis_layer < another.parenthesis_layer) {
			return -1;
		} else { //==
			if (this.priority > another.priority) {
				return 1;
			} else if (this.priority < another.priority) {
				return -1;
			} else { //==
				if (this.index > another.index) {
					return 1;
				} else if (this.index < another.index) {
					return -1;
				} else {
					return 0;
				}
			}
		}


	}
	public double calc (Operand val1, Operand val2) {
		double left = val1.val;
		double right = val2.val;
		if (this.o == '+') {
			return left + right;
		} else if (this.o == '-') {
			return left - right;
		} else if (this.o == '*') {
			return left * right;
		} else { // div, assume that right != 0;
			return left / right;
		}
	}
}

public class MyCalculator {
	// no private properties.
	// 
	public boolean isLeftParenthesis (char ch) { //private...4..
		if (ch == '<' || ch == '(' || ch =='[' || ch == '{') {
			return true;
		} else {
			return false;
		}
	}
	public boolean isRightParenthesis (char ch) {
		if (ch == '>' || ch == ')' || ch ==']' || ch == '}') {
			return true;
		} else {
			return false;
		}
	}
	public boolean isDigit (char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		} else {
			return false;
		}
	}
	public boolean isOperator (char ch) {
		if (ch == '+' || ch == '-' || ch =='*' || ch == '/') {
			return true;
		} else {
			return false;
		}
	}
	public boolean isBalanced (String expression) {
		// Notice that an empty expression like "" is also balanced, and it's value is zero.
		// The standard parenthesis matching checking algorithm hinted on the guideline.
		ArrayStack<Parenthesis> pars = new ArrayStack<Parenthesis> (1 << 8);
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (isLeftParenthesis(c)) {
				pars.push(new Parenthesis(c));
			} else if (isRightParenthesis(c)) {
				if (pars.peek().matchWith(c)) {
					pars.pop();
				} else {
					return false;
				}
			}
		}
		return pars.isEmpty();
	}
	//
	// This function remove the dreaasing parenthesis of an infix expression.
	// So, what is a dressing parenthesis?
	// ...
	public String cleanParenthesis (String infix) { //XXX private
		int len; //the length of infix.
		do {
			len = infix.length();
			if (
				( len >= 2 ) &&
				( isLeftParenthesis(infix.charAt(0)) ) &&
				( new Parenthesis(infix.charAt(0)).matchWith(infix.charAt(len-1)) ) &&
				( isBalanced(infix.substring(1, len-1)) )
			) //then:
				infix = infix.substring(1, len-1);
			else
			       	return infix;
		} while (true);
	}
	//
	//assume that infix is balanced, neat, and has at least one operator.
	public int idofRootOperator (String infix) { //XXX private
		Operator[] ops = new Operator[256];
		int ops_size = 0;
		int parenthesis_layer = 0;
		char c;
		// putting all the outside operators into an operator array: ops...
		for (int i = 0; i < infix.length(); i++) {
			c = infix.charAt(i);
			if (isLeftParenthesis(c)) {
				parenthesis_layer ++;
			} else if (isRightParenthesis(c)) {
				parenthesis_layer --;
			}
			if ( parenthesis_layer == 0 && isOperator(c) ) {
				ops[ops_size] = new Operator(c, i, parenthesis_layer);
				ops_size ++;
			}
		}
		// find the 'minium' operator in ops. that is the root operator.
		Operator root = ops[0];
		for (int i = 1; i < ops_size; i++) {
			if (root.compareTo(ops[i]) > 0) root = ops[i];
		}
		return root.index;
	}
	public String infixToPostfix (String infix) {
		if (! isBalanced(infix)) {
			System.out.println("ERR.infixToPostfix : infix is not balanced.");
			return ""; //err
		}
		infix = cleanParenthesis(infix);
		if (infix == "") return "0";
		if (infix.length() == 1) return infix;
		int root_op = idofRootOperator(infix);
		String infix_left = infix.substring(0, root_op);
		String infix_right = infix.substring(root_op + 1, infix.length());
		return infixToPostfix(infix_left) + " " + infixToPostfix(infix_right) + " " + infix.charAt(root_op);
	}
	public double evaluate (String postfix) {
		ArrayStack<Operand> operand_stack = new ArrayStack<Operand>(1 << 8);
		char c;
		Operand left_operand;
		Operand right_operand;
		for (int i = 0; i < postfix.length(); i++) {
			c = postfix.charAt(i);
			if (c != ' ') {
				if (isDigit(c)) {
					operand_stack.push(new Operand(c));
				} else { // Operator
					//pop:
					right_operand = operand_stack.pop();
					left_operand = operand_stack.pop();
					operand_stack.push(new Operand(
						(new Operator(c)).calc(left_operand, right_operand)
					));
				}
			}
		}
		return operand_stack.peek().val;
	}
}
