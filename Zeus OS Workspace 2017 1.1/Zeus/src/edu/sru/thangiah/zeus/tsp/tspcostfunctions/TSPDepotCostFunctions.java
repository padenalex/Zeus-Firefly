package edu.sru.thangiah.zeus.tsp.tspcostfunctions;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.TSPDepot;

/**
 * Cost Functions specific to the TSP Depot level.
 * <p>Title: Zeus - A Unified Object Oriented Model for TSP's</p>
 * <p>Description: cost functions specific to TSPDepot level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0

 */
public class TSPDepotCostFunctions
    extends TSPAbstractCostFunctions implements
    java.io.Serializable {

  public double getTotalCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalCost(o);

    return depot.getAttributes().getTotalCost();
  }

  /*public double getTotalConstraintCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalConstraintCost(o);

    return depot.getAttributes().totalConstraintCost;
     }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalCrossRoadPenaltyCost(o);

    return depot.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalTurnAroundPenaltyCost(o);

    return depot.getAttributes().totalTurnAroundPenaltyCost;
     }*/

  public float getTotalDemand(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalDemand(o);

    return (int) depot.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalDistance(o);

    return depot.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setTotalTravelTime(o);

    return depot.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setMaxTravelTime(o);

    return depot.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    setAvgTravelTime(o);

    return depot.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    Depot depot = (Depot) o;
	    setTotalStops(o);

	    return depot.getAttributes().getTotalStops();
  }

  public void setTotalCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setTotalCost(ProblemInfo.truckLLLevelCostF.getTotalCost(
        depot.getMainTrucks()));
  }

  /*public void setTotalConstraintCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().totalConstraintCost = ProblemInfo.truckLLLevelCostF.
        getTotalConstraintCost(depot.getMainTrucks());
     }*/

  /*public void setTotalCrossRoadPenaltyCost(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().totalCrossRoadPenaltyCost = ProblemInfo.truckLLLevelCostF.
        getTotalCrossRoadPenaltyCost(depot.getMainTrucks());
     }*/

  /* public void setTotalTurnAroundPenaltyCost(Object o) {
     TSPDepot depot = (TSPDepot) o;
   depot.getAttributes().totalTurnAroundPenaltyCost = ProblemInfo.truckLLLevelCostF.
         getTotalTurnAroundPenaltyCost(depot.getMainTrucks());
   }*/

  public void setTotalDemand(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setTotalDemand((int) ProblemInfo.truckLLLevelCostF.
        getTotalDemand(depot.getMainTrucks()));
  }

  public void setTotalDistance(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setTotalDistance((float) ProblemInfo.truckLLLevelCostF.
        getTotalDistance(depot.getMainTrucks()));
  }

  public void setTotalTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setTotalTravelTime( ProblemInfo.truckLLLevelCostF.
        getTotalTravelTime(depot.getMainTrucks()));
  }

  public void setMaxTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setMaxTravelTime(ProblemInfo.truckLLLevelCostF.
        getMaxTravelTime(depot.getMainTrucks()));
  }

  public void setAvgTravelTime(Object o) {
    TSPDepot depot = (TSPDepot) o;
    depot.getAttributes().setAvgTravelTime(ProblemInfo.truckLLLevelCostF.
        getAvgTravelTime(depot.getMainTrucks()));
  }
  
  public void setTotalStops(Object o) {
	    Depot depot = (Depot) o;
	    depot.getAttributes().setTotalStops((int) ProblemInfo.truckLLLevelCostF.
	        getTotalStops(depot.getMainTrucks()));
  }
  

  /** @todo Might not need CrossRoadPenaltyCost and TurnAroundPenaltyCost */
  public void calculateTotalsStats(Object o) {
    setTotalDemand(o);
    setTotalDistance(o);
    setTotalTravelTime(o);
    setMaxTravelTime(o);
    setAvgTravelTime(o);
    //setTotalCrossRoadPenaltyCost(o);
    // setTotalTurnAroundPenaltyCost(o);
    setTotalCost(o);
    //setTotalConstraintCost(o);
  }
}
