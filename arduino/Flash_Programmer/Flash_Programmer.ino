// Let PORTA 0-7 correspond to DQ0-DQ7
// Let PORTF 0-7 correspond to A0-A7
// Let PORTK 0-7 correspond to A8-A15
// Let PORTG 0, 1, 2, 5 correspond to CE, OE, WE, A16 respectively

#define CENABLE PORTG&=B01111111
#define CDISABLE PORTG|=B10000000
#define OENABLE PORTG&=B10111111
#define ODISABLE PORTG|=B01000000
#define WENABLE PORTG&=B11011111
#define WDISABLE PORTG|=B00100000

#define READCOMMAND 0xAB //these two are arbitrarily chosen
#define WRITECOMMAND 0xCD

void loadAddress(uint16_t addr) {
  PORTF = addr & 0xFF;
  PORTK = (addr >> 8) & 0xFF;
}

// This method loads the 3-byte software data protection to the chip.
void softwareDataProtection() {
  loadAddress(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x5555);
  DDRA = 0xFF;
  PORTA = 0xA0;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
}

void chipErase() {
  loadAddress(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x5555);
  DDRA = 0xFF;
  PORTA = 0x80;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x5555);
  DDRA = 0xFF;
  PORTA = 0xAA;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x2AAA);
  DDRA = 0xFF;
  PORTA = 0x55;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  
  loadAddress(0x5555);
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
  loadAddress(address);
  DDRA = 0xFF;
  PORTA = data;
  CENABLE;
  WENABLE;
  CDISABLE;
  WDISABLE;
  //just to give enough time for the internal write operation to complete
  delayMicroseconds(50);  
}

// This method reads a byte at the given address.
byte readSSTByte(unsigned int address) {
  loadAddress(address);
  DDRA = 0x0;
  PORTG&=B01111111;
  PORTG&=B10111111;
  delay(1);
  byte retVal = 21;
  retVal = PINA;
  PORTG|=B10000000;
  PORTG|=B01000000;
  return retVal;
}

void setup() {
  Serial.begin(9600);
  // set up port directions
  DDRA = 0xFF;
  DDRF = 0xFF;
  DDRK = 0xFF;
  DDRG  |= B11100100;
  PORTG  = B11100000; // let A16 be 0

  
//  Serial.println("Attempting write of 0xab to address 0");
//  writeSSTByte(0, 0xAB);
//  Serial.println("Write complete.");
//  byte b = readSSTByte(0);
//  Serial.println("Printing val of b: ");
//  Serial.println(b);
//  Serial.println("B val printed");

//  Serial.println("attempting to chip-erase...");
//  chipErase();
//  delay(1000);
//  Serial.println("Chip erase should have completed by now.");
  byte b = readSSTByte(0);
  Serial.println("Printing val of b: ");
  Serial.println(b);
  Serial.println("B val printed");
}



void loop() {
  
  // put your main code here, to run repeatedly:
//  while(Serial.available() < 0);
//  byte command = Serial.read();
//  
//  if(command == WRITECOMMAND) {
//      unsigned int address = Serial.read();
//      address |= Serial.read() << 8;
//      byte data = Serial.read();
//      writeByte(address, data);
//  } else if(command == READCOMMAND){
//      unsigned int address = Serial.read();
//      address |= Serial.read() << 8;
//      byte temp = readByte(address);
//      Serial.println(temp, HEX);
//  }
}
