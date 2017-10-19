package edu.sru.thangiah.zeus.tsp;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import java.io.FileInputStream;
import edu.sru.thangiah.zeus.tsp.tspcostfunctions.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TSPRoot {
  /**
   * Constructor. Runs the TSP and calculates the total CPU time
   */
  public TSPRoot() {

    //Settings for the ProblemInfo class
    //Problem info consists of a set of static values that are used by a number
    //of different classes. The following has to be set in order for the program
    //to function correctly.
    ProblemInfo.nodesLLLevelCostF = new TSPNodesLLCostFunctions();
    //ProblemInfo.truckLevelCostF = new TSPTruckCostFunctions();
    //ProblemInfo.truckLLLevelCostF = new TSPTruckLLCostFunctions();
    //ProblemInfo.depotLevelCostF = new TSPDepotCostFunctions();
    //ProblemInfo.depotLLLevelCostF = new TSPDepotLLCostFunctions();
    //Paths for temporary, input and output files
    //ProblemInfo.currDir gives the working directory of the program
    ProblemInfo.tempFileLocation = ProblemInfo.workingDirectory+"\\temp";
    ProblemInfo.inputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\Problems\\";

    ProblemInfo.outputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\Results\\";

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
		
		//while ((String)curRow.getCell(0).getStringCellValue() != "EOF") {
		rowCounter++;
		curRow = sheet.getRow(rowCounter); // the 2nd row is the problem data
		FileName = (String)curRow.getCell(0).getStringCellValue();
		//FileType = (int)curRow.getCell(1).getNumericCellValue();
	   // new TSP(FileName+".xlsx", FileType);
		//}
		
		FileType = 0;
		FileName = "a280.xlsx";
		FileType = 1;
		FileName = "bayg29.xlsx";
		new TSP(FileName, FileType);
		
		
		//---------------------------------------------
	}
	catch (Exception e) {
		System.out.println("readDataFromExcelFile - "+"master.xlsx"+" File is not present");
		e.printStackTrace();
	}
  }
}
