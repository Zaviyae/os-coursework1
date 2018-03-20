import java.util.LinkedList;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO
   private LinkedList<Process> readyQ;
   private int timeQuantum;
   
   public void initialize(Properties parameters) {
      System.out.println("INITIALISED");
    }
   
   public SJFScheduler(){
       readyQ = new LinkedList<Process>();
   }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {

    // TODO

    return null;
  }
}
