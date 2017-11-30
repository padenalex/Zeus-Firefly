package edu.sru.thangiah.zeus.tsp;

/**
*
* <p>Title:</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2005</p>
* <p>Company: </p>
* @author Sam R. Thangiah
* @version 2.0
* Changes: 9/6/2017 - Addition of readDataFromExcelFile() method
*/

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */


//import the parent class
import edu.sru.thangiah.zeus.core.ShipmentLinkedList;
import edu.sru.thangiah.zeus.core.Shipment;
import edu.sru.thangiah.zeus.core.ProblemInfo;

public class TSPShipmentLinkedList
    extends ShipmentLinkedList
    implements java.io.Serializable, java.lang.Cloneable {


	int[][] DistanceArray = (int[][]) ProblemInfo.distanceMatrix;
	int rowcounter = 0;
	Vector<Integer> IndexVisited = new Vector<>();
	
  /**
   * Constructor for the shipment linked list
   * Define it as below
   */
  public TSPShipmentLinkedList() 
  {
      setHead(new TSPShipment()); //header node for head
      setTail(new TSPShipment()); //tail node for tail
      linkHeadTail();			  //point head and tail to each other
      setNumShipments(0);
      
      
  }
  

  
  public void insertShipment(int num, float x, float y, int q, int d, int e, String type) {    //
		TSPShipment thisShip = new TSPShipment(num, x, y, d, q, e, type);		//
		//add the instance to the linked list - in this case it is added at the end of the list
		//the total number of shipments is incremented in the insert
		insertLast(thisShip);	
	}

	

/**
   * insert the shipment into the linked list
   * Constructor
   * @param ind index
   * @param x x-coordinate
   * @param y y-coordinate
   * @param q demand
   * @param d service time
   * @param e frequency
   * @param comb number of combination
   * @param vComb list of combinations (vector)
   * @param cuComb number of combinations (matrix)
   */

  public void insertShipment(int num, float x, float y, int q, int d, int e,
                             int comb, String type,
                             int[] vComb, int[][] cuComb) {
    if (vComb.length <= ProblemInfo.MAX_COMBINATIONS) {
      //create an instance of the Shipment
      TSPShipment thisShip = new TSPShipment(num, x, y, d, q, e, comb, type,
                                             vComb, cuComb);
      //add the instance to the linked list - in this case it is added at the end of the list
      //the total number of shipments is incremented in the insert
      insertLast(thisShip);
    }
    else {
      System.out.println(
          "TSPShipmentLinkedList: Maximum number of combinations exceeded");
    }
  }

  /**
   * Returns the first shipment in the linked list
   * @return first shipment
   */
  public TSPShipment getTSPHead() {
    return (TSPShipment) getHead();
  }
  
  /**
   * Returns the tail shipment in the linked list
   * @return first shipment
   */
  public TSPShipment getTSPTail() {
    return (TSPShipment) getTail();
  }


  /**
   * This method will get the next shipment that is to be inserted based on the
   * type of shipment selection that has been defined in the main method
   * for the variable ProblemInfo.selectShipType
   * @param currDepot current depot being considered for the shipment
   * @param currDepotLL Depot linked list of the problem being solved
   * @param currShipmentLL shipment linked list from which the shipment to be
   * insertion is to be selected
   * @return TSPShipment the shipment to be inserted
   */

  public TSPShipment getNextInsertShipment(TSPDepotLinkedList currDepotLL,
		  TSPDepot currDepot,
		  TSPShipmentLinkedList currShipmentLL,
		  TSPShipment currShip) {

	  TSPShipmentLinkedList selectShip = (TSPShipmentLinkedList) ProblemInfo.
			  selectShipType;
	  return selectShip.getSelectShipment(currDepotLL, currDepot, currShipmentLL,currShip);
  }

  /**
   * This is a stub - Leave it as it is
   * The concrere getSelectShipment will be declared by the class inheriting this
   * class and implementing the actual insertion of shipment  *
   * @param currShipmentLL shipment linked list from which the shipment to be
   * insertion is to be selected
   * @return TSPShipment the shipment to be inserted
   */

  public TSPShipment getSelectShipment(TSPDepotLinkedList currDepotLL,
		  TSPDepot currDepot,
		  TSPShipmentLinkedList currShipmentLL,
		  TSPShipment currShip) {
	  return null;
  }



  /**
   * Print out the shipment linked list to the console
   * @param out PrintStream stream to output to
   */
  public void printTSPShipmentsToConsole() {
	  System.out.println("Number of shipments: " + this.getNumShipments());
	  System.out.println("INDEX | X  |  Y");

	  Shipment ship = super.getHead();
	  TSPShipment tspShip;
	  while (ship != getTail())
	  {
		  tspShip = (TSPShipment)ship ;
		  System.out.println(tspShip.getIndex() + "   " + tspShip.getXCoord() + "  " +
				  tspShip.getYCoord());
		  ship = ship.getNext();
	  }
  }



  /**
   * Writes the shipments to the print stream
   * @param out PrintStream stream to output to
   */
  public void writeTSPShipments(PrintStream out) {
	  out.println(this.getNumShipments());


	  Shipment ship = super.getHead();
	  TSPShipment tspShip;
	  while (ship != getTail())
	  {
		  tspShip = (TSPShipment)ship ;
		  out.println(tspShip.getIndex() + " " + tspShip.getTruckTypeNeeded() + " " +
				  tspShip.getDemand() + " " + tspShip.getXCoord() + " " +
				  //ship.getYCoord() + " " + ship.getPickUpPointName() +
				  tspShip.getYCoord() + " " +
				  tspShip.getExtraVariable());
		  ship = ship.getNext();
	  }
  }
}


//Select the shipment with the shortest distance to the depot
class ClosestEuclideanDistToDepot
extends TSPShipmentLinkedList {
	  public TSPShipment getSelectShipment(TSPDepotLinkedList currDepotLL,
			  TSPDepot currDepot,
                                     TSPShipmentLinkedList currShipLL,
                                     TSPShipment currShip) {
  //currDepotLL is the depot linked list of the problem
  //currDepot is the depot under consideration
  //currShipLL is the set of avaialble shipments
  boolean isDiagnostic = false;
  //TSPShipment temp = (TSPShipment) getHead(); //point to the first shipment
  TSPShipment temp = (TSPShipment) currShipLL.getTSPHead().getNext(); //point to the first shipment
  TSPShipment foundShipment = null; //the shipment found with the criteria
  //double angle;
  //double foundAngle = 360; //initial value
  double distance;
  double foundDistance = 200; //initial distance
  double depotX, depotY;

  //Get the X and Y coordinate of the depot
  depotX = currDepot.getXCoord();
  depotY = currDepot.getYCoord();
  
  	
	    while (temp != currShipLL.getTSPTail()) {
	
	      //if the shipment is assigned, skip it
	      if (temp.getIsAssigned()) {
	        if (isDiagnostic) {
	          System.out.println("has been assigned");
	        }
	
	        temp = (TSPShipment) temp.getNext();
	
	        continue;
	      }
	/** @todo Associate the quadrant with the distance to get the correct shipment.
	       * Set up another insertion that takes the smallest angle and the smallest distance */
	      distance = calcDist(depotX, temp.getXCoord(), depotY, temp.getYCoord());

	
	      if (isDiagnostic) {
	        System.out.println("  " + distance);
	      }
	
	      //check if this shipment should be tracked
	      if (foundShipment == null) { //this is the first shipment being checked
	        foundShipment = temp;
	        foundDistance = distance;
	      }
	      else {
	        if (distance < foundDistance) { //found an angle that is less
	          foundShipment = temp;
	          foundDistance = distance;
	        }
	      }
	      temp = (TSPShipment) temp.getNext(); 
	    }


  return foundShipment; //stub
}
	  
	  
//The WhoAmI methods gives the id of the assigned object
//It is a static method so that it can be accessed without creating an object
public static String WhoAmI() {
 return("Selection Type: Closest euclidean distance to depot");
}

}


//==============================================================================================================

  //Select the shipment with the shortest distance to the depot
  class ClosestMatrixDist
  extends TSPShipmentLinkedList {
	  public TSPShipment getSelectShipment(TSPDepotLinkedList currDepotLL,
			  TSPDepot currDepot,
                                       TSPShipmentLinkedList currShipLL,
                                       TSPShipment currShip) {

   
    TSPShipment temp = (TSPShipment) currShipLL.getTSPHead().getNext(); //point to the first shipment
    TSPShipment foundShipment = null; //the shipment found with the criteria
    
    
    int size = DistanceArray.length; 
    
	int DistToSave = 10000; //Stores The Distance You'll Save (Greedy Distance)
	int DistToSaveIndex=999; //999 For QA Test
	DistToSave = 9999;
	
	//Greedy//
	for(int Counter = 0; Counter < size; Counter++) {
		if(DistanceArray[rowcounter][Counter] != 0) {
			if(!IndexVisited.contains(Counter) && !IndexVisited.contains(rowcounter)) {
				if(DistanceArray[rowcounter][Counter] < DistToSave) {
					DistToSave = DistanceArray[rowcounter][Counter];  //Set Current Smallest Value To DistToSave
					DistToSaveIndex = Counter;
				}
			}
		}
		
	} //End Times To Run Loop	

	IndexVisited.add(rowcounter);	
	System.out.println("The vector is: " + IndexVisited);
	
	//for(int Counter = 0; Counter < size; Counter++) {
	//	DistanceArray[rowcounter][Counter] = 0;
	//	DistanceArray[Counter][rowcounter] = 0;
	//}
	
	rowcounter = DistToSaveIndex;	
	
	//Print Out Array For 0 Check
	/*
	for(int s = 0; s<size; s++){
	    for(int w = 0; w<size; w++)
	    {
	        System.out.print(DistanceArray[s][w] + " ");
	    }
	    System.out.println();
	}
	*/
    
	for(int i = 0; i < rowcounter-1; i++) {
		temp = (TSPShipment) temp.getNext(); 
	}
	
	
	foundShipment = temp;
	
    return foundShipment; //stub
  }
	  
	  
	  
//-------------------------------------- WhoAmI
  //The WhoAmI methods gives the id of the assigned object
  //It is a static method so that it can be accessed without creating an object
 public static String WhoAmI() {
   return("Selection Type: Closest matrix distance to depot");
 }

}

//==============================================================================================================
  
  
//Select the shipment with the smallest polar coordinate angle to the depot
class SmallestPolarAngleToDepot
    extends TSPShipmentLinkedList {
  public TSPShipment getSelectShipment(TSPDepotLinkedList currDepotLL,
                                       TSPDepot currDepot,
                                       TSPShipmentLinkedList currShipLL,
                                       TSPShipment currShip) {
    //currDepotLL is the depot linked list of the problem
    //currDepot is the depot under consideration
    //currShipLL is the set of avaialble shipments
    boolean isDiagnostic = false;
    //TSPShipment temp = (TSPShipment) getHead(); //point to the first shipment
    TSPShipment temp = (TSPShipment) currShipLL.getTSPHead().getNext(); //point to the first shipment
   
    TSPShipment foundShipment = null; //the shipment found with the criteria
    double angle;
    double foundAngle = 360; //initial value
    //double distance;
    //double foundDistance = 200; //initial distance
    double depotX, depotY;
    int type = 2;
    


    //Get the X and Y coordinate of the depot
    depotX = currDepot.getXCoord();
    depotY = currDepot.getYCoord();

    while (temp != currShipLL.getTSPTail()) {

      if (isDiagnostic) {
    	  System.out.println("Temp is " +temp);
    	  System.out.println("Tail is " +getTail());
    	  System.out.print("Shipment " + temp.getIndex() + " ");

    	  if ( ( (temp.getXCoord() - depotX) >= 0) &&
    			  ( (temp.getYCoord() - depotX) >= 0)) {
    		  System.out.print("Quadrant I ");
    	  }
    	  else if ( ( (temp.getXCoord() - depotX) <= 0) &&
    			  ( (temp.getYCoord() - depotY) >= 0)) {
    		  System.out.print("Quadrant II ");
    	  }
    	  else if ( ( (temp.getXCoord()) <= (0 - depotX)) &&
    			  ( (temp.getYCoord() - depotY) <= 0)) {
    		  System.out.print("Quadrant III ");
    	  }
    	  else if ( ( (temp.getXCoord() - depotX) >= 0) &&
    			  ( (temp.getYCoord() - depotY) <= 0)) {
    		  System.out.print("Quadrant VI ");
    	  }
    	  else {
    		  System.out.print("No Quadrant");
    	  }
      }

      //if the shipment is assigned, skip it
      if (temp.getIsAssigned()) {
    	  if (isDiagnostic) {
    		  System.out.println("has been assigned");
    	  }

    	  temp = (TSPShipment) temp.getNext();

    	  continue;
      }

      angle = calcPolarAngle(depotX, depotX, temp.getXCoord(),
    		  temp.getYCoord());

      if (isDiagnostic) {
    	  System.out.println("  " + angle);
      }

      //check if this shipment should be tracked
      if (foundShipment == null) { //this is the first shipment being checked
    	  foundShipment = temp;
    	  foundAngle = angle;
      }
      else {
    	  if (angle < foundAngle) { //found an angle that is less
    		  foundShipment = temp;
    		  foundAngle = angle;
    	  }
      }
      temp =  (TSPShipment) temp.getNext();
    }
    return foundShipment; //stub
  }

  //The WhoAmI methods gives the id of the assigned object
  //It is a static method so that it can be accessed without creating an object
 public static String WhoAmI() {
   return("Selection Type: Smallest polar angle to the depot");
 }
}

//Select the shipment with the smallest polar coordinate angle and the
//shortest distance to the depot
class SmallestPolarAngleShortestDistToDepot
    extends TSPShipmentLinkedList {
  public TSPShipment getSelectShipment(TSPDepotLinkedList currDepotLL,
                                       TSPDepot currDepot,
                                       TSPShipmentLinkedList currShipLL,
                                       TSPShipment currShip) {
    //currDepotLL is the depot linked list of the problem
    //currDepot is the depot under consideration
    //currShipLL is the set of avaialble shipments
    boolean isDiagnostic = false;

    TSPShipment temp = (TSPShipment)currShipLL.getTSPHead().getNext(); //point to the first shipment
    TSPShipment foundShipment = null; //the shipment found with the criteria
    double angle;
    double foundAngle = 360; //initial value
    double distance;
    double foundDistance = 200; //initial distance
    double depotX, depotY;
    int type = 2;

    //Get the X and Y coordinate of the depot
    depotX = currDepot.getXCoord();
    depotY = currDepot.getYCoord();

    while (temp != currShipLL.getTSPTail()) {
      if (isDiagnostic) {
        System.out.print("Shipment " + temp.getIndex() + " ");

        if ( ( (temp.getXCoord() - depotX) >= 0) &&
            ( (temp.getYCoord() - depotX) >= 0)) {
          System.out.print("Quadrant I ");
        }
        else if ( ( (temp.getXCoord() - depotX) <= 0) &&
                 ( (temp.getYCoord() - depotY) >= 0)) {
          System.out.print("Quadrant II ");
        }
        else if ( ( (temp.getXCoord()) <= (0 - depotX)) &&
                 ( (temp.getYCoord() - depotY) <= 0)) {
          System.out.print("Quadrant III ");
        }
        else if ( ( (temp.getXCoord() - depotX) >= 0) &&
                 ( (temp.getYCoord() - depotY) <= 0)) {
          System.out.print("Quadrant VI ");
        }
        else {
          System.out.print("No Quadrant");
        }
      }

      //if the shipment is assigned, skip it
      if (temp.getIsAssigned()) {
        if (isDiagnostic) {
          System.out.println("has been assigned");
        }

        temp = (TSPShipment) temp.getNext();

        continue;
      }

      distance = calcDist(depotX, temp.getXCoord(), depotY, temp.getYCoord());
      angle = calcPolarAngle(depotX, depotX, temp.getXCoord(),
                             temp.getYCoord());

      if (isDiagnostic) {
        System.out.println("  " + angle);
      }

      //check if this shipment should be tracked
      if (foundShipment == null) { //this is the first shipment being checked
        foundShipment = temp;
        foundAngle = angle;
        foundDistance = distance;
      }
      else {
        //if angle and disnace are smaller than what had been found
        //if (angle <= foundAngle && distance <= foundDistance) {
        if (angle+ distance  <= foundAngle + foundDistance) {
        //if ((angle*.90)+ (distance * 0.1)  <= (foundAngle*0.9) + (foundDistance*0.1)) {
          foundShipment = temp;
          foundAngle = angle;
           foundDistance = distance;
        }
      }
      temp = (TSPShipment) temp.getNext();
    }
    return foundShipment; //stub
  }

  //The WhoAmI methods gives the id of the assigned object
  //It is a static method so that it can be accessed without creating an object
 public static String WhoAmI() {
   return("Selection Type: Smallest polar angle to the depot");
 }

}

