// Let PORTA 0-7 correspond to DQ0-DQ7
// Let PORTF 0-7 correspond to A0-A7
// Let PORTK 0-7 correspond to A8-A15
// Let PORTG 0, 1, 2, 5 correspond to CE, OE, WE, A16 respectively

#define CENABLE PORTG&=B11111110
#define CDISABLE PORTG|=B00000001
#define OENABLE PORTG&=B11111101
#define ODISABLE PORTG|=B00000010
#define WENABLE PORTG&=B11111011
#define WDISABLE PORTG|=B00000100

#define READCOMMAND 0x62 //these two are arbitrarily chosen
#define WRITECOMMAND 0x61

void load_address(uint16_t addr) {
  PORTF = addr & 0xFF;
  PORTK = (addr >> 8) & 0xFF;
}

// This method loads the 3-byte software data protection to the chip.
void softwareDataProtection() {
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0xA0;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
}

void chipErase() {
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0x80;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  load_address(0x5555);
  DDRA = 0xFF;
  PORTA = 0x10;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
}

// This method writes the provided byte of data to the provided address, and takes care of loading the SDP sequence beforehand.
void writeSSTByte(unsigned int address, byte data) {
  softwareDataProtection();
  load_address(address);
  DDRA = 0xFF;
  PORTA = data;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  //just to give enough time for the internal write operation to complete
  delayMicroseconds(20);  
}

// This method reads a byte at the given address.
byte readSSTByte(unsigned int address) {
  load_address(address);
  DDRA = 0x0;
  CENABLE;
  OENABLE;
  delayMicroseconds(10);
  byte retVal = 21;
  retVal = PINA;
  CDISABLE;
  ODISABLE;
  return retVal;
}

void setup() {
  pinMode(11, OUTPUT);
  analogWrite(11, 40);
  Serial.begin(9600);
  Serial.flush();
  // set up port directions
  
  DDRA = 0xFF;
  DDRF = 0xFF;
  DDRK = 0xFF;
  DDRG  |= B00100111;
  PORTG  = B00000111; // let A16 be 0
  analogWrite(11, 0);

//  Serial.println("Attempting write of 0xab to address 0");
//  writeSSTByte(0, 0xCD);
//  Serial.println("Write complete.");
//  byte b = readSSTByte(0);
//  Serial.println("Printing val of b: ");
//  Serial.println(b);
//  Serial.println("B val printed");

  Serial.write('A');
//  Serial.print(readSSTByte(0));
//  Serial.write('A');
}


void loop() {
  
  // put your main code here, to run repeatedly:
  while(Serial.available() < 4) {
    ;
  }
  
  byte command = Serial.read(); 
  
  if(command == WRITECOMMAND) {
      unsigned int address = Serial.read();
      address |= Serial.read() << 8;
      byte data = Serial.read();
      writeSSTByte(address, data);
      Serial.write(readSSTByte(address));
      
  } else if(command == READCOMMAND){
      unsigned int address = Serial.read();
      address |= Serial.read() << 8;
      Serial.read(); //get rid of data
      byte temp = readSSTByte(address);
      Serial.write(temp);
  }
  if(Serial.available() != 0) {
    analogWrite(11, 10);
  }
}
