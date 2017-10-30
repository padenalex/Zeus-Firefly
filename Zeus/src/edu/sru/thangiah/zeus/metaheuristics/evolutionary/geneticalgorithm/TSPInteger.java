package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(TSPDepot headDepot)
	{
		int numEvolutions = 500;
	    double maxFitness;
	    //int numGenes = chromeSize;
	    TSPDepot depotHead = headDepot.getNext();
	    TSPTruck truckHead = (TSPTruck) depotHead.getMainTrucks().getHead().getNext();
	    TSPNodesLinkedList nodeHead = truckHead.getTSPMainNodes();
	    int numGenes = nodeHead.getNoNodes();
	    
	    Configuration gaConf = new DefaultConfiguration();
	    
	    Genotype genotype = null;
	    gaConf.setChromosomeSize(numGenes);
	    
	    try {
		      LinkedList<IGene> sampleGenes = new LinkedList<IGene>();
		      sampleGenes.add(new IntegerGene(gaConf, numGenes));
		      gaConf.setSampleGenes(sampleGenes);
		      
		      ISampleGeneChooser chooser = gaConf.getSampleGeneChooser();
		      
		      IChromosome sampleChromosome = new Chromosome(gaConf,
		          chooser.choose(gaConf), gaConf.getChromosomeSize());
		      gaConf.setSampleChromosome(sampleChromosome);
		      gaConf.setPopulationSize(20);
		      gaConf.setFitnessFunction(new TSPCostFunction());
		      gaConf.addGeneticOperator(new StaticMutationOperator(gaConf));
		      gaConf.addGeneticOperator(new BasicCrossoverOperator(gaConf));
		      gaConf.addSelectionOperator(new RouletteSelectionOperator(gaConf));
		      gaConf.setNumGenerations(numEvolutions);
		      gaConf.setCrossoverProbability(0.7);
		      gaConf.setMutationProbability(.001);
		      //gaConf.setMutationProbability(20);
		      genotype = Genotype.randomInitialGenotype(gaConf);
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      System.exit( -2);
		    }
	}

}