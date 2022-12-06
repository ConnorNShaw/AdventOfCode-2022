import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Part1 {

    public static ArrayList<Stack<Character>> stacks = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //Move crates from one stack to the other
        //The stacks start as specified at the top of the input file
        //Move crates based on the input below the starting configuration
        //The crates move one at a time

        File inputFile = new File("Problem5/src/input.txt");
        Scanner inputScanner = new Scanner(inputFile);

        ArrayList<String> matrix = new ArrayList<>();

        //Grab the matrix that represents the stacks of crates
        while (inputScanner.hasNextLine()) {
            String currentLine = inputScanner.nextLine();
            if (currentLine.equals("")) {
                break;
            } else if (currentLine.contains("[")) {
                matrix.add(currentLine);
            }
        }
        createStacks(matrix);
        //Grab the input for the crate moves
        while (inputScanner.hasNextLine()) {
            String currentMove = inputScanner.nextLine();
            String[] split = currentMove.split(" ");
            int[] moves = {Integer.parseInt(split[1]),
                           Integer.parseInt(split[3]),
                           Integer.parseInt(split[5])};
            doMove(moves);
        }
        for (Stack<Character> s: stacks) {
            System.out.print(s.peek());
        }
    }

    private static void createStacks(ArrayList<String> matrix) {
        Collections.reverse(matrix);
        for (int i = 1; i < matrix.get(0).length(); i+=4) {
            Stack<Character> charStack = new Stack<>();
            stacks.add(charStack);
        }
        int tracker = 0;
        for (String s : matrix) {
            for (int j = 1; j < s.length(); j += 4, tracker++) {
                if (s.charAt(j) != ' ') {
                    stacks.get(tracker).add(s.charAt(j));
                }
            }
            tracker = 0;
        }
    }
    public static void doMove(int[] moves) {
        int amount = moves[0];
        int from = moves[1] - 1;
        int to = moves[2] - 1;
        for (int i = 0; i < amount; i++) {
            stacks.get(to).push(stacks.get(from).pop());
        }
    }
}