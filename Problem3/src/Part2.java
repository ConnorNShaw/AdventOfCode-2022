import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {
    private static final HashMap<Character, Integer> priorities = new HashMap<>();
    private static int result = 0;
    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //Each group consists of three rows
        //Find the common item in each of the three rows
        //Then add that items priority to result

        File inputFile = new File("Problem3/src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);                    //Set up scanner

        fillPriorities();

        while(inputScanner.hasNextLine()) {
            String line1 = inputScanner.nextLine();
            String line2 = inputScanner.nextLine();
            String line3 = inputScanner.nextLine();
            ArrayList<Character> elf1 = characterList(line1);
            ArrayList<Character> elf2 = characterList(line2);
            ArrayList<Character> elf3 = characterList(line3);
            findCommonItem(elf1, elf2, elf3);
        }
        System.out.println(result);
    }
    private static void fillPriorities() {
        int i = 1;
        for(char lower = 'a'; lower <= 'z'; lower++, i++) {
            priorities.put(lower, i);
        }
        for(char upper = 'A'; upper <= 'Z'; upper++, i++) {
            priorities.put(upper, i);
        }
    }
    private static ArrayList<Character> characterList(String str) {
        ArrayList<Character> charList = new ArrayList<>();
        for (char ch: str.toCharArray()) {
            charList.add(ch);
        }
        return charList;
    }
    private static void findCommonItem(ArrayList<Character> a, ArrayList<Character> b, ArrayList<Character> c) {
        for (char one: a) {
            for (char two: b) {
                if (b.contains(one) && c.contains(two) && one == two) {
                    result += priorities.get(one);
                    return;
                }
            }
        }
    }
}