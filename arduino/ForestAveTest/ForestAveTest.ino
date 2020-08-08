int note;

void setup() {
  // put your setup code here, to run once:

  pinMode(A0, INPUT_PULLUP);
  pinMode(A1, INPUT_PULLUP);
  pinMode(A2, INPUT_PULLUP);
  Serial.begin(9600);
  //329 293 261
  //E   D   C   for mary had a little lamb
}

void loop() {
  // put your main code here, to run repeatedly:
  note = analogRead(A0)/10;
  Serial.println(note*10);
  tone(6, (map(10*note, 360, 520, 260 , 523)));
  delay(100);
}
