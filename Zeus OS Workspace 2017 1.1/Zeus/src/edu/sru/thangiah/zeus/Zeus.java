package edu.sru.thangiah.zeus;

<<<<<<< HEAD
import  edu.sru.thangiah.zeus.vrp.VRPRoot;
import  edu.sru.thangiah.zeus.tsp.TSPRoot;
=======
import  edu.sru.thangiah.zeus.ff.FFRoot;
>>>>>>> 15975d9c90c9b4e3051941bf23e61adda892f303


import javax.swing.*;

/**
 * Calls the main functions of Zeus.
 * Title: Zeus
 * Description: This class calls the main root method for the problem to be solved
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */

public class Zeus {
  /**
   * Main function for Zeus
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
	  
	  
    //Solve the VRP Problem
    //VRPRoot theRoot = new VRPRoot();
<<<<<<< HEAD
    TSPRoot theRoot = new TSPRoot();
=======
	  FFRoot theRoot = new FFRoot();
>>>>>>> 15975d9c90c9b4e3051941bf23e61adda892f303
    //TOPRoot theRoot = new TOPRoot();
    //MDVRPRoot theRoot = new MDVRPRoot();
	//HDMDVRPRoot theRoot = new HDMDVRPRoot();
	  

	int minCapacity=0;
	int maxCapacity=0; 
	int maxMiles=0;
	int maxStops=0;
	
	System.gc();

  }
}
