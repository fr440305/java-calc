import java.util.Random;
public class Simulation{
  private Queue scheduler;
  private static final int DEFAULT_TIME = 100;
  private static final int NUMBER_OF_JOBS = 10;
  private static final int PRIORITY_LEVELS = 3;
  private static final int MAX_DURATION = 1000;

  public Simulation(Queue scheduler){
    this.scheduler = scheduler;
  }

  public void run(){
    if (scheduler == null){
      return;
    }
    Random generator = new Random();
    System.out.println("Creating jobs...");
    for (int i = 0; i < NUMBER_OF_JOBS; i ++){
      Job temp = new Job(generator.nextInt(PRIORITY_LEVELS),generator.nextInt(MAX_DURATION));
      scheduler.enqueue(temp);
      System.out.println(temp);
    }
    System.out.println();
    System.out.println("Running jobs...");
    int time = DEFAULT_TIME;
    while (!scheduler.isEmpty()){
      Job active = scheduler.dequeue();                        
      System.out.println("Running Job " + active.getId() + " for " + time + " milliseconds..");
      if (active.getDuration() >= time){
        active.run(time);
        time = DEFAULT_TIME;
      } else {
        time = DEFAULT_TIME + time - active.getDuration();
        active.run(active.getDuration()); 
      }
      System.out.print("..." + active);
      if (active.getDuration() > 0){
        scheduler.enqueue(active);
        System.out.println();
      } else {
        System.out.println(" COMPLETE!");
      }
    }
  }
  
  public static void main(String[] args){
    Queue jobs = new PriorityQueue(); // Replace null with the queue you want to test
    Simulation simulator = new Simulation(jobs);
    simulator.run();
  }
}
