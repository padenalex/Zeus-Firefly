package edu.sru.thangiah.zeus.tsp;

//import the parent class
import edu.sru.thangiah.zeus.core.Attributes;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class TSPAttributes
extends Attributes
implements java.io.Serializable, java.lang.Cloneable {
	public TSPAttributes() {
	}

	public Object clone() {
		TSPAttributes clonedAttributes = new TSPAttributes();

		clonedAttributes.setAvgTravelTime(this.getAvgTravelTime());
		clonedAttributes.setMaxTravelTime(this.getMaxTravelTime());
		//clonedAttributes.totalConstraintCost = this.totalConstraintCost;
		clonedAttributes.setTotalCost(this.getTotalCost());
		//clonedAttributes.totalCrossRoadPenaltyCost = this.totalCrossRoadPenaltyCost;
		clonedAttributes.setTotalDemand(this.getTotalDemand());
		clonedAttributes.setTotalDistance(this.getTotalDistance());
		clonedAttributes.setTotalTravelTime(this.getTotalTravelTime());
		//clonedAttributes.totalTurnAroundPenaltyCost = this.
		//    totalTurnAroundPenaltyCost;

		return clonedAttributes;
	}
}

