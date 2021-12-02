import java.math.BigInteger;
import java.util.Arrays;

class Test {
  public static void main(String[] args) {
    BigInteger n = new BigInteger("72970812");
    BigInteger SIXTY_NINE = new BigInteger("69");
    BigInteger FOUR_TWENTY = new BigInteger("420");
    BigInteger C1 = new BigInteger("72970812");
    BigInteger C2 = new BigInteger("42262748");
    for (long i = 0; i < Long.MAX_VALUE; i++) {
      try {
        if (FOUR_TWENTY.multiply(SIXTY_NINE.modInverse(n)).multiply(C1).mod(n) == C2.mod(n)) {
          System.out.println(n.toString(10));
          return;
        }
      } catch (ArithmeticException e) {
      }
      n = n.add(BigInteger.ONE);
      //System.out.println(n.toString());
      
      if (n.mod(new BigInteger("100000000")) == BigInteger.ZERO) {
        System.out.println("did a billion");

      }
    }

  }
}