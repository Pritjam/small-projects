
void setup() {
  // declare pin 9 to be an output:
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(9, OUTPUT);
  Serial.begin(9600);
}
char randomWrite(int x) {

  if (random(2) == 1) {
    digitalWrite(x, HIGH);
  }
  else {
    digitalWrite(x,LOW);
  }
}
// the loop routine runs over and over again forever:
void loop() {
  randomWrite(5);
  randomWrite(6);
  randomWrite(9);
  delay(1000);

}
