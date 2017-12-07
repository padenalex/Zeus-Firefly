package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg;

import java.util.LinkedList;

//import antlr.collections.List;

public class Configuration
{

	private int tspSize;
	private int numEvolutions;
	private int popSize;

	public Configuration(int tsp, int numE, int popS) {
		this.tspSize = tsp;
		this.numEvolutions = numE;
		this.popSize = popS;
	}
	
	public int getTspSize(){ return tspSize; }
	public int getNumGenerations(){ return numEvolutions; }
	public int getPopulationSize(){ return popSize; }

}