package edu.sru.thangiah.zeus.tsp;

//import the parent class
import edu.sru.thangiah.zeus.core.DepotLinkedList;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class TSPDepotLinkedList
    extends DepotLinkedList
    implements java.io.Serializable, java.lang.Cloneable {

  public TSPDepotLinkedList() {
	//Housekeeping for the linked list
	setHead(new TSPDepot()); //header node for head
	setTail(new TSPDepot()); //tail node for tail
	linkHeadTail();			  //point head and tail to each other
			
	//Assign the attributes	
    setAttributes(new TSPAttributes());
  }

 
  /**
   * Attempts to insert the shipment into the depot linked list. Will loop
   * through the available depots until a depot is found that can accommodate
   * the shipment
   * @param theShipment the shipment to route
   * @return true if shipment serviced, false if not.
   */
  public boolean insertShipment(TSPShipment theShipment) {
    boolean status = false;

    TSPDepot depot = (TSPDepot)super.getHead().getNext();
    TSPTruckLinkedList truckLL = (TSPTruckLinkedList)depot.getMainTrucks();

    while (depot != this.getTail()) {
      //Get truck to insert the shipment
      truckLL = (TSPTruckLinkedList)depot.getMainTrucks();
      //status = depot.getMainTrucks().insertShipment(theShipment);
      status = truckLL.insertShipment(theShipment);

      if (status) {
        break;
      }
      depot = (TSPDepot) depot.getNext();
    }

    return status;
  }

  /**
 * Returns the first depot in the linked list
 * @return first depot
 */

public TSPDepot getTSPHead() {
 return (TSPDepot) getHead();
}


  /**
   * Returns an exact copy of the depot linked list
   * @return Object depot linked list copy
   */
  public Object clone() {
    TSPDepotLinkedList clonedDepotLinkedList = new TSPDepotLinkedList();

    clonedDepotLinkedList.setAttributes((TSPAttributes)this.getAttributes().clone());
    clonedDepotLinkedList.setHead((TSPDepot)this.getHead().clone());

    //must establish the links at this level to avoid recursing out of control
    clonedDepotLinkedList.getHead().setPrev(null);

    if (this.getHead() != this.getTail()) {
      TSPDepot currentDepot = (TSPDepot) clonedDepotLinkedList.getHead();
      TSPDepot nextDepot = (TSPDepot)this.getHead().getNext();

      while (nextDepot != null) {
        currentDepot.setNext( (TSPDepot) nextDepot.clone()); //create the next depot
        currentDepot.getNext().setPrev(currentDepot); //set the next depot's prev
        currentDepot = (TSPDepot) currentDepot.getNext();
        nextDepot = (TSPDepot) nextDepot.getNext();

        //once next is null, we have found the tail of the list
        if (nextDepot == null) {
          clonedDepotLinkedList.setTail(currentDepot);
          currentDepot.setNext(null);
        }

      }
    }
    else { //only 1 depot
      clonedDepotLinkedList.setTail(clonedDepotLinkedList.getHead());
    }
    return clonedDepotLinkedList;
  }
}
