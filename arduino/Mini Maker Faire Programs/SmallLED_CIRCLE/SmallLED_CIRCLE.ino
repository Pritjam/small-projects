void setup() {
  // put your setup code here, to run once:
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(16, OUTPUT);
  pinMode(17, OUTPUT);
  digitalWrite(4, LOW);
  digitalWrite(7, LOW);
  digitalWrite(12, LOW);
  digitalWrite(17, LOW);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(3, HIGH);
  delay(50);
  digitalWrite(6, HIGH);
  delay(50);
  digitalWrite(11, HIGH);
  delay(50);
  digitalWrite(16, HIGH);
  delay(100);

  digitalWrite(3, LOW);
  delay(50);
  digitalWrite(6, LOW);
  delay(50);
  digitalWrite(11, LOW);
  delay(50);
  digitalWrite(16, LOW);
  delay(50);

  delay(4000);

}
