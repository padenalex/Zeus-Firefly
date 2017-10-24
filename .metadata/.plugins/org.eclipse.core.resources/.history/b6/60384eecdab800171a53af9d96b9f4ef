package edu.sru.thangiah.zeus.tsp.tspqualityassurance;

import edu.sru.thangiah.zeus.qualityassurance.*;


public class TSPQADepot
    extends QADepot
    implements java.io.Serializable, java.lang.Cloneable {
  public TSPQADepot() {
  }

  public boolean checkDistanceConstraint(TSPQADepot depot) {
    boolean status = true;
    for (int i = 0; i < getTrucks().size(); i++) {
      TSPQATruck truck = (TSPQATruck) getTrucks().elementAt(i);
      truck.checkDistanceConstraint(truck);
    }
    return status;
  }

  public boolean checkCapacityConstraint() {
    boolean status = true;
    for (int i = 0; i < getTrucks().size(); i++) {
      TSPQATruck truck = (TSPQATruck) getTrucks().elementAt(i);
      truck.checkCapacityConstraint(truck);
    }
    return status;
  }


}
