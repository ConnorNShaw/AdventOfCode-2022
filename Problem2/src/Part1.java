import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    static int bTotal = 0;
    private static final HashMap<Character, Character> conditions = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //A and X is rock and add 1
        //B and Y is paper and add 2
        //C and Z is scissors and add 3
        //add 0 if the round was a loss
        //add 3 if the round was a draw
        //add 6 if the round was a win

        File inputFile = new File("Problem2/src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);           //Set up scanner
        char a, b;

        //Used to check win conditions, where key is always the winner over the value.
        conditions.put('A', 'C');
        conditions.put('C', 'B');
        conditions.put('B', 'A');

        //Getting the input from the file
        while(inputScanner.hasNextLine()) {
            String match = inputScanner.nextLine();
            a = match.charAt(0);
            b = match.charAt(2);
            align(a, b);
        }
        System.out.println(bTotal); //b's score at the end of all matches.
    }
    private static void align(char a, char b) {
        //Aligning all the letters to make things easier
        switch (b) {
            case 'X' -> checkMatch(a, 'A');
            case 'Y' -> checkMatch(a, 'B');
            case 'Z' -> checkMatch(a, 'C');
        }
    }
    private static void checkMatch(char a, char b) {
        //draw
        if (a == b) {
            bTotal += 3;
        }
        //a lost, b won
        else if(b != conditions.get(a)) {
            bTotal += 6;
        }
        //add for player b's selected shapes
        switch (b) {
            case 'A' -> bTotal += 1;
            case 'B' -> bTotal += 2;
            case 'C' -> bTotal += 3;
        }
    }
}