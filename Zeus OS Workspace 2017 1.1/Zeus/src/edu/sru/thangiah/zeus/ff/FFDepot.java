package edu.sru.thangiah.zeus.ff;

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

public class FFDepot
extends Depot
implements java.io.Serializable, java.lang.Cloneable {

	public FFDepot() {
		//set the attributes and the truck linked list
		setAttributes (new FFAttributes());
		setMainTrucks(new FFTruckLinkedList());
	}

	/**
	 * Constructor. Creates the depot
	 * @param d depot number
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public FFDepot(int d, float x, float y) {
		//The x,y and d (index number)
		//super(d, x, y);
		setDepotNum(d);
		setXCoord(x);
		setYCoord(y);

		setAttributes(new FFAttributes());
		setMainTrucks(new FFTruckLinkedList());
	}

	/**
	 * Returns the truck linked list
	 * @return main trucks
	 */
	public FFTruckLinkedList getMainTrucks() {
		return (FFTruckLinkedList)super.getMainTrucks();
	}

	/**
	 * Returns the next depot in the linked list
	 * @return next depot
	 */
	public FFDepot getNext() {
		return (FFDepot)super.getNext();
	}

	public Object clone() {
		FFDepot clonedDepot = new FFDepot();

		clonedDepot.setDepotNum(this.getDepotNum());
		clonedDepot.setxCoord(this.getxCoord());
		clonedDepot.setyCoord(this.getyCoord());
		clonedDepot.setAttributes((Attributes) this.getAttributes().clone());
		clonedDepot.setMainTrucks((TruckLinkedList)this.getMainTrucks().clone());

		return clonedDepot;
	}

}
