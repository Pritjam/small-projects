#define BC1 41 //pin 0 on port G
#define BDIR 40 //pin 1 on port G

void setup() {
  DDRC = B11111111;
  DDRG = B11111111;
  PORTC = B01010101;
  PORTG |= B00000100; //RESET HIGH
  pinMode(BC1, OUTPUT);
  pinMode(BDIR, OUTPUT);
  digitalWrite(BC1, LOW);
  digitalWrite(BDIR, LOW);
//  pinMode(LED_BUILTIN, OUTPUT);
//  digitalWrite(LED_BUILTIN, LOW);
  Serial.begin(250000);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
}
void loop() {
  //PORTC = Serial.available();
  if (Serial.available() > 13) {
    int address = 0;
    PORTG &= B11111011; //RESET LOW
    delayMicroseconds(50);
    PORTG |= B00000110; //RESET, BDIR HIGH
    while(Serial.available() > 0) {
      PORTG |= B00000001; //BC1 HIGH, Address Mode
      delayMicroseconds(36);
      PORTC = address; //input address
      delayMicroseconds(36);
      PORTG &= B11111100; //BDIR, BC1 LOW, Inactive Mode
      delayMicroseconds(36);
      PORTC = Serial.read(); //input register data
      delayMicroseconds(36);
      PORTG |= B00000010; //BDIR HIGH, Data Mode
      address++;
    }
  }
  PORTG &= B11111100; // BDIR, BC1 LOW
}
