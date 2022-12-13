import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws IOException {

        //Problem Rules:
        //Find all the trees visible from the outside of the grid
        //The number indicates the height of the tree
        //A tree won't be seen if it is smaller than a tree in front of it

        File inputFile = new File("Problem8/src/input.txt");
        Scanner inputScanner = new Scanner(inputFile);

        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();


        while (inputScanner.hasNextLine()) {
            String data = inputScanner.nextLine();
            String[] split = data.split("");
            grid.add(convert(split));
        }

        System.out.println(grid);
        System.out.println(countVisibleTrees(grid));
        //System.out.println(checkIfVisible(1, 2, grid, 0, 3, 3));
    }

    private static ArrayList<Integer> convert(String[] split) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            result.add(Integer.parseInt(split[i]));
        }
        return result;
    }

    private static int countVisibleTrees(ArrayList<ArrayList<Integer>> grid) {

        int count = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                if (checkIfVisible(i, j, grid, 0, i, j)) {
                    count++;
                    System.out.println(grid.get(i).get(j));
                    System.out.println(i + " " + j + "\n");
                }
            }
        }

        return count;
    }

    private static boolean checkIfVisible(int i, int j, ArrayList<ArrayList<Integer>> grid, int direction, int iOriginal, int jOriginal) {

        switch (direction) {
            //up
            case 1:
                if (i - 1 < 0 && grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j)) {
                    return true;
                } else if (grid.get(iOriginal).get(jOriginal) < grid.get(i - 1).get(j)) {
                    return checkIfVisible(i - 1, j, grid, 1, iOriginal, jOriginal);
                } else {
                    return false;
                }
            //left
            case 2:
                if (j - 1 < 0 && grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j)) {
                    return true;
                } else if (grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j - 1)) {
                    return checkIfVisible(i, j - 1, grid, 1, iOriginal, jOriginal);
                } else {
                    return false;
                }
            //down
            case 3:
                if (i + 1 > grid.size() && grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j)) {
                    return true;
                } else if (grid.get(iOriginal).get(jOriginal) < grid.get(i + 1).get(j)) {
                    return checkIfVisible(i + 1, j, grid, 1, iOriginal, jOriginal);
                } else {
                    return false;
                }
            //right
            case 4:
                if (j + 1 > grid.size() && grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j)) {
                    return true;
                } else if (grid.get(iOriginal).get(jOriginal) < grid.get(i).get(j + 1)) {
                    return checkIfVisible(i, j + 1, grid, 1, iOriginal, jOriginal);
                } else {
                    return false;
                }
        }
        if (i <= 0 || checkIfVisible(i, j, grid, 1, iOriginal, jOriginal)) {
            return true;
        }
        if (j <= 0 || checkIfVisible(i, j, grid, 2, iOriginal, jOriginal)) {
            return true;
        }
        if (i >= grid.size() - 1 && checkIfVisible(i, j, grid, 3, iOriginal, jOriginal)) {
            return true;
        }
        if (j >= grid.size() - 1 && checkIfVisible(i, j, grid, 4, iOriginal, jOriginal)) {
            return true;
        }
        return false;
    }
}