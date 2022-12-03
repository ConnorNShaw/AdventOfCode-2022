import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part1 {
    //Add up all groups of numbers in the input file, then find the largest of the added numbers
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("src/input.txt");     //Set up input file
        Scanner inputScanner = new Scanner(inputFile);           //Set up scanner
        ArrayList<Integer> totals = new ArrayList<>();           //List to hold added totals
        int total = 0, maxTotal;
        while(inputScanner.hasNextLine()) {                      //While there is a next line to be read
            String data = inputScanner.nextLine();               //Grab the line
            if(!data.equals("")) {                               //If it is empty, we have reached the end of the current number group
                int dataNum = Integer.parseInt(data);            //If it is not empty, parse int from the string
                total += dataNum;                                //Add it to the total
            } else {
                totals.add(total);                               //Once here we have reached the end of the number group, add to list of totals
                total = 0;                                       //Reset total to zero for the next number group
            }
        }
        Collections.sort(totals);                                //Use sort to easily find the highest number
        maxTotal = totals.get(totals.size() - 1);                //Highest number is at the end
        System.out.println(maxTotal);                            //Print out the result
    }
}