import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.*;

class Main {
  public static final String[] colors = new String[]{"RED", "ORN", "YLW", "GRN", "BLU", "PRP", "WHT", "BLK", "GLS"};
  public static ArrayList<String> colorsList = new ArrayList<String>();
  public static final String[][] rybbw = new String[][]
  {
    {"RED", "ORN", "PRP", "BLK", "RED"},
    {"ORN", "YLW", "GRN", "BLK", "YLW"},
    {"PRP", "GRN", "BLU", "BLK", "BLU"},
    {"BLK", "BLK", "BLK", "BLK", "GLS"},
    {"RED", "YLW", "BLU", "GLS", "WHT"}
  };
  
  //here we go
  public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
    Scanner scan = new Scanner(System.in);
    ArrayList<ArrayList<Frog>> boxes = new ArrayList<ArrayList<Frog>>(10);
    for (int i = 0; i < 10; i++) {
      boxes.add(new ArrayList<Frog>());
    }
    for (int i = 0; i < colors.length; i++) {
      colorsList.add(colors[i]);
    }
    int randomThree = (int) (Math.random() * 3);
    for (int i = 0; i < 4; i++) {
      if ( i != randomThree) {
        boxes.get(0).add(new Frog(i));
      }
    }
    boolean[] dex = new boolean[90];
    int money = 0;
    int currentBox = 0;
    System.out.println("Welcome to PokeFrogs! You are a frog breeder who specializes in multicolored frogs.\nYour frogs have 2 attributes: a base color, and an accent color.\nYou can breed these frogs to create new color combinations.\nYou have 10 habitats for your frogs to live in.\nGet help at any time by typing the command \"Help\". Have fun!");
    String command;
    String[] parsed;
    for (int i = 0; i < 3; i++) {
      String[] phenotype = phenotype(boxes.get(currentBox).get(i));
      dex[9 * Integer.parseInt(phenotype[1]) + Integer.parseInt(phenotype[2])] = true;
    }
    
    //main game loop
    while(true) {
      System.out.println("Input a command...");
      command = scan.nextLine();
      parsed = parseCommand(command);
      switch(parsed[0]) {
        case "MOVE":
          boxes.get(Integer.parseInt(parsed[2])).add(boxes.get(currentBox).remove(Integer.parseInt(parsed[1])));
          break;
        case "BREED":
          if (command.length() >= 9) {
          Frog frog1 = boxes.get(currentBox).get(Integer.parseInt(parsed[1]));
          Frog frog2 = boxes.get(currentBox).get(Integer.parseInt(parsed[2]));
          if (frog1.getMaturity() == 3 && frog2.getMaturity() == 3) {
            Frog newfrog = punnetCross(frog1, frog2);
            boxes.get(currentBox).add(newfrog);
            String[] phenotype = phenotype(newfrog);
            dex[9 * Integer.parseInt(phenotype[1]) + Integer.parseInt(phenotype[2])] = true;
            System.out.println("Frog " + parsed[1] + " bred with frog " + parsed[2] + " to produce a new frog!");
          }
          else { System.out.println("Frogs need to be fully grown (Maturity 3) to breed!"); }
        }
          else {
            System.out.println("You need to specify which frogs, like so: \"breed 0:1\" to breed frogs 0 and 1");
          }
          break;
          
        case "VIEW":
          System.out.println("Current Box: " + currentBox);
          for (int i = 0; i < boxes.get(currentBox).size(); i++) {
            System.out.println(i + ": " + phenotype(boxes.get(currentBox).get(i))[0] + ", Maturity: " + boxes.get(currentBox).get(i).getMaturity());
          }
          break;
          
        case "SAVE":
          System.out.println("Saving...");
          System.out.println("Please input the save name.");
          save(dex, boxes);
          break;
          
        case "HELP":
          System.out.println("Here are the recognized commands:");
          System.out.println("Breed <index1>:<index2>\nFeed <index>\nMove <index>:<destination box>\nSwitch <box to switch to>\nView (This command lists all frogs in the currently selected box)\nRelease <index>\nFroggydex (shows all frogs unlocked)\nSave\nLoad");
          break;
          
        case "FROGGYDEX":
          System.out.println("Printing FroggyDex:");
          System.out.println("    BASE RED ORN YLW GRN BLU PRP WHT BLK GLS");
          System.out.println("ACCENT");
          for (int i = 0; i < 9; i++) {
            System.out.print(colorsList.get(i) + "      ");
            for (int j = 0; j < 9; j++) {
              if(dex[9 * i + j]) {
                System.out.print(" +  ");
              }
              else {
                System.out.print(" -  ");
              }
            }
            System.out.println();
          }
          break;
          
        case "FEED":
          if (command.length() >= 6) {          
            if (boxes.get(currentBox).get(Integer.parseInt(parsed[1])).feed()) {
              System.out.println("That frog is already fully grown!");
            }
            else { System.out.println(":yum:, feed frog " + parsed[1] + " and increased its maturity by 1"); }
          }
          else { System.out.println("You need to specify which frog to feed, like so: \"feed 2\""); }
          break;
          
        case "RELEASE":
          System.out.println("Are you sure? (Y/N)");
          if (scan.nextLine().toUpperCase().equals("Y")) {
            boxes.get(currentBox).remove(Integer.parseInt(parsed[1]));
            System.out.println("Done.");
          }
          else { System.out.println("Release Cancelled."); }
          break;
          
        case "SWITCH":
          if (command.length() >= 8) {
          currentBox = Integer.parseInt(parsed[1]);
          System.out.println("Switched to box " + Integer.parseInt(parsed[1]));
        }
          else {
            System.out.println("You need to specify which box to switch to!");
          }
          break;
          
        case "LOAD":
          System.out.println("Please type in your save name.");
          String savePath = scan.nextLine();
          
          FileInputStream fileIn = new FileInputStream("saves/" + savePath.toUpperCase() + ".sav");
          ObjectInputStream ois = new ObjectInputStream(fileIn);
          dex = (boolean[]) ois.readObject(); //deserialize the array
          boxes = (ArrayList<ArrayList<Frog>>) ois.readObject();
          ois.close();
          System.out.println("Loaded Successfully!");
          break;
          
        default:
          System.out.println("Not a recognized command! Type \"Help\" for a list of commands.");
      }
      System.out.println();
    }
    
  }
  
  
  public static String[] parseCommand(String input) {
    input += " a:b:c";
    input = input.toUpperCase();
    String[] tokens = input.split(" ");
    String action = tokens[0];
    String[] arguments = tokens[1].split(":");
    String[] parsed = new String[arguments.length + 1];
    parsed[0] = action;
    for (int i = 0; i < arguments.length; i++) {
      parsed[i + 1] = arguments[i];
    }
    return parsed;
  }
  
  public static String[] phenotype(Frog frog) {
    String[] phenotype = new String[3];
    String phenotypeString = "";
    int[] gametes = frog.getGametes();
    //figure out base color
    phenotypeString += rybbw[gametes[0]][gametes[1]];
    phenotypeString += " with an accent of ";
    //figure out accent color
    phenotypeString += rybbw[gametes[2]][gametes[3]];
    //put it all together
    phenotype[0] = phenotypeString;
    phenotype[1] = colorsList.indexOf(rybbw[gametes[0]][gametes[1]]) + "";
    phenotype[2] = colorsList.indexOf(rybbw[gametes[2]][gametes[3]]) + "";
    
    //get the achievement
    return phenotype;
  }
  
  public static Frog punnetCross(Frog f1, Frog f2) {
    int[] newFrogArray = {0, 0, 0, 0};
    int[] gametes1 = f1.getRandGametes();
    int[] gametes2 = f2.getRandGametes();
    newFrogArray[0] = gametes1[0];
    newFrogArray[1] = gametes2[0];
    newFrogArray[2] = gametes1[1];
    newFrogArray[3] = gametes2[1];
    Frog newFrog = new Frog(newFrogArray);
    return newFrog;
  }
  
  public static void save(boolean[] dex, ArrayList<ArrayList<Frog>> boxes) throws IOException {
    Scanner scan = new Scanner(System.in);
    
    String saveOutPath = scan.nextLine();
    FileOutputStream fileStream = new FileOutputStream("saves/" + saveOutPath.toUpperCase() + ".sav");
    ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
    objectStream.writeObject(dex);
    objectStream.writeObject(boxes);
    objectStream.close();
    System.out.println("Saved Successfully as \"" + saveOutPath +"\""); 
  }
}