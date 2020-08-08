void setup() {
  // put your setup code here, to run once:

  pinMode(A0, INPUT_PULLUP);
  pinMode(A1, INPUT_PULLUP);
  pinMode(A2, INPUT_PULLUP);
  //Serial.begin(9600);
  //int note;
  //329 293 261
  //E   D   C   for mary had a little lamb
}

void loop() {
  noTone(6);
  if (analogRead(A0) < 700) {
    tone(6, 261);
  }
  else if (analogRead(A1) < 700) {
    tone(6, 293);
  }
  else if (analogRead(A2) < 700) {
    tone(6, 329);
  }
  delay(100);
}
