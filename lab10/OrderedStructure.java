/**
 * The interface OrderedStructure.
 *
 * @author Marcel Turcotte
 */

public interface OrderedStructure {

    /** 
     * Returns the number of elements currently stored in this OrderedStructure.
     *
     * @return the number of elements of this strucutre.
     */

    public abstract int size();

    /** 
     * Adds an element in increasing order according to the class'
     * natural comparison method (i.e. uses the method compareTo).
     *
     * @return true if the element can be successfully added to and
     * false otherwise.
     * @throws IllegalArgumentException if obj is null.
     */

    public abstract boolean add( Comparable obj ) throws IllegalArgumentException;

    /** 
     * Returns the element at the specified position; the first
     * element has the index 0.
     *
     * @return the element at the specified position.
     * @throws IndexOutOfBoundsException if pos is out of range (pos < 0 || pos >= size()). 
     */

    public abstract Object get( int pos ) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified position; the first
     * element has the index 0.
     *
     * @throws IndexOutOfBoundsException if pos is out of range (pos < 0 || pos >= size()). 
     */

    public abstract void remove( int pos ) throws IndexOutOfBoundsException;

}
