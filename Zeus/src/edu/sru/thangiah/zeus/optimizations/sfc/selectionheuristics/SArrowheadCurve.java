package edu.sru.thangiah.zeus.optimizations.sfc.selectionheuristics;

/**
*
* <p>Title: SArrowheadCurve</p>
*
* <p>Description: This class inherits from the SpaceFillingCurve class to draw a Sierpinski Arrowhead Curve</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: Slippery Rock University</p>
*
* @author David Crissman
* @version 1.0
*/
public class SArrowheadCurve extends SpaceFillingCurve {
    public SArrowheadCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "Sierpinski Arrowhead Curve";
    }

    /**
     * Calculates the number of ordered pairs which make up the curve, given the current value of generation
     */
    protected void calcPoints()
    {
        numPoints = (int)(Math.pow(3, generation)) + 1;
    }

    /**
     * Perform a series of steps, indicated by X in the definition of a Sierpinski Arrowhead Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    public void ArrowCurveX(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            ArrowCurveY(genCount - 1);
            StepForward();
            TurnRight();
            ArrowCurveX(genCount - 1);
            StepForward();
            TurnRight();
            ArrowCurveY(genCount - 1);
        }
    }

    /**
     * Perform a series of steps, indicated by Y in the definition of a Sierpinski Arrowhead Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    public void ArrowCurveY(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            ArrowCurveX(genCount - 1);
            StepForward();
            TurnLeft();
            ArrowCurveY(genCount - 1);
            StepForward();
            TurnLeft();
            ArrowCurveX(genCount - 1);
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
        turnAngle = Math.PI / 3;

        ArrowCurveY(generation);
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
