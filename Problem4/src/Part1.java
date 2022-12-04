import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    private static int numOfContainedPairs = 0;

    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //Each line has two pairs with format 1-2,3-4
        //In how many of those pairs does one fully contain the other?

        File inputFile = new File("Problem4/src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);                    //Set up scanner

        while (inputScanner.hasNextLine()) {
            String data = inputScanner.nextLine();
            String[] stringPairs = data.split(",");
            String[] sPairOne = stringPairs[0].split("-");
            String[] sPairTwo = stringPairs[1].split("-");
            createPairs(sPairOne, sPairTwo);
        }
        System.out.println(numOfContainedPairs);
    }
    private static void createPairs(String[] sPairOne, String[] sPairTwo) {
        Pair pOne = new Pair(Integer.parseInt(sPairOne[0]), Integer.parseInt(sPairOne[1]));
        Pair pTwo = new Pair(Integer.parseInt(sPairTwo[0]), Integer.parseInt(sPairTwo[1]));
        checkRange(pOne, pTwo);
    }
    public static void checkRange(Pair pOne, Pair pTwo) {
        if (pOne.getX() >= pTwo.getX() && pOne.getY() <= pTwo.getY() || pTwo.getX() >= pOne.getX() && pTwo.getY() <= pOne.getY()) {
            numOfContainedPairs += 1;
        }
    }
    private static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}