package edu.sru.thangiah.zeus.tsp.sfc.selectionheuristics;

public class SFCInvalidGenerationException extends Exception {
    private String message;
    private int gen;

    public SFCInvalidGenerationException(int g)
    {
        gen = g;
    }

    public String getMessage() {
        return "Generation Value of " + gen + " is invalid (must be a positive integer)";
    }
}
