public class Job{
  public static final int APPLICATION = 0;
  public static final int USER = 1;
  public static final int SYSTEM  = 2;
  private int owner;
  private int duration;
  private int id;
  private static int currentId = 0;
  
  public Job(int owner, int duration){
    this.owner = owner;
    this.duration = duration;
    this.id = currentId ++;
  }
  
  public int getId(){
    return this.id;
  }
  
  public int getOwner(){
    return this.owner;
  }

  public int getDuration(){
    return this.duration;
  }
  
  public void run(int time){
    this.duration -= time;
    if (this.duration < 0){
      this.duration = 0;
    }
    try{
      if (time > duration){
        Thread.sleep(time);
      } else {
        Thread.sleep(duration);
      }
    }
    catch (Exception e){
      // Do nothing
    }
  }

  public boolean equals(Job other){
    return this.getId() == other.getId();
  }

  public String toString(){
    StringBuffer result = new StringBuffer();
    result.append("Job ");
    result.append(getId());
    result.append(" owned by ");
    switch(getOwner()){
      case SYSTEM: result.append("the system "); break;
      case USER: result.append("the user "); break;
      case APPLICATION: result.append("an application "); break;
      default: result.append(" ERROR "); break;
    }
    result.append("has ");
    result.append(duration);
    result.append(" microseconds remaining;");
    return result.toString();
  }
}
