public interface Queue{ // The interface of this queue
  public abstract boolean isEmpty ();
  public abstract void enqueue ( Job obj );
  public abstract Job dequeue ();
  public abstract void clear ();
}