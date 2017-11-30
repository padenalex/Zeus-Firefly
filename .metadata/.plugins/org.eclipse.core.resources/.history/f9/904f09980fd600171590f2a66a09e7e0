package edu.sru.thangiah.zeus.metaheuristics.evolutionary.geneticalgorithm;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.tsp.*;

public class TSPCostFunction extends AbstractFitnessFunction
{
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
	    TSPNodesLinkedList newList = new TSPNodesLinkedList();
	    Settings.printDebug(Settings.COMMENT, Integer.toString(chrome.size()));
	    Settings.printDebug(Settings.COMMENT, Integer.toString(nodes.getSize()));
	    for (int i = 0; i < nodes.getSize(); i++)
	    {
	    	newList.insertShipment(nodes.getNodeByIndex((int) chrome.getGene(i).getInternalValue()).getShipment());
	    }
	    
	    return newList.getCost();
	}
}