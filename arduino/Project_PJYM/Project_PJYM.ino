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
  digitalWrite(52, LOW);
  while (Serial.available() == 0) {
    digitalWrite(52, HIGH);
    ; //wait for song data to be available
  }
  digitalWrite(52, LOW);

  digitalWrite(41, HIGH);
  delay(1);
  PORTC = 7;
  delay(1);
  digitalWrite(41, LOW);
  delay(1);
  PORTC = songData[AFTER_FRAME_MIXER]; 
  
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
    delay(1);
    PORTC = i; //input address
    delay(1);
    digitalWrite(41, LOW);
    delay(1);
    PORTC = songData[i]; //input register data
    delay(1);
  }
  
}
