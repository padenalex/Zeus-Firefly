package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;

public class TSPIntegerExample {

	public TSPIntegerExample(int flySize) {
		// TODO Auto-noderated method stub
		int numEvolutions = 500;
	    double maxCost;
	    int numNodes = flySize;
	    
	    Configuration gaConf = new DefaultConfiguration();
	    //gaConf.setPreservBestIndividual(true);
	    //gaConf.setKeepPopulationSizeConstant(false);
	    
	    Flytype flytype = null;
	    gaConf.setFireflySize(numNodes);
	       
	    try {
	      LinkedList<INode> sampleNodes = new LinkedList<INode>();
	      sampleNodes.add(new IntegerNode(gaConf, numNodes));
	      gaConf.setSampleNodes(sampleNodes);
	      
	      ISampleNodeChooser chooser = gaConf.getSampleNodeChooser();
	      
	      IFirefly sampleFirefly = new Firefly(gaConf,
	          chooser.choose(gaConf), gaConf.getFireflySize());
	      gaConf.setSampleFirefly(sampleFirefly);
	      gaConf.setPopulationSize(20);
	      gaConf.setCostFunction(new TSPCostFunction());
	      gaConf.addNatureOperator(new StaticMutationOperator(gaConf));
	      gaConf.addNatureOperator(new BasicCrossoverOperator(gaConf));
	      gaConf.addSelectionOperator(new RouletteSelectionOperator(gaConf));
	      gaConf.setNumNoderations(numEvolutions);
	      gaConf.setCrossoverProbability(0.7);
	      gaConf.setMutationProbability(.001);
	      //gaConf.setMutationProbability(20);
	      flytype = Flytype.randomInitialFlytype(gaConf);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      System.exit( -2);
	    }
	    
	    
	    int progress = 0;
	    int percentEvolution = numEvolutions / 50;
	    for (int i = 0; i < numEvolutions; i++) {
	    	
	      flytype.evolve();
	      // Print progress.
	      // ---------------
	      if (percentEvolution > 0 && i % percentEvolution == 0) {
	        progress++;
	        IFirefly best = flytype.getBestFirefly();
	        double cost = best.getCostValue();
	        System.out.println("Currently best Firefly has cost " +
	         cost);
	        
	      }
	    }
	    // Print summary.
	    // --------------
	    IFirefly best = flytype.getBestFirefly();
	    System.out.println("Best Firefly has cost " +
	                       best.getCostValue());
	  }
}
