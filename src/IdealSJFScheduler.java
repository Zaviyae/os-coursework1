import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  // TODO

   private LinkedList<Process> readyQ;
   private int timeQuantum;
   
   public void initialize(Properties parameters) {
      
    }
   
   public IdealSJFScheduler(){
       readyQ = new LinkedList<Process>();
   }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
  
    insertnSort(process);
    //readyQ.add(process);
    
      System.out.println("----------------ADD PROCESS " + process.id + "--------------");
        for (Process p : readyQ){
            System.out.print("~ " + p.id + " ~");
        }
        System.out.println("");
        System.out.println("--------------------------------------------");
  }

  public void insertnSort(Process process){
      readyQ.add(process);
      System.out.println("-----------UNSORTED LIST-------------");
      for(Process p : readyQ){
          
          System.out.println("ID : " + p.id + " BURST : " + p.getNextBurst());
      }
      System.out.println("-------------------------------------");
      boolean swapped = true;
      if(!readyQ.isEmpty()){
        while(swapped){
         swapped = false;
         for(Process p : readyQ){
             if(readyQ.indexOf(p)+ 1 < readyQ.size()){
                if(readyQ.get(readyQ.indexOf(p)).getNextBurst() > readyQ.get(readyQ.indexOf(p) + 1).getNextBurst()){
                    Process temp = readyQ.get(readyQ.indexOf(p)+1);
                    readyQ.remove(readyQ.indexOf(p)+1);
                    readyQ.add(readyQ.indexOf(p), temp);
                    swapped = true;
                    break;
                 
             }
         }
         }
         
      }
      System.out.println("-------------SORTED LIST-------------");
      for(Process p : readyQ){
          
          System.out.println("ID : " + p.id + " BURST : " + p.getNextBurst());
      }
      System.out.println("-------------------------------------");
  }
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
    
    return -1;
    
  }
   
   public boolean isPreemptive() {
    return false;
  }
}
