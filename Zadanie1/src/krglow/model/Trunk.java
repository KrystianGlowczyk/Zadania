package krglow.model;

/**
 * Represents the trunk of a tree with specific attributes such as type and height.
 */
public class Trunk {

    /** The diameter of the trunk in centimeters. */
    private double diameter;

    /** The height of the trunk in centimeters. */
    double height;

    public Trunk() {
    }

    public Trunk(double diameter, double height) {
        this.diameter = diameter;
        this.height = height;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
