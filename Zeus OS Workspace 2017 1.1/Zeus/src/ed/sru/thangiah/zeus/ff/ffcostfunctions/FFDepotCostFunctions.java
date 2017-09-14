package edu.sru.thangiah.zeus.ff.ffcostfunctions;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.ff.FFDepot;

/**
 * Cost Functions specific to the FF Depot level.
 * <p>Title: Zeus - A Unified Object Oriented Model for FF's</p>
 * <p>Description: cost functions specific to FFDepot level</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0

 */
public class FFDepotCostFunctions
    extends FFAbstractCostFunctions implements
    java.io.Serializable {

  public double getTotalCost(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalCost(o);

    return depot.getAttributes().getTotalCost();
  }

  /*public double getTotalConstraintCost(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalConstraintCost(o);

    return depot.getAttributes().totalConstraintCost;
     }*/

  /*public double getTotalCrossRoadPenaltyCost(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalCrossRoadPenaltyCost(o);

    return depot.getAttributes().totalCrossRoadPenaltyCost;
     }*/

  /*public double getTotalTurnAroundPenaltyCost(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalTurnAroundPenaltyCost(o);

    return depot.getAttributes().totalTurnAroundPenaltyCost;
     }*/

  public float getTotalDemand(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalDemand(o);

    return (int) depot.getAttributes().getTotalDemand();
  }

  public double getTotalDistance(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalDistance(o);

    return depot.getAttributes().getTotalDistance();
  }

  public double getTotalTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
    setTotalTravelTime(o);

    return depot.getAttributes().getTotalTravelTime();
  }

  public double getMaxTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
    setMaxTravelTime(o);

    return depot.getAttributes().getMaxTravelTime();
  }

  public double getAvgTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
    setAvgTravelTime(o);

    return depot.getAttributes().getAvgTravelTime();
  }
  
  public int getTotalStops(Object o) {
	    Depot depot = (Depot) o;
	    setTotalStops(o);

	    return depot.getAttributes().getTotalStops();
  }

  public void setTotalCost(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().setTotalCost(ProblemInfo.truckLLLevelCostF.getTotalCost(
        depot.getMainTrucks()));
  }

  /*public void setTotalConstraintCost(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().totalConstraintCost = ProblemInfo.truckLLLevelCostF.
        getTotalConstraintCost(depot.getMainTrucks());
     }*/

  /*public void setTotalCrossRoadPenaltyCost(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().totalCrossRoadPenaltyCost = ProblemInfo.truckLLLevelCostF.
        getTotalCrossRoadPenaltyCost(depot.getMainTrucks());
     }*/

  /* public void setTotalTurnAroundPenaltyCost(Object o) {
     FFDepot depot = (FFDepot) o;
   depot.getAttributes().totalTurnAroundPenaltyCost = ProblemInfo.truckLLLevelCostF.
         getTotalTurnAroundPenaltyCost(depot.getMainTrucks());
   }*/

  public void setTotalDemand(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().setTotalDemand((int) ProblemInfo.truckLLLevelCostF.
        getTotalDemand(depot.getMainTrucks()));
  }

  public void setTotalDistance(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().setTotalDistance((float) ProblemInfo.truckLLLevelCostF.
        getTotalDistance(depot.getMainTrucks()));
  }

  public void setTotalTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().setTotalTravelTime( ProblemInfo.truckLLLevelCostF.
        getTotalTravelTime(depot.getMainTrucks()));
  }

  public void setMaxTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
    depot.getAttributes().setMaxTravelTime(ProblemInfo.truckLLLevelCostF.
        getMaxTravelTime(depot.getMainTrucks()));
  }

  public void setAvgTravelTime(Object o) {
    FFDepot depot = (FFDepot) o;
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
