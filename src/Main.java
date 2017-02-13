import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void newTextTofile(String fileName, String Input) {


        Path filePath = Paths.get(fileName); //step number 1. Start with a path

        // if we want to put the file not in the main.... "../products.txt"

        File productsFile = filePath.toFile(); //step number 2. Create a file path

        //when we have a object that is part of another object its called composition

        // to write things to a file we use PrintWriter
        //we want to open a fileStream to this file so we can add some things

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(productsFile, false)); //basically means if it cant find
            //the file that we created. The computer will make one for us.

            //PrintWriter out2 = new PrintWriter(productsFile); ....this does the same thing as above but does not append.

            out.print(Input);

            out.close(); // this is how we close

        } catch (FileNotFoundException ex) {

        }

    }


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

            //PrintWriter out2 = new PrintWriter(productsFile); ....this does the same thing as above but does not append.

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

        writeTextToFile("Countries.txt", "India\nThe United States\nChina\nRwanda\n"); //this adds the original items to the list


        System.out.println("Welcome to the Countries Maintenance Application");
        System.out.println("1 - See the list of countries");
        System.out.println("2 - Add a country");
        System.out.println("3 - Remove a country");
        System.out.println("4 - Exit");
        System.out.println();

        boolean anotherEntry = true; //this is the exit loop
        while (anotherEntry) {// while this is true, loop the program

            System.out.print("Enter menu number: ");

            int input = scan.nextInt(); //FIXME put in validation

            if (input == 1) { // if input is equal to 1 Print out the list.
                System.out.println(readTextFromFile("Countries.txt")); //this is how you print on the list of countries (text file)

            } else if (input == 2) { //if input is equal to 2....add a country to the list
                System.out.println("Add a country: ");
                String addCountry = scan.next(); //FIXME validation


                writeTextToFile("Countries.txt", addCountry); //this is the method that adds a country

            } else if (input == 3) { //if input is 3, remove the country

                System.out.println("Remove a country: ");
                String userCountry = scan.next();

                String [] countryList  =(readTextFromFile("Countries.txt").toString().split("\n")); //here I am creating a new array list.....
                //I then make that array list = to the current text file, make it a string, then split it at \n

                String output = "";//this will be the new list after the loop

                for (int i = 0; i < countryList.length; i++) {
                    if (!userCountry.equalsIgnoreCase(countryList[i]) ) { //this loop says if the userCountry is not equal to the country list then print that part of the list
                        output = output + countryList[i] + "\n";
                    }
                }

                newTextTofile("Countries.txt", output); //I then add the new list to Countries.txt....however, the method I used has "append" set to False
                //setting the append to False makes it so that I overwrite the text file rather than add to the bottom


            } else if (input == 4){ // if equal to 4 exit the loop and say Buy Bye
                anotherEntry = false;
            }
        }
        System.out.println("Buh-bye!");


    }
}
