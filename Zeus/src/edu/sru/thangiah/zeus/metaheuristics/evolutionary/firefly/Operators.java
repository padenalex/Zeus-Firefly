package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import edu.sru.thangiah.zeus.core.Nodes;
import edu.sru.thangiah.zeus.core.NodesLinkedList;
import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.tsp.*;
import edu.sru.thangiah.zeus.tsp.tspcostfunctions.*;
import edu.sru.thangiah.zeus.core.Attributes;

import edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly.Population;

public class Operators {

	Operators(LinkedList<FireFly> fireFlies){
		int listsize = fireFlies.size();

FireFly Brightest = fireFlies.get(GetBrightestFly(fireFlies));	

Population NewPopulation = new Population();
for(int i=0; i < fireFlies.size(); i++) {
	NewPopulation.fireFlies.add(fireFlies.get(i));
	
	//System.out.println("the old pop is " + fireFlies.get(i).FireFlyt.getRouteString());
}
//for(int i = 0; i < 4; i++) {NewPopulation.fireFlies.add(InvertMutation(Brightest));}


//NewPopulation.fireFlies.add(YtowardsX(Brightest, fireFlies.get(1)));

//System.out.println("New List size is " + NewPopulation.fireFlies.size());
//System.out.println("Best List is   " + Brightest.FireFlyt.getRouteString());
//System.out.println("Second List is " + fireFlies.get(1).FireFlyt.getRouteString() + " cost is " + fireFlies.get(1).FireFlyt.getCost() );
//System.out.println("New List is    " + NewPopulation.fireFlies.get(21).FireFlyt.getRouteString() + " cost is " + NewPopulation.fireFlies.get(21).FireFlyt.getCost());
	
		for(int i=0; i < 50; i++) {
			
			Brightest = NewPopulation.fireFlies.get(GetBrightestFly(NewPopulation.fireFlies));	
			for(int x=0; x < 20; x++)  {
				NewPopulation.fireFlies.add(XtowardsY(Brightest, NewPopulation.fireFlies.get(x)));
				NewPopulation.fireFlies.add(YtowardsX(Brightest, NewPopulation.fireFlies.get(x)));
			}
			for(int x = 0; x < 4; x++) {NewPopulation.fireFlies.add(InvertMutation(Brightest));}
			NewPopulation = TrimPopulation(NewPopulation);

		}
		
		System.out.println("Best List is   " + Brightest.FireFlyt.getRouteString() + " cost is " + Brightest.FireFlyt.getCost());
		
	}
	
	
	public int GetBrightestFly(LinkedList<FireFly> fireFlies) {
			//System.out.println("TTTTTTTTTTTTTTTT");
			double lowestcost= 999999998;
			int lowcostindex = 999999998;
			for(int i=0; i<fireFlies.size(); i++) {		
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
					//System.out.println("Match at index " + tempB);
					MatchHolder.add(tempB);
					//ListPosition.add(i);
				}
			}
	
			//System.out.println("The Index Vector is " + NodeIndex);
			
			if(MatchHolder.size() >= 1 && !BrightestFly.equals(ThisFly)) {
				int FirstIndex = 999999999;
				int SecondIndex = 999999999;
				int Check1 = 999999997;
				int Check2 = 999999997;
				int HowManyTimesRan = 0;
				int Run1 = 1;
				int Run2 = 1;
				int SecondLeft = 0;
		
				
//infinite loop was in here --- Some lists are remaining the same?
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
					//System.out.println("Bright Index is: " +SecondIndex+ " Check1 is " +Check1+ " Check2 is " + Check2);
					
					if(Check1 != SecondIndex && Check2 != SecondIndex) {Run1 = 0;}
					HowManyTimesRan++;
					if(FireFly.FireflyDimension == HowManyTimesRan) {Run1 = 0;}
					//System.out.println("DoWhile For Find A Random Edge Not In List 2 Ran # Times: " + HowManyTimesRan);
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
						
						if(temp1 == temp2) {
							if(temp1 > 0) {
								NodeSetLeft.add(temp2); //System.out.println("the temp is " + temp2 + " " + temp1);
								System.out.println("-------->>>>>>  The Left Node Set is Size " + NodeSetLeft.size());
							} 
							else {Run2 =0;}
						}
						else {Run2 = 0;}
					}
					catch(Exception e){
						System.out.println("Hit Tail?");
						Run2 = 0;
					}
				}
				
				
				temp1 = FirstIndex;
				temp2 = SecondIndex;
				TSPNodes leftnode;
				TSPNodes rightnode;
	
				System.out.println("The index to move is " +FirstIndex+ " and moved to " +SecondIndex);
				System.out.println("the route is  " + NewFly.FireFlyt.getRouteString());
				System.out.println("best route is " + BrightestFly.FireFlyt.getRouteString());
	
				for(int i = 0; i < NodeSetLeft.size(); i++) {
					temp1 = NodeSetLeft.get(i);
					leftnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp1);
					rightnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp2);
					NewFly.FireFlyt.removeNodeByIndex(temp1);
					NewFly.FireFlyt.insertAfterNodes(leftnode, rightnode.getPrev()); //if rightnode = pos 0 then sethead instead of getprev ?
					temp2 = temp1;
				}
				System.out.println("the new route " + NewFly.FireFlyt.getRouteString());
				// -------Find total cost new
				ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
				//System.out.println("new cost test " + ProblemInfo.nodesLLLevelCostF.getTotalCost(NewFly.FireFlyt));
				//System.out.println("old cost test " + ProblemInfo.nodesLLLevelCostF.getTotalCost(ThisFly.FireFlyt));
				//System.out.println("test cost is " + NewFly.FireFlyt.getCost());
				
			}
			else {
				NewFly = InvertMutation(ThisFly);
				System.out.println("~~~InvertMutation~~~");
			}
			
		return NewFly;
		}
	
		
	public FireFly InvertMutation(FireFly CurrFly) {
			
	
				FireFly NewFly = new FireFly(CurrFly);
				Random rand = new Random();
				Vector<Nodes> LeftVect = new Vector<Nodes>();
				Vector<Nodes> FlipVect = new Vector<Nodes>();
				Vector<Nodes> RightVect = new Vector<Nodes>();
				
				//System.out.println("The original NewFly is " + NewFly.FireFlyt.getRouteString() + " size is " + NewFly.FireFlyt.getSize());
				int  Rand1 = rand.nextInt(FireFly.FireflyDimension) + 0;
				int  Rand2 = rand.nextInt(FireFly.FireflyDimension) + 0;
				while(Rand1 == Rand2 || Rand1==Rand2-1 || Rand1==Rand2+1) {Rand2 = rand.nextInt(FireFly.FireflyDimension) + 0;}
				
				if(Rand1 > Rand2) { //Set Rand1 to smaller Rand2 larger
					int temp;
					temp = Rand1;
					Rand1 = Rand2;
					Rand2 = temp;
				}
				int loopSize = Rand2-Rand1;
				//System.out.println("rand1 is " + Rand1 + " rand2 is " + Rand2 + " loop size is " + loopSize);
	//--copy
				if(Rand1 != 0) {
					for(int i = 0; i < Rand1; i++) {
						LeftVect.add((Nodes) NewFly.FireFlyt.getNodesAtPosition(i));
					}
				}
				for(int i = Rand1; i < Rand2; i++) {
					FlipVect.add((Nodes) NewFly.FireFlyt.getNodesAtPosition(i));
				}
				if(Rand2 != FireFly.FireflyDimension) {
					for(int i = Rand2; i < FireFly.FireflyDimension; i++) {
						RightVect.add((Nodes) NewFly.FireFlyt.getNodesAtPosition(i));
					}
				}
				//System.out.println("left size is " + LeftVect.size() + " flip vect is " + FlipVect.size() + " right vect is " + RightVect.size());
	//--flip/empty			
				Collections.reverse(FlipVect);
				NewFly.FireFlyt.emptyList();			
	//---- populate
				int temp1 = 0;
				int temp2 = 0;
				
				if(Rand1 != 0) {
					for(int i = 0; i < Rand1; i++) {
						NewFly.FireFlyt.insertNodeLast(LeftVect.elementAt(i));
					}
				}
				for(int i = Rand1; i < Rand2; i++) {
					NewFly.FireFlyt.insertNodeLast(FlipVect.elementAt(temp1));
					temp1++;
				}
				if(Rand2 != FireFly.FireflyDimension) {
					for(int i = Rand2; i < FireFly.FireflyDimension; i++) {
						NewFly.FireFlyt.insertNodeLast(RightVect.elementAt(temp2));;
						temp2++;
					}
				}
				ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
				
		return NewFly;
		}
	
		
	public Population TrimPopulation(Population TempPop) {
			
			Population NewPop = new Population();
			
			int temp;
			
			//for(int i = 0; i < 44; i++) {
			//	System.out.println("the old pop is " + TempPop.fireFlies.get(i).FireFlyt.getRouteString());
			//}
			//System.out.println(" ");
			//System.out.println("=================");
			//System.out.println(" ");
			
			for(int i = 0; i < Population.popsize; i++) {
				//System.out.println("test");
				temp = GetBrightestFly(TempPop.fireFlies);
				NewPop.fireFlies.add(TempPop.fireFlies.get(temp));
				TempPop.fireFlies.remove(temp);
				
				//System.out.println("temp pop size is " + TempPop.fireFlies.size() + " new pop is " + NewPop.fireFlies.size());
				//System.out.println("the new pop is " + NewPop.fireFlies.get(i).FireFlyt.getRouteString());
				
				
			}
			
			return NewPop;
		}
	
	
//---------------------------- YtoX
//----------------------------
	
	public FireFly YtowardsX(FireFly BrightestFly, FireFly ThisFly) {
		Vector<Integer> MatchHolder = new Vector<Integer>();
		Vector<Integer> NodeSetRight = new Vector<Integer>();
		FireFly NewFly = new FireFly(ThisFly);
		Random rand = new Random();
		//System.out.println(BrightestFly.FireFlyt.getRouteString());
		//System.out.println(ThisFly.FireFlyt.getRouteString());
		
		for(int i=0; i < FireFly.FireflyDimension; i++) {
			int tempB = BrightestFly.FireFlyt.getNodesAtPosition(i).getIndex();
			int tempT = ThisFly.FireFlyt.getNodesAtPosition(i).getIndex();
			if(tempB == tempT) {
				//System.out.println("Match at index " + tempB);
				MatchHolder.add(tempB);
				//ListPosition.add(i);
			}
		}

		//System.out.println("The Index Vector is " + NodeIndex);
		
		if(MatchHolder.size() >= 1 && !BrightestFly.equals(ThisFly)) {
			int FirstIndex = 999999996;
			int SecondIndex = 999999996;
			int Check1 = 999999995;
			int Check2 = 999999995;
			int HowManyTimesRan = 0;
			int Run1 = 1;
			int Run2 = 1;
			int SecondLeft = 0;
	
			
//infinite loop was in here --- Some lists are remaining the same?
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
				//System.out.println("Bright Index is: " +SecondIndex+ " Check1 is " +Check1+ " Check2 is " + Check2);
				
				if(Check1 != SecondIndex && Check2 != SecondIndex) {Run1 = 0;}
				HowManyTimesRan++;
				if(FireFly.FireflyDimension == HowManyTimesRan) {Run1 = 0;}
				//System.out.println("DoWhile For Find A Random Edge Not In List 2 Ran # Times: " + HowManyTimesRan);
			}
	
			//vchanged from to  to check fix ---- This puts y in front of x
			if(SecondLeft == 1) {
				int temp = FirstIndex;
				FirstIndex = SecondIndex;
				SecondIndex = temp;
			}
			
//########	--Build set up from right side instead of left //.getnext not .getprev + Hard limit at max+1 instead of min.getprev (0)	
			int temp1 = SecondIndex;
			int temp2 = SecondIndex;
			NodeSetRight.addElement(SecondIndex);
			 while(Run2 == 1) {
				try {
					temp1 = BrightestFly.FireFlyt.getNodeByIndex(temp1).getNext().getIndex();
					temp2 = NewFly.FireFlyt.getNodeByIndex(temp2).getNext().getIndex();
					
					if(temp1 == temp2) {
						if(temp1 > -1) {
							NodeSetRight.add(temp2); //System.out.println("the temp is " + temp2 + " " + temp1);
							System.out.println("~~~~~~~>>>>>>  The Right Node Set is Size " + NodeSetRight.size());
						} 
						else {Run2 =0;}
					}
					else {Run2 = 0;}
				}
				catch(Exception e){
					System.out.println("Hit Tail?");
					Run2 = 0;
				}
			}
//########			
			
			temp1 = FirstIndex;
			temp2 = SecondIndex;
			TSPNodes leftnode;
			TSPNodes rightnode;

			System.out.println("The index to move is " +SecondIndex+ " and moved to " +FirstIndex);
			System.out.println("best route is " + BrightestFly.FireFlyt.getRouteString());
			System.out.println("the route is  " + NewFly.FireFlyt.getRouteString());
			
			for(int i = 0; i < NodeSetRight.size(); i++) {
				temp2 = NodeSetRight.get(i);
				leftnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp1);
				rightnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp2);
				NewFly.FireFlyt.removeNodeByIndex(temp2);
				NewFly.FireFlyt.insertAfterNodes(rightnode, leftnode);
				temp1 = temp2;
			}
			
			
/*
			for(int i = 0; i < NodeSetRight.size(); i++) {
				temp2 = NodeSetRight.get(i);
				leftnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp1);
				rightnode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(temp2);
				NewFly.FireFlyt.removeNodeByIndex(temp2);
				NewFly.FireFlyt.insertAfterNodes(rightnode, leftnode); //if rightnode = pos 0 then sethead instead of getprev ?
				temp1 = temp2;
			}
*/						
			
			
			
			System.out.println("the new route " + NewFly.FireFlyt.getRouteString());
			// -------Find total cost new
			ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
			//System.out.println("new cost test " + ProblemInfo.nodesLLLevelCostF.getTotalCost(NewFly.FireFlyt));
			//System.out.println("old cost test " + ProblemInfo.nodesLLLevelCostF.getTotalCost(ThisFly.FireFlyt));
			//System.out.println("test cost is " + NewFly.FireFlyt.getCost());
			
		}
		else {
			NewFly = InvertMutation(ThisFly);
			System.out.println("~~~InvertMutation~~~");
		}
		
	return NewFly;
	}
	
	
	
	
	
	
}
