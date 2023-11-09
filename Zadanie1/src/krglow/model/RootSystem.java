package krglow.model;

/**
 * Represents the root system of a plant or tree, detailing its depth and spread.
 */
public class RootSystem {

    /**
     * The depth of the root system in the soil, usually measured in centimeters or meters.
     */
    private double depth;

    /**
     * The lateral spread of the root system, typically measured in centimeters or meters. This describes how far the
     * roots extend horizontally from the main stem or trunk.
     */
    private double spread;

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getSpread() {
        return spread;
    }

    public void setSpread(double spread) {
        this.spread = spread;
    }

}
