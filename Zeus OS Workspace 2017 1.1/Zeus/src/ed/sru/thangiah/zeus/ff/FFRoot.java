package edu.sru.thangiah.zeus.ff;

import edu.sru.thangiah.zeus.core.ProblemInfo;
import edu.sru.thangiah.zeus.ff.ffcostfunctions.*;

public class FFRoot {
  /**
   * Constructor. Runs the FF and calculates the total CPU time
   */
  public FFRoot() {

    //Settings for the ProblemInfo class
    //Problem info consists of a set of static values that are used by a number
    //of different classes. The following has to be set in order for the program
    //to function correctly.
    ProblemInfo.nodesLLLevelCostF = new FFNodesLLCostFunctions();
    ProblemInfo.truckLevelCostF = new FFTruckCostFunctions();
    ProblemInfo.truckLLLevelCostF = new FFTruckLLCostFunctions();
    ProblemInfo.depotLevelCostF = new FFDepotCostFunctions();
    ProblemInfo.depotLLLevelCostF = new FFDepotLLCostFunctions();
    //Paths for temporary, input and output files
    //ProblemInfo.currDir gives the working directory of the program
    ProblemInfo.tempFileLocation = ProblemInfo.workingDirectory+"\\temp";
    ProblemInfo.inputPath = ProblemInfo.workingDirectory+"\\data\\ff\\problems\\";

    ProblemInfo.outputPath = ProblemInfo.workingDirectory+"\\data\\ff\\results\\";

    /* String path = "";
           String newpath = "SBFF/new";
           String oldpath = "SBFF/old";
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
        new FF(path + "data/" + oldpath + "/" + s);
      }
     */
    //new ff.MDFF("mdff_p01.txt");
    //new FF("mdff_p01.txt"); //Reading from a text file
    new FF("a280.xlsx");
    //new edu.sru.thangiah.zeus.ff.FF("mdff_p01.txt");
  }
}
