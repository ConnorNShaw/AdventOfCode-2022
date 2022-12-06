import java.io.*;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {

        //Problem Rules
        //Find the first set of four unique characters
        //Return the number of characters processed to reach that point

        File inputFile = new File("Problem6/src/input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));

        int counter = 0;
        boolean packetFound = false;
        LinkedList<Character> arrivingCharacters = new LinkedList<>();
        HashMap<Character, Integer> occurrences = new HashMap<>();
        int character;

        while ((character = reader.read()) != -1 && !packetFound) {
            arrivingCharacters.add((char)character);
            counter++;
            if(arrivingCharacters.size() >= 4) {
                for (int i = counter - 4; i < counter; i++) {
                    if(occurrences.get(arrivingCharacters.get(i)) == null) {
                        occurrences.put(arrivingCharacters.get(i), 1);
                        packetFound = true;
                    } else {
                        packetFound = false;
                        occurrences = new HashMap<>();
                        break;
                    }
                }
            }
        }
        System.out.println(arrivingCharacters);
        System.out.println(counter);
    }
}