import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

class Pisano {
  
  public static void main(String[] args) {
    
    int twoPrevious = 1;
    int previous = 1;
    int current;
    int count = 3;
    Scanner scan = new Scanner(System.in);
    boolean flagg = true;
    
    while(flagg) {
      count = 3;
      previous = 1;
      twoPrevious = 1;
      System.out.println("Input mod number");
      int mod = scan.nextInt();
      if (mod == 0) {
        break;
      }
      
      boolean flag = true;
      boolean nMinusOne = false;
      boolean currentOne = false;
      
      while(flag) {
        //System.out.println("Loop Start");
        current = (previous + twoPrevious)%mod;
        //System.out.println(count + " " + current + " " + previous + " " + twoPrevious);
        twoPrevious = previous;
        previous = current;
        if (currentOne) {
          if (current == 0) {
            System.out.println("The Pisano Period is: " + (count));
            flag = false;
            break;
          }
          else { nMinusOne = false; currentOne = false;}
        }
        if (nMinusOne) {
          if (current == 1) {
            //System.out.println("CurrentOne set");
            currentOne = true;
          }
          else { nMinusOne = false; currentOne = false;}
        }
        if (current == (mod - 1)) {
          //System.out.println("nMinusOne set");
          nMinusOne = true;
        }
        count++;
      }
    }
  }
}