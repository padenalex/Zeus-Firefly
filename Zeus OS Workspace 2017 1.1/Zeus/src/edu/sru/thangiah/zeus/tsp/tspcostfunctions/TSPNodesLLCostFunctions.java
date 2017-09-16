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

  /*public double getTotalConstraintCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalConstraintCost(o);

    return vNodes.getAttributes().totalConstraintCost;
     }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalCrossRoadPenaltyCost(o);

    return vNodes.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    setTotalTurnAroundPenaltyCost(o);

    return vNodes.getAttributes().totalTurnAroundPenaltyCost;
     }*/

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

    /* if (vNodes.getHead().getNext() != vNodes.getTail()) {
        cost = vNodes.getTruckType().getFixedCost();
        cost += (vNodes.getTruckType().getVariableCost() * dist);
            }

            vNodes.getAttributes().totalCost = cost;
     */

    vNodes.getAttributes().setTotalCost(dist);
  }

  /*public void setTotalConstraintCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    double cost = 0;
    double dist = getTotalDistance(o);

    if (vNodes.getHead().getNext() != vNodes.getTail()) {
      cost = vNodes.getTruckType().getFixedCost();
      cost += (vNodes.getTruckType().getVariableCost() * dist);
      cost += getAvgTravelTime(o);
      cost += getMaxTravelTime(o);
      cost += getTotalCrossRoadPenaltyCost(o);
      cost += getTotalTurnAroundPenaltyCost(o);
    }

    vNodes.getAttributes().totalConstraintCost = cost;
     }*/

  /* public void setTotalCrossRoadPenaltyCost(Object o) {
     TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
     double crossRoadPenaltyCost = 0;

       double actualRouteCost = getTotalCost(o);
       double fixedPenalty = 0;
       double variablePenalty = 0;
       Nodes current = vNodes.getHead();
       Nodes next = current.getNext();
       int currentDMI = 0;
       int nextDMI = 0;
       int lastDMI = 0;
       MatrixNode matrixNode;

       //if route is of size 2 or 3, then it is impossible to have a cross road
       //penalty cost.  so just return 0.
       if (vNodes.getSize() < 3) {
           vNodes.getAttributes().totalCrossRoadPenaltyCost = 0;

           return;
       }

       //loop through the complete route and check to see if there are students
       //being picked up that have to cross the road
       while ((current != null) && (next != null)) {
           //if getNext() point cell is a pick up point
           if (next.getType() == ProblemInfo.PICK_UP_POINT) {
               //get distance matrix indices for the two pick up points
               currentDMI = current.getDistanceMatrixIndex();
               nextDMI = next.getDistanceMatrixIndex();

               //make sure that they are not an indentical pick up point
               if (currentDMI != nextDMI) {
      matrixNode = SBTSPData.distanceMatrix.getDataAt(currentDMI, nextDMI);
                   lastDMI = currentDMI; //save this for later

                   //check to see if we need to impose a cross road penalty cost
                   if (matrixNode.imposeCrossRoadPenaltyCost()) {
                       fixedPenalty = SBTSPData.pickUpPoints.findPickUpPoint(next.getPickUpPointName()).getCrossRoadCostPenalty() * actualRouteCost;

      String busTypeNeeded = next.getShipment().getBusTypeNeeded();

                       //get variable cross road penalty cost based on type of student
                       //and how many of them there are
                       if (busTypeNeeded.equals(SBTSPConstants.REGULAR)) {
                           variablePenalty = next.getDemand() * SBTSPConstants.R_CROSS_ROAD_COST_PENALTY * actualRouteCost;
      } else if (busTypeNeeded.equals(SBTSPConstants.WHEELCHAIR)) {
                           variablePenalty = next.getDemand() * SBTSPConstants.W_CROSS_ROAD_COST_PENALTY * actualRouteCost;
      } else if (busTypeNeeded.equals(SBTSPConstants.WHEELCHAIR_ELEVATOR)) {
                           variablePenalty = next.getDemand() * SBTSPConstants.WE_CROSS_ROAD_COST_PENALTY * actualRouteCost;
      } else if (busTypeNeeded.equals(SBTSPConstants.MONITOR)) {
                           variablePenalty = next.getDemand() * SBTSPConstants.M_CROSS_ROAD_COST_PENALTY * actualRouteCost;
                       }

                       //add up total cross road penalty cost
   crossRoadPenaltyCost = crossRoadPenaltyCost + fixedPenalty + variablePenalty;
                   }
               } else { //currentDMI == nextDMI
                        //this is for cases where the route is expanded and the
                        //point cells could be multiple students in a row from
                        //the same pick up point.  fixed penalty was charged for
                        //the first student in the series, so now we just have to
                        //add the individual variable penalties of the other students
      matrixNode = SBTSPData.distanceMatrix.getDataAt(lastDMI, nextDMI);

                   //check to see if we need to impose a cross road penalty cost
                   if (matrixNode.imposeCrossRoadPenaltyCost()) {
      String busTypeNeeded = next.getShipment().getBusTypeNeeded();

                       //get variable cross road penalty cost based on type of student
                       //and how many of them there are
                       if (busTypeNeeded.equals(SBTSPConstants.REGULAR)) {
   variablePenalty = next.getDemand() * SBTSPConstants.R_CROSS_ROAD_COST_PENALTY;
      } else if (busTypeNeeded.equals(SBTSPConstants.WHEELCHAIR)) {
   variablePenalty = next.getDemand() * SBTSPConstants.W_CROSS_ROAD_COST_PENALTY;
      } else if (busTypeNeeded.equals(SBTSPConstants.WHEELCHAIR_ELEVATOR)) {
   variablePenalty = next.getDemand() * SBTSPConstants.WE_CROSS_ROAD_COST_PENALTY;
      } else if (busTypeNeeded.equals(SBTSPConstants.MONITOR)) {
   variablePenalty = next.getDemand() * SBTSPConstants.M_CROSS_ROAD_COST_PENALTY;
                       }

                       //add up total cross road penalty cost
      crossRoadPenaltyCost = crossRoadPenaltyCost + variablePenalty;
                   }
               }
           }

           current = next;
           next = current.getNext();
       }

     vNodes.getAttributes().totalCrossRoadPenaltyCost = crossRoadPenaltyCost = 0;
   }
   */
  /* public void setTotalTurnAroundPenaltyCost(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;
    double turnAroundPenaltyCost = 0;


             double actualRouteCost = getTotalCost(o);
             Nodes startPC = vNodes.getHead();
             Nodes destinationPC = startPC.getNext();
             int startIndex = 0;
             int destinationIndex;
             int[] traveledRoadSegments;
             int lastTraveledRoadSegment = -1;
             MatrixNode matrixNode;

             while ((startPC != null) && (destinationPC != null)) {
        startIndex = startPC.getDistanceMatrixIndex();
        destinationIndex = destinationPC.getDistanceMatrixIndex();

        if (startIndex != destinationIndex) {
   matrixNode = SBTSPData.distanceMatrix.getDataAt(startIndex, destinationIndex);

            if (startPC.getType() == SBTSPConstants.PICK_UP_POINT) {
     if (matrixNode.imposeTurnAroundPenaltyCost(lastTraveledRoadSegment)) {
                    turnAroundPenaltyCost += (SBTSPData.pickUpPoints.findPickUpPoint(matrixNode.getStartName()).getTurnAroundCostPenalty() * actualRouteCost);
                }
            }

            if (matrixNode.getFinishType() == SBTSPConstants.PICK_UP_POINT) {
                traveledRoadSegments = matrixNode.getSegmentArray();

                if (traveledRoadSegments.length != 1) {
   lastTraveledRoadSegment = traveledRoadSegments[traveledRoadSegments.length - 2];
                } else {
                    lastTraveledRoadSegment = -1;
                }
            }
        }

        startPC = destinationPC;
        destinationPC = startPC.getNext();
             }

    vNodes.getAttributes().totalTurnAroundPenaltyCost = turnAroundPenaltyCost;
     }
   */
  
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

    /*
        if (vNodes.getHead().getNext() != vNodes.getTail()) {
     double backhaul = ProblemInfo.distanceMatrix.getDataAt(vNodes.getHead()
     .getDistanceMatrixIndex(),
              vNodes.getHead().getNext().getDistanceMatrixIndex())
                                                      .getActualDistance();
          backhaul = (backhaul * ProblemInfo.averageBusSpeed) / 60;
          linehaul = getTotalTravelTime(o) - backhaul;
        }
     */
    vNodes.getAttributes().setMaxTravelTime(linehaul);
  }

  public void setAvgTravelTime(Object o) {
    TSPNodesLinkedList vNodes = (TSPNodesLinkedList) o;

    //System.out.println("calculating average travel time");
    double aTT = 0;

    /*
        if (vNodes.getHead().getNext() != vNodes.getTail()) {
          Nodes tempCell = vNodes.getHead().getNext();
          double haulTime = getTotalTravelTime(o);

          while (tempCell.getNext() != null) {
            //add the travel time while on the road (distance)
            double backhaul = 0;

            //if the first cell and the next cell are not the same pick up point
            if (tempCell.getDistanceMatrixIndex() != tempCell.getNext()
     .getDistanceMatrixIndex()) {
              MatrixNode node = ProblemInfo.distanceMatrix.getDataAt(tempCell.getDistanceMatrixIndex(),
                  tempCell.getNext().getDistanceMatrixIndex());
              backhaul = node.getDistance(vNodes.getTruckType().getTruckNo());

              backhaul = (backhaul * ProblemInfo.averageBusSpeed) / 60;

              aTT += (haulTime - backhaul);
              haulTime = haulTime - backhaul;
            }

            //add the travel time for the student getting on the bus
     if (tempCell.getShipment().getBusTypeNeeded().equals(ProblemInfo.REGULAR)) {
              aTT += (ProblemInfo.R_PICK_UP_TIME * tempCell.getDemand());
            } else if (tempCell.getShipment().getBusTypeNeeded().equals(ProblemInfo.WHEELCHAIR)) {
              aTT += (ProblemInfo.W_PICK_UP_TIME * tempCell.getDemand());
            } else if (tempCell.getShipment().getBusTypeNeeded().equals(ProblemInfo.WHEELCHAIR_ELEVATOR)) {
              aTT += (ProblemInfo.WE_PICK_UP_TIME * tempCell.getDemand());
            } else if (tempCell.getShipment().getBusTypeNeeded().equals(ProblemInfo.MONITOR)) {
              aTT += (ProblemInfo.M_PICK_UP_TIME * tempCell.getDemand());
            }

            tempCell = tempCell.getNext();
          }

          //System.out.println("average after getting on bus: " + aTT);
          //System.out.println("final average: " + aTT + "/" + (getSize()-2) + " = " + (aTT / (getSize()-2)));
          if (vNodes.isCollapsed()) {
            //System.out.println("collapsed size = " + vNodes.getCollapsedSize() + " regular size = " + vNodes.getSize());
            aTT = aTT / (vNodes.getCollapsedSize());
          } else {
            aTT = aTT / (vNodes.getSize() - 2);
          }
        }
     */
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
    //setTotalCrossRoadPenaltyCost(o);
    //setTotalTurnAroundPenaltyCost(o);
    setTotalCost(o);
    //setTotalConstraintCost(o);
  }
}
