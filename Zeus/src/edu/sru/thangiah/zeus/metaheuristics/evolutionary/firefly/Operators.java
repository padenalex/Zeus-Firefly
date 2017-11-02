package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.tsp.*;

import edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly.Population;

public class Operators {

	Operators(LinkedList<FireFly> fireFlies){
		int listsize = fireFlies.size();

//test
FireFly Brightest = fireFlies.get(GetBrightestFly(fireFlies));	
Population NewPopulation = new Population();
NewPopulation.fireFlies.add(XtowardsY(Brightest, fireFlies.get(1)));
System.out.println("New List size is " + NewPopulation.fireFlies.size());
System.out.println("Best List is   " + Brightest.FireFlyt.getRouteString());
System.out.println("Second List is " + fireFlies.get(1).FireFlyt.getRouteString());
System.out.println("New List is    " + NewPopulation.fireFlies.get(0).FireFlyt.getRouteString());
		for(int i=0; i < Population.TotalGen; i++) {
			//FireFly Brightest = fireFlies.get(GetBrightestFly(fireFlies));	
			
			for(int x=0; x < listsize; x++) {

				//XtowardsY(Brightest, fireFlies.get(x));
				//XFromY()
				//YtowardsX()
				//YFromX()
				
			}
		//InverMutation(BrightestFireFly);
		//ShrinkListBackTo20
		}
		
	}
	
	public int GetBrightestFly(LinkedList<FireFly> fireFlies) {
		//System.out.println("TTTTTTTTTTTTTTTT");
		double lowestcost= 999999;
		int lowcostindex = 999999;
		for(int i=0; i<Population.popsize; i++) {		
			if(lowestcost > fireFlies.get(i).FireFlyt.getCost()) {
				lowestcost = fireFlies.get(i).FireFlyt.getCost();
				lowcostindex = i;
				//System.out.println("Cheapest FireFly is index " +lowcostindex+ " cost is " + lowestcost);
			}
			//else{System.out.println("Not Cheaper");}
		}
		return lowcostindex;
	}
	
	public FireFly XtowardsY(FireFly BrightestFly, FireFly ThisFly) {
		Vector<Integer> MatchHolder = new Vector<Integer>();
		Vector<Integer> NodeSetLeft = new Vector<Integer>();
		FireFly NewFly = new FireFly(ThisFly);
		Random rand = new Random();
		//System.out.println(BrightestFly.FireFlyt.getRouteString());
		//System.out.println(ThisFly.FireFlyt.getRouteString());
		for(int i=0; i < FireFly.FireflyDimension; i++) {
			int tempB = BrightestFly.FireFlyt.getNodesAtPosition(i).getIndex();
			int tempT = ThisFly.FireFlyt.getNodesAtPosition(i).getIndex();
			if(tempB == tempT) {
				System.out.println("Match at index " + tempB);
				MatchHolder.add(tempB);
				//ListPosition.add(i);
			}
		}
		//System.out.println("The Index Vector is " + NodeIndex);
		
		if(MatchHolder.size() >= 1) {
			int FirstIndex = 9999;
			int SecondIndex = 9999;
			int Check1 = 9989;
			int Check2 = 9989;
			int HowManyTimesRan = 0;
			int Run1 = 1;
			int Run2 = 1;
			int SecondLeft = 0;
			

			while(Run1 == 1) {
			int  n = rand.nextInt(FireFly.FireflyDimension-1) + 0;
			FirstIndex = BrightestFly.FireFlyt.getNodesAtPosition(n).getIndex();

			if(n == FireFly.FireflyDimension-1) {
				SecondIndex = BrightestFly.FireFlyt.getNodesAtPosition(n-1).getIndex();
				SecondLeft = 1;
			}
			else {
				SecondIndex = BrightestFly.FireFlyt.getNodesAtPosition(n+1).getIndex();
			}
			Check2 = ThisFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev().getIndex();
			Check1 = ThisFly.FireFlyt.getNodeByIndex(FirstIndex).getNext().getIndex();
			System.out.println("Bright Index is: " +SecondIndex+ " Check1 is " +Check1+ " Check2 is " + Check2);
			
			if(Check1 != SecondIndex && Check2 != SecondIndex) {Run1 = 0; System.out.println("Inside if");}
			
			HowManyTimesRan++;
			System.out.println("DoWhile For Find A Random Edge Not In List 2 Ran # Times: " + HowManyTimesRan);
			}
			
	
			if(SecondLeft == 1) {
				int temp = FirstIndex;
				FirstIndex = SecondIndex;
				SecondIndex = temp;
			}
			
			int temp1 = FirstIndex;
			int temp2 = FirstIndex;
			NodeSetLeft.addElement(FirstIndex);
			 while(Run2 == 1) {
				try {
					temp1 = BrightestFly.FireFlyt.getNodeByIndex(temp1).getPrev().getIndex();
					temp2 = NewFly.FireFlyt.getNodeByIndex(temp2).getPrev().getIndex();
					
					if(temp1 == temp2) {NodeSetLeft.add(temp2);}
					else {Run2 = 0;}
					
				}
					catch(Exception e){
						System.out.println("Hit Tail?");
						Run2 = 0;
					}
			}
			
			
			temp1 = FirstIndex;
			temp2 = SecondIndex;
			TSPNodes tempnode;
			TSPNodes tempnode2;
			System.out.println("The index to move is " +FirstIndex+ " and moved to " +SecondIndex);
			
			for(int i = 0; i < NodeSetLeft.size(); i++) {
				temp1 = NodeSetLeft.get(i);
				tempnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp1);
				tempnode2 = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp2);
				
				//NewFly.FireFlyt.removeNodeByIndex(temp1);
				//NewFly.FireFlyt.insertAfterNodes(tempnode, tempnode2);
				NewFly.FireFlyt.getNodeByIndex(temp2).setPrev(tempnode);
				//NewFly.FireFlyt.getNodeByIndex(temp2).getShipment().setPrev(tempnode);
				//NewFly.FireFlyt.
				temp2 = temp1;
				
				//NodeSetLeft.
				//Just make a vector for changes? 
			}
			
			
		}
		else {
			//InvertMutation(1, FireFly)
			System.out.println("~~~InvertMutation~~~");
		}
		
		
		return NewFly;
	}
}
