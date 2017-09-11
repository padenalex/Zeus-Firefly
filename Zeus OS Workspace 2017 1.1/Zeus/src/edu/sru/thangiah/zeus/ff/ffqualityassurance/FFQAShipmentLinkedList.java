package edu.sru.thangiah.zeus.ff.ffqualityassurance;

import java.util.Vector;
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
public class FFQAShipmentLinkedList
    extends QAShipmentLinkedList
    implements java.io.Serializable, java.lang.Cloneable {

  public FFQAShipmentLinkedList() {
  }

  public boolean customerServicedOnlyOnce(FFQADepotLinkedList qaDepots) {
    //loop through all the shipments and mark which are serviced and count the number of times
    //the customers are serviced. Each shipment should be serviced no more than once
    for (int i = 0; i < qaDepots.getDepots().size(); i++) {
      FFQADepot d = (FFQADepot) qaDepots.getDepots().elementAt(i);
      for (int j = 0; j < d.getTrucks().size(); j++) {
        FFQATruck t = (FFQATruck) d.getTrucks().elementAt(j);
        for (int k = 0; k < t.getNodes().size(); k++) {
          FFQANode n = (FFQANode) t.getNodes().elementAt(k);
          for (int l = 0; l < getShipments().size(); l++) {
            FFQAShipment s = (FFQAShipment) getShipments().elementAt(l);
            if (s.getIndex() == n.getIndex()) {
              s.setServecount(s.getServecount()+1);
              break;
            }
          }
        }
      }
    }

    boolean ret = true;
    //loop through shipments and look for anomolies
    for (int l = 0; l < getShipments().size(); l++) {
      FFQAShipment s = (FFQAShipment) getShipments().elementAt(l);
      if (s.getServecount() != 1) {
        edu.sru.thangiah.zeus.core.Settings.printDebug(edu.sru.thangiah.zeus.
            core.Settings.ERROR,
            "Shipment " + s.getIndex() + " is serviced " + s.getServecount() + " time(s)");
        ret = false;
      }
    }

    return ret;
  }

}
