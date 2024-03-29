package edu.sru.thangiah.zeus.tsp;

/**
*
* <p>Title:</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2005</p>
* <p>Company: </p>
* @author Sam R. Thangiah
* @version 2.0
* Changes: 9/6/2017 - Addition of readDataFromExcelFile() method
*/

//import the parent class
import edu.sru.thangiah.zeus.core.TruckLinkedList;
import edu.sru.thangiah.zeus.core.Settings;
import edu.sru.thangiah.zeus.core.ProblemInfo;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class TSPTruckLinkedList
    extends TruckLinkedList
    implements java.io.Serializable, java.lang.Cloneable {

	/**
	 * Constructor
	 */
	public TSPTruckLinkedList()
	{
		//Housekeeping for the linked list
		setHead(new TSPTruck()); //header node for head
		setTail(new TSPTruck()); //tail node for tail
		linkHeadTail();			  //point head and tail to each other
		
		//Assign the attributes	
		setAttributes(new TSPAttributes());
	}

	/**
   * Returns the first truck in the linked list
   * @return first truck
   */
  public TSPTruck getHead() {
    return (TSPTruck) super.getHead();
  }
  
  /**
   * Returns the tail node truck in the linked list
   * @return first truck
   */
  public TSPTruck getTail() {
    return (TSPTruck) super.getTail();
  }

  /**
   * Attempts to insert a shipment into the Truck linked list. Will attempt
   * to insert the shipment into each truck until one is found that can
   * accomodate the shipment
   * @param theShipment the shipment to insert
   * @return true if shipment serviced by a truck, false if not.
   */
  public boolean insertShipment(TSPShipment theShipment) {
    boolean status = false;
    //int loopCount=0;

    TSPTruck truck = (TSPTruck)this.getHead().getNext();

    //loopCount = 1;
    while (truck != this.getTail()) {
      if (truck.compatableWith(theShipment)) {
        status = truck.getTSPMainNodes().insertShipment(theShipment);

        // break out of the loop if a good truck is found.
        if (status) {
          break;
        }
      }

      truck = (TSPTruck) truck.getNext();
      //System.out.println("Loop count in insert "+loopCount++);
    }

    //can we create new trucks?
    if ( (status == false) && (Settings.lockTrucks == false)) {
    /** @todo  Is this really needed */
     //create a pointer to the last truck for reference
      TSPTruck last = (TSPTruck)this.getTail().getPrev();
      TSPTruck first = (TSPTruck)this.getHead();

      //loop to find the correct truck type for this customer
      for (int i = 0; i < ProblemInfo.truckTypes.size(); i++) {
        TSPTruckType type = (TSPTruckType) ProblemInfo.truckTypes.elementAt(i);

        if (type.getServiceType().equals(theShipment.getTruckTypeNeeded())) {
          //create a new truck with the truck number of the last + 1, the matching truck type
          //and use the first customer in the last truck (the depot) to get the depot x,y
          
        	
        	
        	TSPTruck newTruck    = new TSPTruck(type,first.getNext().getDepotX(),first.getNext().getDepotY());
        	

          //attempt to put this shipment into the new truck
          status = newTruck.getTSPMainNodes().insertShipment(theShipment);

          if (status == true) {
            //the customer was inserted to the new truck, so insert the new truck to the linked list
        	 System.out.println("** Inserting new Truck");
        	 System.out.println("Depot x and y is:"+ first.getNext().getDepotX()+" "+first.getNext().getDepotY());
            this.insertTruckLast(newTruck);

            return status;
          }
          else {

            return status;
          }
        }
      }
    }

    return status;
  }

  /**
   * Returns a clone of this object
   * @return Object clone
   */
  public Object clone() {
    TSPTruckLinkedList clonedTruckLinkedList = new TSPTruckLinkedList();

    clonedTruckLinkedList.setAttributes((TSPAttributes)this.getAttributes().clone());
    clonedTruckLinkedList.setHead((TSPTruck)this.getHead().clone());

    if (this.getHead() != this.getTail()) {
      TSPTruck currentTruck = (TSPTruck) clonedTruckLinkedList.getHead();
      TSPTruck nextTruck = (TSPTruck)this.getHead().getNext();

      while (nextTruck != null) {
        currentTruck.setNext( (TSPTruck) nextTruck.clone()); //create the next depot
        currentTruck.getNext().setPrev(currentTruck); //set the next depot's prev
        currentTruck = (TSPTruck) currentTruck.getNext();
        nextTruck = (TSPTruck) nextTruck.getNext();

        //once next is null, we have found the tail of the list
        if (nextTruck == null) {
          clonedTruckLinkedList.setTail(currentTruck);
          currentTruck.setNext(null);
        }

      }
    }
    else { //only 1 depot
      clonedTruckLinkedList.setTail(clonedTruckLinkedList.getHead());
    }
    return clonedTruckLinkedList;
  }

}
