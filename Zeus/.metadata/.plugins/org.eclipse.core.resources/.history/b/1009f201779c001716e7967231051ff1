package edu.sru.thangiah.zeus.tsp;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.tsp.tspcostfunctions.*;

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
    ProblemInfo.truckLevelCostF = new TSPTruckCostFunctions();
    ProblemInfo.truckLLLevelCostF = new TSPTruckLLCostFunctions();
    ProblemInfo.depotLevelCostF = new TSPDepotCostFunctions();
    ProblemInfo.depotLLLevelCostF = new TSPDepotLLCostFunctions();
    //Paths for temporary, input and output files
    //ProblemInfo.currDir gives the working directory of the program
    ProblemInfo.tempFileLocation = ProblemInfo.workingDirectory+"\\temp";
    ProblemInfo.inputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\problems\\";

    ProblemInfo.outputPath = ProblemInfo.workingDirectory+"\\data\\tsp\\results\\";

    /* String path = "";
           String newpath = "SBTSP/new";
           String oldpath = "SBTSP/old";
           Settings.debugLevel = Settings.WARNING; //only show error messages

           ProblemInfo.inputPath  = path + "data/" + newpath + "/";
           ProblemInfo.outputPath = path + "results/" + newpath + "/";

           ProblemInfo.inputPath  = path + "data/" + oldpath + "/";
           ProblemInfo.outputPath = path + "results/" + oldpath + "/";
     */


    /* All problems are labeled as p01, p02,...,p10, p11.
       This code generates the file names automatically and passes it to
      algorithm to be solved.
     */
    /* for(int i=1; i<=2; i++){
        String s = "p";
        //all files that have single digits are named p01, p02 ..
        if(i<10){
          s += "0" + i;
        }
        //for files that have double digits such as p10,p11
        else{
          s += "" + i;
        }

        System.out.println("Executing old " + s);
        new TSP(path + "data/" + oldpath + "/" + s);
      }
     */
    //new tsp.MDTSP("mdtsp_p01.txt");
    //new TSP("mdtsp_p01.txt"); //Reading from a text file
    new TSP("a280.xlsx");
    //new edu.sru.thangiah.zeus.tsp.TSP("mdtsp_p01.txt");
  }
}
