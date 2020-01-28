import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Utils {
	public static void compress() throws IOException {
		DeflaterOutputStream dos;
		try(	FileInputStream fis = new FileInputStream("grafo.txt")){
			FileOutputStream fos= new FileOutputStream("grafoBin.bin");
			dos=new DeflaterOutputStream(fos);
			int data;
			while((data=fis.read())!=-1) {
				dos.write(data);
			}
		
		dos.close();
	}
}
	public static void decompress() throws IOException {
		FileInputStream fis = new FileInputStream("grafoBin.bin");
		InflaterInputStream iis;
		
		try (
			FileOutputStream fos= new FileOutputStream("grafoDes.txt")){
			
			iis= new InflaterInputStream(fis);
			
			int data;
			while((data=iis.read())!=-1) {
				fos.write(data);
			}
		
	}
		iis.close();
}
public static GrafoListAristas  read (GrafoListAristas grafoListaAristas) {
	
	  String cadena;
	  try {
    FileReader file = new FileReader("grafo.txt");
    BufferedReader buffer = new BufferedReader(file);
//    cadena = buffer.readLine();
    while ((cadena = buffer.readLine())!=null) {
  	  if(cadena.contains(",")) {
  		  //añado las aristas
  		 /// System.out.println("entre"+cadena);
  		  String[] result = cadena.split(",");
  		  Nodo nodo1=grafoListaAristas.obtenerNodo(result[0], grafoListaAristas);
  		  Nodo nodo2=grafoListaAristas.obtenerNodo(result[1], grafoListaAristas);
  		  grafoListaAristas.nuevoArco(nodo1.getNombre(),nodo2.getNombre(),(Integer.parseInt(result[2])));
  		  //grafoListaAristas.nuevoArco("A","B",3);
  		 // System.out.println(result[0]+result[1]+result[2]);
  	  }
  	  else {
  		  //añado vertive
  		  System.out.println(cadena);
  		  grafoListaAristas.nuevoVertice(cadena);
  	  }
  	  
    }
	buffer.close();
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  
	  }
	
	  return grafoListaAristas;
}

}
