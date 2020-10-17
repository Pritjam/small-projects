
void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
  Serial.begin(250000);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
}
void loop() {
  if (Serial.available() > 0) {    
    byte incomingByte = 0;
    incomingByte = Serial.read(); // read the incoming byte:
    if (incomingByte == B10010101) { // -1 means no data is available
      digitalWrite(LED_BUILTIN, HIGH);
    }
    if (incomingByte == B11110001) { // -1 means no data is available
      digitalWrite(LED_BUILTIN, LOW);
    }
  }
}
