import java.util.zip.DeflaterOutputStream;
import java.io.*;  
import java.util.zip.*;

public class main {


	public static void main(String[] args) throws IOException {
		
		GrafoListAristas grafoListaAristas= new GrafoListAristas();
		grafoListaAristas=Utils.read(grafoListaAristas);
		Utils.compress();
		Utils.decompress();
		
		
		
		 RunnableDemo R1 = new RunnableDemo( "Thread-1","A","B",grafoListaAristas);
	      R1.start();
	      
	    RunnableDemo R2 = new RunnableDemo( "Thread-2","C","D",grafoListaAristas);
	      R2.start();
	   
	      System.out.println("aca va a tofos lados");

			grafoListaAristas.mejorCamino(grafoListaAristas);
	
	//	  System.out.println(grafoListaAristas.sucesores("B",grafoListaAristas));
		
		

	}




}
