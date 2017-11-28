package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(TSPDepotLinkedList mainDepot)
	{
		int numEvolutions = 500;
	    double maxFitness;
	    TSPNodesLinkedList mainNodes;
	    mainNodes = (TSPNodesLinkedList) mainDepot.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
	    int chromeSize = mainNodes.getSize();
	    
	    Configuration gaConf = new DefaultConfiguration();
	    
	    Genotype genotype = null;
	    
	    try {
	    		gaConf.setChromosomeSize(chromeSize);
	    		gaConf.setFitnessFunction(new TSPCostFunction(mainNodes));
	    		gaConf.setNumGenerations(numEvolutions);
	    		gaConf.addGeneticOperator(new PMXGeneticOperator(gaConf));
	    		gaConf.setGeneticOperators(new LinkedList<IGeneticOperator>());
	    		gaConf.addGeneticOperator(new PMXGeneticOperator(gaConf));
	    		genotype = new Genotype(gaConf);
	    		gaConf.getGeneticOperators().getLast().operate(genotype.getPopulation());
	    		
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      System.exit( -2);
		    }
	}

}
