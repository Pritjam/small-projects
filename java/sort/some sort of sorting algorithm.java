import java.util.Scanner;
import java.lang.Math; 
import java.util.ArrayList;

class Lesson_17_Activity_Two {
    public static void main(String[] args)
     {
      Scanner scan = new Scanner (System.in);
      boolean flag = true;
      ArrayList<Integer> unsorted = new ArrayList<Integer>();
      ArrayList<Integer> sorted = new ArrayList<Integer>();
      int input;
      
      //take input and add it to the unsorted arrlist
      while(flag) {
        input = scan.nextInt();
        //negative one is the "no more" term
        if (input == -1) {
         flag = false; 
        }
        else {
         unsorted.add(input); 
        }
      }
      
      //find max value in the unsorted list
      int max = 0;
      for (int i = 0; i < unsorted.size(); i++) {
        if (unsorted.get(i) > max) {
          max = unsorted.get(i);
        }
      }

      //just a debug line
      System.out.println("The maximum value found out of " + unsorted.size() + " elements is: " + max);

      //create the array with places for each integer less than or equal to the max value
      int[] temp = new int[max + 1];
      
      //fill in the temp array, at this point the temp array will be a tally of how many of each number is in the unsorted      
      for (int val : unsorted) {
        temp[val] += 1;
      }
      
      //populate the sorted array
      for (int k = 0; k < temp.length; k++) {
       //add x number of each element of k, but only if there are any in the first place
        if (temp[k] != 0) {
          for (int m = 0; m < temp[k]; m++) {
            sorted.add(k);
          }
        }
      }
      
      //print the array 
      System.out.println("Here is the sorted array:");
      System.out.println(sorted.toString());
    }
}