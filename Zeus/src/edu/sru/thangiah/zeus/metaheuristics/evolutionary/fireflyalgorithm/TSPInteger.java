package edu.sru.thangiah.zeus.metaheuristics.evolutionary.fireflyalgorithm;

import java.util.LinkedList;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(TSPDepot headDepot)
	{
		int numEvolutions = 500;
	    double maxCost;
	    //int numNodes = flySize;
	    TSPDepot depotHead = headDepot.getNext();
	    TSPTruck truckHead = (TSPTruck) depotHead.getMainTrucks().getHead().getNext();
	    TSPNodesLinkedList nodeHead = truckHead.getTSPMainNodes();
	    int numNodes = nodeHead.getNoNodes();
	    
	    Configuration gaConf = new DefaultConfiguration();
	    
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
	}

}
