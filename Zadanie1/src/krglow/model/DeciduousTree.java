package krglow.model;

import java.util.Random;


/**
 * Represents a deciduous tree, a type of tree that sheds its leaves annually. Examples include oaks, maples, and elms.
 */
public class DeciduousTree extends Tree {

    private static final int GROWTH_RATE = 30; // Centimeters per year
    private static final int HEALTH_DECREASE_RATE = 5;
    private static final double TRUNK_GROWTH_RATE = 0.5; // Centimeters per year

    /**
     * Default constructor for DeciduousTree.
     */
    public DeciduousTree() {
        super();
    }

    /**
     * Makes the deciduous tree grow. The growth rate is defined by GROWTH_RATE.
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
        int newBranches = random.nextInt(6); // Add up to 5 new branches
        for (int i = 0; i < newBranches; i++) {
            Branch branch = new Branch(random.nextDouble() * 15); // New branches with random lengths up to 15 cm
            branches.add(branch);

            int newLeavesForBranch = random.nextInt(10); // Add up to 9 new leaves for this branch
            for (int j = 0; j < newLeavesForBranch; j++) {
                branch.addLeaf(new Leaf("oval", "green", 5.0, "pinnate", "serrated", "opposite"));
            }
        }
    }

    /**
     * Handles the change of season for the deciduous tree. In fall, the tree sheds its leaves and in winter, the tree
     * loses some health.
     */
    @Override
    public void changeSeason(Season season) {
        if (season == Season.FALL) {
            for (Branch branch : branches) {
                branch.getLeaves().clear(); // Shed all leaves from each branch
            }
        } else if (season == Season.WINTER) {
            decreaseHealth(HEALTH_DECREASE_RATE);
        }
    }

    /**
     * Adds a leaf to a specific branch.
     *
     * @param branch The target branch to which the leaf will be added.
     * @param leaf The leaf to be added.
     */
    public void addLeafToBranch(Branch branch, Leaf leaf) {
        if (branch != null) {
            branch.addLeaf(leaf);
        } else {
            throw new IllegalArgumentException("Branch cannot be null.");
        }
    }

    /**
     * Removes a leaf from a specific branch.
     *
     * @param branch The branch from which the leaf will be removed.
     * @param leaf The leaf to be removed.
     */
    public void removeLeafFromBranch(Branch branch, Leaf leaf) {
        if (branch != null && branch.getLeaves().contains(leaf)) {
            branch.getLeaves().remove(leaf);
        } else {
            throw new IllegalArgumentException("Branch is null or does not contain the specified leaf.");
        }
    }

}
