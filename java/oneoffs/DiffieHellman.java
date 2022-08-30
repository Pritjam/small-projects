package oneoffs;
import java.math.BigInteger;

class DiffieHellman {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("78787");
        BigInteger g = new BigInteger("16405");

        BigInteger aa = new BigInteger("59145");
        BigInteger bb = new BigInteger("18081");

        BigInteger a = BigInteger.ZERO, b = BigInteger.ZERO;

        boolean bFound = false, aFound = false;
        for(int i = 0; i < 50000; i++) {
            BigInteger t = BigInteger.valueOf(i);
            if(bb.equals(g.modPow(t, p)) && !bFound) {
                System.out.println("Value found! b = " + t.toString());
                b = t;
                bFound = true;
            }
            if(aa.equals(g.modPow(t, p)) && !aFound) {
                System.out.println("Value found! a = " + t.toString());
                a = t;
                aFound = true;
            }
            if(aFound && bFound) {
                break;
            }
            
            if(i % 500 == 0) {
                System.out.println(i + " loop iterations complete");
            }
        }
        
        System.out.println(g.modPow(a.multiply(b), p).toString());
    }
}