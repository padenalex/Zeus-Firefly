package edu.sru.thangiah.zeus.ff;

//import the parent class
import edu.sru.thangiah.zeus.core.Feasibility;
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

public class FFFeasibility
    extends Feasibility
    implements java.io.Serializable, java.lang.Cloneable {
  public FFFeasibility() {
  }

  /**
   * Constructor for the feasibilty, will send the constants as well as a
   * pointer to the route that will be checked
   * @param maxd max duration
   * @param maxq max capacity
   * @param thisR the route
   */
  public FFFeasibility(double maxd, float maxq, FFNodesLinkedList thisR) {
    super(thisR);
    
    setMaxDuration(maxd);
    setMaxCapacity(maxq);
    //thisRoute = thisR;
  }

  /**
   * This function checks the feasibility of the route.
   * @return true if feasible, false if not.
   */
  public boolean isFeasible() {
    double currentDistance;
    double currentDemand;
 
    currentDistance = ProblemInfo.nodesLLLevelCostF.getTotalDistance(getRoute());
    currentDemand = ProblemInfo.nodesLLLevelCostF.getTotalDemand(getRoute());
    
    //System.out.println("Current Distance ="+ currentDistance);
    //System.out.println("Current Demand ="+ currentDemand);
    

    if ( (currentDistance <= getMaxDuration()) && (currentDemand <= getMaxCapacity())) {
      return true;
    }
    else {
      return false;
    }
  }
  

	public Object clone() {
		FFFeasibility clonedFeasibility = new FFFeasibility();

		clonedFeasibility.setMaxCapacity(this.getMaxCapacity());
		clonedFeasibility.setMaxDuration(this.getMaxDuration());

		return clonedFeasibility;
	}
  
}
