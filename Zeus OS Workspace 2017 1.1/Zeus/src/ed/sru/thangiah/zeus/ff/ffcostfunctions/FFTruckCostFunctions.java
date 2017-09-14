package edu.sru.thangiah.zeus.ff.ffcostfunctions;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.ff.FFTruck;

/**
 * Cost functions specific to FFTruck level
 * <p>Title: Zeus - A Unified Object Oriented Model for FF's</p>
 * <p>Description: cost functions specific to FF Truck level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
public class FFTruckCostFunctions
    extends FFAbstractCostFunctions implements
    java.io.Serializable  {

  public double getTotalCost(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalCost(o);

    return truck.getAttributes().getTotalCost();
  }

  /* public double getTotalConstraintCost(Object o) {
     FFTruck truck = (FFTruck) o;
     setTotalConstraintCost(o);

     return truck.getAttributes().totalConstraintCost;
   }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalCrossRoadPenaltyCost(o);

    return truck.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalTurnAroundPenaltyCost(o);

    return truck.getAttributes().totalTurnAroundPenaltyCost;
     }*/

  public float getTotalDemand(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalDemand(o);

    return (int) truck.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalDistance(o);

    return truck.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    setTotalTravelTime(o);

    return truck.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    setMaxTravelTime(o);

    return truck.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    setAvgTravelTime(o);

    return truck.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    FFTruck truck = (FFTruck) o;
	    setTotalStops(o);

	    return truck.getAttributes().getTotalStops();
}


  public void setTotalCost(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setTotalCost(ProblemInfo.nodesLLLevelCostF.getTotalCost(
        truck.getMainNodes()));
  }

  /*public void setTotalConstraintCost(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().totalConstraintCost = ProblemInfo.nodesLLLevelCostF.
        getTotalConstraintCost(truck.getMainNodes());
     }*/

  /*public void setTotalCrossRoadPenaltyCost(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().totalCrossRoadPenaltyCost = ProblemInfo.nodesLLLevelCostF.
        getTotalCrossRoadPenaltyCost(truck.getMainNodes());
     }*/

  /*public void setTotalTurnAroundPenaltyCost(Object o) {
    FFTruck truck = (FFTruck) o;
   truck.getAttributes().totalTurnAroundPenaltyCost = ProblemInfo.nodesLLLevelCostF.
        getTotalTurnAroundPenaltyCost(truck.getMainNodes());
     }*/

  public void setTotalDemand(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setTotalDemand(ProblemInfo.nodesLLLevelCostF.getTotalDemand(
        truck.getMainNodes()));
  }

  public void setTotalDistance(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setTotalDistance(ProblemInfo.nodesLLLevelCostF.
        getTotalDistance(truck.getMainNodes()));
  }

  public void setTotalTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setTotalTravelTime(ProblemInfo.nodesLLLevelCostF.
        getTotalTravelTime(truck.getMainNodes()));
  }

  public void setMaxTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setMaxTravelTime(ProblemInfo.nodesLLLevelCostF.
        getMaxTravelTime(truck.getMainNodes()));
  }

  public void setAvgTravelTime(Object o) {
    FFTruck truck = (FFTruck) o;
    truck.getAttributes().setAvgTravelTime(ProblemInfo.nodesLLLevelCostF.
        getAvgTravelTime(truck.getMainNodes()));
  }
  
  public void setTotalStops(Object o) {
	    FFTruck truck = (FFTruck) o;
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
    //setTotalCrossRoadPenaltyCost(o);
    //setTotalTurnAroundPenaltyCost(o);
    setTotalCost(o);
    //setTotalConstraintCost(o);
  }
}
