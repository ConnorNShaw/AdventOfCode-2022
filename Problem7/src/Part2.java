import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2 {

    private static final ArrayList<Integer> sums = new ArrayList<>();
    private static final ArrayList<Integer> deletionCandidates = new ArrayList<>();
    private static final int totalSpace = 70000000;
    private static final int updateSpace = 30000000;

    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //Based off the commands from the input file, find each directory that has
        //total size of at most 100000, then calculate their sums

        File inputFile = new File("Problem7/src/input.txt");
        Scanner inputScanner = new Scanner(inputFile);
        Directory rootDirectory = null, currentDirectory = null;

        while (inputScanner.hasNextLine()) {
            String data = inputScanner.nextLine();
            String[] dataArr = data.split(" ");
            if (data.contains("$ cd")) {
                String dirLetter = dataArr[2];
                switch (dirLetter) {
                    case "/" -> {
                        rootDirectory = new Directory(null, "/");
                        currentDirectory = rootDirectory;
                    }
                    case ".." -> {
                        sums.add(currentDirectory.getSize());
                        currentDirectory = currentDirectory.getOuterDirectory();
                    }
                    default -> currentDirectory = currentDirectory.findDirectory(dirLetter);
                }
            }
            if (data.contains("dir ")) {
                String dirLetter = dataArr[1];
                currentDirectory.addItem(new Directory(currentDirectory, dirLetter));
            } else if (data.charAt(0) != '$') {
                currentDirectory.addItem(new FileItem(Integer.parseInt(dataArr[0])));
            }
        }
        int spaceTaken = rootDirectory.getSize();
        int spaceAvailable = totalSpace - spaceTaken;
        int spaceNeeded = updateSpace - spaceAvailable;
        int sizeToDelete = 0;
        for (Integer i: sums) {
            if(i >= spaceNeeded) {
                deletionCandidates.add(i);
            }
        }
        Collections.sort(deletionCandidates);
        sizeToDelete = deletionCandidates.get(0);
        System.out.println(spaceNeeded);
        System.out.println(sizeToDelete);
    }
}