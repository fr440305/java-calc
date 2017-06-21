//...
//
public class QuestionTwo {
	public static void main (String[] args) {
		MyCalculator mc = new MyCalculator();
		System.out.println( mc.evaluate(mc.infixToPostfix("(2-3*4)+1")) );
	}
}
