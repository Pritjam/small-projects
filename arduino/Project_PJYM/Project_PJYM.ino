void setup() {
  // put your setup code here, to run once:
  //PORTD is pins 0-7, we'll use that for data
  //PORTC is analog A0-A5, used for addressing

  Serial.begin(250000);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
  byte metadata[64];
  int i = 0;
  while(Serial.available() > 0) {
    byte incomingByte = Serial.read(); // read the incoming byte
    metadata[i] = incomingByte;
    i++;
  }

  //process metadata
  //tempo mainly
}

void loop() {
  // put your main code here, to run repeatedly:
  while(Serial.available() == 0) {
    ; //wait for song data to be available
  }

  byte songdata[64];
  int i = 0;
  while(Serial.available() > 0) {
    byte incomingByte = Serial.read(); // read the incoming byte
    songdata[i] = incomingByte;
    i++;
  }

  //write song data
  for(int i = 0; i < 64; i++) {
    PORTC = i;
    PORTD = songData[i];
  }
}
