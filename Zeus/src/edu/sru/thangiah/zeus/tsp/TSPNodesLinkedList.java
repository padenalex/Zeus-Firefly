package edu.sru.thangiah.zeus.tsp;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.tsp.TSPNodes;
//import the parent class
import edu.sru.thangiah.zeus.core.NodesLinkedList;



public class TSPNodesLinkedList
    extends NodesLinkedList implements java.io.Serializable,
    java.lang.Cloneable {
	
  public TSPNodesLinkedList() {
	  setHead(new TSPNodes());
      setTail(new TSPNodes());
      linkHeadTail();
      setAttributes(new TSPAttributes());
  }

  /**
   * Constructor
   * @param tT truck type
   * @param depotX depot x-coordinate
   * @param depotY depot y-coordinate
   */
  public TSPNodesLinkedList(TSPTruckType tT, double depotX, double depotY, int tN) {

	  //super(tT, depotX, depotY, tN);
	  setTruckType(tT);
	  setTruckNum(tN);
	  //Set the feasibility check to be done for the route
	  setFeasibility(new TSPFeasibility(getTruckType().getMaxDuration(),
			  getTruckType().getMaxCapacity(), this));
	  setHead(new TSPNodes(new TSPShipment(0, depotX, depotY, 0, 0, "D", "D")));
	  setTail(new TSPNodes(new TSPShipment( -1, depotX, depotY, 0, 0, "D", "D")));
	  linkHeadTail();
	  //assign the TSP attributes
	  setAttributes(new TSPAttributes());
  }

  /**
   * Returns the first cell in the linked list
   * @return first cell
   */
  public TSPNodes getTSPHead() {
    return (TSPNodes) getHead();
  }

  /**
   * This is a stub - Leave it as it is
   * The concrere getInsertShipment will be declared by the class inheriting this
   * class and implementing the actual insertion of shipment  *
   * @param currNodesLL current nodes linked list
   * @param theShipment shipment to be inserted
   * @return true if inserted, false if not
   */

  public boolean getInsertShipment(TSPNodesLinkedList currNodeLL,
                                   TSPShipment theShipment) {
    return false;
  }

  /**
   * Inserts a shipment into the route, creating a new Nodes for the
   * Shipment. Will attempt to put the newly created point cell into every
   * possible location in the route in an attempt to find the best possible
   * initial solution.
   * @param theShipment the shipment to insert
   * @return true if inserted, false if not
   */
  public boolean insertShipment(Shipment theShipment) {
    //method for inserting the shipment into a truck
    TSPNodesLinkedList status = (TSPNodesLinkedList) ProblemInfo.insertShipType;
    return status.getInsertShipment(this, (TSPShipment) theShipment);
  }

  /**
   * Same as insertShipment except the insertion parameter must be specified
   * as a point cell and also the previous point cell is returned. This method
   * is used by the local optimization methods
   * @param insertNode Nodes that is to be inserted into the route
   * @return Nodes that is previous to the inserted Nodes.
   */
  /** @todo  This method should be inside the class that inherits the NodesLinkedList */
  public Nodes insertNodes(Nodes insertNode) {
    boolean isDiagnostic = false;
    Nodes pcBeforeInsertNode = null;
    Shipment theShipment = insertNode.getShipment();

    if (isDiagnostic) {
      System.out.println("========================================");
      System.out.println("In InsertNodes in Nodeslinked list");
      System.out.println("Nodes to be inserted " + theShipment.getIndex());
      System.out.println("Route to be inserted " + this.getRouteString());
      System.out.println("Cost before insertion " + this.getCost());
    }

    // the route is empty
    if (getHead().getNext() == getTail()) {
      getHead().setNext(insertNode);
      getTail().setPrev(insertNode);
      insertNode.setPrev(getHead());
      insertNode.setNext(getTail());

      pcBeforeInsertNode = getHead(); //return head depot

      if (isDiagnostic) {
        System.out.println("Route is empty");
        System.out.println("After inserting the node " + this.getRouteString());
      }

      if (getFeasibility().isFeasible()) {
        this.removeNodes(insertNode); //route is infeasible, remove this cell
        if (isDiagnostic) {
          System.out.println("Insertion infeasible - Returning null");
          System.out.println("Returning to original route " +
                             this.getRouteString());
          System.out.println("================== Exiting insertNodes ");
        }
        return null;
      }
    }
    // the route is not empty
    else {
      double cost = Double.MAX_VALUE;
      Nodes costCell = null; //cell after which the new cell was inserted to achieve cost
      Nodes prevCell = getHead();
      Nodes nextCell = getHead().getNext();

      if (isDiagnostic) {
        System.out.println("Route is not empty");
      }

      //loop through all the cells looking for the cheapest place to put the
      //new cell.
      while (nextCell != null) {
        //insert the cell after current prevCell
        prevCell.setNext(insertNode);
        insertNode.setPrev(prevCell);
        insertNode.setNext(nextCell);
        nextCell.setPrev(insertNode);

        //calculate the cost
        //double tempCost = ProblemInfo.nodesLLLevelCostF.getTotalConstraintCost(this);
        //double tempCost = 0; // ------------- FIX THIS ---------------
        double tempCost = ProblemInfo.nodesLLLevelCostF.getTotalCost(this);
        if (isDiagnostic) {
          System.out.println("After inserting node " + this.getRouteString());
          System.out.println("Cost after insertion " + tempCost);
        }

        //check to see if the new route exceeds the maximum distance allowed
        if (getFeasibility().isFeasible()) {
          //decide if this cell should be saved
          if (tempCost < cost) {
            cost = tempCost;
            costCell = prevCell;
          }
          if (isDiagnostic) {
            System.out.println("Insertion is feasible ");
            System.out.println("Cost before and after insertion " + cost + " " +
                               tempCost);
          }
        }

        //remove the new cell
        prevCell.setNext(nextCell);
        nextCell.setPrev(prevCell);
        insertNode.setNext(null);
        insertNode.setPrev(null);
        if (isDiagnostic) {
          System.out.println("After removing the node from the route " +
                             this.getRouteString());
        }

        //set prevCell and nextCell to the next cells in linked list
        prevCell = nextCell;
        nextCell = prevCell.getNext();
      }

      if (costCell != null) {
        //put the cell in the cheapest place you found
        prevCell = costCell;
        nextCell = prevCell.getNext();
        prevCell.setNext(insertNode);
        insertNode.setPrev(prevCell);
        insertNode.setNext(nextCell);
        nextCell.setPrev(insertNode);

        pcBeforeInsertNode = prevCell;
        if (isDiagnostic) {
          System.out.println("Cost is not null");
          System.out.println(
              "After inserting the cell in the cheapest place found" +
              this.getRouteString());
        }
      }
      else {
        if (isDiagnostic) {
          System.out.println("Cost is null");
        }
        return null;
      }
    }
    theShipment.setIsAssigned(true);
    //ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(this);
    return pcBeforeInsertNode;
  }

  /**
   * This was in the LinearGreedyInsertShipment class and was move to the TSPNodesLinkedList class
   * @return Object
   */
  public Object clone() {

    TSPNodesLinkedList clonedNodesLinkedList = new TSPNodesLinkedList();

    clonedNodesLinkedList.setAttributes((Attributes)this.getAttributes().clone());
    //clonedNodesLinkedList.setCollapsed(this.collapsed);
    if (this.getFeasibility() != null)
    	clonedNodesLinkedList.setFeasibility( (TSPFeasibility)this.getFeasibility().clone());
    else
    	clonedNodesLinkedList.setFeasibility(new TSPFeasibility());
    
    if (this.getTruckType() != null)
    	clonedNodesLinkedList.setTruckType( (TSPTruckType)this.getTruckType().clone());
    else
    	clonedNodesLinkedList.setTruckType(new TSPTruckType());

    clonedNodesLinkedList.setTruckNum(this.getTruckNum());
    clonedNodesLinkedList.setHead( (TSPNodes)this.getHead().clone());

    this.expandRoute();

    if (this.getHead() != this.getTail()) {
      TSPNodes currentNodes = (TSPNodes) clonedNodesLinkedList.getHead();
      TSPNodes nextNodes = (TSPNodes)this.getHead().getNext();

      while (nextNodes != null) {
        currentNodes.setNext( (TSPNodes) nextNodes.clone()); //create the next depot
        currentNodes.getNext().setPrev(currentNodes); //set the next depot's prev
        currentNodes = (TSPNodes) currentNodes.getNext();
        nextNodes = (TSPNodes) nextNodes.getNext();

        //once next is null, we have found the tail of the list
        if (nextNodes == null) {
          clonedNodesLinkedList.setTail(currentNodes);
          currentNodes.setNext(null);
        }
      }
    }

    //Set the route for the feasibility
    clonedNodesLinkedList.getFeasibility().setRoute(clonedNodesLinkedList);

    return clonedNodesLinkedList;
  }
}

class LinearGreedyInsertShipment
    extends TSPNodesLinkedList {
  public boolean getInsertShipment(TSPNodesLinkedList currNodeLL,
                                   TSPShipment theShipment) {
	  
	TSPNodes tmpPtr;
    //currNodeLL is the reference to the current node linked list being considered for insertion
    //theShipment is the shipment to be inserted
    TSPNodes theCell = new TSPNodes(theShipment);

    // the route is empty
    if (currNodeLL.getHead().getNext() == currNodeLL.getTail()) {
      currNodeLL.setHeadNext(theCell);
      currNodeLL.getTail().setPrev(theCell);
      theCell.setPrev(currNodeLL.getHead());
      theCell.setNext(currNodeLL.getTail());
      


      //if its not feasible, return route to what it was and return false
      if (!currNodeLL.getFeasibility().isFeasible()) {
    	//remove the inserted node
    	tmpPtr = (TSPNodes) currNodeLL.getHead().getNext();
    	tmpPtr.setNext(null);
    	tmpPtr.setPrev(null);
    	
    	//point the head and tail to each other
        currNodeLL.setHeadNext(currNodeLL.getTail());
        currNodeLL.getTail().setPrev(currNodeLL.getHead());
        

        return false;
      }
    }
    // the route is not empty
    else {
      double cost = Double.MAX_VALUE;
      TSPNodes costCell = null; //cell after which the new cell was inserted to achieve cost

      TSPNodes prevCell = (TSPNodes) currNodeLL.getHead();
      TSPNodes nextCell = (TSPNodes) currNodeLL.getHead().getNext();

      while (nextCell != currNodeLL.getTail()) {
        //insert the cell after current prevCell
        prevCell.setNext(theCell);
        theCell.setPrev(prevCell);
        theCell.setNext(nextCell);
        nextCell.setPrev(theCell);

        //check to see if the new route is feasible
        if (currNodeLL.getFeasibility().isFeasible()) {
          //calculate the cost
          double tempCost = ProblemInfo.nodesLLLevelCostF.getTotalCost(
              currNodeLL);

          //decide if this cell should be saved
          if (tempCost < cost) {
            cost = tempCost;
            costCell = prevCell;
          }
        }
        //remove the new cell
        prevCell.setNext(nextCell);
        nextCell.setPrev(prevCell);
        theCell.setNext(null);
        theCell.setPrev(null);

        //set prevCell and nextCell to the next cells in linked list
        prevCell = nextCell;
        nextCell = (TSPNodes) prevCell.getNext();
      }

      
      
      if (costCell != null) {
        prevCell = costCell;
        nextCell = (TSPNodes) prevCell.getNext();
        prevCell.setNext(theCell);
        theCell.setPrev(prevCell);
        theCell.setNext(nextCell);
        nextCell.setPrev(theCell);
      }
      else {
        return false;
      }
    }

    theShipment.setIsAssigned(true);
    ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(currNodeLL);
    
    {
    /*	
  	  System.out.println("Route is:");
  	  TSPNodes tempPtr= (TSPNodes)currNodeLL.getHead();
  	  while (tempPtr != (TSPNodes)currNodeLL.getTail())
  	  {
  		  System.out.print(tempPtr.getIndex()+"("+tempPtr.getDemand()+")-");
  		  tempPtr = tempPtr.getTSPNext();
  	  } 	  
  	  System.out.println();
  	  */
    }
    
    return true;
  }

  //The WhoAmI methods gives the id of the assigned object
  //It is a static method so that it can be accessed without creating an object
  public static String WhoAmI() {
    return ("Insertion Type: Linear greedy insertion heuristic");
  }
}


//---------------------------------



class InsertAsGiven
extends TSPNodesLinkedList {
public boolean getInsertShipment(TSPNodesLinkedList currNodeLL,
                               TSPShipment theShipment) {
  
TSPNodes tmpPtr;
//currNodeLL is the reference to the current node linked list being considered for insertion
//theShipment is the shipment to be inserted
TSPNodes theCell = new TSPNodes(theShipment);

//the route is empty
if (currNodeLL.getHead().getNext() == currNodeLL.getTail()) {
currNodeLL.setHeadNext(theCell);
currNodeLL.getTail().setPrev(theCell);
theCell.setPrev(currNodeLL.getHead());
theCell.setNext(currNodeLL.getTail());



//if its not feasible, return route to what it was and return false
if (!currNodeLL.getFeasibility().isFeasible()) {
	//remove the inserted node
	tmpPtr = (TSPNodes) currNodeLL.getHead().getNext();
	tmpPtr.setNext(null);
	tmpPtr.setPrev(null);
	
	//point the head and tail to each other
currNodeLL.setHeadNext(currNodeLL.getTail());
currNodeLL.getTail().setPrev(currNodeLL.getHead());


return false;
}
}
//the route is not empty


else {
  double cost = Double.MAX_VALUE;
  TSPNodes costCell = null; //cell after which the new cell was inserted to achieve cost

  TSPNodes prevCell = (TSPNodes) currNodeLL.getHead();
  TSPNodes nextCell = (TSPNodes) currNodeLL.getHead().getNext();

  while (nextCell != currNodeLL.getTail()) {
    //insert the cell after current prevCell
    prevCell.setNext(theCell);
    theCell.setPrev(prevCell);
    theCell.setNext(nextCell);
    nextCell.setPrev(theCell);

    //check to see if the new route is feasible
    if (currNodeLL.getFeasibility().isFeasible()) {
        //calculate the cost
        double tempCost = ProblemInfo.nodesLLLevelCostF.getTotalCost(
            currNodeLL);

        //decide if this cell should be saved
      //  if (tempCost < cost) {
          cost = tempCost;
          costCell = prevCell;
      //  }
      }
    
    
    //remove the new cell
    prevCell.setNext(nextCell);
    nextCell.setPrev(prevCell);
    theCell.setNext(null);
    theCell.setPrev(null);

    //set prevCell and nextCell to the next cells in linked list
    prevCell = nextCell;
    nextCell = (TSPNodes) prevCell.getNext();
  }

  
  
  if (costCell != null) {
    prevCell = costCell;
    nextCell = (TSPNodes) prevCell.getNext();
    prevCell.setNext(theCell);
    theCell.setPrev(prevCell);
    theCell.setNext(nextCell);
    nextCell.setPrev(theCell);
  }
  else {
    return false;
  }
}

theShipment.setIsAssigned(true);
ProblemInfo.nodesLLLevelCostF.calculateTotalsStats(currNodeLL);


return true;
}



//The WhoAmI methods gives the id of the assigned object
//It is a static method so that it can be accessed without creating an object
public static String WhoAmI() {
return ("Insertion Type: Insert As Given");
}
}
