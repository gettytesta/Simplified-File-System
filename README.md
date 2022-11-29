# Simplified-File-System
In this assignment, you will be modeling a file system using a ternary (3-child) tree data structure. In most operating systems, files are stored in directories. These directories may contain other directories along with files. 

In addition to a file system, an OS typically also provides a shell, which is an interface allowing you to run programs and move through the file system. You are likely very familiar with a Graphical User Interface, or GUI, to interact with your host, although a much simpler interface is sometimes all that is required.

In Unix-based operating systems, the Bourne-again shell, or bash, is a terminal-based shell allowing you to run programs by typing commands into a terminal prompt. An example of a bash terminal is shown in the provided below, from a machine running Ubuntu 15.04, a linux distribution. In this assignment, you will implement a very simple bash shell providing a few very basic functions allowing you to interact with your file system.


Required Classes
The following sections describe classes which are required for this assignment. Each section provides a description and the specifications necessary to complete each class. If you feel that additional methods would be useful, you may feel free to add them during your implementation as you see fit. However, all the variables and methods in the following specifications must be included in your project.

1. DirectoryNode
Write a fully-documented class named DirectoryNode which represents a node in the file tree. The DirectoryNode class should contain 3 DirectoryNode references, left, middle, and right. In addition, the class should contain a String member variable name, which indicates the name of the node in the tree.

NOTE: The name member variable should be a full string with no spaces, tabs, or any other whitespace.

Since DirectoryNodes can be either a file or a folder, include a boolean member variable named isFile to differentiate between the two. Note that if this value is set to true, then the node is not a directory, and therefore should NOT contain any children. That is, files are not allowed to have children.

public DirectoryNode() - constructor (you may include a constructor with parameters)

Three DirectoryNode member variables:
left
middle
right

A String member variable:
name

A boolean member variable:
isFile

public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException
Brief:
Adds newChild to any of the open child positions of this node (left, middle, or right).
NOTE: Children should be added to this node in left-to-right order, i.e. left is filled first, middle is filled second, and right is filled last

Preconditions:
This node is not a file.
There is at least one empty position in the children of this node (left, middle, or right).

Postconditions:
newChild has been added as a child of this node. If there is no room for a new node, throw a FullDirectoryException.

Throws:
NotADirectoryException: Thrown if the current node is a file, as files cannot contain DirectoryNode references (i.e. all files are leaves).
FullDirectoryException: Thrown if all child references of this directory are occupied.

2. DirectoryTree
Write a fully-documented class named DirectoryTree which implements a ternary (3-child) tree of DirectoryNodes. The class should contain a reference to the root of the tree, a cursor for the present working directory, and various methods for insertion and deletion.

The DirectoryTree class should provide an implementation for the operations defined for the system (see list below and sample I/O for details). The class should contain methods for moving the cursor through the file system, printing the filepath of the present working directory (cursor location), listing the directories and files in the present working directory, printing the entire file system, and finding a file in the file system. For further information, see the UML diagram and sample I/O below.

Two DirectoryNode member variables:
root
cursor

public DirectoryTree() - constructor (you may include a constructor with parameters)

Brief:
Initializes a DirectoryTree object with a single DirectoryNode named "root".

Preconditions:
None.

Postconditions:
The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
NOTE: Do not confuse the name of the directory with the name of the reference variable. The DirectoryNode member variable of DirectoryTree named root should reference a DirectoryNode who's name is "root", i.e. root.getName().equals("root") is true.

public void resetCursor()
Brief:
Moves the cursor to the root node of the tree.

Preconditions:
None.

Postconditions:
The cursor now references the root node of the tree.

public void changeDirectory(String name) throws NotADirectoryException
Brief:
Moves the cursor to the directory with the name indicated by name.

Preconditions:
'name' references a valid directory ('name' cannot reference a file).

Postconditions:
The cursor now references the directory with the name indicated by name. If a child could not be found with that name, then the user is prompted to enter a different directory name. If the name was not a directory, a NotADirectoryException hs been thrown

Throws:
NotADirectoryException: Thrown if the node with the indicated name is a file, as files cannot be selected by the cursor, or cannot be found.

NOTE: In modern operating systems, the change directory command (cd {path}) allows the user to jump from a current directory to any other directory in the file system given an absolute or relative path. In this assignment, you will only be required to change directory to direct children of the cursor (cd {dir}). However, you may implement the more general command for absolute paths for extra credit.

public String presentWorkingDirectory()
Brief:
Returns a String containing the path of directory names from the root node of the tree to the cursor, with each name separated by a forward slash "/".
e.g. root/home/user/Documents if the cursor is at Documents in the example above.

Preconditions:
None.

Postconditions:
The cursor remains at the same DirectoryNode.

public String listDirectory()
Brief:
Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
e.g. dev home bin if the cursor is at root in the example above.

Preconditions:
None.

Postconditions:
The cursor remains at the same DirectoryNode.

Returns:
A formatted String of DirectoryNode names.

public void printDirectoryTree()
Brief:
Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
See sample I/O for an example.

Preconditions:
None.

Postconditions:
The cursor remains at the same DirectoryNode.

public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException
Brief:
Creates a directory with the indicated name and adds it to the children of the cursor node. Remember that children of a node are added in left-to-right order.

Parameters:
name The name of the directory to add.

Preconditions:
'name' is a legal argument (does not contain spaces " " or forward slashes "/").

Postconditions:
A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.

Throws:
IllegalArgumentException: Thrown if the 'name' argument is invalid.
FullDirectoryException: Thrown if all child references of this directory are occupied.

public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException
Brief:
Creates a file with the indicated name and adds it to the children of the cursor node. Remember that children of a node are added in left-to-right order.

Parameters:
name The name of the file to add.

Preconditions:
'name' is a legal argument (does not contain spaces " " or forward slashes "/").

Postconditions:
A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.

Throws:
IllegalArgumentException: Thrown if the 'name' argument is invalid.
FullDirectoryException: Thrown if all child references of this directory are occupied.

3. BashTerminal
Write a fully-documented class named BashTerminal. The class should contain a single main method which allows a user to interact with a file system implemented by an instance of DirectoryTree using the following commands (note that commands are case-sensitive and will always be lower-case):

Command	Description
pwd	Print the "present working directory" of the cursor node (e.g root/home/user/Documents).
ls	List the names of all the child directories or files of the cursor.
ls -R	Recursive traversal of the directory tree. Prints the entire tree starting from the cursor in
pre-order traversal.
cd {dir}	Moves the cursor to the child directory with the indicated name (Only consider the direct children of the cursor).
cd /	Moves the cursor to the root of the tree.
mkdir {name}	Creates a new directory with the indicated name as a child of the cursor, as long as there is room.
touch {name}	Creates a new file with the indicated name as a child of the cursor, as long as there is room.
exit	Terminates the program.

It should be noted that these commands should all have the same effect as if you were to execute them in a live bash shell on any Unix-based operating system (linux, mac, etc.). However, the command ls -R has been modified to make the assignment easier, and you are not expected to move up directories or change directories for absolute and relative paths with the cd command. You are encouraged to try these commands on a live bash terminal if you have access to get a feel for how they should work.

public static void main(String[] args)
Brief:
Runs a program which takes user input and builds a DirectoryTree using the commands indicated above.
