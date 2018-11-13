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
    public WordSearch(int rows,int cols, String fileName){
      data = new char[rows][cols];
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try {
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        while(in.hasNext()) {	
          String word = in.nextLine();
          wordsToAdd.add(word);
        }
      } catch(FileNotFoundException e) {
        System.out.println("File: " + fileName + " does not exist!");
      }
      randgen = new Random();
      clear();
      addAllWords();
    }

    public WordSearch(int rows, int cols, String fileName, int Randseed) {
      data = new char[rows][cols];
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try {
        File f = new File(fileName);
        Scanner in = new Scanner(f);
        while(in.hasNext()) {
          String word = in.nextLine();
          wordsToAdd.add(word);
        }
      } catch(FileNotFoundException e) {
        System.out.println("File: " + fileName + " does not exist!");
      }
      randgen = new Random(Randseed);
      clear();
      addAllWords();
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

   private void addAllWords() {
     int colLength = data[0].length;
     int rowLength = data.length;
     int row = 0;
     int col = 0;
     int count = 0;
     int colIncrement = -1;
     int rowIncrement = -1;
     int amountofwords = wordsToAdd.size();
    for(int i = 0; i < amountofwords; i++) {
      String word = wordsToAdd.get(0);
      if(addWord(word, row, col, rowIncrement, colIncrement) && count < 1000) {
          count++;
      }
     }
   }



}
