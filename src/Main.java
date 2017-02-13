import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void writeTextToFile(String fileName, String Input) {


        Path filePath = Paths.get(fileName); //step number 1. Start with a path

        // if we want to put the file not in the main.... "../products.txt"

        File productsFile = filePath.toFile(); //step number 2. Create a file path

        //when we have a object that is part of another object its called composition

        // to write things to a file we use PrintWriter
        //we want to open a fileStream to this file so we can add some things

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(productsFile, true)); //basically means if it cant find
            //the file that we created. The computer will make one for us.

            //PrintWrier out2 = new PrintWriter(productsFile); ....this does the same thing as above but does not append.

            out.print(Input);

            out.close(); // this is how we close

        } catch (FileNotFoundException ex) {

        }

    }

    public static StringBuilder readTextFromFile(String fileName) {


        Path filePath = Paths.get(fileName); //step number 1. Start with a path

        File productsFile = filePath.toFile();

        StringBuilder result = new StringBuilder();

        try {
            FileReader r = new FileReader(productsFile);
            BufferedReader reader = new BufferedReader(r);

            String line = reader.readLine();

            while (line != null) { //this will print everything to the text file
                // System.out.println(line);
                result.append(line + "\n");
                line = reader.readLine();
            }

            reader.close();
            return result;


        } catch (FileNotFoundException ex) {
            return null;


        } catch (IOException ex) {
            return null;
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        writeTextToFile("Countries.txt", "India\nThe United States\nChina\nRwanda\n");


        System.out.println("Welcome to the Countries Maintenance Application");
        System.out.println("1 - See the list of countries");
        System.out.println("2 - Add a country");
        System.out.println("3 - Exit");
        System.out.println();

        boolean anotherEntry = true;
        while (anotherEntry) {

            System.out.print("Enter menu number: ");

            int input = scan.nextInt(); //FIXME put in validation

            if (input == 1) {
                System.out.println(readTextFromFile("Countries.txt"));

            } else if (input == 2) {
                System.out.println("Add a country: ");
                String addCountry = scan.next(); //FIXME validation


                writeTextToFile("Countries.txt", addCountry);

            } else if (input == 3) {

                anotherEntry = false;
            }
        }
        System.out.println("Buh-bye!");


    }
}
