package edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics;

/**
*
* <p>Title: KochCurve</p>
*
* <p>Description: This class inherits from the SpaceFillingCurve class to draw a Koch Curve</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: Slippery Rock University</p>
*
* @author David Crissman
* @version 1.0
*/
public class KochCurve extends SpaceFillingCurve {
    public KochCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "Koch Curve";
    }

    /**
     * Calculates the number of ordered pairs which make up the curve, given the current value of generation
     */
    protected void calcPoints()
    {
        numPoints = (int)(Math.pow(4, generation)) + 1;
    }

    /**
     * Perform a series of steps, indicated by the formula for a Koch Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void KCurve(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            KCurve(genCount - 1);
            TurnRight();
            KCurve(genCount - 1);
            TurnLeft();
            TurnLeft();
            KCurve(genCount - 1);
            TurnRight();
            KCurve(genCount - 1);
        }
        //This is the last step of the recursive formula
        else {
            StepForward();
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

        KCurve(generation);

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
