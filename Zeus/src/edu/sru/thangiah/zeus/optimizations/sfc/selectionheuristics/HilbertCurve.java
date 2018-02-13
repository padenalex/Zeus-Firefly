package edu.sru.thangiah.zeus.optimizations.sfc.selectionheuristics;

/**
 *
 * <p>Title: HilbertCurve</p>
 *
 * <p>Description: This class inherits from the SpaceFillingCurve class to draw a Hilbert Curve (type I)</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Slippery Rock University</p>
 *
 * @author David Crissman
 * @version 1.0
 */
public class HilbertCurve extends SpaceFillingCurve {
    public HilbertCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "Hilbert Curve (Type I)";
    }

    /**
     * Calculates the number of ordered pairs which make up the curve, given the current value of generation
     */
    public void calcPoints()
    {
        numPoints = (int)(Math.pow(4, generation));
    }

    /**
     * Perform the series of actions indicated by L in the definition of a Hilbert Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void HCurveL(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            TurnRight();
            HCurveR(genCount - 1);
            StepForward();
            TurnLeft();
            HCurveL(genCount - 1);
            StepForward();
            HCurveL(genCount - 1);
            TurnLeft();
            StepForward();
            HCurveR(genCount - 1);
            TurnRight();
        }
    }

    /**
     * Perform the series of actions indicated by R in the definition of a Hilbert Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void HCurveR(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            TurnLeft();
            HCurveL(genCount - 1);
            StepForward();
            TurnRight();
            HCurveR(genCount - 1);
            StepForward();
            HCurveR(genCount - 1);
            TurnRight();
            StepForward();
            HCurveL(genCount - 1);
            TurnLeft();
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

        HCurveL(generation);

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
