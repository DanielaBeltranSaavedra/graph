
import java.util.ArrayList;
import java.util.List;

public class GrafoListAristas  {
	public static  Double distanciaRecorrida=0.0;
	private List<Nodo> nodos = new ArrayList<>();
	private static  int numeroNodos=0;
	static List <Arista> aristas;
	public GrafoListAristas(){
		aristas = new ArrayList<>();


	}

	public Nodo buscarNodo(int a){
		for (Nodo nodo : nodos) {
			if(nodo.getNumeroNodo()==a){

				return nodo;
			}  
		}
		return null;
	}

	public void addNodo(Nodo nodoA) {
		nodos.add(nodoA);
	}

	public void nuevoVertice(String nombre){
		boolean esta = numVertice(nombre) >= 0;
		if(!esta){
			Nodo v = new Nodo(nombre);
			v.setNumeroNodo(numeroNodos);
			nodos.add(v);
			numeroNodos++;
		}
	}

	public int numVertice(String nombre) {
		Nodo v = new Nodo(nombre);
		boolean encontrado = false;
		int i = 0;
		for(; (i<numeroNodos) && !encontrado;){
			encontrado = nodos.get(i).equals(v);
			if(!encontrado){
				i++;
			}
		}
		return (i<numeroNodos) ? i : -1;
	}

	public  Double pesoArco(String a, String b) {
		Double costo = 0.0;
		for(Arista ari: GrafoListAristas.aristas) {
			if(ari.getVertice1()==a&& ari.getVertice2()==b) {
				costo=ari.getCosto();
				//System.out.println("imprimo"+ari.getVertice1()+"en"+ari.getVertice2()+"es"+ari.getCosto());
			}
		}

		return costo;

	}

	public static Double pesoArco2(String a, String b) {
		Double costo = 0.0;
		for(Arista ari: GrafoListAristas.aristas) {
			if(ari.getVertice1().contentEquals(a)&& ari.getVertice2().contentEquals(b)) {
				costo=ari.getCosto();
				//	System.out.println("imprimo"+ari.getVertice1()+"en"+ari.getVertice2()+"es"+ari.getCosto());
			}
		}

		return costo;

	}
	public Nodo obtenerNodo(String string, GrafoListAristas graph) {
		for(Nodo node:graph.getNodos()) {
			if(node.getNombre().equals(string)) {
				return node;
			}
		}
		return null;
	}

	public Double nuevoArco(String a, String b, double peso) {
		aristas.add(new Arista(a,b,peso));
		//aristas.add(new Arista(b,a,peso));
		return peso;

	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public  int getNumeroNodos() {
		return this.numeroNodos;
	}

	public void setNumeroNodos(int numeroNodos) {
		this.numeroNodos = numeroNodos;
	}


	public static  List<String> sucesores(String vertice,GrafoListAristas graph)  {
		
		List<String> res = new ArrayList<String>();
		int num=graph.getNumeroNodos();
	
		for (int i = 0; i < graph.aristas.size() ; i++) {
			//System.out.println("costo"+graph.aristas.get(i).getCosto()+graph.aristas.get(i).getVertice1()+vertice);
			if((graph.aristas.get(i).getVertice1().equals(vertice) && graph.aristas.get(i).getCosto()!=0)||(graph.aristas.get(i).getVertice2()==vertice && aristas.get(i).getCosto()!=0)) {
				
				if(graph.aristas.get(i).getVertice1()==vertice) {
					if(!res.contains(graph.aristas.get(i).getVertice2())) {
						res.add(graph.aristas.get(i).getVertice2());
					}
				}
				else {
					if(!res.contains(graph.aristas.get(i).getVertice1())) {
						res.add(graph.aristas.get(i).getVertice1());
					}
				}


			}


		}
		return res;
	}
	public static void dijkstra(GrafoListAristas graph, Nodo inicial, Nodo fin) {
		List<Nodo> nodosAgregados = new ArrayList<>();
		List<Nodo> nodosNoAgregados = new ArrayList<>();
		nodosAgregados.add(inicial);

		Nodo NodoActual =inicial;
		nodosNoAgregados.add(NodoActual);
		NodoActual.setMarca(true);

		if(fin!=null) {
			System.out.print(NodoActual.getNombre()+"-");
			//System.out.println("voy en "+NodoActual.getNombre());

			while (!nodosAgregados.contains(fin) ) {
				
				NodoActual=MenorDistancia(NodoActual,graph,fin);
				System.out.println();
				System.out.println("la distancia recorrida es"+distanciaRecorrida);
				nodosAgregados.add(NodoActual);
			}
		}
		else {
			//System.out.println("voy en "+NodoActual.getNombre());
			while(nodosNoAgregados.size()!=0) {

				NodoActual=MenorDistancia(NodoActual,graph,fin);
				System.out.println("la distancia recorrida es"+distanciaRecorrida);
				nodosAgregados.add(NodoActual);
			}

		}
		nodosNoAgregados.remove(NodoActual);


	}

	public static Nodo MenorDistancia(Nodo actual,GrafoListAristas graph,Nodo fin) {
		List <String> Sucesorses=new ArrayList<String>();
		Sucesorses=sucesores(actual.getNombre(), graph);
		Double p=0.0;
		Double pf=0.0;
		Double sumaFinal=222222.22222;
		Double sumita=distanciaRecorrida;
		String nodoMenor = null;
		Nodo nodoFinal=null;
		Double conexFin =1000.00;
		//p=graph.pesoArco("A", "B");
		Nodo voyEn=null;
		boolean finE=false;

		for(String su : Sucesorses) {
			voyEn=graph.obtenerNodo(su, graph);
			p=graph.pesoArco2(actual.getNombre(), su);
			sumita+=p;
			if(Sucesorses.contains(fin.getNombre())&& su==fin.getNombre()) {
				sumaFinal=(double) (+p);
				nodoMenor=su;
				pf=p;
				finE=true;
			}
			else {
				List <String> Sucesorses2=new ArrayList<String>();
				Sucesorses2=sucesores(su, graph);
				if(Sucesorses2.contains(fin.getNombre())) {
					if(graph.pesoArco2(su, fin.getNombre())<conexFin &&  !voyEn.isMarca()&&finE==false ) {
						conexFin=graph.pesoArco2(su, fin.getNombre());
						if((p+conexFin)<sumaFinal) {

							sumaFinal=(double) (p+conexFin);
							nodoMenor=su;
							pf=p;

						}

					}
				}else {
					if(sumita<sumaFinal && Sucesorses2.size()!=0&&!voyEn.isMarca()&&finE==false) {

						
						sumaFinal=(double) (+sumita);
						nodoMenor=su;
						pf=p;
					}
				}

			}



			sumita=distanciaRecorrida;




			


		}
		distanciaRecorrida+=pf;
		nodoFinal=graph.obtenerNodo(nodoMenor, graph);
		if(pf==0) {
			System.out.println("NO EXISTE UN CAMINO PARA IR");
		}
		else {
		System.out.print(nodoMenor+"-");
		}
		//voyEn.setMarca(true);
		nodoFinal.setMarca(true);
		return nodoFinal;

	}


	public void mejorCamino(GrafoListAristas graph) {
		
		List<List<Nodo>> caminos=new ArrayList<List<Nodo>>();
		List<Nodo> caminoFinal = new ArrayList<Nodo>();

		List<Nodo> nodosCaminoAuxiliar = new ArrayList<Nodo>();
		for(Nodo nodo : graph.getNodos()) {
			caminos.add(EvaluarCamino(graph,nodo));
		}
		Double sumaFinal=2000.0000;
		for(int i=0; i< caminos.size();i++) {
			Double sumaParcial=0.0;
			List<Nodo> nodosCamino = new ArrayList<Nodo>();
			for(int k=0;k<caminos.get(i).size();k++) {

				if(k<(caminos.get(i).size()-1)) {
					
					sumaParcial=sumaParcial+(graph.pesoArco2(caminos.get(i).get(k).getNombre(),caminos.get(i).get(k+1).getNombre()));
					//System.out.println("suma parcial es"+sumaParcial+" "+caminos.get(i).get(k).getNombre()+caminos.get(i).get(k+1).getNombre());

				}
				if(!nodosCamino.contains(caminos.get(i).get(k))) {
					nodosCamino.add(caminos.get(i).get(k));
				}

				//System.out.println("laa suma parcial hasta el momento es"+ sumaParcial);
			}
			//System.out.println("el tamano del camino es"+ nodosCamino.size());
		
			if(sumaParcial !=0&&((sumaParcial<sumaFinal)||(nodosCamino.size()>nodosCaminoAuxiliar.size()))) {
				
				sumaFinal=sumaParcial;
				caminoFinal=caminos.get(i);
				nodosCaminoAuxiliar=nodosCamino;
			}

		}
		System.out.println("CAMINO FINAL");
		for(Nodo nodo: caminoFinal) {
			System.out.print(nodo.getNombre()+"-");
		}
		System.out.println("Suma final "+ (sumaFinal));

	}

	public List<Nodo> EvaluarCamino(GrafoListAristas graph,Nodo inicial) {
		boolean hay1=false;
		for(Nodo nodo: graph.getNodos()) {
			nodo.setMarca(false);

		}
		List <Nodo> agregados=new ArrayList<Nodo>();
		List <Nodo> rechazados=new ArrayList<Nodo>();
		Double pesoCaminoMenor=200.000,i=0.0;
		Nodo menor=null,nodin=null;
		inicial.setMarca(true);
		agregados.add(inicial);
		boolean acabe=false;
		List<Nodo> finalito= new ArrayList<Nodo>();
		
		while(acabe==false) {
			nodin=inicial;
			List <String> Sucesorses=new ArrayList<String>();
			Sucesorses=sucesores(inicial.getNombre(), graph);
			

			for(String su : Sucesorses) {

				Nodo actual=graph.obtenerNodo(su, graph);
				
				List <String> Sucesorses2=new ArrayList<String>();
				Sucesorses2=sucesores(su, graph);
			
				if(Sucesorses2.size()==1&&!agregados.contains(actual)) {
					if(graph.pesoArco2(inicial.getNombre(),actual.getNombre())!=0||i==(graph.getNumeroNodos()-2)) {
						if(actual.isMarca()==false) {
							
							//System.out.print(actual.getNombre()+"-");
							menor=inicial;
							actual.setMarca(true);
							menor.setMarca(true);
							hay1=true;
							agregados.add(actual);
						}
					}
					else {
						if(actual.isMarca()==false) {
							
							menor=inicial;
							actual.setMarca(true);
							menor.setMarca(true);
							hay1=true;
							//rechazados.remove(actual);
						}
					}


				}
				else {
					if(graph.pesoArco2(inicial.getNombre(),actual.getNombre())!=0&&(pesoCaminoMenor>graph.pesoArco2(inicial.getNombre(), actual.getNombre())&&!actual.isMarca()&&!agregados.contains(actual)&& hay1==false)) {
						
						pesoCaminoMenor=graph.pesoArco2(inicial.getNombre(), actual.getNombre());
						if(menor!=null) {
							rechazados.add(menor);
						}
						menor=actual;
						if(rechazados.contains(menor)) {
							rechazados.remove(menor);
						}
						actual.setMarca(true);
						menor.setMarca(true);



					}
					if(actual.getNombre()!=menor.getNombre()) {
						actual.setMarca(false);


					}
				}
			}
			hay1=false;
			i++;
			pesoCaminoMenor=200.000;
			inicial=menor;
			agregados.add(menor);
			//rechazados.add(inicial);
		
			if(i>2) {
				if(nodin==inicial) {
					//System.out.println("entre"+nodin.getNombre()+inicial.getNombre()+i);
					agregados.remove(menor);
					acabe=true;
				}
			}
			if(acabe==true&& rechazados.size()!=0) {
				
				
				
				String padre=null;
				Nodo recha=null;
				for(Nodo no: rechazados) {
					Double menorAri=200.00;
					Double actualAri=0.0;
				for(Arista ari: graph.aristas) {
					if(ari.getVertice2().equals(no.getNombre())) {
						//System.out.println("voy en rechazados nodo"+no.getNombre());
						actualAri=ari.getCosto();
						if(actualAri<menorAri) {
							menorAri=actualAri;
						//	System.out.println("el padre es"+ari.getVertice1());
							if(agregados.contains(obtenerNodo(ari.getVertice1(),graph))&&!agregados.contains(no)) {
								//System.out.println("el padre es"+ari.getVertice1()+no.getNombre());
								padre=ari.getVertice1();
								recha=no;
							}
							
						}
						
			
						
					}
					
				}
			}
				
				
				int j=0;
				for(Nodo no:agregados)
				{
					Nodo padrecito=null;
					finalito.add(no);
					if(no.getNombre().equals(padre)) {
						if(j<agregados.size()-2) {
							finalito.add(recha);
						
							padrecito=obtenerNodo(padre,graph);
							finalito.add(padrecito);
							
						}
						else {
							finalito.add(recha);
						}
						
					}
					j++;
					
				}
				
			}

		}
		

		return finalito;






	}








}

