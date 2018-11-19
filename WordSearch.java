import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private char[] alphabet;
    private int seed;
    private int[]info = new int[2];
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
    private boolean VarifyFile(String fileName) {
      try{
        file = new File(fileName);
        in = new Scanner(file);
      } catch(FileNotFoundException e) {
        return false;
      }
      return true;
    }

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

    private void fillDatabase(String fileName) {
        String word = in.next();
        wordsToAdd.add(word);
    }

    public WordSearch(int rows, int cols, String fileName, int Randseed, boolean answer){
      data = new char[rows][cols];
      clear();
      if(Randseed == 0) {
        Random limit = new Random();
        seed = limit.nextInt();
      }
      else {
        seed = Randseed;
      }
      randgen = new Random(seed);
      info[0] = 0;
      info[1] = 0;
      if(!VarifyFile(fileName)) {
        fillDatabase(fileName);
      }
      addAllWords();
      if(!answer) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        RandomLetters();
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
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
        result += data[i][j];
      }
    }
  }
    return result;
    }

   private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
     if(rowIncrement == 0 && colIncrement == 0) {return false;}
     if(word == "") {return false;}
     int Length = word.length();
     int rowendpoint = (Length-1)*rowIncrement;
     int colendpoint = (Length-1)*colIncrement;
     int currentrow = row;
     int currentcol = col;
     if(rowendpoint > data.length || colendpoint > data[0].length) {return false;}
     for(int i = 0 ; i < Length; i++) {
       char letter = word.charAt(i);
       if(data[currentrow][currentcol] != letter && data[currentrow][currentcol] != '_') {return false;}
       currentrow++;
       currentcol++;
     }
     currentcol = col;
     currentrow = row;
     for(int j = 0; j < Length; j++) {
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
   private int ListIndex(int length) {
     int num = 0;
     for(int i = 0; i < length - 1; i++) {
       num += Math.abs(randgen.nextInt() % 2);
     }
     return num;
   }

   private void Bestrowcol(int[] rowcol) { // A method that returns the best row and col to start, to be Written!

   }
   private void addAllWords() {
    while(wordsToAdd.size() > 0) {
      int Length = wordsToAdd.size();         //
      int index = ListIndex(Length);          // This should be inside of a loop
      String word = wordsToAdd.get(index);    //
      for(int i = info[0]; i < data.length; i++) {
        for(int j = info[1]; j < data[0].length; j++) {
          if((addWord(word,i,j,1,1) || addWord(word,i,j,1,0)) || addWord(word,i,j,0,1)) {
            wordsToAdd.remove(index);
            wordsAdded.add(word);
          }
        }
      }
//    Bestrowcol();
//    int row = info[0];                      // This finds the best row and col to start on
//    int col = info[1];
   }
}

// I use tutorialsPoint to check my code before testing it in any Driver!

public static void main(String args[]) {
  int row = Integer.parseInt(args[0]);
  int col = Integer.parseInt(args[1]);
  String fileName = args[2];
  int RandSeed = Integer.parseInt(args[3]);
  boolean answer = false;

  if(args.length == 5 && args[4] == "key") {
    answer = true;
  }

  WordSearch Block = new WordSearch(row,col,fileName,RandSeed,answer);
  System.out.println(Block);
}
}
