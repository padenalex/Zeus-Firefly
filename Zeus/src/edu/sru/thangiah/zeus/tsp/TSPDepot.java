package edu.sru.thangiah.zeus.tsp;

import edu.sru.thangiah.zeus.core.Attributes;
//import the parent class
import edu.sru.thangiah.zeus.core.Depot;
import edu.sru.thangiah.zeus.core.TruckLinkedList;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class TSPDepot
extends Depot
implements java.io.Serializable, java.lang.Cloneable {

	public TSPDepot() {
		//set the attributes and the truck linked list
		setAttributes (new TSPAttributes());
		setMainTrucks(new TSPTruckLinkedList());
	}

	/**
	 * Constructor. Creates the depot
	 * @param d depot number
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public TSPDepot(int d, float x, float y) {
		//The x,y and d (index number)
		//super(d, x, y);
		setDepotNum(d);
		setXCoord(x);
		setYCoord(y);

		setAttributes(new TSPAttributes());
		setMainTrucks(new TSPTruckLinkedList());
	}

	/**
	 * Returns the truck linked list
	 * @return main trucks
	 */
	public TSPTruckLinkedList getMainTrucks() {
		return (TSPTruckLinkedList)super.getMainTrucks();
	}

	/**
	 * Returns the next depot in the linked list
	 * @return next depot
	 */
	public TSPDepot getNext() {
		return (TSPDepot)super.getNext();
	}

	public Object clone() {
		TSPDepot clonedDepot = new TSPDepot();

		clonedDepot.setDepotNum(this.getDepotNum());
		clonedDepot.setxCoord(this.getxCoord());
		clonedDepot.setyCoord(this.getyCoord());
		clonedDepot.setAttributes((Attributes) this.getAttributes().clone());
		clonedDepot.setMainTrucks((TruckLinkedList)this.getMainTrucks().clone());

		return clonedDepot;
	}

}
