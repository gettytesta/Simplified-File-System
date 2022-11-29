/*
 * Getty Testa
 * 115217416
 * Recitation 01
 */

public class DirectoryNode {
    private String name;
    private DirectoryNode left;
    private DirectoryNode middle;
    private DirectoryNode right;
    private boolean isFile;
    private String path;

    /**
     * Empty constructor for DirectoryNode class
     */
    public DirectoryNode() {
        this("", false, "");
    }

    /**
     * Overloaded constructor for DirectoryNode class
     * 
     * @param name   the name of the node
     * @param isFile true if the node is a file, false otherwise
     * @param path   the path of the node
     */
    public DirectoryNode(String name, boolean isFile, String path) {
        this.name = name;
        left = null;
        middle = null;
        right = null;
        this.isFile = isFile;
        this.path = path;
    }

    /**
     * Retrieves the name of the node.
     * 
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the node based on the user's input.
     * 
     * @param name the new name for the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the left node in the ternary tree.
     * 
     * @return the left node in the tree
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * Retrieves the middle node in the ternary tree.
     * 
     * @return the middle node in the tree
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     * Retrieves the right node in the ternary tree.
     * 
     * @return the right node in the tree
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Checks whether or not a node is a file.
     * 
     * @return true if the node is a file, false otherwise
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     * Retrieves the path of the node in the tree.
     * 
     * @return the path of the node
     */
    public String getPath() {
        return path;
    }

    /**
     * Adds a new child to any of the open child positions of this node.
     * Preconditions: this node is not a file and there is at least one empty
     * position in the children of this node.
     * Postconditions: the new child has been added or an exception has been
     * thrown if there is no room.
     * 
     * @param newChild the new child to be added to the tree
     * @throws FullDirectoryException when all three children of the node are
     *                                occupied
     * @throws NotADirectoryException when the current node is a file
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException {
        if (this.isFile()) {
            throw new NotADirectoryException();
        }
        if (left == null) {
            left = newChild;
        } else if (middle == null) {
            middle = newChild;
        } else if (right == null) {
            right = newChild;
        } else {
            throw new FullDirectoryException();
        }
    }
}