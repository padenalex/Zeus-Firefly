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

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.core.Attributes;
//import the parent class
import edu.sru.thangiah.zeus.core.Truck;

public class TSPTruck
    extends Truck implements java.io.Serializable, java.lang.Cloneable {
  public TSPTruck() {
	
	  //Assign the nodes linkes list
	 setMainNodes(new TSPNodesLinkedList());
	  
    //Assign the attributes
    setAttributes(new TSPAttributes());
  }

  /**
   * Constructor
   * @param tt truck type
   * @param depotX depot's x coordinate
   * @param depotY depot's y coordinate
   */
  public TSPTruck(TSPTruckType tt, double depX, double depY) {
    //super(tt, depX, depY);
    setAttributes(new TSPAttributes());
    setDepotX(depX);
    setDepotY(depY);
    setTruckNum(ProblemInfo.numTrucks++);
    setTruckType(tt);

    setMainNodes(new TSPNodesLinkedList(tt, depX, depY, getTruckNum()));


  }

  /**
   * Returns the visit nodes linked list (route)
   * @return route
   */
  public TSPNodesLinkedList getTSPMainNodes() {
    return (TSPNodesLinkedList) getMainNodes();
  }

  /**
   * Returns the next depot in the linked list
   * @return next depot
   */
  public TSPTruck getTSPNext() {
    return (TSPTruck) getNext();
  }

  /**
   * Creates a clone of the current trucks. Does not create the next and prev
   * links, that is the responsibility of the truck linked list clone() function
   * @return Object truck clone
   */
  public Object clone() {
    TSPTruck clonedTruck = new TSPTruck();

    clonedTruck.setAttributes((TSPAttributes)this.getAttributes().clone());
    clonedTruck.setDepotX(this.getDepotX());
    clonedTruck.setDepotY(this.getDepotY());
    clonedTruck.setMainNodes((TSPNodesLinkedList)this.getMainNodes().clone());
    clonedTruck.setTruckNum(this.getTruckNum());
    if (this.getTruckType() != null)
    	clonedTruck.setTruckType((TSPTruckType)this.getTruckType().clone());
    else
    	clonedTruck.setTruckType(new TSPTruckType());
    	
    return clonedTruck;
  }

}
