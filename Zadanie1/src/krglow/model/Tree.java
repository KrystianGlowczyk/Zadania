package krglow.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a basic tree structure.
 */
public abstract class Tree {

    private static final int MIN_HEALTH = 0;

    /**
     * The main structure supporting the tree, extending from the roots to the top.
     */
    protected Trunk trunk;

    /**
     * List of branches extending from the trunk of the tree.
     */
    protected List<Branch> branches;

    /**
     * Represents the underground part of the tree which absorbs water and nutrients.
     */
    protected RootSystem rootSystem;

    /**
     * Represents the overall health of the tree. Ranges from 0 (unhealthy) to 100 (healthy).
     */
    protected int health;

    /**
     * Tree constructor initializing basic tree components.
     */
    protected Tree() {
        this.trunk = new Trunk();
        this.branches = new ArrayList<>();
        this.rootSystem = new RootSystem();
        this.health = 100;
    }

    /**
     * Makes the tree grow. Implementation depends on the tree type.
     */
    public abstract void grow();

    public abstract void changeSeason(Season season);

    public void decreaseHealth(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.health = Math.max(this.health - value, MIN_HEALTH);
    }

    public Trunk getTrunk() {
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public RootSystem getRootSystem() {
        return rootSystem;
    }

    public void setRootSystem(RootSystem rootSystem) {
        this.rootSystem = rootSystem;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
