package edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics;

/**
 *
 * <p>Title: SquareCurve</p>
 *
 * <p>Description: This class inherits from the SpaceFillingCurve class to draw a Square Curve</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Slippery Rock University</p>
 *
 * @author David Crissman
 * @version 1.0
 */
public class SquareCurve
    extends SpaceFillingCurve {
  public SquareCurve(int gen, double step, double fac, double x, double y) throws
      SFCInvalidGenerationException {
    super(gen, step, fac, x, y);
    curveName = "Square Curve";
  }

  /**
   * Calculates the number of ordered pairs which make up the curve, given the current value of generation
   */
  protected void calcPoints() {
    numPoints = 5;
    for (int i = 0; i <= generation - 1; i++) {
      numPoints += 16 * Math.pow(4, i);
    }
  }

  /**
   * Perform a series of steps, defined in the formula for a Square Curve
   * @param genCount int - Number of recursive steps which must still be taken
   */
  private void SCurve(int genCount) {
    //We have not yet reached the last recursive step
    if (genCount > 0) {
      SCurve(genCount - 1);
      StepForward();
      TurnLeft();
      StepForward();
      TurnRight();
      StepForward();
      TurnLeft();
      SCurve(genCount - 1);
      StepForward();
      TurnRight();
      StepForward();
      TurnRight();
      SCurve(genCount - 1);
      StepForward();
      TurnLeft();
      StepForward();
      TurnRight();
      StepForward();
      TurnLeft();
      SCurve(genCount - 1);
    }
  }

  /**
   * Returns a series of (x,y) coordinates, corresponding to the vertices of the space-filling curve
   * The curve is generated with the specified generation (positive integer) and starting facing (in radians)
   * @return double[] - Series of x and y coordinates which define the generated curve
   */
  public double[] DrawCurve() {
    calcPoints();
    curveCoords = new double[numPoints * 2];
    curveCoords[0] = 0.0;
    curveCoords[1] = 0.0;
    currentIndex = 2;
    facing = startFacing;
    turnAngle = Math.PI / 2;

    StepForward();
    TurnRight();
    SCurve(generation);
    StepForward();
    TurnRight();
    StepForward();
    TurnRight();
    SCurve(generation);
    StepForward();

    return curveCoords;
  }

  /**
   * Provides data about the curve which can be displayed by the ZeusGUI
   * @return String - String containing the type of curve, generation, and starting facing
   */
  public String toString() {
    return "Name: " + curveName + " " + super.toString();
  }
}
