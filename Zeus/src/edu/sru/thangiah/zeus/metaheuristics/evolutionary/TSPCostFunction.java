package edu.sru.thangiah.zeus.metaheuristics.evolutionary;

import edu.sru.thangiah.zeus.core.DepotLinkedList;
import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPCostFunction extends AbstractFitnessFunction
{
	static NodesLinkedList StartList = new TSPNodesLinkedList();
	DepotLinkedList depotList;
	NodesLinkedList nodes;
	public TSPCostFunction(DepotLinkedList depotIn)
	{
		depotList = depotIn;
		nodes = (NodesLinkedList) depotList.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
	}
	
	public double evaluate(IChromosome chrome){
	    NodesLinkedList newList = (NodesLinkedList) StartList.clone();

	    for (int i = 0; i < chrome.size(); i++){
	    	int index = (int) chrome.getGene(i).getInternalValue() + 1; //+1 Zeus stores indexes from 1 not 0
	    	Nodes nodey = new Nodes(); 
	    	nodey.setShipment(nodes.getNodeByIndex(index).getShipment());
	    	newList.insertNodeLast(nodey);
	    }
	    
	    ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(newList);
	    
	    return newList.getCost();
	}
}