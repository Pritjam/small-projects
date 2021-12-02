import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class ChordWriter {
    public static void main(String[] args) throws IOException, InterruptedException {
		Scanner scan = new Scanner(System.in);
		SerialPort arduino = SerialPort.getCommPort("COM3"); // device name
		arduino.setComPortParameters(250000, 8, 1, 0); // default connection settings for Arduino
		arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

		if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
			scan.close();
			return;
		}

		if (arduino.openPort()) {
			System.out.println("Port is open :)");
		} else {
			System.out.println("Failed to open port :(");
		}
		Thread.sleep(2000);

        Frame bMinorFrame = new Frame(new String[] {"B5", "D5", "FS5"});
        Frame cSharpMinor = new Frame(new String[] {"AS5", "CS5","FS5"});

        writeFrame(bMinorFrame, arduino);
        Thread.sleep(900);
        writeFrame(cSharpMinor, arduino);

		scan.close();

		if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
			return;
		}

    }
    
    public static void writeFrame(Frame Frame, SerialPort port) throws IOException {
        byte[] frame = Frame.getFrame();
        for(byte b : frame) {
            port.getOutputStream().write(b);
            System.out.printf("Byte written: 0x%x\n", b);
        }
    }
}
