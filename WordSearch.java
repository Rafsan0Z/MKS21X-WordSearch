import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private char[] alphabet;
    private int seed;
    private Random randgen;
    private File file;
    private Scanner in;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */

// This method checks the file that the user inputs
/*    private boolean VarifyFile(String fileName) {
      try{
        file = new File(fileName);
        in = new Scanner(file);
      } catch(FileNotFoundException e) {
        return false;
      }
      return true;
    } */

// This method inputs random letters where are blank spaces
    private void RandomLetters() {
      for(int i = 0; i < data.length; i++) {
        for(int j = 0; j < data[0].length; j++) {
          if(data[i][j] == '_') {
            int place = ListIndex(27);
            data[i][j] = alphabet[place];
          }
        }
      }
    }

/*    private void fillDatabase(String fileName) {
      while(in.hasNext()) {
        String word = in.next();
        wordsToAdd.add(word);
      }
    } */

    public WordSearch(int rows, int cols, String fileName, int Randseed, boolean answer){
      data = new char[rows][cols];
      clear();
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      if(Randseed == 0) {
        Random limit = new Random();
        seed = limit.nextInt();
      }
      else {
        seed = Randseed;
      }
      randgen = new Random(seed);
      try{
        file = new File(fileName);
        in = new Scanner(file);
      } catch(FileNotFoundException e) {
        System.out.println("File does not exist!");
        System.exit(1);
      }
      while(in.hasNext()) {
        String word = in.next();
        wordsToAdd.add(word);
      }
      addAllWords();
//      if(!answer) {
//        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//        RandomLetters();
//      }
//      else {
//
//      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    public void clear(){
      for(int i = 0; i < data.length; i++) {
        for(int j = 0; j < data[i].length; j++) {
          data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
    String result = "";
  for (int i = 0; i < data.length ; i++) {
    for(int j = 0; j < data[i].length ; j++) {
      if ( j == 0 ) {
        result += "|";
      }
      if ( j == data[i].length - 1) {
        result += data[i][j];
        result += "| \n";
      }
      else{
        result += data[i][j] + " ";
      }
    }
  }
    return result;
    }

   public boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
     int len = word.length();
     if(rowIncrement == 0 && colIncrement == 0) {return false;}
     if(len == 0) {return false;}
     if((rowIncrement == 1 && data.length < row + len) || (colIncrement == 1 && data[row].length < len + col)) {return false;}
     if((rowIncrement == -1 && row + 1 <len) || (colIncrement == -1 && col + 1 < len)) {return false;}
     int rowendpoint = (len-1)*rowIncrement;
     int colendpoint = (len-1)*colIncrement;
     int currentrow = row;
     int currentcol = col;
     if(rowendpoint > data.length || colendpoint > data[0].length) {return false;}
     for(int i = 0 ; i < len && currentcol < data.length && currentrow < data[0].length; i++) {
       char letter = word.charAt(i);
       if(data[currentrow][currentcol] != letter && data[currentrow][currentcol] != '_') {return false;}
       currentrow++;
       currentcol++;
     }
     currentcol = col;
     currentrow = row;
     for(int j = 0; j < len && currentcol < data.length && currentrow < data[0].length; j++) {
       char letter = word.charAt(j);
       data[currentrow][currentcol] = letter;
       currentcol++;
       currentrow++;
     }
     wordsToAdd.remove(word);
     wordsAdded.add(word);
     return true;
   }

// This function randomly choses a word from the words not yet added!
   public int ListIndex(int length) {
     return randgen.nextInt(length);
   }

   private int changeStarts(int length) { // A method that resets the row and col position!
     int start = Math.abs(randgen.nextInt()) % length;
     return start;
   }
   private int changeSteps() {
     int increment = randgen.nextInt() % 2;
     return increment;
   }
   public void addAllWords() {
     int count = 1000;
     int rowIncrement = 1;
     int colIncrement = 1;
     int rowstart = 0;
     int colstart = 0;
    while(wordsToAdd.size() > 0) {
      int Length = wordsToAdd.size();         //
      int index = randgen.nextInt(Length);         // This should be inside of a loop
      String word = wordsToAdd.get(index);
      for(int i = 0; i < count; i++) {
      if(addWord(word,rowstart,colstart,rowIncrement,colIncrement)) {
        wordsAdded.add(word);
        i = count;
      }
      else {
        rowIncrement = changeSteps();
        colIncrement = changeSteps();
        rowstart = changeStarts(data.length);
        colstart = changeStarts(data[0].length);
      }
   }
   wordsToAdd.remove(index);
 }
}
// I use tutorialsPoint to check my code before testing it in any Driver!

public static void main(String args[]) {
  int row = Integer.parseInt(args[0]);
  int col = Integer.parseInt(args[1]);
  String fileName = args[2];
  int RandSeed = 0;
  boolean answer = false;

  if(args.length == 4) {
    RandSeed = Integer.parseInt(args[3]);
  }

  if(args.length == 5 && args[4] != "key") {
    System.out.println("Invalid input for answer!");
    System.exit(1);
  }

  if(args.length == 5 && args[4] == "key") {
    answer = true;
  }

  WordSearch Block = new WordSearch(row,col,fileName,RandSeed,answer);
  System.out.println(Block);
}
}
