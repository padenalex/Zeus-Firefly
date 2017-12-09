package edu.sru.thangiah.zeus.metaheuristics.evolutionaryalg;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.tsp.TSPNodes;
import edu.sru.thangiah.zeus.tsp.TSPNodesLinkedList;

public class INodeLL {
	
	private LinkedList<Integer> nodeLL;
	private double tspCost;
	private int tspSize;
	static NodesLinkedList startList;
	
	public INodeLL(Configuration eaConf, NodesLinkedList ogNodeLL) {
		startList = (NodesLinkedList) ogNodeLL.clone();
		this.nodeLL = new LinkedList<Integer>();
		this.tspSize = eaConf.getTspSize();
		
		for(int i=0; i < this.tspSize; i++) {this.nodeLL.add(ogNodeLL.getNodesAtPosition(i).getIndex());}
		
		System.out.println("List: " + this.nodeLL.toString());
		CalculateCost();
	}
	
	
	public INodeLL(Configuration eaConf) {
		this.nodeLL = new LinkedList<Integer>();
		this.tspSize = eaConf.getTspSize();
		
		for(int i=1; i <= this.tspSize; i++) {this.nodeLL.add(i);}
		
		Random rnd = new Random(System.currentTimeMillis());
		Collections.shuffle(this.nodeLL, rnd);
		System.out.println("List: " + this.nodeLL.toString());
		CalculateCost();
	}
	
	public LinkedList<Integer> GetINodeLL() {return this.nodeLL;}
	public int Size() {return this.tspSize;}
	public double GetTSPCost() {return this.tspCost;}
	
	
	public int GetIndexByPosition(int pos) {
		int index = this.nodeLL.get(pos);
		return index;
	}
	
	//Runs TSPCost Function and sets new cost for INodeLL object
	public void CalculateCost(){
	    TSPNodesLinkedList newList = (TSPNodesLinkedList) startList.clone();
	    newList.emptyList();

	    for (int i = 0; i < this.tspSize; i++){
	    	int tempIndex = this.nodeLL.get(i);
	    	TSPNodes tempNode = new TSPNodes();
	    	tempNode.setShipment(startList.getNodeByIndex(tempIndex).getShipment());
	    	newList.insertNodeLast(tempNode);
	    }
	    
	    if(newList.getNoNodes() != startList.getNoNodes()) {System.out.println("ERROR IN EVOLUTIONARY ALGORITHM .GETCOST FUNCTION");}
	    
	    ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(newList);
	    this.tspCost = newList.getCost();
	    //System.out.println("Cost is " + this.tspCost);
	}
}










