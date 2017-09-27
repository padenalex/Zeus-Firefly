package edu.sru.thangiah.zeus.tsp;

import java.io.*;
import java.util.*;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.localopts.*;
import edu.sru.thangiah.zeus.tsp.tspqualityassurance.*;
import edu.sru.thangiah.zeus.gui.*;
import edu.sru.thangiah.zeus.localopts.OptInfo;
import edu.sru.thangiah.zeus.localopts.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class TSP {

	int m = 0, //number of vehicles
			n = 0, //number of customers
			t = 0, //number of days(or depots)
			D = 0, //maximum duration of route
			Q = 0; //maximum capacity of vehicle

	long startTime, endTime; //track the CPU processing time
	private Vector mainOpts = new Vector(); //contains the collections of optimizations
	@SuppressWarnings("rawtypes")
	private Vector optInformation = new Vector(); //contains information about routes
	private TSPShipmentLinkedList mainShipments = new TSPShipmentLinkedList(); //customers read in from a file or database that are available
	private TSPDepotLinkedList mainDepots = new TSPDepotLinkedList(); //depots linked list for the TSP problem
	private TSPQualityAssurance tspQA; //check the integrity and quality of the solution

	//constructor for the class
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TSP(String dataFile) {

		//Truck types are placed into a vector
		ProblemInfo.truckTypes = new Vector();

		//Type of shipment insterion to be performed
		//ProblemInfo.insertShipType = new Object();

		boolean isDiagnostic = false;
		Shipment tempShip;
		Depot thisDepot;
		int type;
		int depotNo;
		int countAssignLoop;
		boolean status;
		String outputFileName;

		/** @todo  Need to put in a TSP file and read in TSP data. The readfile method will have to be changed to match the format of the
		 * tsp file*/
		//read in the MDTSP data
		//readDataFromTextFile(ProblemInfo.inputPath + dataFile);
		readDataFromExcelFile(ProblemInfo.inputPath + dataFile);
		Settings.printDebug(Settings.COMMENT,
				"Read Data File: " + ProblemInfo.inputPath + dataFile);
		printDataToConsole();
		writeDataFile(dataFile.substring(dataFile.lastIndexOf("/") + 1));

		//Ensure that the shipment linked list has been loaded with the data
		if (mainShipments.getTSPHead() == null) {
			Settings.printDebug(Settings.ERROR,
					"TSP: Shipment linked list is empty");
		}

		//Set up the shipment selection type

		ProblemInfo.selectShipType = new SmallestPolarAngleToDepot();
		Settings.printDebug(Settings.COMMENT, SmallestPolarAngleToDepot.WhoAmI());


		//set up the shipment insertion type
		ProblemInfo.insertShipType = new LinearGreedyInsertShipment();
		Settings.printDebug(Settings.COMMENT, LinearGreedyInsertShipment.WhoAmI());

		//Capture the CPU time required for solving the problem
		startTime = System.currentTimeMillis();
		// captures the initial information on solving the problem
		// returns the total customer and total distance after the initial solution
		optInformation.add("Inital Solution " + createInitialRoutes());
		System.out.println("Completed initial routes");

		//Get the initial solution
		//Depending on the Settings status, display information on the routes
		//Trucks used, total demand, dist, travel time and cost
		Settings.printDebug(Settings.COMMENT, "Created Initial Routes ");
		Settings.printDebug(Settings.COMMENT,
				"Initial Stats: " + mainDepots.getSolutionString());
		//At this point all shipments have been assigned
		writeLongSolution(dataFile.substring(dataFile.lastIndexOf("/") + 1));
		//writeShortSolution(dataFile.substring(dataFile.lastIndexOf("/") + 1));

		//create a vector of search strategy/optimizations to execute
		mainOpts = new Vector(1); //vector capacity of 1
		//sets the upperbound in LocalOneOpt and

		
		runOptimizations();
		writeLongSolution(dataFile.substring(dataFile.lastIndexOf("/") + 2));

		//Check for the quality and integrity of the solution
		System.out.println("Starting QA");
		tspQA = new TSPQualityAssurance(mainDepots, mainShipments);
		if (tspQA.runQA() == false) {
			Settings.printDebug(Settings.ERROR, "QA FAILED!");
		}
		else {
			Settings.printDebug(Settings.COMMENT, "QA succeeded");


		}
		
		ZeusGui guiPost = new ZeusGui(mainDepots, mainShipments);

	} 

	/**
	 * Creates the initial solution for the problem
	 */
	public OptInfo createInitialRoutes() {
		//OptInfo has old and new attributes
		OptInfo info = new OptInfo();
		TSPDepot currDepot = null; //current depot
		TSPShipment currShip = null; //current shipment
		//int countLoop=0;

		//check if selection and insertion type methods have been selected
		if (ProblemInfo.selectShipType == null) {
			Settings.printDebug(Settings.ERROR,
					"No selection shipment type has been assigned");

		}
		if (ProblemInfo.insertShipType == null) {
			Settings.printDebug(Settings.ERROR,
					"No insertion shipment type has been assigned");
		}

		//capture the old attributes
		info.setOldAttributes(mainDepots.getAttributes());
		/* Get the shipment that is closest to a depot with respect to a criteria
     The method for assigning customers to the depots is as follows. As there are
     a fixed number of depots, start with the first depot and find closest customer
     to the depot and insert that customer into a truck allocated to the depot if
     it does not exceed the constraints.  If it does, then create another truck
     from the same depot and add the customer to it.
     The next step is to go to the next depot and allocate the a customer closest
     to that depot to a truck. This process will loop through all the depots and
     sequentially allocate customers to the depot until all customers have been
     assigned to a depot.

     The class assignment to ProblemInfo.selectShipType determines the type of
     shipment insertion to be executed.
		 */

		//countLoop=1;
		while (!mainShipments.isAllShipsAssigned()) {
			double x, y;
			int i = 0;
			//Get the x an y coordinate of the depot
			//Then use those to get the customer, that has not been allocated,
			// that is closest to the depot
			currDepot = (TSPDepot) mainDepots.getTSPHead().getNext();
			x = currDepot.getXCoord();
			y = currDepot.getYCoord();
			//Send the entire mainDepots and mainShipments to get the next shipment
			//to be inserted including the current depot
			TSPShipment theShipment = mainShipments.getNextInsertShipment(mainDepots,
					currDepot, mainShipments, currShip);

			if (theShipment == null) { //shipment is null, print error message
				Settings.printDebug(Settings.COMMENT, "No shipment was selected");
			}
			//The selected shipment will be inserted into the route
			if (!mainDepots.insertShipment(theShipment)) {
				Settings.printDebug(Settings.COMMENT, "The Shipment: <" + theShipment.getIndex() +
						"> cannot be routed");
			}
			else {
				Settings.printDebug(Settings.COMMENT,
						"The Shipment: <" + theShipment.getIndex() +// " " + theShipment +
						"> was routed");
				//tag the shipment as being routed
				theShipment.setIsAssigned(true);
			}
		}

		ProblemInfo.depotLLLevelCostF.calculateTotalsStats(mainDepots);
		info.setNewAtributes(mainDepots.getAttributes());
		return info;
	}

	
	
	/* 
	 * Method used to read data from the specified Excel file 
	 */
	public int readDataFromExcelFile(String TSPFileName) {

		//type = 0 EUC_2D
		//     = 1 GEO
		//     = 2 ATT
		//     = 1 EXPLICIT
		//     = 2 CEIL_2D
		
		char ch;
		String temp = "";
		int index = 0,
				j = 0,
				type = 0, //type
				m        = 0,                           //number of vehicles
				n        = 0,                           //number of customers
				t        = 0,                           //number of days(or depots)
				D        = 0,                           //maximum duration of route
				Q        = 0;                           //maximum load of vehicle
		int p = 3; //Np neighborhood size

		int depotIndex;

		//Open the requested file
		XSSFWorkbook workbook = new XSSFWorkbook();    
		FileInputStream fis;
		XSSFSheet sheet;
		XSSFRow curRow;
		int rowCounter = 0; //initial the row counter

		//    FileInputStream fis;
		//    InputStreamReader isr;
		//    BufferedReader br;
		try {
			fis = new FileInputStream(TSPFileName);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			curRow = sheet.getRow(rowCounter+1); // the 2nd row is the problem data

		}
		catch (Exception e) {
			System.out.println("readDataFromExcelFile - "+TSPFileName+" File is not present");
			return 0;
		}



		//read in the first line
		try {
			//type = (int)curRow.getCell(0).getNumericCellValue();
			type = 0;
			m = 1;   // M is equal to number of vehicles 
			curRow = sheet.getRow(3);
			n = (int)curRow.getCell(1).getNumericCellValue();
			t = 1; // number of depots
			D = 9999999; // Max duration
			Q = 9999999; // Max load
		}
		catch (Exception e) {
			System.out.println("Line could not be read in");
		}

		//Put the problem information into the ProblemInfo class
		//set the problem info for the problem
		ProblemInfo.numDepots = t; //Set the number of depots to 1 for this problem
		ProblemInfo.fileName = TSPFileName; //name of the file being read in
		ProblemInfo.probType = type; //problem type
		ProblemInfo.noOfVehs = m; //number of vehicles
		ProblemInfo.noOfShips = n; //number of shipments
		ProblemInfo.noOfDays = t; //number of days (horizon) or number of depots for MDTSP

		if (Q == 0) { //if there is no maximum capacity, set it to a very large number
			Q = 999999999;
		}
		if (D == 0) { //if there is no travel time, set it to a very large number
			D = 999999999; //if there is not maximum distance, set it to a very large number
			//ProblemInfo.maxCapacity = Q;  //maximum capacity of a vehicle
			//ProblemInfo.maxDistance = D;  //maximum distance of a vehicle
		}
		/** @todo  There three variables need to be defined at the beginning of
		 * the method */
		float maxCapacity = Q; //maximum capacity of a vehicle
		float maxDistance = D; //maximum distance of a vehicle

		String serviceType = "1"; //serviceType is the trucktype. Should match with
		//required truck type
		//In some problems, different truck types might be present to solve
		//the problem. For this problem, we assume that there is only one
		//truck type that is available.
		//loop through each truck type and store each one in the vector
		int numTruckTypes = 1;
		for (int i = 0; i < numTruckTypes; i++) {
			TSPTruckType truckType = new TSPTruckType(i, maxDistance,
					maxCapacity, serviceType);
			ProblemInfo.truckTypes.add(truckType);
		}

		//Some problems tend to have different customer types. In this problem
		//there is only one customter type. The integer value for the customer type
		//should match with the integer value for the truck type for the compatibiliy
		//check to work
		//read in the different customer types
		Vector custTypes = new Vector();
		//Obtain the different customer types
		for (int ct = 0; ct < 1; ct++) {
			custTypes.add(new Integer(1));
		}


		//display the information from the first line
		System.out.println("type is       " + type);
		System.out.println("numVeh is         " + m);
		System.out.println("numCust is        " + n);


		if (type != 0) { //then it is not an MDTSP problem
			System.out.println("Problem is not an MDTSP problem");
			return 0;
		}

		//This section will get the depot x and y for the PTSP and the PTSP.
		float x = 0, //x coordinate
				y = 0; //y coordinate
		int i = 0, //customer number
				d = 0, //service duration
				q = 0, //demand
				f = 0, //frequency of visit
				a = 0; //number of combinations allowed
		int runTimes;


		//if MDTSP problem, readn in n+t lines
		if (type == 0) {
			runTimes = n;

		}
		//if  PTSP/PTSP, read in n+1 lines
		else {
			runTimes = n + 1;
			//This section will get the customers/depots and related information
		}

		try {
			//The first for loop runtimes dependent upon how many lines are to be read in
			//The next for loop reads the line into s.  Then the entire string in s
			//is processed until the the entire line is processed and there are no
			//more characters are to be processed. There is a case for each index
			//except for the combinations.  The combinations are processed
			//until the last character in s is processed

			rowCounter = 6; //set the rowCounter, customer data begin from the 4th row

			for (int k = 0; k < runTimes; k++) {
				index = 0;
				temp = "";
				curRow = sheet.getRow(rowCounter);
				try { // read the current row
					i = (int)curRow.getCell(0).getNumericCellValue();
					x = (int)curRow.getCell(1).getNumericCellValue();
					y = (int)curRow.getCell(2).getNumericCellValue();
					d = 0;
					q = 0;
					f = 0;
				}
				catch (Exception e) {
					System.out.println("Line could not be read in line 474");
				}

				Integer custType = (Integer) custTypes.elementAt(0);
				mainShipments.insertShipment(i, x, y, q, d, f, custType.toString());
		
				rowCounter++; //go to next row          
			}// end for

			
			
			
			rowCounter = 3+n+1; //set the rowCounter, depot data begin from the (3+n+1)th row
			for (int k = 0; k < t; k++) { //add it to the depot linked list
				curRow = sheet.getRow(rowCounter);

				try { // read the current row
					i =  (int)curRow.getCell(0).getNumericCellValue();
					x =  (int)curRow.getCell(1).getNumericCellValue();
					y =  (int)curRow.getCell(2).getNumericCellValue();
				}
				catch (Exception e) {
					System.out.println("Line could not be read in line 505");
				}

				//insert the depot into the depot linked list
				TSPDepot depot = new TSPDepot(i - n, x, y); //n is the number of customers
				mainDepots.insertDepotLast(depot);

				//Each depot has a mainTrucks. The different truck types available are
				//inserted into the mainTrucks type. For the TSP, there is only one truck type
				depot = (TSPDepot) mainDepots.getHead().getNext();
				for (i = 0; i < ProblemInfo.truckTypes.size(); i++) {
					TSPTruckType ttype = (TSPTruckType) ProblemInfo.truckTypes.
							elementAt(i);
					depot.getMainTrucks().insertTruckLast(new TSPTruck(ttype,
							depot.getXCoord(), depot.getYCoord()));                   
				}

				rowCounter++; //go to next row 
			} //end for
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Reading the line");
		}

		try {
			//availShipments.outputMDTSPShipData(type, t, MDTSPFileName, "outCust.txt");   //problem type, #days or depots
			//outputMDTSPShipData(type, t, MDTSPFileName, "outCust.txt");   //problem type, #days or depots
		}
		catch (Exception e) {
			System.out.println("Shipment information could not be sent to the file");
		}
		return 1;
	}


	
	
	
	
	
	

	public void printDataToConsole() {
		try {
			mainShipments.printTSPShipmentsToConsole();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write out the data file that was read in
	 * @param file name of file used for generating the data
	 */

	public void writeDataFile(String file) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(ProblemInfo.
					outputPath +file +"_students.txt"));
			mainShipments.writeTSPShipments(ps);
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	/**
	 * Will write a long detailed solution for the problem
	 * @param file name of the file to write to
	 */
	public void writeLongSolution(String file) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(ProblemInfo.
					outputPath + file + "_long.txt"));
			mainDepots.printDepotLinkedList(ps);
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	/**
	 * Will write a short solution for the problem
	 * @param file name of the file to write to
	 */
	public void writeShortSolution(String file) {
		try {
			//PrintStream ps = new PrintStream(new FileOutputStream(ProblemInfo.
			//outputPath + "/" + file + "_short.txt"));
			PrintStream ps = new PrintStream(new FileOutputStream(ProblemInfo.
					outputPath + file + "_short.txt"));

			ps.println("File: " + file + " Num Depots: " +
					ProblemInfo.numDepots + " Num Pick Up Points: " +
					ProblemInfo.numCustomers + " Num Trucks: " +
					ProblemInfo.numTrucks + " Processing Time: " +
					(endTime - startTime) / 1000 + " seconds");
			ps.println(mainDepots.getAttributes().toDetailedString());
			ps.println();

			Depot depotHead = mainDepots.getHead();
			Depot depotTail = mainDepots.getTail();

			while (depotHead != depotTail) {
				Truck truckHead = depotHead.getMainTrucks().getHead();
				Truck truckTail = depotHead.getMainTrucks().getTail();

				while (truckHead != truckTail) {
					ps.print("Truck #" + truckHead.getTruckNum() + " MaxCap: " +
							truckHead.getTruckType().getMaxCapacity() + " Demand: " +
							truckHead.getAttributes().getTotalDemand() + " ROUTE:");

					Nodes nodesHead = truckHead.getMainNodes().getHead();
					Nodes nodesTail = truckHead.getMainNodes().getTail();

					while (nodesHead != nodesTail) {
						ps.print(nodesHead.getIndex() + " ");
						nodesHead = nodesHead.getNext();
					}

					ps.println();
					truckHead = truckHead.getNext();
				}

				ps.println();
				ps.println();
				depotHead = depotHead.getNext();
			}
			for (int i = 0; i < optInformation.size(); i++) {
				ps.println(optInformation.elementAt(i));
			}
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	/*
	 * Create a excel file to put in the final solutions
	 */
	
	public static void createFinalExcelFile(int numSolution){
		  
		  
		  int rowCounter = 0;
		  int curCol = 0;	//column counter
		  XSSFWorkbook workbook = new XSSFWorkbook(); // create a book
		  XSSFSheet sheet = workbook.createSheet("Sheet1");// create a sheet
		  XSSFRow curRow = sheet.createRow(rowCounter++); // create a row
		  
		  //fill the first row of excel, Solution numbers
		  curCol = 2;
		  for(int i=0; i<numSolution; i++){
			  curRow.createCell(curCol).setCellValue("Solution " + (i));
			  curCol = curCol+4;		  
		  }
		  
		  curRow = sheet.createRow(rowCounter++); // create a row
		  curCol = 0;
		  curRow.createCell(curCol++).setCellValue("Problem");
		  curRow.createCell(curCol++).setCellValue("Best Known Solution");	  
		  for(int i=0; i<numSolution; i++){
			  curRow.createCell(curCol++).setCellValue("Num of Vehicle");
			  curRow.createCell(curCol++).setCellValue("Distance");
			  curRow.createCell(curCol++).setCellValue("Cost");
			  curRow.createCell(curCol++).setCellValue("Percentage");		  	  
		  }
		  	  
	      //save the file
	      try 
	      {
	    	  FileOutputStream fout = new FileOutputStream(new File(ProblemInfo.outputPath + "Comparison.xlsx"));
	    	  workbook.write(fout); 
	          fout.close();
	      } 
	      catch (Exception e) 
	      { 
	          e.printStackTrace(); 
	      } 
		  
	  }

	/**
	 * Runs optmizations inserted into the mainOpts vector
	 */
	public void runOptimizations() {
		OptInfo info = new OptInfo();
		for (int i = 0; i < mainOpts.size(); i++) {
			//Extract the operator in the vector and convert it into a
			//SearchStrategy type. The object that is loaded into the
			//SearchStrategy type is not SearchStrategy as it is an
			//abstract class but a class that inherits off of
			//SearchStrategy such as FirstBestIntraOpt, FirstBestInterOpt
			//and so on. the opt.run method runs the run method
			//from the inheriting class such as FirstBestIntraOpt.
			SearchStrategy opt = (SearchStrategy) mainOpts.elementAt(i);
			Settings.printDebug(Settings.COMMENT, "Running " + opt);

			//The opt.run method called is dependent on the object that
			//was created in the mainOpts. If the object was BestBestIntraSearch
			//then the run method in BestBestIntraSearch is called.
			optInformation.add(opt.toString() + " " + opt.run(mainDepots));
			Settings.printDebug(Settings.COMMENT,
					opt.toString() + " Stats: " +
							mainDepots.getSolutionString());
		}
	}

} //End of TSP file
