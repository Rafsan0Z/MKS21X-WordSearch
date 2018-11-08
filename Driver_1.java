public class Driver_1
{
  public static void main(String[] args)
  {
    System.out.println("testing Constructor");
    WordSearch testCase = new WordSearch(3, 5);
    System.out.println("should print out 3 by 5 array with underscores");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("Testing addWordHorizontal");
    testCase.addWordHorizontal("hey", 0, 0);
    System.out.println("top row should say hey");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    testCase.addWordHorizontal("whats", 0, 0);
    System.out.println("should not contain whats because of capacity");
    System.out.println(testCase.toString());
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("testing addWordVertical");
    testCase.addWordVertical("hey", 0, 0);
    System.out.println("first column should say hey");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    testCase.addWordVertical("greetings", 0, 0);
    System.out.println("should not print greetings because of capacity");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("should have yeh on third column");
    testCase.addWordVertical("yeh", 0, 2);
    System.out.println(testCase.toString());
  }
}
