void setup() {
  // put your setup code here, to run once:
  for (int i = 0; i <= 19; i++)
  {
    pinMode(i, OUTPUT);
  }
 
}
int led = 0;



void loop() {
  // put your main code here, to run repeatedly:
  for (int i = -19; i < 20; i++) {
    digitalWrite(i,LOW);
    digitalWrite(i+19,HIGH);
    delay(100);
  }
  delay (50);
}
