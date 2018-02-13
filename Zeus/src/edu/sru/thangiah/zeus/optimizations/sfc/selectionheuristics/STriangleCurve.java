package edu.sru.thangiah.zeus.optimizations.sfc.selectionheuristics;

/**
*
* <p>Title: STriangleCurve</p>
*
* <p>Description: This class inherits from the SpaceFillingCurve class to draw a Sierpinski Triangle Curve</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: Slippery Rock University</p>
*
* @author David Crissman
* @version 1.0
*/
public class STriangleCurve extends SpaceFillingCurve {
    public STriangleCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "Sierpinski Triangle Curve";
    }

    /**
     * Calculates the number of ordered pairs which make up the curve, given the current value of generation
     */
    protected void calcPoints()
    {
        numPoints = ((int)(Math.pow(3, generation - 1)) * 18) + 1;
    }

    /**
     * Perform a series of steps, indicated by F in the definition of a Sierpinski Triangle Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void STCurveF(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 1) {
            STCurveF(genCount - 1);
            STCurveF(genCount - 1);
        }
        //This is the last recursive step
        else {
            StepForward();
            StepForward();
        }
    }

    /**
     * Perform a series of steps, indicated by X in the definition of a Sierpinski Triangle Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void STCurveX(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 1) {
            TurnLeft();
            TurnLeft();
            STCurveF(genCount - 1);
            STCurveX(genCount - 1);
            STCurveF(genCount - 1);
            TurnRight();
            TurnRight();
            STCurveF(genCount - 1);
            STCurveX(genCount - 1);
            STCurveF(genCount - 1);
            TurnRight();
            TurnRight();
            STCurveF(genCount - 1);
            STCurveX(genCount - 1);
            STCurveF(genCount - 1);
            TurnLeft();
            TurnLeft();
        }
        //This is the last recursive step
        else {
            TurnLeft();
            TurnLeft();
            StepForward();
            StepForward();
            TurnRight();
            TurnRight();
            StepForward();
            StepForward();
            TurnRight();
            TurnRight();
            StepForward();
            StepForward();
            TurnLeft();
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
        turnAngle = Math.PI / 3;

        STCurveF(generation);
        STCurveX(generation);
        STCurveF(generation);
        TurnLeft();
        TurnLeft();
        STCurveF(generation);
        STCurveF(generation);
        TurnLeft();
        TurnLeft();
        STCurveF(generation);
        STCurveF(generation);

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
