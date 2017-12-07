package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg;

import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPInteger {
	
	public TSPInteger(TSPDepotLinkedList mainDepots){
		//TSPCostFunction setStart = new   //Turned off but available if needed
		
		NodesLinkedList ogNodesLL = mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
		
	    int tspSize = ogNodesLL.getSize();
	    int numEvolutions = 100;
	    int popSize = 20;
	    
	    System.out.println("Test ------ " + ogNodesLL.getNodesAtPosition(0).getIndex());
	    
	    Configuration eaConf = new Configuration(tspSize, numEvolutions, popSize);
	    Population startPop = new Population(eaConf, ogNodesLL);
	    

	}

}