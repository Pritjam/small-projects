package oneoffs;

import java.util.Scanner;

// The Pisano Period is the period with which the Fibonacci Sequence repeats under a given modulus.
// For example, with modulus 3, the Fibonacci sequence is 
//    0, 1, 1, 2, 0, 2, 2, 1, 0, 1, 1, 2, 0, 2, 2, 1, 0, 1, 1, 2, 0, 2, 2, 1, 0, ... (sequence A082115 in the OEIS)
// With this modulus, the sequence repeats every 8 terms.
// To find the period, one can either continually generate terms of the sequence and see if a repeat has been generated, which is very slow.
// Another option is to use the property that if the Pisano Period for some modulus m is n, then the terms f(n - 1) and f(n - 2) 
// must be equal to (m - 1) and 1 mod m. This means the next term will be equal to 1 mod m, and so the cycle repeats.
// With this knowledge, we can just use a form of sliding-window DP to generate the sequence and check for the above condition.

class Pisano {

  public static void main(String[] args) {
    int current;
    Scanner scan = new Scanner(System.in);

    int count = 1;
    int a = 0;
    int b = 1;
    System.out.println("Input mod number");
    int mod = scan.nextInt();
    scan.close();
    if (mod == 0) {
      return;
    }

    while (true) {
      if (a == 1 && b == (mod - 1)) {
        System.out.println("The Pisano Period is: " + count);
        break;
      }
      current = (a + b) % mod;
      b = a;
      a = current;
      count++;
    }
  }
}