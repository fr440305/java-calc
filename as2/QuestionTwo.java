public class QuestionTwo {
	public static void main (String[] args) {
		System.out.println("q2 <<<<<<<");
		MyCalculator mc = new MyCalculator();
		//System.out.println(mc.isBalanced("()()()()(()"));
		//System.out.println(mc.cleanParenthesis("<<(1+2*4+(1+9))>>"));
		System.out.println("(2-3*4)+1");
		System.out.println(mc.idofRootOperator("(2-3*4)+1"));
		System.out.println(mc.infixToPostfix("(2-3*4)+1"));
		System.out.println("_________________________________");
		System.out.println( mc.evaluate(mc.infixToPostfix("(2-3*4)+1")) );
	}
}
