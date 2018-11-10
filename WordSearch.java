import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.Filenotfoundexception;
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
    public WordSearch(int rows,int cols, String fileName){
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while(in.hasNext()) {
        String word = in.next();
        wordsToAdd.add(word);
      }
      data = new char[rows][cols];
      Random rand = new Random();
      int Randseed = rand.nextInt();
      Random randgen = new Random(Randseed);
//      addAllWords();
    }

    public WordSearch(int rows, int cols, String fileName, int Randseed) {
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while(in.hasNext()) {
        String word = in.next();
        wordsToAdd.add(word);
      }
      data = new char[rows][cols];
      Random randgen = new Random(Randseed);
//      addAllWords();
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


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      int Length = word.length();
      int index = 0;
      if(col + Length > data[row].length) {return false;}
      for(int j = col; j < Length + col; j++) {
        char letter = word.charAt(index);
        index++;
        if(data[row][j] != letter && data[row][j] != '_') {return false;}
      }
      index = 0;
      for(int j = col; j < Length + col; j++) {
        char letter = word.charAt(index);
        data[row][j] = letter;
        index++;
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      int Length = word.length();
      int index = 0;
      if(row + Length > data.length ) {return false;}
      for(int i = row; i < Length + row; i++) {
        char letter = word.charAt(index);
        index++;
        if(data[i][col] != letter && data[i][col] != '_') {return false;}
      }
      index = 0;
      for(int i = row; i < Length + row; i++) {
        char letter = word.charAt(index);
        data[i][col] = letter;
        index++;
      }
      return true;
    }

    public boolean addWordDiagonal(String word, int row, int col) {
     int Length = word.length();
     int index = 0;
     int i = col;
     if(row + Length > data.length) {return false;}
     for(int j = row; j < row + Length; j++) {
       char letter = word.charAt(index);
       index++;
       if(data[j][i] != letter && data[j][i] != '_') {
         return false;
       }
       i++;
     }
     i = col;
     index = 0;
     for(int j = row; j < Length + row; j++) {
       char letter = word.charAt(index);
       data[j][i] = letter;
       i++;
       index++;
     }
     return true;
   }

   public boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
     if(rowIncrement == 0 && colIncrement == 0) {return false;}
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
     return true;
   }

   public boolean addAllWords() {
     int row = 1;
     int col = 1;
     for(int i = 0; i < wordsToAdd.size(); i++) {
       Random num1 = new Random(randgen.nextInt());
       Random num2 = new Random(randgen.nextInt());
       int rowIncrement = num1.nextInt();
       int colIncrement = num2.nextInt();
       String word = wordsToAdd.get(i);
       if(addWord(word,row,col,rowIncrement,colIncrement)) {}
     }


   }

}
