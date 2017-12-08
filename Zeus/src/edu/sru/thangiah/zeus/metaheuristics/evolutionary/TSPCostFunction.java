package edu.sru.thangiah.zeus.metaheuristics.evolutionary;

import edu.sru.thangiah.zeus.core.Depot;
import edu.sru.thangiah.zeus.core.DepotLinkedList;
import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.core.Truck;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPCostFunction extends AbstractFitnessFunction
{
	static TSPNodesLinkedList StartList = new TSPNodesLinkedList();
	TSPDepotLinkedList depotList;
	TSPNodesLinkedList nodes;
	public TSPCostFunction(TSPDepotLinkedList depotIn)
	{
		depotList = depotIn;
		nodes = (TSPNodesLinkedList) depotList.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes();
	}
	
	public double evaluate(IChromosome chrome)
	{
		//Settings.printDebug(Settings.COMMENT, Integer.toString(chrome.size()));
	    NodesLinkedList newList = (NodesLinkedList) StartList.clone();
	    //Settings.printDebug(Settings.COMMENT, Integer.toString(chrome.size()));
	    //Settings.printDebug(Settings.COMMENT, Integer.toString(nodes.getSize()));
	    for (int i = 0; i < chrome.size(); i++)
	    {
	    	int index = (int) chrome.getGene(i).getInternalValue() + 1; //+1 Zeus stores indexes from 1 not 0
	    	TSPNodes nodey = new TSPNodes(); 
	    	nodey.setShipment(nodes.getNodeByIndex(index).getShipment());
	    	newList.insertNodeLast(nodey);
	    }
	    
	    ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(newList);
	    
	    return newList.getCost();
	}
}