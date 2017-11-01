package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.tsp.*;


public class FireFly
{

	public static int FireflyDimension;
	static Vector <Nodes> IndexVector = new Vector<Nodes>();
	static TSPNodesLinkedList StartList = new TSPNodesLinkedList();

	public FireFly()
	{
		TSPNodesLinkedList FireFly = new TSPNodesLinkedList();
		FireFly = StartList;
		FireFly.emptyList();
		Collections.shuffle(IndexVector);

		
		for(int i=0; i < FireflyDimension; i++) {
				FireFly.insertShipment(IndexVector.elementAt(i).getShipment());
		}

		
		System.out.println("The attributes for This random firefly is " + FireFly.getAttributes());
		System.out.println("The route is " + FireFly.getRouteString());
	}
	
//-------------------
	
	public FireFly(NodesLinkedList mainNodes) {
		NodesLinkedList FireFly = new NodesLinkedList();
		FireFly = (NodesLinkedList) mainNodes.clone();
		
		int size = FireFly.getSize();
		FireflyDimension = size;
		
		//System.out.println("The Size is --------------- " + size);
		
		for(int i=1; i <= FireflyDimension; i++) {
			IndexVector.add(FireFly.getNodeByIndex(i));
		}
		
		System.out.println("The Base Attributes are " + FireFly.getAttributes());
		System.out.println("The route is " + FireFly.getRouteString());
		StartList = (TSPNodesLinkedList) FireFly.clone();
	}

}
