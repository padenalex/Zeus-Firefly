package edu.sru.thangiah.zeus.ff;

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

public class FFDepotLinkedList
    extends DepotLinkedList
    implements java.io.Serializable, java.lang.Cloneable {

  public FFDepotLinkedList() {
	//Housekeeping for the linked list
	setHead(new FFDepot()); //header node for head
	setTail(new FFDepot()); //tail node for tail
	linkHeadTail();			  //point head and tail to each other
			
	//Assign the attributes	
    setAttributes(new FFAttributes());
  }

 
  /**
   * Attempts to insert the shipment into the depot linked list. Will loop
   * through the available depots until a depot is found that can accommodate
   * the shipment
   * @param theShipment the shipment to route
   * @return true if shipment serviced, false if not.
   */
  public boolean insertShipment(FFShipment theShipment) {
    boolean status = false;

    FFDepot depot = (FFDepot)super.getHead().getNext();
    FFTruckLinkedList truckLL = (FFTruckLinkedList)depot.getMainTrucks();

    while (depot != this.getTail()) {
      //Get truck to insert the shipment
      truckLL = (FFTruckLinkedList)depot.getMainTrucks();
      //status = depot.getMainTrucks().insertShipment(theShipment);
      status = truckLL.insertShipment(theShipment);

      if (status) {
        break;
      }
      depot = (FFDepot) depot.getNext();
    }

    return status;
  }

  /**
 * Returns the first depot in the linked list
 * @return first depot
 */

public FFDepot getFFHead() {
 return (FFDepot) getHead();
}


  /**
   * Returns an exact copy of the depot linked list
   * @return Object depot linked list copy
   */
  public Object clone() {
    FFDepotLinkedList clonedDepotLinkedList = new FFDepotLinkedList();

    clonedDepotLinkedList.setAttributes((FFAttributes)this.getAttributes().clone());
    clonedDepotLinkedList.setHead((FFDepot)this.getHead().clone());

    //must establish the links at this level to avoid recursing out of control
    clonedDepotLinkedList.getHead().setPrev(null);

    if (this.getHead() != this.getTail()) {
      FFDepot currentDepot = (FFDepot) clonedDepotLinkedList.getHead();
      FFDepot nextDepot = (FFDepot)this.getHead().getNext();

      while (nextDepot != null) {
        currentDepot.setNext( (FFDepot) nextDepot.clone()); //create the next depot
        currentDepot.getNext().setPrev(currentDepot); //set the next depot's prev
        currentDepot = (FFDepot) currentDepot.getNext();
        nextDepot = (FFDepot) nextDepot.getNext();

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
