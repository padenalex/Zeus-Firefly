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
import edu.sru.thangiah.zeus.core.Shipment;


public class TSPShipment
    extends Shipment
    implements java.io.Serializable, java.lang.Cloneable {

  private double extraVariable;

  public TSPShipment() {
  }
  
  public TSPShipment(int i)
  {
	  setIndex(i);
  }
  
  public TSPShipment(int i, float x, float y, int d, int q, int e, String t) {
	    //super(i, x, y, q, d, t, p);
	    setIndex(i);
	    setXCoord(x);
	    setYCoord(y);
	    setDemand(q);
	    //serviceTime = d;
	    setTruckTypeNeeded(t);
	    //pickUpPointName = p;
	}

  /**
   * Constructor
   * @param i index
   * @param x x-coordinate
   * @param y y-coordinate
   * @param q demand
   * @param d service time
   * @param t truck type
   * @param p pick up point name
   */
  public TSPShipment(int i, double x, double y, float q, float d, String t,
                     String p) {
    //super(i, x, y, q, d, t, p);
    setIndex(i);
    setXCoord(x);
    setYCoord(y);
    setDemand(q);
    //serviceTime = d;
    setTruckTypeNeeded(t);
    //pickUpPointName = p;

  }
  

  //calls the super in Shipment
  public TSPShipment(int i, float x, float y, int d, int q, int e, int comb,
		  String t,
		  int[] vComb, int[][] cuComb) {
	  //super(ind, x, y, d, q, e, comb, t, vComb, cuComb);
	  setIndex(i);
	  setXCoord(x);
	  setYCoord(y);
	  setDemand(q);
	  //serviceTime = d;

	  // frequency = e;
	  // noComb = comb;
	  setTruckTypeNeeded(t);
	  //visitComb = vComb;
	  //currentComb = cuComb;
	  setIsAssigned(false);
	  setIsSelected(false);
	  setNext(null);

	  //the combinations to be created should not exceed the maximum allowable
	  //combination
	  /*for (int i = 0; i < noComb; i++) {
      visitComb[i] = vComb[i];
    }*/

	  extraVariable = Math.random();
  }

  //calls the super in shipment
  public TSPShipment(int i, int x, int y, int d, int q, int e, int comb,
		  String t,
		  int[] vComb, int[][] cuComb) {
	  //super(ind, x, y, d, q, e, comb, t, vComb, cuComb);
	  //serviceTime = d;
	  //frequency = e;
	  //noComb = comb;
	  //visitComb = vComb;
	  //currentComb = cuComb;

	  setIndex(i);
	  setXCoord(x);
	  setYCoord(y);
	  setDemand(q);
	  //serviceTime = d;

	  // frequency = e;
	  // noComb = comb;
	  setTruckTypeNeeded(t);
	  //visitComb = vComb;
	  //currentComb = cuComb;
	  setIsAssigned(false);

	  setNext(null);



    extraVariable = Math.random();
  }

  /**
   * Returns the value of extraVariable
   * @return extraVariable int
   */
  public double getExtraVariable() {
    //get the next shipment from the shipment linked list
    //but return it as a TSPShipment
    return extraVariable;
  }
  
  public Object clone() {
		TSPShipment clonedShipment = new TSPShipment();

		clonedShipment.setDemand(this.getDemand());
		clonedShipment.setIndex(this.getIndex());
		clonedShipment.setIsAssigned(this.getIsAssigned());
		clonedShipment.setTruckTypeNeeded(this.getTruckTypeNeeded());
		clonedShipment.setXCoord(this.getXCoord());
		clonedShipment.setYCoord(this.getYCoord());

		return clonedShipment;
	}

}
