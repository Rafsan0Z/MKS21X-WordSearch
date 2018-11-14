import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public void VarifyFile(String fileName) {
      try{
        File file = new File(fileName);
        Scanner in = new Scanner(file);
      } catch(IllegalArgumentException e) {
        System.out.println("The File: " + fileName + "does not exist! Check your Directory!");
      }
    }

    public WordSearch(int rows, int cols, String fileName, int Randseed, boolean answer) {
      data = new char[rows][cols];
      clear();
      VarifyFile(fileName);
      seed = Randseed;
      randgen = new Random();
      addAllWords();
      if(!solution) {
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

   private void Bestrowcol(int[] info) {}    // A method that returns the best row and col to start, to be Written!

   private void addAllWords() {
    int rowIncrement = -1;
    int colIncrement = 1;
    int Length = wordsToAdd.size();         //
    int index = ListIndex(Length);          // This should be inside of a loop
    String word = wordsToAdd.get(index);    //

   }


// I use tutorialsPoint to check my code before testing it in any Driver!
}
