package edu.sru.thangiah.zeus.tsp.tspcostfunctions;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.TSPTruck;


public class TSPTruckCostFunctions
    extends TSPAbstractCostFunctions implements
    java.io.Serializable  {

  public double getTotalCost(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setTotalCost(o);

    return truck.getAttributes().getTotalCost();
  }

 
  public float getTotalDemand(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setTotalDemand(o);

    return (int) truck.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setTotalDistance(o);

    return truck.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setTotalTravelTime(o);

    return truck.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setMaxTravelTime(o);

    return truck.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    setAvgTravelTime(o);

    return truck.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    TSPTruck truck = (TSPTruck) o;
	    setTotalStops(o);

	    return truck.getAttributes().getTotalStops();
}


  public void setTotalCost(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setTotalCost(ProblemInfo.nodesLLLevelCostF.getTotalCost(
        truck.getMainNodes()));
  }

 

  public void setTotalDemand(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setTotalDemand(ProblemInfo.nodesLLLevelCostF.getTotalDemand(
        truck.getMainNodes()));
  }

  public void setTotalDistance(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setTotalDistance(ProblemInfo.nodesLLLevelCostF.
        getTotalDistance(truck.getMainNodes()));
  }

  public void setTotalTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setTotalTravelTime(ProblemInfo.nodesLLLevelCostF.
        getTotalTravelTime(truck.getMainNodes()));
  }

  public void setMaxTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setMaxTravelTime(ProblemInfo.nodesLLLevelCostF.
        getMaxTravelTime(truck.getMainNodes()));
  }

  public void setAvgTravelTime(Object o) {
    TSPTruck truck = (TSPTruck) o;
    truck.getAttributes().setAvgTravelTime(ProblemInfo.nodesLLLevelCostF.
        getAvgTravelTime(truck.getMainNodes()));
  }
  
  public void setTotalStops(Object o) {
	    TSPTruck truck = (TSPTruck) o;
	    truck.getAttributes().setTotalStops(ProblemInfo.nodesLLLevelCostF.
	        getTotalStops(truck.getMainNodes()));
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
