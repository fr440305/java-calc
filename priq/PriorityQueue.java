public class PriorityQueue implements Queue{
  private Job[] heap;
  private int tail; // Size of Queue
  
  public PriorityQueue(){
    heap = new Job[10];
    tail = 0;
  }
  
  public boolean isEmpty(){
    // TODO Implement this method
    return (tail == 0);
  }
  
  public void enqueue(Job element){
    throw new UnsupportedOperationException( "not implemented yet!" );
    // TODO Implement this method
  }
  
  public Job dequeue(){
    // TODO Implement this method
    throw new UnsupportedOperationException( "not implemented yet!" );
    //return null; // Dummy return value; please remove
  }
  
  public void clear(){
    // TODO Implement this method
    throw new UnsupportedOperationException( "not implemented yet!" );
  }
  
  private void resize(){
    // TODO Implement this method
    throw new UnsupportedOperationException( "not implemented yet!" );
  }
  
  private void reverseHeapify(int i){
    // TODO Implement this method
    throw new UnsupportedOperationException( "not implemented yet!" );
  }
  
        /**
         * Given an array index i, this method returns the array index of the i's
         * parent node in the heap.
         * 
         * @param i The index of the node for whom's parent we are searching.
         * @return The index in the array of i's parent.
         */
  private int parent(int i){
    return (int) Math.floor((i - 1) / 2.0f);
  }
  
        /**
         * Given an array index i, this method returns the array index of the i's
         * left child node in the heap.
         * 
         * @param i The index of the node for whom's left child we are searching.
         * @return The index in the array of i's left child.
         */
  private int left(int i){
    return 2 * i + 1;
  }
  
        /**
         * Given an array index i, this method returns the array index of the i's
         * right child node in the heap.
         * 
         * @param i The index of the node for whom's right child we are searching.
         * @return The index in the array of i's right child.
         */
  private int right(int i){
    return 2 * i + 2;
  }
  
        /**
         * Swaps the Job's at position i and j.
         * 
         * @param i the array index of one of the job's to be swapped.
         * @param j the array index of the other job to be swapped.
         */
  private void swap(int i, int j){
    Job temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }
  
        /**
         * Heapify works by comparing the current internal node with its children 
         * to see which is the largest. If the root is not the largest then it is 
         * swapped with the largest of its children. Since the child has now been 
         * altered it needs to check to make sure it still satisfies the heap property. 
         * Heapify works its way down the tree in this manner and, when it is complete, 
         * the tree should again be a heap. The details of how Heapify works are a 
         * little complex but the method is provided for you, you just need to 
         * understand when to use it.
         * 
         * The purpose of heapify is to restore the heap property after one of the 
         * internal nodes of the heap has been altered. In the case of a priority queue,
         * this internal nodes get altered during dequeue operations.
         * 
         * @param i The index of the node that we are not certain if it satisfied the
         * heap property (one or both of its children might be larger).
         */
  private void heapify(int i){
    int l = left(i);
    int r = right(i);
    int largest = i;
    if (l < tail && heap[l].getOwner() > heap[largest].getOwner()){
      largest = l;
    }
    if (r < tail && heap[r].getOwner() > heap[largest].getOwner()){
      largest = r;
    }
    if (largest != i){
      swap(i, largest);
      heapify(largest);
    }
  }        
}