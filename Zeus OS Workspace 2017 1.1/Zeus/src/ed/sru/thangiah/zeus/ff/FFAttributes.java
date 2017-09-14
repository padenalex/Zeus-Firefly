package edu.sru.thangiah.zeus.ff;

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

public class FFAttributes
extends Attributes
implements java.io.Serializable, java.lang.Cloneable {
	public FFAttributes() {
	}

	public Object clone() {
		FFAttributes clonedAttributes = new FFAttributes();

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

