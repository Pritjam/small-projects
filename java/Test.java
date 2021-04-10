import java.math.BigInteger;

class Test {
  public static void main(String[] args) {
    BigInteger c = new BigInteger("2f7f63b5e27343dcf750bf83fb4893fe3b20a87e81e6fb62c33d30", 16);
    BigInteger k = new BigInteger("5a0b05d9831438ac8561d2b0a42be1cf5613db21deb9a443e21c4d", 16);
    System.out.println(k.xor(c).toString(16));
  }
}