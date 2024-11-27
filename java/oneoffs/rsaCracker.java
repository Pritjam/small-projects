package oneoffs;
import java.math.BigInteger; 
import java.util.Scanner; 
  
class RsaCracker { 
    // Driver method 
    public static void main(String args[]) { 
        // BigInteger n = new BigInteger("13153737045088962500091416416012315150136768820687785543240799090529558894509944100202415224539145225907565923017082142664212770689432533105603415907875928057339583297933065507890189970459810145261356951988153774209099941226206201982818590638529278084517398766208431835173615746873083486732071498464086");
        // BigInteger ct = new BigInteger("12685531052139424950526813282603923838286086491182813685369140809005475596231262253975474036264054172211181838490113425714386047983375315761846432949867646850383223134234642681055968091319370380126801803353717799623500373089099534567349915651898907976296650408060318551438155639958764020324817736566047");
        // BigInteger e = new BigInteger("65537");
        // BigInteger phi = n.divide(new BigInteger("2")).subtract(BigInteger.ONE);
        // System.out.println(phi);
        BigInteger p = new BigInteger("1825428272569447135073405415915488515443777250495756086591490176333940771194653231832419239057571109754628578294628082137674817721066645149276344998592");
        System.out.println(p.multiply(p));
        //BigInteger d = modInverse(e, phi);
        //System.out.println(d);
        
    } 


    // Returns modulo inverse of a with 
    // respect to m using extended Euclid 
    // Algorithm Assumption: a and m are 
    // coprimes, i.e., gcd(a, m) = 1 
    static BigInteger modInverse(BigInteger a, BigInteger m) 
    { 
        BigInteger m0 = m; 
        BigInteger y = BigInteger.ZERO, x = BigInteger.ONE; 
  
        if (m.equals(1)) 
            return BigInteger.ZERO; 
  
        while (a.compareTo(BigInteger.ONE) > 1) 
        { 
            // q is quotient 
            BigInteger q = a.divide(m); 
  
            BigInteger t = m; 
  
            // m is remainder now, process 
            // same as Euclid's algo 
            m = a.mod(m); 
            a = t; 
            t = y; 
  
            // Update x and y 
            y = x.subtract(q.multiply(y)); 
            x = t; 
        } 
  
        // Make x positive 
        if (x.compareTo(BigInteger.ZERO) < 0) 
            x.add(m0); 
  
        return x; 
    } 
}