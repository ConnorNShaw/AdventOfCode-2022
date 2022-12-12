import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    private static final ArrayList<Integer> sums = new ArrayList<>();

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
        int total = 0;
        for (Integer i: sums) {
            if (i < 100000) {
                total += i;
            }
        }
        System.out.println(total);
    }
}