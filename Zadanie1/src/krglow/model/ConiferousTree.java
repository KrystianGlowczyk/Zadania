package krglow.model;

import java.util.Random;


/**
 * Represents a coniferous tree, a type of tree that produces cones and typically has needle-shaped leaves. Examples
 * include pines, firs, and spruces.
 */
public class ConiferousTree extends Tree {

    private static final int GROWTH_RATE = 25; // Centimeters per year
    private static final int HEALTH_DECREASE_RATE = 10;
    private static final double TRUNK_GROWTH_RATE = 0.5; // Centimeters per year

    /**
     * Default constructor for ConiferousTree.
     */
    public ConiferousTree() {
        super();
    }

    /**
     * Makes the coniferous tree grow. The growth rate is defined by GROWTH_RATE.
     */
    @Override
    public void grow() {
        // Increase the height of the trunk
        double currentHeight = trunk.getHeight();
        trunk.setHeight(currentHeight + GROWTH_RATE);

        double currentDiameter = trunk.getDiameter();
        trunk.setDiameter(currentDiameter + TRUNK_GROWTH_RATE);

        // Add new branches and leaves based on some random logic (this can be adjusted as needed)
        Random random = new Random();
        int newBranches = random.nextInt(5); // Add up to 4 new branches
        for (int i = 0; i < newBranches; i++) {
            Branch branch = new Branch(random.nextDouble() * 10); // New branches with random lengths up to 10 cm
            branches.add(branch);

            int newLeavesForBranch = random.nextInt(7); // Add up to 6 new leaves (needles) for this branch
            for (int j = 0; j < newLeavesForBranch; j++) {
                branch.addLeaf(new Leaf("needle", "green", 2.5, "parallel", "smooth", "alternate"));
            }
        }
    }

    /**
     * Handles the change of season for the coniferous tree. In winter, the tree loses some health due to harsh
     * conditions.
     */
    @Override
    public void changeSeason(Season season) {
        if (season == Season.WINTER) {
            decreaseHealth(HEALTH_DECREASE_RATE);
        }
    }

}
