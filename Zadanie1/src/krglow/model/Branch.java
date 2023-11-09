package krglow.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a branch of a tree, which can have sub-branches and leaves.
 */
public class Branch {

    /** The length of the branch in meters. */
    double length;

    /** List containing sub-branches originating from this branch. */
    List<Branch> subBranches;

    /** List containing leaves that are part of this branch. */
    List<Leaf> leaves;

    public Branch(double length) {
        this.length = length;
        this.subBranches = new ArrayList<>();
        this.leaves = new ArrayList<>();
    }

    public void addSubBranch(Branch branch) {
        subBranches.add(branch);
    }

    public void addLeaf(Leaf leaf) {
        leaves.add(leaf);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Branch> getSubBranches() {
        return subBranches;
    }

    public void setSubBranches(List<Branch> subBranches) {
        this.subBranches = subBranches;
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaf> leaves) {
        this.leaves = leaves;
    }

}
