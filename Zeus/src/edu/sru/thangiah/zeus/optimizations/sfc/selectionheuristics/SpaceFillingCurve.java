package edu.sru.thangiah.zeus.optimizations.sfc.selectionheuristics;

/**
 *
 * <p>Title: SpaceFillingCurve</p>
 *
 * <p>Description: This is the parent of the other curve-generating classes</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Slippery Rock University</p>
 *
 * @author David Crissman
 * @version 1.0
 */
public abstract class SpaceFillingCurve extends Object {
    protected double startFacing, facing; //Initial anc current direction (in radians) in which drawing is taking place (0.0 is right/east)
    protected double currentX = 0.0, currentY = 0.0; //Coordinates where the drawing is taking place
    protected double turnAngle; //Angle (in radians) to turn when TurnLeft or Turn Right is called
    protected double stepLength; //Distance travelled with each step in the curve
    protected int generation; //Number of recursive generations to apply to the space filling curve formula
    protected String curveName;
    protected int numPoints; //Number of ordered pairs which make up the curve
    protected int currentIndex;
    protected double[] curveCoords;

    protected SpaceFillingCurve() {
        generation = 1;
        startFacing = 0.0;
        stepLength = 1.0;
        currentX = 0.0;
        currentY = 0.0;
    }

    /**
     *
     * @param gen int - Recursive generation to use in the curve's formula
     * @param step double - Distance to move with each step forward in the curve
     * @param fac double - Direction (in radians) to begin drawing the curve
     */
    protected SpaceFillingCurve(int gen, double step, double fac, double x,
                                double y) throws SFCInvalidGenerationException {
        if (gen >= 1) {
            generation = gen;
        } else {
            throw new SFCInvalidGenerationException(gen);
        }

        stepLength = step;
        startFacing = fac;
        currentX = x;
        currentY = y;
    }

    /**
     * This function is implemented by the various subclasses in order to generate the points which make up the curve
     * @return double[] - Series of x and y coordinates which define the generated curve
     */
    public abstract double[] DrawCurve();

    /**
     * This function is implemented by the various subclasses to calculate how many points will make up the generated curve
     */
    protected abstract void calcPoints();

    /**
     * Rotate the current facing by the stored angle, counter-clockwise
     */
    protected void TurnLeft() {
        facing = (facing - turnAngle) % (Math.PI * 2);
    }

    /**
     * Rotate the current facing by the stored angle, clockwise
     */
    protected void TurnRight() {
        facing = (facing + turnAngle) % (Math.PI * 2);
    }

    /**
     * Take a step in the currently stored direction, add the new coordinates to the array, and update the current position
     */
    protected void StepForward() {
        //Horizontal and vertical distances to move, based on the current facing and step length
        double dx = stepLength * Math.cos(facing);
        double dy = stepLength * Math.sin(facing);

        //Update the current position
        currentX += dx;
        currentY += dy;

        //Add the new coordinates to the vector
        curveCoords[currentIndex] = currentX;
        curveCoords[currentIndex + 1] = currentY;
        currentIndex += 2;
    }

    /**
     * Called by the toString functions of the various child classes, in order to complete the output data
     * @return String - String containing the generation and starting facing of the curve
     */
    public String toString() {
        return "Gen: " + generation + " Step: " + stepLength + " StartFac: " +
                startFacing;
    }
}
