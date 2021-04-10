import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Scanner;

public class App {

	public static void writeFrame(byte[] frame, SerialPort arduino) throws IOException, InterruptedException {
		int count = 0;
		for (byte val : frame) {
			arduino.getOutputStream().write(val);
			count++;
		}
		System.out.println(count + " bytes written");
	}

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

		System.out.println("Enter File Path");
		String filePath = scan.nextLine();
		File songFile = new File(filePath);
		while (!songFile.exists()) {
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
		if (!idString.equals("YM3!")) {
			scan.close();
			fileReader.close();
			System.out.println(idString);
			throw new IllegalArgumentException("Error! That file is in an unsupported type!");
		}

		fileLength -= 4;
		int frames = (int) (fileLength / 14);
		byte[][] framesArray = new byte[frames][14];

		//populate array
		for (int i = 0; i < frames; i++) {
			framesArray[i] = new byte[14];
		}
		System.out.println(framesArray[0].length); //should be 14

		// now we read the song into memory
		for(int frame = 0; frame < frames; frame++) {
			for(int register = 0; register < 14; register++) {
				framesArray[frame][register] = (byte) fileReader.read();
			}
		}
		
		fileReader.close();

		if (arduino.openPort()) {
			System.out.println("Port is open :)");
		} else {
			System.out.println("Failed to open port :(");
		}
		Thread.sleep(2000);

		// // now to play the song
		System.out.println(framesArray.length);
		for (byte[] frame : framesArray) {
			writeFrame(frame, arduino);
			Thread.sleep(23);
		}


		// int toWrite;
		// do {
		// System.out.println("Input byte to print, 255 to exit: ");
		// toWrite = scan.nextInt();
		// arduino.getOutputStream().write(toWrite);
		// System.out.printf("Byte written: 0x%x\n", toWrite);
		// }while(toWrite != 255);

		// byte[] cMajorChord = {
		// (byte) 0b11011101,
		// (byte) 0b00000001,
		// (byte) 0b01111011,
		// (byte) 0b00000001,
		// (byte) 0b00111110,
		// (byte) 0b00000001,
		// (byte) 0b00010101,
		// (byte) 0b11111000,
		// (byte) 0b00000100,
		// (byte) 0b00000100,
		// (byte) 0b00000100,
		// (byte) 0b11111111,
		// (byte) 0b00001111,
		// (byte) 0b00001000,
		// (byte) 0b00000000,
		// (byte) 0b00000000
		// };

		// for(byte b : cMajorChord) {
		// arduino.getOutputStream().write(b);
		// System.out.printf("Byte written: 0x%x\n", b);
		// }

		scan.close();

		if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
			return;
		}

	}
}
