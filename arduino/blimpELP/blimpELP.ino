/*
  RC PulseIn Joystick
  By: Nick Poole
  SparkFun Electronics
  Date: 5
  License: CC-BY SA 3.0 - Creative commons share-alike 3.0
  use this code however you'd like, just keep this license and
  attribute. Let me know if you make hugely, awesome, great changes.
*/

int ch1; // Here's where we'll keep our channel values
int ch2;
int throttleraw;
int steer;
int throttletime;
void setup() {

  pinMode(3, OUTPUT);
  pinMode(5, INPUT); // Set our input pins as such
  pinMode(6, INPUT);
  Serial.begin(9600); // Pour a bowl of Serial

}

void loop() {

  ch1 = pulseIn(5, HIGH, 25000); // Read the pulse width of
  ch2 = pulseIn(6, HIGH, 25000); // each channel

  throttleraw = abs(map(ch2, 1049, 2032, 255, -255) - 22);
  steer = (map(ch1, 1005, 1995, -255, 255));
  throttletime = (map(throttleraw, -2, 233, 40, 0));
  if ((-4 < throttleraw) && (throttleraw < 5))  {
    Serial.println ("nothing");
  }
  else {
    digitalWrite(3, HIGH);
    delay(100);
    digitalWrite(3, LOW);
    delay(throttletime);
    //Serial.println("--------");
  };
  //Serial.println(throttletime);
  //Serial.println(throttleraw);

  //delay(1000);
}
