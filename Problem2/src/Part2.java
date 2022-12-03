import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    static int bTotal = 0;
    private static HashMap winningMoves = new HashMap();
    private static HashMap losingMoves = new HashMap();

    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //A is rock and add 1
        //B is paper and add 2
        //C is scissors and add 3
        //X is need to lose
        //Y is need for draw
        //Z is need to win
        //add 0 if the round was a loss
        //add 3 if the round was a draw
        //add 6 if the round was a win

        File inputFile = new File("src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);           //Set up scanner
        char a, b;

        //Used to check win conditions, where key is always the winner over the value.
        winningMoves.put('A', 'C');
        winningMoves.put('C', 'B');
        winningMoves.put('B', 'A');
        //Used to check lose conditions, where key is always the loser to the value.
        losingMoves.put('C', 'A');
        losingMoves.put('A', 'B');
        losingMoves.put('B', 'C');

        //Getting the input from the file
        while(inputScanner.hasNextLine()) {
            String match = inputScanner.nextLine();
            a = match.charAt(0);
            b = match.charAt(2);
            checkCondition(a, b);
        }
        System.out.println(bTotal); //b's score at the end of all matches.
    }

    private static void checkCondition(char a, char b) {
        //If a draw is needed, pick same shape as player a
        if (b == 'Y') {
            checkMatch(a, a);
        }
        //Win is needed, so we make sure to grab a move that player a will always lose to
        if (b == 'Z') {
            checkMatch(a, (char)losingMoves.get(a));
        }
        //Lose is needed, so we make sure to grab a move that will let player a win
        if (b == 'X') {
            checkMatch(a, (char)winningMoves.get(a));
        }
    }

    private static void checkMatch(char a, char b) {
        //draw
        if (a == b) {
            bTotal += 3;
        }
        //a lost, b won
        else if(b != (char)winningMoves.get(a)) {
            bTotal += 6;
        }
        //add for player b's selected shapes
        switch (b) {
            case 'A':
                bTotal += 1;
                break;
            case 'B':
                bTotal += 2;
                break;
            case 'C':
                bTotal += 3;
                break;
        }
    }
}