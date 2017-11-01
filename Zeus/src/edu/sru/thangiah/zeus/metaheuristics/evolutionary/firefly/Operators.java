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
		
		for(int i=0; i < Population.TotalGen; i++) {
			FireFly Brightest = fireFlies.get(GetBrightestFly(fireFlies));	
			
			for(int x=0; x < listsize; x++) {

				//XtowardsY()
				//XFromY(fireFlies.index(x)
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
	
}
