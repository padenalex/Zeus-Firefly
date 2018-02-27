package edu.sru.thangiah.zeus.optimizations.oldfirefly;
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

import edu.sru.thangiah.zeus.optimizations.oldfirefly.Population;

public class Operators {

	public FireFly FinalBright;
	
	Operators(LinkedList<FireFly> fireFlies){
		int listsize = fireFlies.size();
		Population NewPopulation = new Population();
		for(int i=0; i < fireFlies.size(); i++) {NewPopulation.fireFlies.add(fireFlies.get(i));} //Set New Pop to passed in pop
			
		for(int x=0; x < Population.TotalGen; x++) {
			int BrightIndex = GetBrightestFly(NewPopulation.fireFlies);
			FireFly Brightest = NewPopulation.fireFlies.get(BrightIndex);
			for(int i=0; i < 4; i++) {NewPopulation.fireFlies.add(InvertMutation(NewPopulation.fireFlies.get(0)));}
				for(int i=1; i < Population.popsize; i++) {
					//System.out.println("The list is " + i);
					FindNewEdge Edge = new FindNewEdge(Brightest, NewPopulation.fireFlies.get(i));
					NewPopulation.fireFlies.add(XtoY(Edge, NewPopulation.fireFlies.get(i)));
					NewPopulation.fireFlies.add(YtoX(Edge, NewPopulation.fireFlies.get(i)));
					NewPopulation.fireFlies.add(XfromY(Edge, NewPopulation.fireFlies.get(i)));
					NewPopulation.fireFlies.add(YfromX(Edge, NewPopulation.fireFlies.get(i)));
					//System.out.println("end list for " + i);
				}
			//System.out.println("Pre trim pop " + NewPopulation.fireFlies.size());
			NewPopulation = TrimPopulation(NewPopulation);
			//System.out.println("New pop is " + NewPopulation.fireFlies.size());
		}
		int BrightIndex = GetBrightestFly(NewPopulation.fireFlies);
		FireFly Brightest = NewPopulation.fireFlies.get(BrightIndex);
		System.out.println("The brightest found is: " + Brightest.FireFlyt.getRouteString() + " cost is " + Brightest.FireFlyt.getCost());
		System.out.println("Brightest cost is " + Brightest.FireFlyt.getCost());

		this.FinalBright = Brightest;
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
	
	//Remove possibility of duplicates from trim pop	
	public Population TrimPopulation(Population TempPop) {
			
			Population NewPop = new Population();
			int temp;
			int shouldAdd = 1;
			String routeString;
			
			for(int i = 0; i < Population.popsize; i++) {
				temp = GetBrightestFly(TempPop.fireFlies);
				FireFly tempFly = TempPop.fireFlies.get(temp);
				TempPop.fireFlies.remove(temp);
				routeString = tempFly.FireFlyt.getRouteString();
				shouldAdd = 1;
				
				for(int x=0; x < NewPop.fireFlies.size(); x++) {
					String newLS = NewPop.fireFlies.get(x).FireFlyt.getRouteString();
					if (routeString.equals(newLS)) {shouldAdd = 0; break;}
				}
				
				if(shouldAdd == 1) {NewPop.fireFlies.add(tempFly);}

			}
			
			return NewPop;
		}
	
	
	
	
	private FireFly XtoY(FindNewEdge edge, FireFly CurrentFly) {
	
			Vector<Integer> NSLout = new Vector<Integer>();
			NSLout = edge.NSLout;
			int SRindex = edge.SRindex;
			FireFly NewFly = new FireFly(CurrentFly);
		try {
			TSPNodes RightNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(SRindex);
			
			for(int i=0; i < NSLout.size(); i++) {
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSLout.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSLout.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, RightNode.getPrev());
				RightNode = tempNode;
			}
			ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
			//System.out.println("Old Fly-: " + CurrentFly.FireFlyt.getRouteString() + " cost is " + CurrentFly.FireFlyt.getCost());
			//System.out.println("New Fly1: " + NewFly.FireFlyt.getRouteString() + " cost is " + NewFly.FireFlyt.getCost());
		}
		catch(Exception e) {System.out.println("error in XtoY"); e.printStackTrace();}
		return NewFly;

	}
	
//=====

	private FireFly YtoX(FindNewEdge edge, FireFly CurrentFly) {
			Vector<Integer> NSRout = new Vector<Integer>();
			NSRout = edge.NSRout;
			int SLindex = edge.SLindex;
			FireFly NewFly = new FireFly(CurrentFly);
		try {
			TSPNodes LeftNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(SLindex);
			
			for(int i=0; i < NSRout.size(); i++) {
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSRout.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSRout.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, LeftNode);
				LeftNode = tempNode;
			}
			
			ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
			//System.out.println("Old Fly: " + CurrentFly.FireFlyt.getRouteString() + " cost is " + CurrentFly.FireFlyt.getCost());
			//System.out.println("New Fly2: " + NewFly.FireFlyt.getRouteString() + " cost is " + NewFly.FireFlyt.getCost());
		}
		catch(Exception e) {System.out.println("error in YtoX"); e.printStackTrace();}
		return NewFly;
	}
	
//====
	
	private FireFly XfromY(FindNewEdge edge, FireFly CurrentFly) {
			Vector<Integer> NSLin = new Vector<Integer>();
			Vector<Integer> NSRin = new Vector<Integer>();
			NSLin = edge.NSLin;
			NSRin = edge.NSRin;
			int SRindex = edge.SRindex;
			FireFly NewFly = new FireFly(CurrentFly);
		try {
			TSPNodes LeftNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(SRindex).getPrev();
			
			for(int i=0; i < NSRin.size(); i++) {
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSRin.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSRin.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, LeftNode);
				LeftNode = tempNode;
			}
			
			for(int i=0; i < NSLin.size(); i++) {	
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSLin.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSLin.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, LeftNode);
				LeftNode = tempNode;
			}
			
			ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
			//System.out.println("Old Fly: " + CurrentFly.FireFlyt.getRouteString() + " cost is " + CurrentFly.FireFlyt.getCost());
			//System.out.println("New Fly3: " + NewFly.FireFlyt.getRouteString() + " cost is " + NewFly.FireFlyt.getCost());
		}
		catch(Exception e) {System.out.println("error in XfromY"); e.printStackTrace();}
			return NewFly;
	}
	
//=====
	
	private FireFly YfromX(FindNewEdge edge, FireFly CurrentFly) {
			Vector<Integer> NSLin = new Vector<Integer>();
			Vector<Integer> NSRin = new Vector<Integer>();
			NSLin = edge.NSLin;
			NSRin = edge.NSRin;
			int SRindex = edge.SRindex;
			int SLindex = edge.SLindex;
			FireFly NewFly = new FireFly(CurrentFly);
		try {
			TSPNodes LeftNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSLin.get(0)).getPrev();
//Flips Y (NSRin) and puts before X			
			for(int i=0; i < NSRin.size(); i++) {
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSRin.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSRin.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, LeftNode);
				LeftNode = tempNode;
			}
//flips X after Y
			for(int i=0; i < NSLin.size(); i++) {	
				TSPNodes tempNode = (TSPNodes) NewFly.FireFlyt.getNodeByIndex(NSLin.get(i));
				NewFly.FireFlyt.removeNodeByIndex(NSLin.get(i));
				NewFly.FireFlyt.insertAfterNodes(tempNode, LeftNode);
				LeftNode = tempNode;
			}
			
			ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(NewFly.FireFlyt);
			//System.out.println("Old Fly: " + CurrentFly.FireFlyt.getRouteString() + " cost is " + CurrentFly.FireFlyt.getCost());
			//System.out.println("New Fly4: " + NewFly.FireFlyt.getRouteString() + " cost is " + NewFly.FireFlyt.getCost());
		}
		catch(Exception e) {System.out.println("error in YfromX"); e.printStackTrace();}
			return NewFly;
	}
	
}
