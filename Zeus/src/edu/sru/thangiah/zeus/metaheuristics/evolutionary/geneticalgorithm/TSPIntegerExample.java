package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import java.util.LinkedList;

public class TSPIntegerExample {

	public TSPIntegerExample(int chromeSize) {
		// TODO Auto-generated method stub
		int numEvolutions = 500;
	    double maxFitness;
	    int numGenes = chromeSize;
	    
	    Configuration gaConf = new DefaultConfiguration();
	    //gaConf.setPreservFittestIndividual(true);
	    //gaConf.setKeepPopulationSizeConstant(false);
	    
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
	    
	    
	    int progress = 0;
	    int percentEvolution = numEvolutions / 50;
	    for (int i = 0; i < numEvolutions; i++) {
	    	
	      genotype.evolve();
	      // Print progress.
	      // ---------------
	      if (percentEvolution > 0 && i % percentEvolution == 0) {
	        progress++;
	        IChromosome fittest = genotype.getFittestChromosome();
	        double fitness = fittest.getFitnessValue();
	        System.out.println("Currently fittest Chromosome has fitness " +
	         fitness);
	        
	      }
	    }
	    // Print summary.
	    // --------------
	    IChromosome fittest = genotype.getFittestChromosome();
	    System.out.println("Fittest Chromosome has fitness " +
	                       fittest.getFitnessValue());
	  }
}
