package edu.sru.thangiah.zeus.tsp.tspcostfunctions;

import edu.sru.thangiah.zeus.tsp.TSPDepot;
import edu.sru.thangiah.zeus.tsp.TSPDepotLinkedList;
import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.TSPDepotLinkedList;

/**
 * Cost functions specific to Depot LinkedList level
 * <p>Title: Zeus - A Unified Object Oriented Model for TSP's</p>
 * <p>Description: cost functions specific to Depot LinkedList level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class TSPDepotLLCostFunctions
    extends TSPAbstractCostFunctions implements
    java.io.Serializable {

  public double getTotalCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalCost(o);

    return depotLL.getAttributes().getTotalCost();
  }

  /* public double getTotalConstraintCost(Object o) {
     TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
     setTotalConstraintCost(o);

     return depotLL.getAttributes().totalConstraintCost;
   }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalCrossRoadPenaltyCost(o);

    return depotLL.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalTurnAroundPenaltyCost(o);

    return depotLL.getAttributes().totalTurnAroundPenaltyCost;
     }*/

  public float getTotalDemand(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalDemand(o);

    return (int) depotLL.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalDistance(o);

    return depotLL.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setTotalTravelTime(o);

    return depotLL.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setMaxTravelTime(o);

    return depotLL.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    setAvgTravelTime(o);

    return depotLL.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
	    setTotalStops(o);
	    return depotLL.getAttributes().getTotalStops();
  }


  public void setTotalCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    double cost = 0;

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      cost += ProblemInfo.depotLevelCostF.getTotalCost(d);
      d = d.getNext();
    }

    depotLL.getAttributes().setTotalCost(cost);
  }

  /*public void setTotalConstraintCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    double cost = 0;

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      cost += ProblemInfo.depotLevelCostF.getTotalConstraintCost(d);
      d = d.getNext();
    }

    depotLL.getAttributes().totalConstraintCost = cost;
     }*/

  /*public void setTotalCrossRoadPenaltyCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    depotLL.getAttributes().totalCrossRoadPenaltyCost = 0;

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      depotLL.getAttributes().totalCrossRoadPenaltyCost += ProblemInfo.
          depotLevelCostF.getTotalCrossRoadPenaltyCost(d);
      d = d.getNext();
    }
     }*/

  /*public void setTotalTurnAroundPenaltyCost(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    depotLL.getAttributes().totalTurnAroundPenaltyCost = 0;

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      depotLL.getAttributes().totalTurnAroundPenaltyCost += ProblemInfo.
          depotLevelCostF.getTotalTurnAroundPenaltyCost(d);
      d = d.getNext();
    }
     }*/

  public void setTotalDemand(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    depotLL.getAttributes().setTotalDemand(0);

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      depotLL.getAttributes().setTotalDemand(depotLL.getAttributes().getTotalDemand() + (float) ProblemInfo.depotLevelCostF.
          getTotalDemand(d));
      d = d.getNext();
    }
  }

  public void setTotalDistance(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    depotLL.getAttributes().setTotalDistance(0);

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      depotLL.getAttributes().setTotalDistance(depotLL.getAttributes().getTotalDistance()  + (float) ProblemInfo.depotLevelCostF.
          getTotalDistance(d));
      d = d.getNext();
    }
  }

  public void setTotalTravelTime(Object o) {
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
     depotLL.getAttributes().setTotalTravelTime(0);

    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      depotLL.getAttributes().setTotalTravelTime(depotLL.getAttributes().getTotalTravelTime() + ProblemInfo.depotLevelCostF.
          getTotalTravelTime(d));
      d = d.getNext();
    }
  }

  public void setMaxTravelTime(Object o) {
    double max = 0;
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    Depot d = depotLL.getHead();

    while (d != depotLL.getTail()) {
      if (ProblemInfo.depotLevelCostF.getMaxTravelTime(d) > max) {
        max = ProblemInfo.depotLevelCostF.getMaxTravelTime(d);
      }

      d = d.getNext();
    }

    depotLL.getAttributes().setMaxTravelTime(max);
  }

  public void setAvgTravelTime(Object o) {
    double avg = 0;
    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
    Depot d = depotLL.getHead();

    if (ProblemInfo.depotLLLevelCostF.getTotalDemand(depotLL) != 0) {
      while (d != depotLL.getTail()) {
        avg +=
            (ProblemInfo.depotLevelCostF.getAvgTravelTime(d) *
             ProblemInfo.depotLevelCostF.getTotalDemand(d));
        d = d.getNext();
      }

      depotLL.getAttributes().setAvgTravelTime(avg /
          ProblemInfo.depotLLLevelCostF.getTotalDemand(depotLL));
    }
    else {
      depotLL.getAttributes().setAvgTravelTime(0);
    }
  }
  
  public void setTotalStops(Object o) {
	    TSPDepotLinkedList depotLL = (TSPDepotLinkedList) o;
	    depotLL.getAttributes().setTotalStops(0);

	    TSPDepot d = (TSPDepot) depotLL.getHead().getNext();

	    while (d != depotLL.getTail()) {
	      depotLL.getAttributes().setTotalStops (depotLL.getAttributes().getTotalStops()  + (int) ProblemInfo.depotLevelCostF.
	          getTotalStops(d));
	      d = (TSPDepot) d.getNext();
	    }
  }


  /** @todo Might not need CrossRoadPenaltyCost and TurnAroundPenaltyCost */
  public void calculateTotalsStats(Object o) {
	boolean isDiagnostic=true;
    setTotalDemand(o);
    setTotalDistance(o);
    setTotalTravelTime(o);
    setMaxTravelTime(o);
    setAvgTravelTime(o);
    //setTotalCrossRoadPenaltyCost(o);
    //setTotalTurnAroundPenaltyCost(o);
    setTotalCost(o);
    //setTotalConstraintCost(o);
    if (isDiagnostic)
    {
    	System.out.println("Total Demand = "+getTotalDemand(o));
    	System.out.println("Total Distance = "+getTotalDistance(o));
    	System.out.println("Total TravelTime = "+getTotalTravelTime(o));
    	System.out.println("MaxTravel Time = "+getMaxTravelTime(o));
    	System.out.println("Average Travel Time = "+getAvgTravelTime(o));
    }
    
  }
}
