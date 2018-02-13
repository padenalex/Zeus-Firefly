package edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.DepotLinkedList;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.firefly.FireFlyOperator;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.geneticalgorithm.OXGeneticOperator;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.geneticalgorithm.PMXGeneticOperator;


public class TSPInteger {
	
	public TSPInteger(DepotLinkedList mainDepots)
	{
		int numEvolutions = 40000;
	    //double maxFitness;
	    NodesLinkedList mainNodes = (NodesLinkedList) mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
	    int chromeSize = mainNodes.getSize();
	    
	    Configuration gaConf = new DefaultConfiguration();
	    
	    Genotype genotype = null;
	    
	    try {
	    		gaConf.setChromosomeSize(chromeSize);
	    		gaConf.setSampleChromosome(new Chromosome(gaConf));
	    		gaConf.setSampleGenes(new LinkedList<IGene>());
	    		gaConf.setFitnessFunction(new TSPCostFunction(mainDepots));
	    		gaConf.setNumGenerations(numEvolutions);
	    		gaConf.setPopulationSize(8);
	    		gaConf.setOGChromosome(mainNodes);
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