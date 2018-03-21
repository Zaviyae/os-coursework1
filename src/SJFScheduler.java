import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO
   private LinkedList<Process> readyQ;
   private float alphaBurst;
   private float initialBurst;
   private Map<Integer ,Integer> previousEA;
   private Map<Integer , Integer> ExpAvgs;
   
   public void initialize(Properties parameters) {
       alphaBurst = Float.parseFloat(parameters.getProperty("alphaBurstEstimate"));
       initialBurst = Float.parseFloat(parameters.getProperty("initialBurstEstimate"));
       previousEA = new HashMap<Integer, Integer>();
       ExpAvgs = new HashMap<Integer, Integer>();
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
     //System.out.println(process.state.name());
     System.out.println("(" + process.getRecentBurst()+ " * " + alphaBurst + ")" + " + " + "((1 - " + alphaBurst + ") * " + previousEA.getOrDefault(process.id, (int)initialBurst) + " = " + exponentialAverage(process));
      
     insertnSort(process);

  }

  public int exponentialAverage(Process process){
         int expavg;
         
         expavg = (int)((process.getRecentBurst()* alphaBurst) + ((1 - alphaBurst) * previousEA.getOrDefault(process.id, (int)initialBurst)));
         previousEA.put(process.id, expavg);
         
         return expavg;
  }
  
  public void insertnSort(Process process){
      
      readyQ.add(process);
   
      boolean swapped = true;
      if(!readyQ.isEmpty()){
        while(swapped){
         swapped = false;
         for(Process p : readyQ){
             if(readyQ.indexOf(p)+ 1 < readyQ.size()){
                if(ExpAvgs.get(readyQ.get(readyQ.indexOf(p))) > ExpAvgs.get(readyQ.get(readyQ.indexOf(p) + 1))){
                    Process temp = readyQ.get(readyQ.indexOf(p)+1);
                    readyQ.remove(readyQ.indexOf(p)+1);
                    readyQ.add(readyQ.indexOf(p), temp);
                    swapped = true;
                    break;
                 
             }
         }
         }
         
      }
      
      //System.out.println("-------------SORTED LIST-------------");
      for(Process p : readyQ){
           //System.out.println("ID : " + p.id + " EXPONENTIAL AVERAGE : " + previousEA.getOrDefault(p.id,(process.getRecentBurst()* alphaBurst) + ((1 - alphaBurst) * previousEA.getOrDefault(process.id, (int)initialBurst))));
              
      }
      /*
      System.out.println("-------------------------------------");
           System.out.println("-----------UNSORTED LIST-------------");
      for(Process p : readyQ){
          
          System.out.println("ID : " + p.id + " EXPONENTIAL AVERAGE : " + previousEA.get(process.id));
          
      }
      System.out.println("-------------------------------------");
     */
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
        System.out.println("-----------------------------------------------");
        }
        return readyQ.poll();
        
    
  }
}
