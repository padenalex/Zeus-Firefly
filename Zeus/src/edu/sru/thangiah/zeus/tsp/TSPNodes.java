package edu.sru.thangiah.zeus.tsp;

//import the parent class
import edu.sru.thangiah.zeus.core.Nodes;


public class TSPNodes
    extends Nodes
    implements java.io.Serializable, java.lang.Cloneable {
  public TSPNodes() {
  }

  /**
   * Constructor
   * @param s shipment conatining this cells information
   */
  public TSPNodes(TSPShipment s) {
    //super(s);
    setShipment(s);
  }

  /**
   * Returns the next point cell in the linked list
   * @return next point cell
   */
  public TSPNodes getTSPNext() {
    return (TSPNodes) getNext();
  }

  /**
   * Creates a copy of this node and returns it. It will not create the next
   * and prev links, because this would cause infinite recursion. These are
   * set in the nodes linked list clone() function.
   * @return Object node clone
   */
  public Object clone() {
    TSPNodes clonedNode = new TSPNodes();

    
    if (this.getShipment() != null)
    	clonedNode.setShipment((TSPShipment)this.getShipment().clone());
    	//clonedNode.theShipment = (TSPShipment)this.theShipment.clone();
    else
    	clonedNode.setShipment(new TSPShipment());
    
    return clonedNode;
  }

}
