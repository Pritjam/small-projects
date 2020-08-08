#include <Servo.h>

Servo tilt;  // create servo object to control a servo
Servo pan;
int tiltRaw;
int tiltServo;
int panRaw;
int panServo;


void setup() {
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  tilt.attach(9);  // attaches the servos on pin 9 and 10 to the servo objects
  pan.attach(10);
}

void loop() {
  tiltRaw = pulseIn(A0, HIGH);
  tiltServo = map(tiltRaw, 0, 1023, 0, 180);
  tilt.write(tiltServo);
  panRaw = pulseIn(A1, HIGH);
  panServo = map(panRaw, 0, 1023, 0, 180);     // scale it to use it with the servo (value between 0 and 180)
  pan.write(panServo);   
  delay(15);                           // waits for the servo to get there
}
