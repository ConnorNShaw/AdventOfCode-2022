import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Part1 {
    private static final HashMap<Character, Integer> priorities = new HashMap<>();
    private static int result = 0;
    public static void main(String[] args) throws FileNotFoundException {

        //Problem Rules
        //Each group has an even number of items
        //Each group has two compartments
        //Find the duplicates in each compartment and add their priority

        File inputFile = new File("Problem3/src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);                    //Set up scanner

        fillPriorities();

        while(inputScanner.hasNextLine()) {
            String group = inputScanner.nextLine();
            ArrayList<Character> compA = characterList(group.substring(0, group.length() / 2));
            ArrayList<Character> compB = characterList(group.substring(group.length() / 2));
            findDuplicates(compA, compB);
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
    private static void findDuplicates(ArrayList<Character> a, ArrayList<Character> b) {
        for (Character character : a) {
            if (b.contains(character)) {
                result += priorities.get(character);
                return;
            }
        }
    }
}