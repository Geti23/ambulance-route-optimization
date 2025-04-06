package org.algorithm.model;

public class Hospital extends NodeContent{
    private static int ID = 0;

    private final int id;
    private final int maxSeverity;

    /**Builds a new {@link Hospital} at the provided node using the id passed as parameter.
     * @param node
     * @param id
     * @param maxSeverity
     */
    Hospital(int node, int id, int maxSeverity) {
        super(node);
        this.id = id;
        this.maxSeverity = maxSeverity;

        ID = Math.max(ID, id + 1);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("H_%d @ N%d (%d)", id, getNode(), maxSeverity);
    }
}
