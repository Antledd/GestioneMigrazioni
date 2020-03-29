package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {

	//Modello -> Stato del sistema ad ogni passo
	private Graph<Country, DefaultEdge> grafo;
	
	//Tipi di evento/coda prioritaria
	//1 solo evento
	private PriorityQueue<Evento> queue;
	
	//Parametri della simulazione
	private int N_MIGRANTI = 1000;
	private Country partenza;
	
	//Valori in output
	private int T; //T = Tempo iniziale
	private Map<Country, Integer> stanziali;
	
	public void init(Country partenza, Graph<Country,DefaultEdge> grafo) {
		//ricevo i parametri
		this.partenza = partenza;
		this.grafo = grafo;
		
		//impostazione dello stato iniziale
		this.T = 1; //Tempo iniziale T impostato a 1 
		stanziali = new HashMap<Country, Integer>();
		for(Country c : this.grafo.vertexSet()) {
				stanziali.put(c,  0);
		}
		queue = new PriorityQueue<Evento>();
		
		//inserisco il primo evento (l'evento di partenza) 
		this.queue.add(new Evento(T, N_MIGRANTI, partenza)); //Tempo iniziale = 1, numero migranti = 1000,country di partenza)
	}
	
	public void run() {
		//Estraggo un evento per volta dalacoda e lo eseguo
		//finché la coda non si svuota
		Evento e;
		
		while((e = queue.poll()) != null) {
			//ESEGUO L'EVENTO
			
			this.T=e.getT(); //aggiorno il Tempo iniziale all'ultimo T cui siamo arrivati
			
			int nPersone = e.getN();
			Country stato = e.getStato();
			List<Country> confinanti = Graphs.neighborListOf(this.grafo, stato);
			int migranti = (nPersone/2)/confinanti.size();
			
			if(migranti > 0) {
				//le persone si possono muovere
				for(Country confinante : confinanti)
					queue.add(new Evento(e.getT()+1,migranti, confinante)); //persone da distribuire in ogni stato confinante
			}
			
			int stanziali = nPersone - migranti * confinanti.size(); 
			this.stanziali.put(stato, this.stanziali.get(stato) + stanziali);
		}
		
	}

	public int getLastT() {
			return T;
	}
	
	public Map<Country, Integer> getStanziali(){
		return this.stanziali;
	}
}
