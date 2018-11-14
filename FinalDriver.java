import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FinalDriver {

    public static void main(String args[]) {


    if(isDouble(args[0]) || isDouble(args[1])) {
      System.out.println("Include Proper Perameters");                 // When row and col are double, and not int
    }

    if(args[2] == 0) {System.out.println("Please specify the file!");} // When there is no file included
    try{
      File f = new File(args[2]);
      Scanner in = new Scanner(f);
    } catch(IllegalArgumentException e) {
      System.out.println("File: " + args[2] + " not found!");         // When the file is specified but does not exists
    }

    if(args.length = 4) {
      Randseed = args[3];                                             // Add RandomSeed if present
    }

    if(args.length = 5 && args[4] == "key") {
      // print the answer Key when this happens!
    }


    }
}
