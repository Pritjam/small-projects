import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Arrays;
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
		SerialPort arduino = SerialPort.getCommPort("COM5"); // device name
		
		arduino.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
		arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

		if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
			scan.close();
			return;
		}

		File binary = new File("binaries/ROM.bin");
		System.out.println("Loaded file: " + binary.exists());
		FileInputStream fileReader = new FileInputStream(binary);

		if (arduino.openPort()) {
			System.out.println("Port is open :)");
		} else {
			System.out.println("Failed to open port :(");
		}

		while(true) {
			if(arduino.getInputStream().available() > 0 && arduino.getInputStream().read() == 'A') {
				break;
			}
		}

		int i = 0;
		System.out.println("Writing");
		long start = System.nanoTime();
		

		while(fileReader.available() > 0) { // while(fileReader.available() > 0) {
			int b = fileReader.read();
			// System.out.println(b);
			
			byte[] command = {(byte) 0x61, (byte) (i >> 8),  (byte) i, (byte) b};
			arduino.getOutputStream().write(command);
			// System.out.println("Wrote");
			
			while(arduino.getInputStream().available() <= 0) {
				;				
			}
			int j = 0;
			while(arduino.getInputStream().available() > 0) {
				int written = arduino.getInputStream().read();
				if(written != b)
					throw new IOException("Error: Read back different byte than was written! " + written + " " + b);
				j++;
			}
			if(j > 1)
				throw new IOException("Error: Recieved more than 1 byte response!");
			i++;
			if(i > 16384) {
				System.out.println("Somehow printed too many bytes...");
				break;
			}
			if(i % 1024 == 0) {
				System.out.println("Printed 1 kb");
			}
			// System.out.println("Wrote one iteration, confirmed from Ard");
		}
		System.out.println("Elapsed time: " + (System.nanoTime() - start) / 1000000.0);
		fileReader.close();

		while(arduino.getInputStream().available() > 0) {
			System.out.println(arduino.getInputStream().read());
		}


		fileReader = new FileInputStream(binary);
		int p = 0;
		System.out.println("Reading");
		long startRead = System.nanoTime();
		
		while(fileReader.available() > 0) { // while(fileReader.available() > 0) {
			int b = fileReader.read();
			// System.out.println(b);
			
			byte[] command = {(byte) 0x62, (byte) (p >> 8),  (byte) p, (byte) b};
			arduino.getOutputStream().write(command);
			// System.out.println("Wrote");
			
			while(arduino.getInputStream().available() <= 0) {
				;				
			}
			int j = 0;
			while(arduino.getInputStream().available() > 0) {
				int read = arduino.getInputStream().read();
				// System.out.println(read);
				
				if(read != b)
					throw new IOException("Error: Read back different byte than was written! " + read + " " + b);
				j++;
				if(j > 1)
					throw new IOException("Error: Recieved more than 1 byte response!");
			}
			
			p++;
			if(p > 16384) {
				System.out.println("Somehow printed too many bytes...");
				break;
			}
			if(p % 1024 == 0) {
				System.out.println("Printed 1 kb");
			}
			// System.out.println("Wrote one iteration, confirmed from Ard");
		}
		System.out.println("Elapsed time: " + (System.nanoTime() - startRead) / 1000000.0);
		

		

		if (arduino.closePort()) {
			System.out.println("Port is closed :)");
		} else {
			System.out.println("Failed to close port :(");
		}

		fileReader.close();
		scan.close();
	}
}
