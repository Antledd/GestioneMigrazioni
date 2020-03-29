package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento>{
	private int t; //tempo t in cui si verifica l'evento
    private int n; //Il numero di persone arrivate e che si sposteranno
    private Country stato; // il paese in cui le persone arrivano e da cui partirà il 50%
	public Evento(int t, int n, Country stato) {
		super();
		this.t = t;
		this.n = n;
		this.stato = stato;
	}
	
	public int getT() {
		return t;
	}
	public int getN() {
		return n;
	}
	public Country getStato() {
		return stato;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.t - o.t; // tempi t ordinati in modo crescente
	}
    
    
    
}
    
