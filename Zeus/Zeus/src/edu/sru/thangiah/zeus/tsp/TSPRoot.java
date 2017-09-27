package edu.sru.thangiah.zeus.tsp;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.tsp.tspcostfunctions.*;

public class TSPRoot {
  /**
   * Constructor. Runs the TSP and calculates the total CPU time
   */
  public TSPRoot() {

    ProblemInfo.nodesLLLevelCostF = new TSPNodesLLCostFunctions();
    ProblemInfo.truckLevelCostF = new TSPTruckCostFunctions();
    ProblemInfo.truckLLLevelCostF = new TSPTruckLLCostFunctions();
    ProblemInfo.depotLevelCostF = new TSPDepotCostFunctions();
    ProblemInfo.depotLLLevelCostF = new TSPDepotLLCostFunctions();
    ProblemInfo.tempFileLocation = ProblemInfo.workingDirectory+"\\temp";
    ProblemInfo.inputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\problems\\";

    ProblemInfo.outputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\results\\";

    //-------------------------------------------------------------------------
	//Open the requested file
	XSSFWorkbook workbook = new XSSFWorkbook();    
	FileInputStream fis;
	XSSFSheet sheet;
	XSSFRow curRow;
	int rowCounter = 0; //initial the row counter
	String FileName;
	int FileType;
	

	//    FileInputStream fis;
	//    InputStreamReader isr;
	//    BufferedReader br;
	try { 
		fis = new FileInputStream(ProblemInfo.inputPath+"master.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		curRow = sheet.getRow(rowCounter); // the 2nd row is the problem data
		//---------------------------------------------
		while ((String)curRow.getCell(0).getStringCellValue() != "EOF") {
		rowCounter++;
		curRow = sheet.getRow(rowCounter); // the 2nd row is the problem data
		FileName = (String)curRow.getCell(0).getStringCellValue(); //Get File Name From Master
		FileType = (int)curRow.getCell(1).getNumericCellValue(); //Get File Type From Master
	    new TSP(FileName+".xlsx", FileType);
		}
		//---------------------------------------------
	}
	catch (Exception e) {
		System.out.println("readDataFromExcelFile - "+"master.xlsx"+" File is not present");
	}
    
	
	//--------------------------------------------------------------------------
    //Read In File
    //new TSP("a280.xlsx");

  }
}
