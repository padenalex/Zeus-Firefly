package edu.sru.thangiah.zeus.tsp.tspqualityassurance;

import edu.sru.thangiah.zeus.qualityassurance.*;


public class TSPQADepotLinkedList
    extends QADepotLinkedList
    implements java.io.Serializable, java.lang.Cloneable {
  public TSPQADepotLinkedList() {
  }

  public boolean checkDistanceConstraint() {
    boolean status = true;
    for (int i = 0; i < getDepots().size(); i++) {
      TSPQADepot depot = (TSPQADepot) getDepots().elementAt(i);
      status = status && depot.checkDistanceConstraint(depot);
    }
    return status;
  }

  public boolean checkCapacityConstraint() {
   boolean status = true;
   for (int i = 0; i < getDepots().size(); i++) {
     TSPQADepot depot = (TSPQADepot) getDepots().elementAt(i);
     status = status && depot.checkCapacityConstraint();
   }
   return status;
 }


}
