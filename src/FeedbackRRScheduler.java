import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {
   private Queue<Process> rQueue1;
   private Queue<Process> rQueue2;
   private Queue<Process> rQueue3;
   
   private int timeQuantumBase;
   private int timeQuantum;
   
   public void initialize(Properties parameters) {
      timeQuantumBase = Integer.parseInt(parameters.getProperty("timeQuantum"));
      timeQuantum = timeQuantumBase;
      System.out.println(timeQuantum);
    }
   public FeedbackRRScheduler(){
       rQueue1 = new LinkedList<Process>();
       rQueue2 = new LinkedList<Process>();
       rQueue3 = new LinkedList<Process>();
   }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    if(process.state == process.state.CREATED){
        rQueue1.add(process);
        process.setPriority(1);
    }else{
    switch(process.priority){
        case 1:
            if(usedFullTimeQuantum){
                //System.out.println(process.id + " <------- Used full time, moving down");
                rQueue2.add(process);
                process.setPriority(2);
            }else{
                rQueue1.add(process);
            }
            break;
        case 2:
            if(usedFullTimeQuantum){
                rQueue3.add(process);
                process.setPriority(3);
            }else{
                rQueue2.add(process);
            }
            break;
        case 3:
            rQueue3.add(process);
            break;
        default:
            break;
    }    

  }
  }

  public void displayqueues(Process pr){
      System.out.println("<><><><><><><><><><><><><><><>");
      System.out.print("Q1 :  ");
      for (Process p : rQueue1){
        System.out.print("- " + p.id + " -");
      }
      System.out.println("");
      System.out.print("Q2 :  ");
      for (Process p : rQueue2){
        System.out.print("- " + p.id + " -");
      }
      System.out.println("");
      System.out.print("Q3 :  ");
      for (Process p : rQueue3){
        System.out.print("- " + p.id + " -");        
      }
      System.out.println("");
      if(pr != null){
        System.out.println("SCHEDULE :  " + pr.id);
      }else{
        System.out.println("SCHEDULE :  NOTHING ");  
      }
  }
  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    
    if(rQueue1.isEmpty()){
        if(rQueue2.isEmpty()){
            // do queue 3
            timeQuantum = timeQuantumBase * 3; 
            displayqueues(rQueue3.peek());
            return rQueue3.poll();
            // queue 3 done
        }else{
            // do queue 2
            timeQuantum = timeQuantumBase * 2;
            displayqueues(rQueue2.peek());
            return rQueue2.poll();
            // queue 2 done
        }
    }else{
        //do queue 1
        timeQuantum = timeQuantumBase;
        displayqueues(rQueue1.peek());
        return rQueue1.poll();
        // queue 1 done
    }
    
  }
  
    public int getTimeQuantum() {
    
    return timeQuantum;
  }
   // public boolean isPreemptive() {
        
   // return true;
  //}
}
