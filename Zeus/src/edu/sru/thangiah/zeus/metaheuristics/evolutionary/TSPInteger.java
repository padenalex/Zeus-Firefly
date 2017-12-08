package edu.sru.thangiah.zeus.metaheuristics.evolutionary;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly.FireFlyOperator;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm.OXGeneticOperator;
import edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm.PMXGeneticOperator;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(TSPDepotLinkedList mainDepots)
	{
		int numEvolutions = 50;
	    double maxFitness;
	    TSPNodesLinkedList mainNodes = (TSPNodesLinkedList) mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
	    int chromeSize = mainNodes.getSize();
	    
	    Configuration gaConf = new DefaultConfiguration();
	   // gaConf.setOGChromosome(mainNodes);
	    
	    Genotype genotype = null;
	    
	    try {
	    		gaConf.setChromosomeSize(chromeSize);
	    		gaConf.setSampleChromosome(new Chromosome(gaConf));
	    		gaConf.setSampleGenes(new LinkedList<IGene>());
	    		gaConf.setFitnessFunction(new TSPCostFunction(mainDepots));
	    		gaConf.setNumGenerations(numEvolutions);
	    		gaConf.setGeneticOperators(new LinkedList<IGeneticOperator>());
	    		gaConf.addGeneticOperator(new FireFlyOperator(gaConf));
	    		genotype = new Genotype(gaConf);
	    		gaConf.getGeneticOperators().getLast().operate(genotype.getPopulation());
	    		
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      System.exit( -2);
		    }
	}

}