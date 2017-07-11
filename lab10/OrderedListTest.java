public class OrderedListTest {
	public static void main (String[] args) {
		OrderedList ol = new OrderedList(20);
		System.out.println(ol.size());
		ol.add(new Integer(1));
		ol.add(new Integer(2));
		ol.add(new Integer(3));
		ol.add(new Integer(4));
		System.out.println(ol.size());
		System.out.println(ol.get(2));
		ol.remove(3);
		System.out.println("list::");
		ol.print();
	}
}
