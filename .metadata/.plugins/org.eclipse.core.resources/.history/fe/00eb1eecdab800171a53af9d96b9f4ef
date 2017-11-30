package edu.sru.thangiah.zeus.tsp;

//import the parent class
import edu.sru.thangiah.zeus.core.TruckType;


public class TSPTruckType
    extends TruckType
    implements java.io.Serializable, java.lang.Cloneable {
  public TSPTruckType() {
  }

  /**
   * Constructor
   * @param N type number
   * @param D max duration
   * @param Q max capacity
   * @param s type of customers the truck can service
   */
  public TSPTruckType(int N, float D, float Q, String s) {
    setTruckNo(N);
    setServiceType(s);

    if (D == 0) {
      setMaxDuration(Integer.MAX_VALUE);
    }
    else {
    	setMaxDuration(D);
    }

    if (Q == 0) {
      setMaxCapacity(Integer.MAX_VALUE);
    }
    else {
      setMaxCapacity(Q);
    }

    setFixedCost(getMaxCapacity());
    setVariableCost((double) getMaxCapacity() / 1000);
  }

  public Object clone() {
	    TSPTruckType clonedTruckType = new TSPTruckType();
	    clonedTruckType.setFixedCost(this.getFixedCost());
	    clonedTruckType.setMaxCapacity(this.getMaxCapacity());
	    clonedTruckType.setMaxDuration(this.getMaxDuration());
	    clonedTruckType.setServiceType(this.getServiceType());
	    clonedTruckType.setTruckNo(this.getTruckNo());
	    clonedTruckType.setVariableCost(this.getVariableCost());

	    return clonedTruckType;
	  }
}
