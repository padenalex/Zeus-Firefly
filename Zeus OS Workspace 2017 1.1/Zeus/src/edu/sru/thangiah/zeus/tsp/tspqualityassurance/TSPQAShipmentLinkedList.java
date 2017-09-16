package edu.sru.thangiah.zeus.tsp.tspqualityassurance;

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
public class TSPQAShipmentLinkedList
    extends QAShipmentLinkedList
    implements java.io.Serializable, java.lang.Cloneable {

  public TSPQAShipmentLinkedList() {
  }

  public boolean customerServicedOnlyOnce(TSPQADepotLinkedList qaDepots) {
    //loop through all the shipments and mark which are serviced and count the number of times
    //the customers are serviced. Each shipment should be serviced no more than once
    for (int i = 0; i < qaDepots.getDepots().size(); i++) {
      TSPQADepot d = (TSPQADepot) qaDepots.getDepots().elementAt(i);
      for (int j = 0; j < d.getTrucks().size(); j++) {
        TSPQATruck t = (TSPQATruck) d.getTrucks().elementAt(j);
        for (int k = 0; k < t.getNodes().size(); k++) {
          TSPQANode n = (TSPQANode) t.getNodes().elementAt(k);
          for (int l = 0; l < getShipments().size(); l++) {
            TSPQAShipment s = (TSPQAShipment) getShipments().elementAt(l);
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
      TSPQAShipment s = (TSPQAShipment) getShipments().elementAt(l);
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
