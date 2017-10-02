package edu.sru.thangiah.zeus.tsp.tspqualityassurance;

import java.util.Vector;

import edu.sru.thangiah.zeus.qualityassurance.*;

/**
 *
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Sam R. Thangiah
 * @version 2.0
 */
/** @todo Need to document the variables and the parameters */
public class TSPQANodesLinkedList
    extends QANodesLinkedList
    implements java.io.Serializable, java.lang.Cloneable
    {
		private Vector nodes;
		public TSPQANodesLinkedList()
		{
			nodes = new Vector();
		}

		public Vector getNodes()
		{
			// TODO Auto-generated method stub
			return nodes;
		}
		
		public boolean checkDistanceConstraint()
		{
			boolean isDiagnostic = true;
		    boolean status = true;
		    double totalDistance = 0;
		    double distance = 0;
		    TSPQANode node1;
		    TSPQANode node2;

		    //Used in the comparison of distances
		    int intDistance1;
		    int intDistance2;
		    if (isDiagnostic)
		    {
		        System.out.println();
		        System.out.println("Sequence of the route is:");
		        for (int i = 0; i < getNodes().size(); i++)
		        {
		        	node1 = (TSPQANode) getNodes().elementAt(i);
		        	System.out.print(" " + node1.getIndex());
		        }
		        System.out.println();
		    }
		    
		    if (getNodes().size() == 0)
		    {
		        return status;
		    }
		    
		    node1 = (TSPQANode) getNodes().elementAt(0);
		    
		    for (int i = 1; i < getNodes().size(); i++)
		    {
		    	node2 = (TSPQANode) getNodes().elementAt(i);
		    	distance = (float) Math.sqrt( (double) (node1.getX() - node2.getX())
                        * (node1.getX() - node2.getX()) +
                        ( (node1.getY() - node2.getY())
                        * (node1.getY() - node2.getY())));
		    	totalDistance += distance;
		    		    
		    	if (isDiagnostic)
		    	{
		    		System.out.println("    Distance from " + node1.getIndex() + " to " +
		                           node2.getIndex() + " = " + distance);
		    	}
		    	node1 = node2;
		    }
		    return status;
		}
	}
