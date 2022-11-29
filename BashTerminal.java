/*
 * Getty Testa
 * 115217416
 * Recitation 01
 */

import java.util.Scanner;

public class BashTerminal {
    /**
     * Runs a program which takes user input and builds a DirectoryTree using
     * specific commands.
     * 
     * @param args the command line arguments for the file
     */
    public static void main(String[] args) {
        boolean isTerminated = false;
        Scanner inputScanner = new Scanner(System.in);
        DirectoryTree fileTree = new DirectoryTree();
        System.out.println("Starting bash terminal.");

        while (!isTerminated) {
            System.out.print("[gtesta@Shodan]: $ ");
            String userInput = inputScanner.nextLine();
            String[] splitInput = userInput.split(" ");

            if (userInput.equals("pwd")) {
                System.out.println(fileTree.presentWorkingDirectory());
            } else if (userInput.equals("ls -R")) {
                System.out.println();
                fileTree.printDirectoryTree(0, fileTree.getCursor());
                System.out.println();
            } else if (userInput.equals("ls")) {
                System.out.println(fileTree.listDirectory());
            } else if (splitInput[0].equals("cd")) {
                if (splitInput.length == 2) {
                    if (splitInput[1].equals("/")) {
                        fileTree.resetCursor();
                    } else {
                        try {
                            fileTree.changeDirectory(splitInput[1]);
                        } catch (NotADirectoryException nade) {
                            System.out.println("ERROR: Cannot change directory into a file.");
                        } catch (NullPointerException npe) {
                            System.out.printf("ERROR: No such directory named '%s'.\n", splitInput[1]);
                        }
                    }
                }
            } else if (splitInput[0].equals("mkdir")) {
                if (splitInput.length == 2) {
                    try {
                        fileTree.makeDirectory(splitInput[1]);
                    } catch (IllegalArgumentException iae) {
                        System.out.println("ERROR: Invalid name.");
                    } catch (FullDirectoryException fde) {
                        System.out.println("ERROR: Present directory is full.");
                    } catch (NotADirectoryException nade) {
                        System.out.println("ERROR: Cannot add directory to a file.");
                    }
                } else {
                    System.out.println("ERROR: Invalid name.");
                }
            } else if (splitInput[0].equals("touch")) {
                if (splitInput.length == 2) {
                    try {
                        fileTree.makeFile(splitInput[1]);
                    } catch (IllegalArgumentException iae) {
                        System.out.println("ERROR: Invalid name.");
                    } catch (FullDirectoryException fde) {
                        System.out.println("ERROR: Present directory is full.");
                    } catch (NotADirectoryException nade) {
                        System.out.println("ERROR: Cannot add directory to a file.");
                    }
                } else {
                    System.out.println("ERROR: Invalid name.");
                }
            } else if (userInput.equals("exit")) {
                isTerminated = true;
            } else {
                System.out.println("No such command.");
            }

        }
        inputScanner.close();
        System.out.println("Program terminating normally");
    }
}
