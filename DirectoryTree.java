/*
 * Getty Testa
 * 115217416
 * Recitation 01
 */

public class DirectoryTree {
    private DirectoryNode root;
    private DirectoryNode cursor;

    /**
     * Empty constructor for the DirectoryTree class.
     */
    public DirectoryTree() {
        root = new DirectoryNode("root", false, "root");
        cursor = root;
    }

    /**
     * Retreives the root of the tree.
     * 
     * @return the root of the tree
     */
    public DirectoryNode getRoot() {
        return root;
    }

    /**
     * Retrieves the cursor node in the tree.
     * 
     * @return the cursor of the tree
     */
    public DirectoryNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to the root of the tree.
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * Moves the cursor to the directory indicated by name.
     * Preconditions: 'name' references a valid directory.
     * Postconditions: The cursor now references the directory indicated
     * by name. Throws an exception if the name wasn't a directory.
     * 
     * @param name the name of the directory to traverse to
     * @throws NotADirectoryException if name doesn't match any directory name
     * @throws NullPointerException   if the cursor or every child is null
     */
    public void changeDirectory(String name) throws NotADirectoryException, NullPointerException {
        if (cursor == null) {
            throw new NullPointerException();
        }
        if (cursor.getLeft() != null) {
            if (cursor.getLeft().getName().equals(name)) {
                if (cursor.getLeft().isFile()) {
                    throw new NotADirectoryException();
                }
                cursor = cursor.getLeft();
                return;
            }
        }
        if (cursor.getMiddle() != null) {
            if (cursor.getMiddle().getName().equals(name)) {
                if (cursor.getMiddle().isFile()) {
                    throw new NotADirectoryException();
                }
                cursor = cursor.getMiddle();
                return;
            }
        }
        if (cursor.getRight() != null) {
            if (cursor.getRight().getName().equals(name)) {
                if (cursor.getRight().isFile()) {
                    throw new NotADirectoryException();
                }
                cursor = cursor.getRight();
                return;
            }
        }
        throw new NullPointerException();
    }

    /**
     * Returns a String containing the path of the cursor in the tree.
     * Postcondition: The cursor remains at the same node
     * 
     * @return a String containing the path of the cursor
     */
    public String presentWorkingDirectory() {
        return cursor.getPath();
    }

    /**
     * Returns a String containing a list of names of all the child nodes of the
     * cursor.
     * Postconditions: the cursor remains at the same node
     * 
     * @return a String containing a list of the names of the child nodes
     */
    public String listDirectory() {
        String listDir = "";
        if (cursor.getLeft() != null) {
            listDir += cursor.getLeft().getName();
        }
        if (cursor.getMiddle() != null) {
            listDir += " " + cursor.getMiddle().getName();
        }
        if (cursor.getRight() != null) {
            listDir += " " + cursor.getRight().getName();
        }
        return listDir;
    }

    /**
     * Recursively prints a formatted nested list of names of all the nodes in
     * the directory tree, starting from the cursor.
     * Postcondition: the cursor remains at the same node.
     * 
     * @param level  the level of files in the tree, used for spacing
     * @param cursor the current node to be printed
     */
    public void printDirectoryTree(int level, DirectoryNode cursor) {
        if (cursor == null) {
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        if (!cursor.isFile()) {
            System.out.printf("|- %s\n", cursor.getName());
        } else {
            System.out.printf("- %s\n", cursor.getName());
        }
        printDirectoryTree(level + 1, cursor.getLeft());
        printDirectoryTree(level + 1, cursor.getMiddle());
        printDirectoryTree(level + 1, cursor.getRight());
    }

    /**
     * Creates a directory and add its to the children of the cursor node.
     * Precondition: name doesn't contain any whitespace or forward slashes.
     * Postcoditions: A new node has been added to the children of the cursor
     * or an exception has been thrown.
     * 
     * @param name the name of the directory to add
     * @throws IllegalArgumentException if name isn't a valid argument
     * @throws FullDirectoryException   if the chilren of the cursor are full
     * @throws NotADirectoryException   if the cursor references a file
     */
    public void makeDirectory(String name)
            throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
        if (name == null || name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException();
        }
        cursor.addChild(new DirectoryNode(name, false, cursor.getPath() + "/" + name));
    }

    /**
     * Creates a file and add its to the children of the cursor node.
     * Precondition: name doesn't contain any whitespace or forward slashes.
     * Postcoditions: A new node has been added to the children of the cursor
     * or an exception has been thrown.
     * 
     * @param name the name of the file to add
     * @throws IllegalArgumentException if name isn't a valid argument
     * @throws FullDirectoryException   if the chilren of the cursor are full
     * @throws NotADirectoryException   if the cursor references a file
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
        if (name == null || name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException();
        }
        cursor.addChild(new DirectoryNode(name, true, cursor.getPath() + "/" + name));
    }
}
