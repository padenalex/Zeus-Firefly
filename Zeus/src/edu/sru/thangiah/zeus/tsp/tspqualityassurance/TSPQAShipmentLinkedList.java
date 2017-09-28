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

  public boolean customerServicedOnlyOnce(TSPQANodesLinkedList qaNodes) {
    //loop through all the shipments and mark which are serviced and count the number of times
    //the customers are serviced. Each shipment should be serviced no more than once
    for (int k = 0; k < qaNodes.getNodes().size(); k++)
    {
    	TSPQANode n = (TSPQANode) qaNodes.getNodes().elementAt(k);
    	for (int l = 0; l < getShipments().size(); l++)
    	{
    		TSPQAShipment s = (TSPQAShipment) getShipments().elementAt(l);
            if (s.getIndex() == n.getIndex())
            {
              s.setServecount(s.getServecount()+1);
              break;
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
