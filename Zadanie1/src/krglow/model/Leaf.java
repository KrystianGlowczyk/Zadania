package krglow.model;

/**
 * Represents a leaf with specific characteristics and properties.
 */
public class Leaf {

    /**
     * The general shape of the leaf.
     */
    String shape;

    /**
     * The color of the leaf.
     */
    String color;

    /**
     * The size of the leaf in centimeters.
     */
    double size;

    /**
     * The vein pattern of the leaf (e.g., parallel, pinnate).
     */
    String veinPattern;

    /**
     * The type of the leaf's edge (e.g., smooth, serrated).
     */
    String edgeType;

    /**
     * The arrangement of the leaf relative to the stem (e.g., opposite, alternate).
     */
    String arrangement;

    public Leaf(String shape, String color, double size, String veinPattern, String edgeType, String arrangement) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.veinPattern = veinPattern;
        this.edgeType = edgeType;
        this.arrangement = arrangement;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getVeinPattern() {
        return veinPattern;
    }

    public void setVeinPattern(String veinPattern) {
        this.veinPattern = veinPattern;
    }

    public String getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(String edgeType) {
        this.edgeType = edgeType;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

}
