import java.util.Base64;

class BaseSixtyFourTest {
  
  public static void main(String[] args) {
    String originalInput = "Feelin The Blues \nBLU RED \nBLU ORN \nBLU YLW \nBLU GRN \nBLU BLU \nBLU PRP";
    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    
    System.out.println(encodedString);
    System.out.println();
    
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    String decodedString = new String(decodedBytes);
    
    System.out.println(decodedString);
  }
}