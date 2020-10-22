import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class App {

  public static void writeFrame(byte[] frame, SerialPort sp) throws IOException, InterruptedException {
    for(byte val : frame) {
      sp.getOutputStream().write(val);
      //System.out.println(val);
    }
  }
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        SerialPort sp = SerialPort.getCommPort("COM3"); // device name
        sp.setComPortParameters(250000, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
        

        if (sp.closePort()) {
          System.out.println("Port is closed :)");
        } else {
          System.out.println("Failed to close port :(");
          return;
        }
        

        
        System.out.println("Enter File Path");
        String filePath = scan.nextLine();
        File songFile = new File(filePath);
        while(!songFile.exists()) {
          System.out.println("File doesn't exist! Try a different path.");
          filePath = scan.nextLine();
          songFile = new File(filePath);
        }

        long fileLength = songFile.length();
        System.out.println("File size: " + fileLength);
        byte[] identifier = new byte[4];

        FileInputStream fileReader = new FileInputStream(songFile);
        fileReader.read(identifier);
        String idString = new String(identifier);
        if(!idString.equals("YM3!")) {
          scan.close();
          fileReader.close();
          System.out.println(idString);
          throw new IllegalArgumentException("Error! That file is in an unsupported type!");
        }

        fileLength -= 4;
        int frames = (int) (fileLength/14);
        byte[][] framesArray = new byte[frames][14];


        //now we read the song into memory
        for(int i = 0; i < frames; i++) {
          byte[] temp = new byte[14];
          fileReader.read(temp);
          framesArray[i] = temp;
        }

        if (sp.openPort()) {
          System.out.println("Port is open :)");
        } else {
          System.out.println("Failed to open port :(");
        }
        

        //now to play the song
        //now to play the song
        int i = 0;
        for(byte[] frame : framesArray) {
          i++;
          if(i % 10 == 0) {
            System.out.println("second has passed");
          }
          writeFrame(frame, sp);
          Thread.sleep(3500);
        }
        fileReader.close();
        scan.close();
        
        if (sp.closePort()) {
          System.out.println("Port is closed :)");
        } else {
          System.out.println("Failed to close port :(");
          return;
        }
        
        
      }
}
