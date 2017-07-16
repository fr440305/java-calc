public class PriorityQueue implements Queue{
	/**
	 * The top element (first out) of this
	 * heap has the greatest priority, hence it is a max-root heap.
	 * For this heap:
	 *   - Capacity - this.heap.length
	 *   - Length   - this.tail
	 */
	private Job[] heap;
	private int tail; // Size of Queue

	public PriorityQueue(){
		heap = new Job[10];
		tail = 0;
	}

	public boolean isEmpty(){
		return (tail == 0);
	}
	
	public boolean isFull () {
		return (tail == this.heap.length);
	}

	public void enqueue(Job element){
		// push to the tail...
		// assume that the capacity is enough. TODO boundary check.
		if (!isEmpty() && isFull()) {
			resize();
		}
		tail ++;
		this.heap[tail - 1] = element;
		// and, reverseHeapify...
		reverseHeapify(tail - 1);
	}
	
	public Job dequeue(){
		//print
		System.out.println("<<<<<<<<<<<" + this.tail);
		for (int i = 0; i < this.tail; i++) {
			System.out.println(heap[i]);
		}
		System.out.println(">>>>>>>>>>>");
		Job current_job;
		swap(0, tail - 1);
		current_job = this.heap[tail - 1];
		this.heap[tail - 1] = null;
		tail --;
		heapify(0);
		return current_job;
	}
	
	/**
	 * This method deletes all the jobs in the heap.
	 */
	public void clear(){
		heap = new Job[10];
		tail = 0;
	}
	
	/**
	 * This method double the capacity of the heap. If the original capacity
	 * of the heap is N, then the new capacity will be 2*N.
	 */
	private void resize(){
		Job[] old_job_heap = this.heap;
		this.heap = new Job[2 * old_job_heap.length];
		for (int i = 0; i < old_job_heap.length; i++) {
			this.heap[i] = old_job_heap[i];
		}
	}
	
	private void reverseHeapify(int i){
		int p = parent(i);
		// compare heap[i] and heap[parent(i)].
		if (p >= 0 && heap[p].getOwner() < heap[i].getOwner()) {
			swap(i, p);
			reverseHeapify(p);
		}
	}
	
	/**
	 * Given an array index i, this method returns the array index of the i's
	 * parent node in the heap.  If heap[i] is the root node
	 * (which means i = 0), then this method will return -1.
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
