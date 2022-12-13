import java.io.*;
import java.util.ArrayList;
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

        System.out.println(countVisibleTrees(grid));
    }

    private static ArrayList<Integer> convert(String[] split) {
        ArrayList<Integer> result = new ArrayList<>();
        for (String s : split) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

    private static int countVisibleTrees(ArrayList<ArrayList<Integer>> grid) {

        int count = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                if (checkIfVisible(i, j, grid)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean checkIfVisible(int i, int j, ArrayList<ArrayList<Integer>> grid) {

        if ((i <= 0 || j <= 0 || i >= grid.size() - 1 || j >= grid.size() - 1)) {
            return true;
        }

        boolean resultHB = true, resultHT = true, resultVB = true,  resultVT = true;

        ArrayList<Integer> horizontalSlice = grid.get(i);
        ArrayList<Integer> verticalSlice = new ArrayList<>();
        for (ArrayList<Integer> integers : grid) {
            verticalSlice.add(integers.get(j));
        }

        int currentTree = grid.get(i).get(j), b, t;
        for (b = 0; b < j; b++) {
            if (currentTree <= horizontalSlice.get(b)) {
                resultHB = false;
            }
        }
        for (b = 0; b < i; b++) {
            if (currentTree <= verticalSlice.get(b)) {
                resultVB = false;
            }
        }
        for (t = grid.size() - 1; j < t; t--) {
            if (currentTree <= horizontalSlice.get(t)) {
                resultHT = false;
            }
        }
        for (t = grid.size() - 1; i < t; t--) {
            if (currentTree <= verticalSlice.get(t)) {
                resultVT = false;
            }
        }

        return resultHB || resultVB || resultHT || resultVT;
    }
}