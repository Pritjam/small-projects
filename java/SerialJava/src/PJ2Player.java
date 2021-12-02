import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;

public class PJ2Player {
    public static void main(String[] args) throws Exception {
        final String DELIMITER = "-";
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter File Path");
		String filePath = scan.nextLine();
		File songFile = new File(filePath);
		while (!songFile.exists()) {
			System.out.println("File doesn't exist! Try a different path.");
			filePath = scan.nextLine();
			songFile = new File(filePath);
        }
        scan.close();
        FileInputStream fileStream = new FileInputStream(songFile);
        Scanner reader = new Scanner(fileStream);
        String version = reader.nextLine();
        if(!version.equals("PJ2!")) {
            reader.close();
            throw new Exception("Bad file, this is not in PJ2 format!");
        }

        double bpm = Integer.parseInt(reader.nextLine());
        int delayMillis = (int) ((15.0 / bpm) * 1000.0); // 15/bpm is seconds per frame
        System.out.println("Millisecond delay: " + delayMillis);

        int length = Integer.parseInt(reader.nextLine());
        System.out.println("length: " + length);
        String[][] channelStreams = new String[3][length];

        for(int i = 0; i < 3; i++) {
            String line = reader.nextLine();
            System.out.println(line);
            channelStreams[i] = line.split(DELIMITER, -1);
        }
        reader.close();

        PJ2Frame[] frames = new PJ2Frame[length];
        for(int i = 0; i < length; i++) {
            frames[i] = new PJ2Frame(new String[]{channelStreams[0][i], channelStreams[1][i], channelStreams[2][i]});
        }

        //System.out.println(Arrays.toString(frames));

        SerialPort arduino = SerialPort.getCommPort("COM4"); // device name
		arduino.setComPortParameters(250000, 8, 1, 0); // default connection settings for Arduino
		arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

		if (arduino.openPort()) {
			System.out.println("Port is open :)");
		} else {
			System.out.println("Failed to open port :(");
		}
		Thread.sleep(2000);



        for(PJ2Frame f : frames) {
            writeFrame(f, arduino);
            Thread.sleep(delayMillis);
        }

        if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
			scan.close();
			return;
		}

        

    }


    public static void writeFrame(PJ2Frame frame, SerialPort port) throws IOException {
        byte[] frameBytes = frame.getFrame();
        for(byte b : frameBytes) {
            port.getOutputStream().write(b);
        }
    }
}
