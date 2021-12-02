#define AFTER_FRAME_MIXER 14

byte songData[32];


void setup() {
  DDRG = B11111111;
  DDRC = B11111111;
  pinMode(41, OUTPUT);
  pinMode(52, OUTPUT);
  // put your setup code here, to run once:
  digitalWrite(LED_BUILTIN, LOW);
  Serial.begin(250000);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
}

void loop() {
  digitalWrite(LED_BUILTIN, HIGH);
  while (Serial.available() == 0) {
    digitalWrite(LED_BUILTIN, LOW);
    ; //wait for song data to be available
  }
  digitalWrite(LED_BUILTIN, HIGH);

//  if (songData[AFTER_FRAME_MIXER] != B00000000) {
//    digitalWrite(41, HIGH);
//    PORTC = 7;
//    digitalWrite(41, LOW);
//    PORTC = songData[AFTER_FRAME_MIXER];
//  }
delay(20);

  int j = 0;
  while (Serial.available() > 0) {
    byte incomingByte = Serial.read();
    songData[j] = incomingByte;
    j++;
    // read the incoming byte into the array
  }

  //write song data to ym2149
  //PORTC represents the 8-bit data/address buss
  for (int i = 0; i < 14; i++) {
    digitalWrite(41, HIGH);
    PORTC = i; //input address
    digitalWrite(41, LOW);
    PORTC = songData[i]; //input register data
  }

}
