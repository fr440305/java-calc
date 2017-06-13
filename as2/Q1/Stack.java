// Stack.java
// Dev By __HUO_YU__

public interface Stack <T> {
	// This function returns true
	// when the stack is empty,
	// else, it returns false.
	boolean isEmpty ();

	// This function returns true
	// when the number of elements
	// equals to the capacity.
	// else, it returns false.
	boolean isFull ();

	// This function returns the top
	// element in the stack.
	// If this stack is empty, it does
	// nothing and returns null.
	T peek();

	// This function removes the top
	// element from the stack and returns
	// this element.
	// If this stack is empty, it does nothing
	// and return null.
	T pop ();

	// This function can place a new
	// item into the stack.
	void push(T new_el);

	// This function removes all the elements
	// from the stack.
	void clear();

	// This function returns the number of
	// elements in the stack.
	int size();

	// This function reverse the elements.
	// e.g: Here is a stack:
	// STACK | 1 2 3 4 5 
	// and reverse:
	// STACK | 5 4 3 2 1
	void reverse();

	// This function displays all the elements.
	void display();
}
