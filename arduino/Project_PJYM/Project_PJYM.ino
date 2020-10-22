void setup() {
  DDRG = B11111111;
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(41, OUTPUT);
  // put your setup code here, to run once:
  digitalWrite(LED_BUILTIN, LOW);
  Serial.begin(250000);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
//  byte metadata[64];
//  int i = 0;
//  while(Serial.available() > 0) {
//    byte incomingByte = Serial.read(); // read the incoming byte
//    metadata[i] = incomingByte;
//    i++;
//  }

//  if(metadata[0] == B01010101) {
//    for(int i = 0; i < 1000; i++) {
//     digitalWrite(LED_BUILTIN, HIGH);
//     delay(250);
//     digitalWrite(LED_BUILTIN, LOW); 
//     delay(250);
//    }
//  } else {
//     digitalWrite(LED_BUILTIN, HIGH);
//     delay(100);
//     digitalWrite(LED_BUILTIN, LOW); 
//     delay(100);
//     digitalWrite(LED_BUILTIN, HIGH);
//     delay(100);
//     digitalWrite(LED_BUILTIN, LOW); 
//     delay(250);
//  }

  //process metadata
  //tempo mainly
}

void loop() {
  // put your main code here, to run repeatedly:
  while(Serial.available() == 0) {
    ; //wait for song data to be available
  }

  byte songData[14];
  int i = 0;
  while(Serial.available() > 0) {
    byte incomingByte = Serial.read(); // read the incoming byte
    songData[i] = incomingByte;
    i++;
  }
  //Serial.write(songData);

  //write song data
  for(int i = 0; i < 14; i++) {
//    PORTG = PORTG | B0000001; //address mode
    digitalWrite(41, HIGH);
    delay(1);
    PORTC = i; //input address
    delay(1);
    digitalWrite(41, LOW);
    delay(1);
//    PORTG = PORTG & B11111110;
    PORTC = songData[i];
    delay(50);
  }
}
