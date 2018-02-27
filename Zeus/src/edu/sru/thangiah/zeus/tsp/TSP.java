package edu.sru.thangiah.zeus.tsp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import edu.sru.thangiah.zeus.core.*;
import edu.sru.thangiah.zeus.localopts.*;
import edu.sru.thangiah.zeus.localopts.interopts.*;
import edu.sru.thangiah.zeus.localopts.intraopts.*;
import edu.sru.thangiah.zeus.optimizations.sfc.MainSFC;
import edu.sru.thangiah.zeus.tsp.tspqualityassurance.*;
import edu.sru.thangiah.zeus.gui.*;
import edu.sru.thangiah.zeus.localopts.OptInfo;
import edu.sru.thangiah.zeus.localopts.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import edu.sru.thangiah.zeus.optimizations.metaheuristics.evolutionary.TSPInteger;
import edu.sru.thangiah.zeus.optimizations.oldfirefly.FireFly;
import edu.sru.thangiah.zeus.optimizations.oldfirefly.Population;

//For the Simulated Annealing metaheuristic
//import edu.sru.thangiah.zeus.metaheuristics.simulatedannealing.*;


//import edu.sru.thangiah.zeus.metaheuristics.simulatedannealing.*;
//import edu.sru.thangiah.zeus.metaheuristics.tabu.*;
import edu.sru.thangiah.zeus.qualityassurance.*;
//import edu.sru.thangiah.zeus.localopts.mixedfleet.*;




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
	
	
	public TSP(String dataFile, int FileType) {

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
		readDataFromExcelFile(ProblemInfo.inputPath + dataFile, FileType);
		Settings.printDebug(Settings.COMMENT,
				"Read Data File: " + ProblemInfo.inputPath + dataFile);
		printDataToConsole();

		//Ensure that the shipment linked list has been loaded with the data
		if (mainShipments.getTSPHead() == null) {
			Settings.printDebug(Settings.ERROR,
					"TSP: Shipment linked list is empty");
		}

		//Set up the shipment selection type
		if (FileType == 0) {
			//ProblemInfo.selectShipType = new SmallestPolarAngleToDepot();
			//Settings.printDebug(Settings.COMMENT, SmallestPolarAngleToDepot.WhoAmI());
			
			//ProblemInfo.selectShipType = new SmallestPolarAngleShortestDistToDepot();        
	        //Settings.printDebug(Settings.COMMENT,SmallestPolarAngleShortestDistToDepot.WhoAmI());
			
			
			//Temp Location for required variables of spacefilling curve method
			int method = MainSFC.DragonCurve;
		    int recursionLevel = 3;
		    double translateX = 0;//.4;
		    double translateY = 0;//.5;
		    double rotate = Math.PI/4;
		    double scaleX = 0;//1.5;
		    double scaleY = 0;//1.5;
		    double shearX = 0;//1.1;
		    double shearY = 0;//1.1;

		    SpaceFillingPathToDepot spaceFillingPath = new SpaceFillingPathToDepot(
		            method, recursionLevel, translateX, translateY,
		            rotate, scaleX, scaleY, shearX, shearY);
		        ProblemInfo.selectShipType = spaceFillingPath; // new SpaceFillingPathToDepot();
		        Settings.printDebug(Settings.COMMENT, spaceFillingPath.WhoAmI()); //SpaceFillingPathToDepot.WhoAmI());
		        
		        
		       // ProblemInfo.selectShipType = new ClosestEuclideanDistToDepot();        
		        //Settings.printDebug(Settings.COMMENT,ClosestEuclideanDistToDepot.WhoAmI());
		}
        
		if (FileType == 1 || FileType == 2 || FileType == 3 || FileType == 4) {
	        ProblemInfo.selectShipType = new ClosestMatrixDist();        
	        Settings.printDebug(Settings.COMMENT,ClosestMatrixDist.WhoAmI());
		}
        
		//set up the shipment insertion type
		ProblemInfo.insertShipType = new LinearGreedyInsertShipment();
		Settings.printDebug(Settings.COMMENT, LinearGreedyInsertShipment.WhoAmI());
		
		//InsertAsGiven
		ProblemInfo.insertShipType = new InsertAsGiven();
		Settings.printDebug(Settings.COMMENT, InsertAsGiven.WhoAmI());
		
		
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
       //-------- CALL WRITE LONG/SHORT TO EXCEL BELOW -------------------------------------------------
		//writeLongSolutionToExcel(dataFile.substring(dataFile.lastIndexOf("/") + 1));
		//writeShortSolutionExcel(dataFile.substring(dataFile.lastIndexOf("/") + 1));


		//create a vector of search strategy/optimizations to execute
		mainOpts = new Vector(1); //vector capacity of 1
		runOptimizations();

		//Check for the quality and integrity of the solution
		System.out.println("Starting QA");
		tspQA = new TSPQualityAssurance(mainDepots, mainShipments);
		if (tspQA.runQA() == false) {
			Settings.printDebug(Settings.ERROR, "QA FAILED!");
		}
		else {
			Settings.printDebug(Settings.COMMENT, "QA succeeded");


		}
		//--------------------------------------------------------------(TURN GUI ON/OFF )---------
		//ZeusGui guiPost = new ZeusGui(mainDepots, mainShipments);
		

	//====== Metaheuristic Call Section ===================================================
		
			//TSPInteger ga = new TSPInteger(mainDepots);
		//FireFly oldfly = new FireFly(mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes());
		Population FFOptimization = new Population(mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getMainNodes());

	//====== END Metaheuristic Call Section ================================================	
		
			
			//At this point all shipments have been assigned
		       //-------- CALL WRITE LONG/SHORT TO EXCEL BELOW -------------------------------------------------
				writeLongSolutionToExcel(dataFile.substring(dataFile.lastIndexOf("/") + 1));
				writeShortSolutionExcel(dataFile.substring(dataFile.lastIndexOf("/") + 1));
	//Signal End of This TSP
	System.out.println(" \n\n =================== END OF TSP SECTION ===================  \n\n");
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
		while (!mainShipments.isAllShipsAssigned()) {


			currDepot = (TSPDepot) mainDepots.getTSPHead();
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
				//Settings.printDebug(Settings.COMMENT,
				//		"The Shipment: <" + theShipment.getIndex() +// " " + theShipment +
				//		"> was routed");
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
	public int readDataFromExcelFile(String TSPFileName, int FileType) {
		
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
		ProblemInfo.noOfDays = t; //number of days (horizon) or number of depots 
		

		if (Q == 0) { //if there is no maximum capacity, set it to a very large number
			Q = 999999999;
		}
		if (D == 0) { //if there is no travel time, set it to a very large number
			D = 999999999; //if there is not maximum distance, set it to a very large number

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
		//there is only one customer type. The integer value for the customer type
		//should match with the integer value for the truck type for the compatibility
		//check to work
		//read in the different customer types
		Vector custTypes = new Vector();
		//Obtain the different customer types
		for (int ct = 0; ct < 1; ct++) {
			custTypes.add(new Integer(1));
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

			rowCounter = 6; 
//----------------------------------------------------------------FIRST IF=0 XY FILES ONLY
		if (FileType == 0) {
			
			for (int k = 0; k < runTimes; k++) {
				index = 0;
				temp = "";
				curRow = sheet.getRow(rowCounter);
	
				try { // read the current row
					i = (int)curRow.getCell(0).getNumericCellValue();
					x = (int)curRow.getCell(1).getNumericCellValue();
					y = (int)curRow.getCell(2).getNumericCellValue();
				}
				catch (Exception e) {
					System.out.println("Line could not be read in line 474");
				}

				Integer custType = (Integer) custTypes.elementAt(0);
				mainShipments.insertShipment(i, x, y, q, d, f, custType.toString());    //
		
				rowCounter++; //go to next row          
			}// end for
		
					i = 0;
					x = 0;
					y= 0;

				TSPDepot depot = new TSPDepot(i, x, y); //n is the number of customers
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
		} 
//--------------------------------END X/Y READIN METHOD
		
		
//--------------------------------START 1,2,3,4 TYPE READIN
		if (FileType == 1 || FileType == 2 || FileType == 3 || FileType == 4) {
			
			
			try {
				XSSFRow DimensionRow = sheet.getRow(3);
				n = (int)DimensionRow.getCell(1).getNumericCellValue(); //get dimension
			}
			catch (Exception e) {
				System.out.println("Error Dimension ReadIn");
			} // End First Try/Catch  (Used To Get Dimension For 2D Array)
			
			
			
			int[][] DataArray = new int[n][n];
			
			
			
			try { //===========START TRY
				
				rowCounter = 7;
				curRow = sheet.getRow(rowCounter);
				Cell CurrentCell = (Cell)curRow.getCell(0);
				
				for(int nRow = 0; nRow < n; nRow++) {	//Populate 2D Array With Matrices
					
					curRow = sheet.getRow(nRow+7);
					
					for(int nCount = 0; nCount < n; nCount++) {
						CurrentCell = (Cell)curRow.getCell(nCount);
						DataArray[nRow][nCount] = (int) CurrentCell.getNumericCellValue();
					}

				}
				ProblemInfo.distanceMatrix = new int[n][n];
				ProblemInfo.distanceMatrix = DataArray;
				
			/*
				//PRINT OUT 2D ARRAY 
				for(int s = 0; s<n; s++){
				    for(int w = 0; w<n; w++)
				    {
				        System.out.print(DataArray[s][w] + " ");
				    }
				    System.out.println();
				}
					//END PRINT OUT 2D ARRAY
				    System.out.println();
				    System.out.println();

			*/	    
				}	//=============END TRY
			

				
			catch (Exception e) {
				System.out.println("Error in Initial File Readin Dimension & Row Start");
			}
			
			
			
//~~~~~~~~ Populate with (n) InsertShipments For Matrices Problems
			
			for(int TimesToRun = 0; TimesToRun < n-1; TimesToRun++){	//Times To Run Loop
				Integer custType = 1;
				mainShipments.insertShipment(TimesToRun, 0, 0, 0, 0, 0, custType.toString());
				
			} //End Times To Run Loop		
			
			int ia = 0;
			int xa = 0;
			int ya= 0;

			TSPDepot depot = new TSPDepot(ia, xa, ya); //n is the number of customers
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
		}
//~~~~~~End Populate with (n) InsertShipments

			
		
		} // end of try
				
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Reading the line");
		}

		return 1;
	}

//=================================================================END READIN 
	
	
	
	

	public void printDataToConsole() {
		try {
			mainShipments.printTSPShipmentsToConsole();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	//---(New Write Excel Short/Long---------------
	
	
	public void writeShortSolutionExcel(String file) 
	{
		
		//setup excel file
		int rowCounter = 0;
		XSSFWorkbook workbook = new XSSFWorkbook(); // create a book
		XSSFSheet sheet = workbook.createSheet("Sheet1");// create a sheet
		XSSFRow curRow = sheet.createRow(rowCounter); // create a row
		
		//Problem info
		curRow.createCell(0).setCellValue("File: ");
		curRow.createCell(1).setCellValue(file);
		curRow.createCell(2).setCellValue("Num Depots: ");
		curRow.createCell(3).setCellValue(ProblemInfo.numDepots);
		curRow.createCell(4).setCellValue("Num Pick Up Points: ");
		curRow.createCell(5).setCellValue(ProblemInfo.numCustomers);
		curRow.createCell(6).setCellValue("Num Trucks: ");
		curRow.createCell(7).setCellValue(ProblemInfo.numTrucks);
		curRow.createCell(8).setCellValue("Processing Time: ");
		curRow.createCell(9).setCellValue((endTime - startTime) / 1000);
		curRow.createCell(10).setCellValue("seconds");
		
		//next row
		rowCounter++;
		curRow = sheet.createRow(rowCounter);
		
		
		curRow.createCell(0).setCellValue("Total Demand =");
		curRow.createCell(1).setCellValue(mainDepots.getAttributes().getTotalDemand());
		curRow.createCell(2).setCellValue("Total Distance =");
		curRow.createCell(3).setCellValue(mainDepots.getAttributes().getTotalDistance());
		curRow.createCell(4).setCellValue("Total Travel Time =");
		curRow.createCell(5).setCellValue(mainDepots.getAttributes().getTotalTravelTime());
		curRow.createCell(6).setCellValue("Total Cost = ");
		curRow.createCell(7).setCellValue(Math.round(mainDepots.getAttributes().getTotalCost()*100.0)/100.0);
			
		rowCounter++;
		curRow = sheet.createRow(rowCounter);
			
		Depot depotHead = mainDepots.getHead();
		Depot depotTail = mainDepots.getTail();
		
		//Truck header info
		curRow.createCell(0).setCellValue("Truck #");
		curRow.createCell(1).setCellValue("MaxCap:");
		curRow.createCell(2).setCellValue("Demand:");
		
		//loop through Depots, trucks, nodes
		while (depotHead != depotTail) 
		{
			Truck truckHead = depotHead.getMainTrucks().getHead();
			Truck truckTail = depotHead.getMainTrucks().getTail();
			
			//print truck data
			while (truckHead != truckTail) 
			{
				try 
				{
					rowCounter++;
					curRow = sheet.createRow(rowCounter);
					
					
					curRow.createCell(1).setCellValue(truckHead.getTruckType().getMaxCapacity());
					curRow.createCell(0).setCellValue(truckHead.getTruckNum());
					curRow.createCell(2).setCellValue(truckHead.getAttributes().getTotalDemand());
					
					
					Nodes nodesHead = truckHead.getMainNodes().getHead().getNext();
					Nodes nodesTail = truckHead.getMainNodes().getTail();
					
					rowCounter++;
					curRow = sheet.createRow(rowCounter);
					
					curRow.createCell(0).setCellValue("ROUTE:");
					int cellCount = 1;
					
					//print rout data
					while (nodesHead != nodesTail) 
					{
						curRow.createCell(cellCount).setCellValue(nodesHead.getIndex());

						cellCount++;
						nodesHead = nodesHead.getNext();
					}
					
					cellCount = 0;
				}
				catch(NullPointerException ex)
				{
						//System.out.println("Null truck types detected");
						rowCounter--;
				}
					truckHead = truckHead.getNext();
			}
			depotHead = depotHead.getNext();
		}
			 
			rowCounter +=2;
			curRow = sheet.createRow(rowCounter);
			
			curRow.createCell(0).setCellValue("optimization Info");
			
			rowCounter ++;
			curRow = sheet.createRow(rowCounter);
			
			//print Optimization information
			for (int i = 0; i < optInformation.size(); i++) 
			{
				curRow.createCell(i).setCellValue(optInformation.elementAt(i).toString());
			}
			
			try 
		    {
				FileOutputStream fout = new FileOutputStream(new File(ProblemInfo.outputPath + file + "_short.xlsx"));
		    	workbook.write(fout); 
		        fout.close();
		    } 
		    catch (Exception e) 
		    { 
		       e.printStackTrace(); 
		    } 
	}
	
	// Create a excel file to put in the final solutions
	
	public void writeLongSolutionToExcel(String file) {
		try {
			// setting up workbook, row 1 information
			XSSFWorkbook workbook = new XSSFWorkbook();
			PrintStream fos = new PrintStream(new FileOutputStream(ProblemInfo.outputPath + file + "_long.xlsx"));
			XSSFSheet sheet = workbook.createSheet("Sheet1");
			XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue(ProblemInfo.numDepots); 
			
			// row 2 information, depot information(?)
			XSSFRow row2 = sheet.createRow(1);

			row2.createCell(1).setCellValue(mainDepots.getTSPHead().getNext().getXCoord()); // depot location x
			row2.createCell(2).setCellValue(mainDepots.getTSPHead().getNext().getYCoord()); // depot location y
			row2.createCell(3).setCellValue(mainDepots.getAttributes().getTotalDemand());
			row2.createCell(4).setCellValue(mainDepots.getAttributes().getTotalDistance());
			row2.createCell(5).setCellValue(ProblemInfo.noOfVehs);
			
			// row 3 information, truck information(?)
			XSSFRow row3 = sheet.createRow(2);
			row3.createCell(0).setCellValue(mainDepots.getTSPHead().getNext().getMainTrucks().getHead().getNext().getTruckNum()); // what is this
			row3.createCell(1).setCellValue(mainDepots.getNumTrucksUsed());
			row3.createCell(2).setCellValue(mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getAttributes().getTotalDemand());
			row3.createCell(3).setCellValue(mainDepots.getAttributes().getTotalCost());
			row3.createCell(4).setCellValue(mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getTruckType().getMaxDuration());
			row3.createCell(5).setCellValue(mainDepots.getHead().getNext().getMainTrucks().getHead().getNext().getTruckType().getMaxCapacity());
			row3.createCell(6).setCellValue(ProblemInfo.noOfShips);
			
			int curRow = 3;
			Depot depotHead = mainDepots.getHead();
			Depot depotTail = mainDepots.getTail();
			
			// depots
			while (depotHead != depotTail) {
				Truck truckHead = depotHead.getMainTrucks().getHead();
				Truck truckTail = depotHead.getMainTrucks().getTail();
				
				// trucks
				while (truckHead != truckTail) {
					Nodes nodesHead = truckHead.getMainNodes().getHead().getNext();
					Nodes nodesTail = truckHead.getMainNodes().getTail();
					
					// nodes
					while (nodesHead != nodesTail) {
						XSSFRow newRow = sheet.createRow(curRow);
						int isAssigned = (nodesHead) != null ? 1 : 0;
						newRow.createCell(0).setCellValue(nodesHead.getIndex());
						newRow.createCell(1).setCellValue(nodesHead.getShipment().getDemand());
						newRow.createCell(2).setCellValue(nodesHead.getShipment().getXCoord());
						newRow.createCell(3).setCellValue(nodesHead.getShipment().getYCoord());
						newRow.createCell(4).setCellValue(isAssigned);
						curRow++;
						nodesHead = nodesHead.getNext();
					}
				truckHead = truckHead.getNext();
				}
			depotHead = depotHead.getNext();
			}
		workbook.write(fos);
		fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	

	//---------------------------------------------

	
	 // Runs optmizations inserted into the mainOpts vector
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
