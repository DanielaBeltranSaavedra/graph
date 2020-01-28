
public class RunnableDemo implements Runnable {
	  private Thread t;
	   private String threadName;
	   private String Inicio;
	   private String Fin;
	   private GrafoListAristas graph;
	   
	   RunnableDemo( String name,String inicio,String fin,GrafoListAristas graph1) {
	      threadName = name;
	      Inicio=inicio;
	      Fin=fin;
	      graph=graph1;
	      System.out.println("Creating " +  threadName );
	   }
	   
	   public void run() {
	      System.out.println("Running " +  threadName );
	      try {
	         
	            System.out.println("Thread: " + threadName + Inicio+" a " + Fin );
	            if(graph.sucesores(Inicio,graph).size()!=0&&graph.sucesores(Fin,graph).size()!=0) {
	            	
	            	graph.dijkstra(graph,graph.obtenerNodo(Inicio,graph),graph.obtenerNodo(Fin,graph));
	    		}
	    		else
	    		{
	    			System.out.println("No hay camino");
	    		}
	            // Let the thread sleep for a while.
	            Thread.sleep(50);
	         
	      } catch (InterruptedException e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
	      System.out.println("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}




