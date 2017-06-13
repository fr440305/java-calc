public class QuestionOne {
	public static void main (String[] args) {
		ArrayStack<Integer> int_arrstack = new ArrayStack<Integer>(20);
		int_arrstack.display();
		int_arrstack.push(new Integer(10));
		int_arrstack.push(new Integer(20));
		int_arrstack.push(new Integer(30));
		int_arrstack.display();
		int_arrstack.push(new Integer(40));
		int_arrstack.push(new Integer(50));
		int_arrstack.display();
		int_arrstack.reverse();
		int_arrstack.display();
		System.out.println("______LIST_STACK________");
		ListStack<Integer> int_lstack = new ListStack<Integer>(20);
		int_lstack.display();
		int_lstack.push(10);
		int_lstack.push(20);
		int_lstack.push(30);
		int_lstack.push(40);
		int_lstack.push(50);
		int_lstack.display();
		int_lstack.reverse();
		int_lstack.display();
	}
}
