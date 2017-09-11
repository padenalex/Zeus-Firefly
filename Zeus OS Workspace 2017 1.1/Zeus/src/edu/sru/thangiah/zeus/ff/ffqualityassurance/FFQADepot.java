package edu.sru.thangiah.zeus.ff.ffqualityassurance;

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
public class FFQADepot
    extends QADepot
    implements java.io.Serializable, java.lang.Cloneable {
  public FFQADepot() {
  }

  public boolean checkDistanceConstraint(FFQADepot depot) {
    boolean status = true;
    for (int i = 0; i < getTrucks().size(); i++) {
      FFQATruck truck = (FFQATruck) getTrucks().elementAt(i);
      truck.checkDistanceConstraint(truck);
    }
    return status;
  }

  public boolean checkCapacityConstraint() {
    boolean status = true;
    for (int i = 0; i < getTrucks().size(); i++) {
      FFQATruck truck = (FFQATruck) getTrucks().elementAt(i);
      truck.checkCapacityConstraint(truck);
    }
    return status;
  }


}
