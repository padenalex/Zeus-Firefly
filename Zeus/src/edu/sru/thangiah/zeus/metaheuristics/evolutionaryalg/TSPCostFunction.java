package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg;


import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;


public class TSPCostFunction{
	
	static NodesLinkedList startList = new NodesLinkedList();
	
	
	public TSPCostFunction(NodesLinkedList ogNodeLL) {startList = (NodesLinkedList) ogNodeLL.clone();}
	
	
	
	public double GetCost(INodeLL tempList){
	    NodesLinkedList newList = (NodesLinkedList) startList.clone();
	    newList.emptyList();
	    
	    for (int i = 0; i < tempList.Size(); i++){
	    	int tempIndex = tempList.GetIndexByPosition(i);
	    	Nodes tempNode = startList.getNodeByIndex(tempIndex);
	    	newList.insertNodeLast(tempNode);
	    }
	    
	    if(newList.getNoNodes() != startList.getNoNodes()) {System.out.println("ERROR IN EVOLUTIONARY ALGORITHM COST FUNCTION");}
	    
	    ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(newList);
	    
	    return newList.getCost();
	}
}