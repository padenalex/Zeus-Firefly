package edu.sru.thangiah.zeus.tsp.tspcostfunctions;

import edu.sru.thangiah.zeus.tsp.TSPTruck;
import edu.sru.thangiah.zeus.tsp.TSPTruckLinkedList;
import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.*;

/**
 * Cost functions specific to TSP Truck Linked List level
 * <p>Title: Zeus - A Unified Object Oriented Model for TSP's</p>
 * <p>Description: cost functions specific to TSP Truck Linked List level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class TSPTruckLLCostFunctions
    extends TSPAbstractCostFunctions implements
    java.io.Serializable  {

  public double getTotalCost(Object o) {
    TSPTruckLinkedList tLL = (TSPTruckLinkedList) o;
    setTotalCost(o);

    return tLL.getAttributes().getTotalCost();
  }

  /* public double getTotalConstraintCost(Object o) {
     TSPTruckLinkedList tLL = (TSPTruckLinkedList) o;
     setTotalConstraintCost(o);

     return tLL.getAttributes().totalConstraintCost;
   }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    TSPTruckLinkedList tLL = (TSPTruckLinkedList) o;
    setTotalCrossRoadPenaltyCost(o);

    return tLL.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    TSPTruckLinkedList tLL = (TSPTruckLinkedList) o;
    setTotalTurnAroundPenaltyCost(o);

    return tLL.getAttributes().totalTurnAroundPenaltyCost;
     }*/

  public float getTotalDemand(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    setTotalDemand(o);

    return (int) truckLL.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    setTotalDistance(o);

    return truckLL.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    setTotalTravelTime(o);

    return truckLL.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    setMaxTravelTime(o);

    return truckLL.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    setAvgTravelTime(o);

    return truckLL.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
	    setTotalStops(o);
	    return truckLL.getAttributes().getTotalStops();
  }


  public void setTotalCost(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    truckLL.getAttributes().setTotalCost(0);

    Truck t = truckLL.getHead();

    while (t != truckLL.getTail()) {
      if (!t.isEmptyMainNodes()) {
        truckLL.getAttributes().setTotalCost(truckLL.getAttributes().getTotalCost() + ProblemInfo.truckLevelCostF.
            getTotalCost(t));
      }

      t = t.getNext();
    }
  }

  /*public void setTotalConstraintCost(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    truckLL.getAttributes().totalConstraintCost = 0;

    Truck t = truckLL.getHead();

    while (t != getTail()) {
      if (!t.isEmpty()) {
        truckLL.getAttributes().totalConstraintCost += ProblemInfo.truckLevelCostF.
            getTotalConstraintCost(t);
      }

      t = t.getNext();
    }
     }*/

  /*public void setTotalCrossRoadPenaltyCost(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    truckLL.getAttributes().totalCrossRoadPenaltyCost = 0;

    Truck t = truckLL.getHead();

    while (t != getTail()) {
      if (!t.isEmpty()) {
        truckLL.getAttributes().totalCrossRoadPenaltyCost += ProblemInfo.
            truckLevelCostF.getTotalCrossRoadPenaltyCost(t);
      }

      t = t.getNext();
    }
     }*/

  /* public void setTotalTurnAroundPenaltyCost(Object o) {
     TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
     truckLL.getAttributes().totalTurnAroundPenaltyCost = 0;

     Truck t = truckLL.getHead();

     while (t != getTail()) {
       if (!t.isEmpty()) {
         truckLL.getAttributes().totalTurnAroundPenaltyCost += ProblemInfo.
             truckLevelCostF.getTotalTurnAroundPenaltyCost(t);
       }

       t = t.getNext();
     }
   }*/

  public void setTotalDemand(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    truckLL.getAttributes().setTotalDemand(0);

    Truck t = truckLL.getHead();

    while (t != truckLL.getTail()) {
      if (!t.isEmptyMainNodes()) {
        truckLL.getAttributes().setTotalDemand(truckLL.getAttributes().getTotalDemand() + ProblemInfo.truckLevelCostF.
            getTotalDemand(t));
      }

      t = t.getNext();
    }
  }

  public void setTotalDistance(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    //IMPORTANT: Distance has to be set to 0
    truckLL.getAttributes().setTotalDistance(0);
    //truckLL.getAttributes().getTotalDistance();



    Truck t = truckLL.getHead();

    while (t != truckLL.getTail()) {
      if (!t.isEmptyMainNodes()) {
        truckLL.getAttributes().setTotalDistance(truckLL.getAttributes().getTotalDistance() + ProblemInfo.truckLevelCostF.
            getTotalDistance(t));
      }

      t = t.getNext();
    }
  }

  public void setTotalTravelTime(Object o) {
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    truckLL.getAttributes().setTotalTravelTime(0);

    Truck t = truckLL.getHead();

    while (t != truckLL.getTail()) {
      if (!t.isEmptyMainNodes()) {
        truckLL.getAttributes().setTotalTravelTime(truckLL.getAttributes().getTotalTravelTime() + ProblemInfo.truckLevelCostF.
            getTotalTravelTime(t));
      }

      t = t.getNext();
    }
  }

  public void setMaxTravelTime(Object o) {
    double max = 0;
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    Truck t = truckLL.getHead();

    while (t != truckLL.getTail()) {
      if (!t.isEmptyMainNodes()) {
        if (ProblemInfo.truckLevelCostF.getMaxTravelTime(t) > max) {
          max = ProblemInfo.truckLevelCostF.getMaxTravelTime(t);
        }
      }

      t = t.getNext();
    }

    truckLL.getAttributes().setMaxTravelTime(max);
  }

  public void setAvgTravelTime(Object o) {
    double avg = 0;
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
    Truck t = truckLL.getHead();

    if ( (truckLL.getSize() != 0) &&
        (ProblemInfo.truckLLLevelCostF.getTotalDemand(truckLL) != 0)) {
      while (t != truckLL.getTail()) {
        if (!t.isEmptyMainNodes()) {
          avg +=
              (ProblemInfo.truckLevelCostF.getAvgTravelTime(t) *
               ProblemInfo.truckLevelCostF.getTotalDemand(t));
        }

        t = t.getNext();
      }

      truckLL.getAttributes().setAvgTravelTime(avg /
          ProblemInfo.truckLLLevelCostF.getTotalDemand(truckLL));
    }
    else {
      truckLL.getAttributes().setAvgTravelTime(0);
    }
  }
  
  public void setTotalStops(Object o) {
	    TSPTruckLinkedList truckLL = (TSPTruckLinkedList) o;
	    truckLL.getAttributes().setTotalStops(0);

	    TSPTruck t = (TSPTruck) truckLL.getHead().getNext();

	    while (t != truckLL.getTail()) {
	      if (!t.isEmptyMainNodes()) {
	        truckLL.getAttributes().setTotalStops(ProblemInfo.truckLevelCostF.getTotalStops(t));
	      }

	      t = (TSPTruck) t.getNext();
	    }
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
