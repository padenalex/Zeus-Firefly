package edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics;

/**
*
* <p>Title: ThirtyTwoCurve</p>
*
* <p>Description: This class inherits from the SpaceFillingCurve class to draw a 32-Segment Curve</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: Slippery Rock University</p>
*
* @author David Crissman
* @version 1.0
*/
public class ThirtyTwoCurve extends SpaceFillingCurve {
    public ThirtyTwoCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "32-Segment Curve";
    }

    /**
         * Calculates the number of ordered pairs which make up the curve, given the current value of generation
         */
        protected void calcPoints()
        {
            numPoints = ((int)(Math.pow(32, generation - 1)) * 4) + 1;
    }

    /**
     * Perform a series of steps, indicated by the formula for a 32-Segment Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    private void TTCurve(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 1) {
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnRight();
            TTCurve(genCount - 1);
            TurnLeft();
            TTCurve(genCount - 1);
            TurnRight();
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
        turnAngle = Math.PI / 2;

        TTCurve(generation);
        TurnRight();
        TTCurve(generation);
        TurnRight();
        TTCurve(generation);
        TurnRight();
        TTCurve(generation);

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
