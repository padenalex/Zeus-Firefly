package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(NodesLinkedList nodeHead)
	{
		int numEvolutions = 500;
	    double maxFitness;
	    //int numGenes = chromeSize;
	    
	    int numGenes = nodeHead.getNoNodes();
	    
	    Configuration gaConf = new DefaultConfiguration();
	    
	    Genotype genotype = null;
	    gaConf.setChromosomeSize(numGenes);
	    
	    try {
		      	LinkedList<IGene> sampleGenes = new LinkedList<IGene>();
		      	for (int i = 0; i < numGenes; i++)
			    {
			    	sampleGenes.add(new IntegerGene(gaConf, nodeHead.getNodeByIndex(i).getShipment().getIndex(), numGenes));
			    }
			    gaConf.setSampleGenes(sampleGenes);
			    
			    gaConf.getSampleChromosome().setFitnessValue(nodeHead.getCost());
			      
			    ISampleGeneChooser chooser = gaConf.getSampleGeneChooser();
			      
			    IChromosome sampleChromosome = new Chromosome(gaConf,
			    chooser.choose(gaConf), gaConf.getChromosomeSize());
			    gaConf.setSampleChromosome(sampleChromosome);
			    gaConf.setPopulationSize(20);
			    gaConf.setFitnessFunction(new TSPCostFunction());
			    gaConf.addGeneticOperator(new StaticMutationOperator(gaConf));
			    gaConf.addGeneticOperator(new PMXGeneticOperator(gaConf));
			    gaConf.addSelectionOperator(new RouletteSelectionOperator(gaConf));
			    gaConf.setNumGenerations(numEvolutions);
			    gaConf.setCrossoverProbability(0.7);
			    gaConf.setMutationProbability(.001);
			    //gaConf.setMutationProbability(20);
			    //genotype = Genotype.randomInitialGenotype(gaConf);
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      System.exit( -2);
		    }
	}

}
