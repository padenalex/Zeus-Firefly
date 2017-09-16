package edu.sru.thangiah.zeus.tsp.tspqualityassurance;

import edu.sru.thangiah.zeus.qualityassurance.*;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
/** @todo Need to document the variables and the parameters */
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
