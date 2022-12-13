import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) throws IOException {

        //Problem Rules:
        //Find all the trees visible from a given tree
        //The number indicates the height of the tree
        //A tree won't be seen if it is smaller than a tree in front of it
        //Multiply the distances seen in every direction to get scenic score
        //What is the highest possible scenic score?

        File inputFile = new File("Problem8/src/input.txt");
        Scanner inputScanner = new Scanner(inputFile);

        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();

        while (inputScanner.hasNextLine()) {
            String data = inputScanner.nextLine();
            String[] split = data.split("");
            grid.add(convert(split));
        }

        System.out.println(countVisibleTrees(grid, scores));
    }

    private static ArrayList<Integer> convert(String[] split) {
        ArrayList<Integer> result = new ArrayList<>();
        for (String s : split) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

    private static int countVisibleTrees(ArrayList<ArrayList<Integer>> grid, ArrayList<Integer> scores) {

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                scores.add(calculateScores(i, j, grid));
            }
        }

        Collections.sort(scores);
        return scores.get(scores.size() - 1);
    }

    private static int calculateScores(int i, int j, ArrayList<ArrayList<Integer>> grid) {

        int up = 0, down = 0, left = 0, right = 0, edge = 1;

        ArrayList<Integer> horizontalSlice = grid.get(i);
        ArrayList<Integer> verticalSlice = new ArrayList<>();
        for (ArrayList<Integer> integers : grid) {
            verticalSlice.add(integers.get(j));
        }

        int currentTree = grid.get(i).get(j), b, t;
        for (b = j - 1; b >= 0; b--) {
            if (currentTree > horizontalSlice.get(b)) {
                left++;
            }
            if (currentTree <= horizontalSlice.get(b)) {
                left++;
                break;
            }
        }
        for (b = i - 1; b >= 0; b--) {
            if (currentTree > verticalSlice.get(b)) {
                up++;
            }
            if (currentTree <= verticalSlice.get(b)) {
                up++;
                break;
            }
        }
        for (t = j + 1; t < grid.size(); t++) {
            if (currentTree > horizontalSlice.get(t)) {
                right++;
            }
            if (currentTree <= horizontalSlice.get(t)) {
                right++;
                break;
            }
        }
        for (t = i + 1; t < grid.size(); t++) {
            if (currentTree > verticalSlice.get(t)) {
                down++;
            }
            if (currentTree <= verticalSlice.get(t)) {
                down++;
                break;
            }
        }

        if ((i <= 0 || j <= 0 || i >= grid.size() - 1 || j >= grid.size() - 1)) {
            edge = 0;
        }

        return up * down * left * right * edge;
    }
}