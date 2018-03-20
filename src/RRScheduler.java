import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
   private Queue<Process> readyQ;
   private int timeQuantum;
   
   public void initialize(Properties parameters) {
      
       
       timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
       System.out.println("time Q = " + timeQuantum);
    }
   
   public RRScheduler(){
       readyQ = new LinkedList<Process>();
   }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    if(usedFullTimeQuantum){
     //   System.out.println("used full time...");
    }else{
        //System.out.println("didnt use full time...");
    }
    readyQ.add(process);
    
      System.out.println("----------------ADD PROCESS " + process.id + "--------------");
        for (Process p : readyQ){
            System.out.print("~ " + p.id + " ~");
        }
        System.out.println("");
        System.out.println("--------------------------------------------");
  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
        //System.out.println("Scheduler selects process "+ readyQ.peek());
        if(readyQ.peek() != null){
        System.out.println("---------------SCHEDULE PROCESS " + readyQ.peek().id + "--------------");
        for (Process p : readyQ){
            System.out.print("~ " + p.id + " ~");
        }
        System.out.println("");
        System.out.println("----------------------------------------------");
        }
        return readyQ.poll();
        
    
  }
   public int getTimeQuantum() {
    
    return timeQuantum;
  }
   
   public boolean isPreemptive() {
    return false;
  }
}
