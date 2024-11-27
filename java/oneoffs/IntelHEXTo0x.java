package oneoffs;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

class IntelHEXTo0x {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<String> bytes = new ArrayList<String>();
    
    try {
      File hex = new File("hexes.txt");
      Scanner myReader = new Scanner(hex);
      while (myReader.hasNextLine()) {
        lines.add(myReader.nextLine());
      }
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred. The requested file wasn't found.");
      e.printStackTrace();
    }
    //:20000000F3C38A0100000000C324010000000000C3C0000000000000C35401FFFFFFFFFF21
    //first index is 9, last is 71, so 
    for (String line : lines) {
      for(int i = 0; i < 63; i+=2) {
        bytes.add("0x" + line.substring(i + 9, i + 11));
      }
    }
    System.out.println(bytes.toString());
    System.out.println("entries: " + bytes.size());
  }
}