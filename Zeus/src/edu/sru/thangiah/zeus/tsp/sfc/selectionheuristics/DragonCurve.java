package edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics;

/**
 *
 * <p>Title: DragonCurve</p>
 *
 * <p>Description: This class inherits from the SpaceFillingCurve class to draw a Dragon Curve</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Slippery Rock University</p>
 *
 * @author David Crissman
 * @version 1.0
 */
public class DragonCurve extends SpaceFillingCurve {
    public DragonCurve(int gen, double step, double fac, double x, double y) throws SFCInvalidGenerationException {
        super(gen, step, fac, x, y);
        curveName = "Dragon Curve";
        calcPoints();
    }

    /**
     * Calculates the number of ordered pairs which make up the curve, given the current value of generation
     */
    protected void calcPoints()
    {
        numPoints = (int)(Math.pow(2, generation));
    }

    /**
     * Perform a series of steps, indicated by X in the definition of a Dragon Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    public void DCurveX(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            DCurveX(genCount - 1);
            TurnRight();
            DCurveY(genCount - 1);
            StepForward();
            TurnRight();
        }
    }

    /**
     * Perform a series of steps, indicated by Y in the definition of a Dragon Curve
     * @param genCount int - Number of recursive steps which must still be taken
     */
    public void DCurveY(int genCount) {
        //We have not yet reached the last recursive step
        if (genCount > 0) {
            TurnLeft();
            StepForward();
            DCurveX(genCount - 1);
            TurnLeft();
            DCurveY(genCount - 1);
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

        DCurveX(generation);

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
