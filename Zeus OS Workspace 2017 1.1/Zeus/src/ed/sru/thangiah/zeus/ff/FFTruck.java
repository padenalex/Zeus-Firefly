package edu.sru.thangiah.zeus.ff;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.core.Attributes;
//import the parent class
import edu.sru.thangiah.zeus.core.Truck;

public class FFTruck
    extends Truck implements java.io.Serializable, java.lang.Cloneable {
  public FFTruck() {
	
	  //Assign the nodes linkes list
	 setMainNodes(new FFNodesLinkedList());
	  
    //Assign the attributes
    setAttributes(new FFAttributes());
  }

  /**
   * Constructor
   * @param tt truck type
   * @param depotX depot's x coordinate
   * @param depotY depot's y coordinate
   */
  public FFTruck(FFTruckType tt, double depX, double depY) {
    //super(tt, depX, depY);
    setAttributes(new FFAttributes());
    setDepotX(depX);
    setDepotY(depY);
    setTruckNum(ProblemInfo.numTrucks++);
    setTruckType(tt);

    setMainNodes(new FFNodesLinkedList(tt, depX, depY, getTruckNum()));


  }

  /**
   * Returns the visit nodes linked list (route)
   * @return route
   */
  public FFNodesLinkedList getFFMainNodes() {
    return (FFNodesLinkedList) getMainNodes();
  }

  /**
   * Returns the next depot in the linked list
   * @return next depot
   */
  public FFTruck getFFNext() {
    return (FFTruck) getNext();
  }

  /**
   * Creates a clone of the current trucks. Does not create the next and prev
   * links, that is the responsibility of the truck linked list clone() function
   * @return Object truck clone
   */
  public Object clone() {
    FFTruck clonedTruck = new FFTruck();

    clonedTruck.setAttributes((FFAttributes)this.getAttributes().clone());
    clonedTruck.setDepotX(this.getDepotX());
    clonedTruck.setDepotY(this.getDepotY());
    clonedTruck.setMainNodes((FFNodesLinkedList)this.getMainNodes().clone());
    clonedTruck.setTruckNum(this.getTruckNum());
    if (this.getTruckType() != null)
    	clonedTruck.setTruckType((FFTruckType)this.getTruckType().clone());
    else
    	clonedTruck.setTruckType(new FFTruckType());
    	
    return clonedTruck;
  }

}
