package edu.sru.thangiah.zeus.ff.ffcostfunctions;

import edu.sru.thangiah.zeus.core.*;

abstract public class FFAbstractCostFunctions implements CostFunctions {

  /** Methods not used by FF in the computation of cost can be declared
   * here with dummy methods. Then have the cost functions in the ff
   * inherit this class instead of the CostFunctions class.
   */

  public float getTotalScore(Object o) {
    return 0;
  }

  public void setTotalScore(Object o) {

  }
  
  public void setTotalDays(Object o) {

  }
  
  public int getTotalDays(Object o) {
	  
	  	return 0;
  }

 
}
