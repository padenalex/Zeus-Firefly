package edu.sru.thangiah.zeus.tsp.sfc;


import edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: This Class will contain both SFC features and SFC routing methods </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Slippery Rock University</p>
 *
 * @author Peter Schallot
 * @version 1.0
 */
public class TSPSpaceFillingCurve {
  public static final int DragonCurve = 0;
  public static final int HilbertCurve = 1;
  public static final int HilbertCurveII = 2;
  public static final int KochCurve = 3;
  public static final int PeanoCurve = 4;
  public static final int PeanoGosperCurve = 5;
  public static final int QuadKochIslandCurve = 6;
  public static final int SArrowheadCurve = 7;
  public static final int SquareCurve = 8;
  public static final int STriangleCurve = 9;
  public static final int ThirtyTwoCurve = 10;
  public static int[] AvailableCurveIDs = { // Included to simplify the process of executing all curves.
      DragonCurve,
      HilbertCurve,
      HilbertCurveII,
      KochCurve,
      PeanoCurve,
      PeanoGosperCurve,
      QuadKochIslandCurve,
      SArrowheadCurve,
      SquareCurve,
      STriangleCurve,
      ThirtyTwoCurve
  };
  public static String[] AvailableCurveNames = {
      "DragonCurve",
      "HilbertCurve",
      "HilbertCurveII",
      "KochCurve",
      "PeanoCurve",
      "PeanoGosperCurve",
      "QuadKochIslandCurve",
      "SArrowheadCurve",
      "SquareCurve",
      "STriangleCurve",
      "ThirtyTwoCurve"
  };

  /*public static int[] maxRecursion = {
    2, //"DragonCurve",
    2, //"HilbertCurve",
    2,  //"HilbertCurveII",
    2, //"KochCurve",
    2,  //"PeanoCurve",
    2,  //"PeanoGosperCurve",
    2,  //"QuadKochIslandCurve",
    2, //"SArrowheadCurve",
    2,  //"SquareCurve",
    2, //"STriangleCurve",
    2  //"ThirtyTwoCurve"
   }; */


  public static int[] maxRecursion = {
      20, //"DragonCurve",
      10, //"HilbertCurve",
      6, //"HilbertCurveII",
      10, //"KochCurve",
      6, //"PeanoCurve",
      7, //"PeanoGosperCurve",
      6, //"QuadKochIslandCurve",
      12, //"SArrowheadCurve",
      8, //"SquareCurve",
      10, //"STriangleCurve",
      4 //"ThirtyTwoCurve"
  };

  private double[] curve; // The generated SFC
  private double[] data; // The problem's data
  double translateX;
  double translateY;
  double rotate;
  double scaleX;
  double scaleY;
  double shearX;
  double shearY;

  public TSPSpaceFillingCurve(double[] dataSet, int curveType, int generation,
                 double translateX,
                 double translateY, double rotate, double scaleX, double scaleY,
                 double shearX, double shearY) throws
      SFCInvalidGenerationException {

    this.translateX = translateX;
    this.translateY = translateY;
    this.rotate = rotate;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.shearX = shearX;
    this.shearY = shearY;

    double step = 1; // - These parameters
    double fac = 0; // -   will be implemented
    double x = 0; // -     in this class instead of in the SFC class.
    double y = 0; // -

    data = dataSet;

    // Create the desired space-filling curve.
    switch (curveType) {
      case DragonCurve:
        curve = new DragonCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case HilbertCurve:
        curve = new HilbertCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case HilbertCurveII:
        curve = new HilbertCurveII(generation, step, fac, x, y).DrawCurve();
        break;
      case KochCurve:
        curve = new KochCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case PeanoCurve:
        curve = new PeanoCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case PeanoGosperCurve:
        curve = new PeanoGosperCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case QuadKochIslandCurve:
        curve = new QuadKochIslandCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case SArrowheadCurve:
        curve = new SArrowheadCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case SquareCurve:
        curve = new SquareCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case STriangleCurve:
        curve = new STriangleCurve(generation, step, fac, x, y).DrawCurve();
        break;
      case ThirtyTwoCurve:
        curve = new ThirtyTwoCurve(generation, step, fac, x, y).DrawCurve();
        break;
      default:
        System.out.println("Error in TSPSFC Constructor: " + curveType +
                           " is not a valid curve type.");
    }
    shapeCurveToData();

  }

  /**
   * Scale the curve to fit over the data points, and then distort according
   * to the ditstortion parameters.
   */
  private void shapeCurveToData() {

    if (data.length < 2 || curve.length < 2) {
      return;
    }

    // Extreme values in the dataSet and nodeSet vectors
    double dataXMax = data[0];
    double dataYMax = data[1];
    double dataXMin = dataXMax;
    double dataYMin = dataYMax;
    double nodeXMax = curve[0];
    double nodeYMax = curve[1];
    double nodeXMin = nodeXMax;
    double nodeYMin = nodeYMax;
    // Scale and translation factors
    double xScale, xTranslate, yScale, yTranslate;
    // Temporary scaled values
    double xTemp, yTemp;

    // Shear and Rotate the nodeSet.
    // To rotate (x,y) theta radians about the origin:
    // x = cos(theta)*x-sin(theta)*y;
    // y = sin(theta)*x+cos(theta)*y;
    double x;
    double y;
    for (int i = 0; i < curve.length - 1; i += 2) {
      x = curve[i];
      y = curve[i + 1];

      // Shear
      x = x + shearX * y;
      y = y + shearY * x;

      // Rotate
      x = Math.cos(rotate) * x - Math.sin(rotate) * y;
      y = Math.sin(rotate) * x + Math.cos(rotate) * y;

      curve[i] = x;
      curve[i + 1] = y;
    }

    // Find extrema in both vectors:
    for (int i = 0; i < data.length - 1; i += 2) {
      if (data[i] > dataXMax) {
        dataXMax = data[i];
      }
      if (data[i] < dataXMin) {
        dataXMin = data[i];
      }

      if (data[i + 1] > dataYMax) {
        dataYMax = data[i + 1];
      }
      if (data[i + 1] < dataYMin) {
        dataYMin = data[i + 1];
      }
    }

    dataXMin = dataXMin - scaleX * (dataXMax - dataXMin) / 2 + translateX;
    dataYMin = dataYMin - scaleX * (dataYMax - dataYMin) / 2 + translateX;
    dataXMax = dataXMax + scaleY * (dataXMax - dataXMin) / 2 + translateY;
    dataYMax = dataYMax + scaleY * (dataYMax - dataYMin) / 2 + translateY;

    for (int i = 0; i < curve.length - 1; i += 2) {
      if (curve[i] > nodeXMax) {
        nodeXMax = curve[i];
      }
      if (curve[i] < nodeXMin) {
        nodeXMin = curve[i];
      }

      if (curve[i + 1] > nodeYMax) {
        nodeYMax = curve[i + 1];
      }
      if (curve[i + 1] < nodeYMin) {
        nodeYMin = curve[i + 1];
      }
    }

    // Calculate scaling and translation values
    xScale = ( -dataXMin + dataXMax) / ( -nodeXMin + nodeXMax);
    xTranslate = - (dataXMax * nodeXMin - nodeXMax * dataXMin) /
        ( -nodeXMin + nodeXMax);
    yScale = ( -dataYMin + dataYMax) / ( -nodeYMin + nodeYMax);
    yTranslate = - (dataYMax * nodeYMin - nodeYMax * dataYMin) /
        ( -nodeYMin + nodeYMax);

    for (int i = 0; i < curve.length - 1; i += 2) {
      xTemp = curve[i] * xScale + xTranslate;
      yTemp = curve[i + 1] * yScale + yTranslate;
      curve[i] = xTemp;
      curve[i + 1] = yTemp;
    }
  }

  /**
   * Returns the euclidean distance bewteen the point (x1,y1) and (x2,y2).
   * @param x1 double
   * @param y1 double
   * @param x2 double
   * @param y2 double
   * @return double
   */
  private double EuclideanDistance(double x1, double y1, double x2, double y2) {
    return Math.sqrt( ( (x1 - x2) * (x1 - x2)) + ( (y1 - y2) * (y1 - y2)));
  }

  /**
   * Returns the euclidean distance between the point (x1,y1,z1) and (x2,y2,z2).
   * @param x1 double
   * @param y1 double
   * @param z1 double
   * @param x2 double
   * @param y2 double
   * @param z2 double
   * @return double
   */
  private double EuclideanDistance(double x1, double y1, double z1,
                                   double x2, double y2, double z2) {
    return Math.sqrt( ( (x1 - x2) * (x1 - x2))
                     + ( (y1 - y2) * (y1 - y2))
                     + ( (z1 - z2) * (z1 - z2)));
  }

  /**
   * Determine a distance between two points on the plane using the spacefilling curve.
   * This is done by finding the corresponding point along the curve.
   * @param x double
   * @param y double
   * @return double
   */
  public double curvePosition(double x, double y) {

    // If the array is null, or does not contain two values, all points are equidistant.
    if (curve.length <= 1) {
      return 0;
    }

    // Compare the distances to all points in the curve, and assign (x,y) to the closest node
    int i = 0;
    double currentX = curve[i];
    double currentY = curve[i + 1];
    double currentDistance = EuclideanDistance(x, y, currentX, currentY);
    double minimumFoundDistance = currentDistance;
    int index = 0;

    for (i = 2; i < curve.length - 1; i += 2) {
      currentX = curve[i];
      currentY = curve[i + 1];
      currentDistance = EuclideanDistance(x, y, currentX, currentY);

      if (currentDistance < minimumFoundDistance) {
        minimumFoundDistance = currentDistance;
        index = i / 2;
      }
    }
    /*    for(int k=0; k<curve.length; k+=2){
          System.out.print( "[" + curve[k] + "," + curve[k+1] + "], " );
        }
     */
    return index;
  }

  /**
   * Determine a distance between two three-dimensional points and a point on a
   * two-dimensional spacefilling curve lying on the xy plane.
   * @todo THIS DOES NOT WORK AS INTENDED AND WILL PROBABLY NEED TO BE REMOVED
   * @param x double
   * @param y double
   * @param z double
   * @return double
   */
  public double curvePosition(double x, double y, double z) {

    // If the array is null, or does not contain two values, all points are equidistant.
    if (curve.length <= 1) {
      return 0;
    }

    // Compare the distances to all points in the curve, and assign (x,y) to the closest node
    int i = 0;
    int index = 0;
    double CurveShiftZ = 0; // The position along the z-axis that the spacefilling curve will be placed
    double currentX = curve[i];
    double currentY = curve[i + 1];
    double currentDistance = EuclideanDistance(x, y, z, currentX, currentY,
                                               CurveShiftZ);
    double minimumFoundDistance = currentDistance;

    for (i = 2; i < curve.length - 1; i += 2) {
      currentX = curve[i];
      currentY = curve[i + 1];

      currentDistance = EuclideanDistance(x, y, z, currentX, currentY,
                                          CurveShiftZ);
//      System.out.println("DIFFERENCE = " + (currentDistance - EuclideanDistance(x,y,currentX,currentY))  );

      if (currentDistance < minimumFoundDistance) {
        minimumFoundDistance = currentDistance;
        index = i / 2;
      }
    }
    return index;
  }

  public static String curveInfo(int curveType) {
    if (AvailableCurveIDs.length != AvailableCurveNames.length ||
        AvailableCurveNames.length == 0) {
      return
          "ERROR (in TSPSFC.curveInfo): Curve Names are not defined correctly";
    }
    else {
      for (int i = 0; i < AvailableCurveIDs.length; i++) {
        if (curveType == AvailableCurveIDs[i]) {
          return AvailableCurveNames[i];
        }
      }
    }
    return "ERROR (in TSPSFC.curveInfo): Nonexistent curve, Type " + curveType +
        ".";
  }

  /**
   * Return the maximum reasonable recursion level for a method.
   * @param method int
   * @return int
   */
  public static int getMaxRecursion(int method) {
    if (method < 0 || method >= maxRecursion.length) {
      return 0;
    }
    else {
      return maxRecursion[method];
    }
  }

  public double[] getCurve(){
    return curve;
  }

}






