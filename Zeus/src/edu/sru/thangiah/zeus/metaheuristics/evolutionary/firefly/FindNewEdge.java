package edu.sru.thangiah.zeus.metaheuristics.evolutionary.firefly;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;


public class FindNewEdge {

	public int SLindex; 
	public int SRindex;
	public Vector<Integer> NSLin = new Vector<Integer>();  // --> <--
	public Vector<Integer> NSRin = new Vector<Integer>();  // --> <--
	public Vector<Integer> NSLout = new Vector<Integer>(); // <-- -->
	public Vector<Integer> NSRout = new Vector<Integer>(); // <-- -->
	
	FindNewEdge(FireFly BrightestFly, FireFly CurrentFly){
	
		Random rand = new Random();		
		int FirstIndex = 999999996;
		int SecondIndex = 999999996;
		int Check1 = 999999995;
		int Check2 = 999999995;
		int HowManyTimesRan = 0;
		int Run1 = 1;
		int Run2 = 1;
		int SecondLeft = 0;
try {		
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
			Check2 = CurrentFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev().getIndex();
			Check1 = CurrentFly.FireFlyt.getNodeByIndex(FirstIndex).getNext().getIndex();
			//System.out.println("Bright Index is: " +SecondIndex+ " Check1 is " +Check1+ " Check2 is " + Check2);
			
			if(Check1 != SecondIndex && Check2 != SecondIndex) {Run1 = 0;}
			HowManyTimesRan++;
			if(FireFly.FireflyDimension == HowManyTimesRan) {Run1 = 0;}
			//System.out.println("DoWhile For Find A Random Edge Not In List 2 Ran # Times: " + HowManyTimesRan);
		}
		
		//According to Brightest List this makes the left(first pos) X and right(second pos) Y
		if(SecondLeft == 1) {
			int temp = FirstIndex;
			FirstIndex = SecondIndex;
			SecondIndex = temp;
		}
		
//-- Sets left to current list's left
		int isleft=0;
		int isright=0;
		for(int i=0; i < FireFly.FireflyDimension; i++) {
			int temp = CurrentFly.FireFlyt.getNodesAtPosition(i).getIndex();
			if(temp==FirstIndex) {isleft=1; break;}
			if(temp==SecondIndex) {isright=1; break;}
		}
		//if(isleft==1) {System.out.println("first index is left");}
		if(isright==1) {
			int temp = FirstIndex;
			FirstIndex = SecondIndex;
			SecondIndex = temp;
			//System.out.println("first index is right");
		}
//--		
		
		
		this.SLindex = FirstIndex;
		this.SRindex = SecondIndex;
}//end try
catch(Exception e) {System.out.println("error inside FindNewEdge");}		
//Start NS out ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
try {
		//Build in feature if not of atleast one match then move random (invert)
		Run1 = 1;
		Run2 = 1;
		//Node Set Left
		this.NSLout.add(FirstIndex);
		while (CurrentFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev() != CurrentFly.FireFlyt.getHead()) {
			
			int temp1 = CurrentFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev().getIndex();
			int temp2 = BrightestFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev().getIndex();
			
			if(temp1 == temp2) {this.NSLout.add(CurrentFly.FireFlyt.getNodeByIndex(FirstIndex).getPrev().getIndex());}
			else {break;}
			FirstIndex = temp1;
		}
		//System.out.println("NSLout is " + this.NSLout);
		
//=====
		
		//Node Set Right
		this.NSRout.add(SecondIndex);
		while (CurrentFly.FireFlyt.getNodeByIndex(SecondIndex).getNext() != CurrentFly.FireFlyt.getTail()) {
			
			int temp1 = CurrentFly.FireFlyt.getNodeByIndex(SecondIndex).getNext().getIndex();
			int temp2 = BrightestFly.FireFlyt.getNodeByIndex(SecondIndex).getNext().getIndex();
			
			if(temp1 == temp2) {this.NSRout.add(CurrentFly.FireFlyt.getNodeByIndex(SecondIndex).getNext().getIndex());}
			else {break;}
			SecondIndex = temp1;
		}
		//System.out.println("NSRout is " + this.NSRout);
}
catch(Exception e) {System.out.println("error in NSout");}
//End NS out ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("Current route: " + CurrentFly.FireFlyt.getRouteString() + " cost is: " + CurrentFly.FireFlyt.getCost());
		//System.out.println("Best    route: " + BrightestFly.FireFlyt.getRouteString());
//Start NSin----------------------------------------------------------------------
try {
		this.NSLin = this.NSLout;
		this.NSRin = this.NSRout;
		Collections.reverse(this.NSLin);
		Collections.reverse(this.NSRin);

		//System.out.println("NSLin is " + this.NSLin);
		//System.out.println("NSRin is " + this.NSRin);
}
catch(Exception e) {System.out.println("error in NSout");}		
//End NSin------------------------------------------------------------------------

	}
}








