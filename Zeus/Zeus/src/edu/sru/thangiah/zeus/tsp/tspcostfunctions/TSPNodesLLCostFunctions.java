package edu.sru.thangiah.zeus.tsp.tspcostfunctions;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.TSPNodesLinkedList;

/**
 * Cost functions specific to TSPNodesLinkedList level
 * <p>Title: Zeus - A Unified Object Oriented Model for TSP's</p>
 * <p>Description: cost functions specific to TSP Nodes LinkedList level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class TSPNodesLLCostFunctions
    extends TSPAbstractCostFunctions implements
    java.io.Serializable  {

  public double getTotalCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalCost(o);

    return vNodes.getAttributes().getTotalCost();
  }

 

  public float getTotalDemand(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalDemand(o);

    return (int) vNodes.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalDistance(o);

    return vNodes.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalTravelTime(o);

    return vNodes.getAttributes().getMaxTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setMaxTravelTime(o);

    return vNodes.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setAvgTravelTime(o);

    return vNodes.getAttributes().getMaxTravelTime();
  }
  
  public int getTotalStops(Object o) 
  {
	    NodesLinkedList vNodes = (NodesLinkedList) o;
	    setTotalStops(o);

	    return vNodes.getAttributes().getTotalStops();
  }


  public void setTotalCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    double cost = 0;
    double dist = getTotalDistance(o);


    vNodes.getAttributes().setTotalCost(dist);
  }
  
  /*
   * Traverse through the linked and get the totalDemand for the route
   */
  public void setTotalDemand(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;

    Nodes tempCell = vNodes.getHead();
    int tempD = 0;

    while (tempCell != vNodes.getTail()) {
      tempD += tempCell.getDemand();
      tempCell = tempCell.getNext();
    }

    vNodes.getAttributes().setTotalDemand(tempD);
  }
  
  /*
   * Traverse through the linked and get the totalDistance for the route
   */

  public void setTotalDistance(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    float distTravelled = 0;
    Nodes left = vNodes.getHead();
    Nodes right = vNodes.getHead().getNext(); //node after head

    while (right != vNodes.getTail()) { //head){

      try {
        distTravelled += (float) Math.sqrt( (double) ( ( (right.getShipment()
            .getXCoord() -
            left.getShipment().getXCoord()) * (right.getShipment()
                                               .getXCoord() -
                                               left.getShipment().getXCoord())) +
            ( (right.getShipment().getYCoord() -
               left.getShipment().getYCoord()) * (right.getShipment()
                                                  .getYCoord() -
                                                  left.getShipment().getYCoord()))));
      }
      catch (ArithmeticException e) {
        System.out.println(e);
      }

      //System.out.println(distTravelled);
      left = right;
      right = right.getNext();
    }

    vNodes.getAttributes().setTotalDistance(distTravelled);
  }

  /*
   * Traverse through the route and obtain the totalTravelTime
   */
  
  public void setTotalTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;

    double travTime = 0;
    double dist = getTotalDistance(o);
    double milesPerMinute = ProblemInfo.averageBusSpeed / 60;
    travTime = milesPerMinute * dist;

    Nodes theCell = vNodes.getHead().getNext(); //start at the first customer

    while (theCell != vNodes.getTail()) {
      if (theCell.getShipment().getTruckTypeNeeded().equals(ProblemInfo.REGULAR)) {
        travTime += (ProblemInfo.R_PICK_UP_TIME * theCell.getDemand());
      }
      else if (theCell.getShipment().getTruckTypeNeeded().equals(ProblemInfo.
          WHEELCHAIR)) {
        travTime += (ProblemInfo.W_PICK_UP_TIME * theCell.getDemand());
      }
      else if (theCell.getShipment().getTruckTypeNeeded().equals(ProblemInfo.
          WHEELCHAIR_ELEVATOR)) {
        travTime += (ProblemInfo.WE_PICK_UP_TIME * theCell.getDemand());
      }
      else if (theCell.getShipment().getTruckTypeNeeded().equals(ProblemInfo.
          MONITOR)) {
        travTime += (ProblemInfo.M_PICK_UP_TIME * theCell.getDemand());
      }

      theCell = theCell.getNext();
    }

    vNodes.getAttributes().setMaxTravelTime(travTime);
  }

  public void setMaxTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;

    double linehaul = 0;

    vNodes.getAttributes().setMaxTravelTime(linehaul);
  }

  public void setAvgTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;

    //System.out.println("calculating average travel time");
    double aTT = 0;

 
    vNodes.getAttributes().setMaxTravelTime(aTT);
  }
  
  /*
   * Find the total number of stops made by this route This will be used in determining feasibility
   * The total number of stops is the count of the total number of partial shipments that start and end at different 
   * origins and destinations
   */
  public void setTotalStops(Object o) {

	 
	    NodesLinkedList vNodes = (NodesLinkedList) o;
	    Nodes left = vNodes.getHead().getNext();
	    int totalStops=0;
	    

	    while (left != vNodes.getTail()) { //head){
	    	 totalStops++;
	      //System.out.println(distTravelled);
	      left = left.getNext();
	    }
	    vNodes.getAttributes().setTotalStops(totalStops);
	  }



  /** @todo Might not need CrossRoadPenaltyCost and TurnAroundPenaltyCost */
  public void calculateTotalsStats(Object o) {
    setTotalDemand(o);
    setTotalDistance(o);
    setTotalTravelTime(o);
    setMaxTravelTime(o);
    setAvgTravelTime(o);
    setTotalCost(o);
  }
}
