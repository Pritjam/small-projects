#include <IBusBM.h>
#define RIGHT 1;
#define LEFT 2;

IBusBM IBus;

void setup() {
  //Use the only serial I have, serial0, using pins 0 and 1 for rx and tx
  Serial.begin(Serial);
  IBus.addSensor(IBUSS_EXTV);
  IBus.addSensor(IBUSS_EXTV);
  //these batteries will range from 12.6V (charged) to 9V (discharged)
  //I won't go any lower than 10.5, however, and will prefer to land before then
  //Because of this, I think a 1:3 voltage divider setup is best: 10.5 would become 3.5, and 12.6 would become 4.2.
  //3.5V, then, would be an analogRead of 716, and 4.2V would be 860

}

void loop() {
  // put your main code here, to run repeatedly:
  //right motor voltage = ((int)50*(analogRead(A0)/1024.0));
  IBus.setSensorMeasurement(RIGHT, ((int)50*(analogRead(A0)/1024.0)));
  //left motor voltage = ((int)50*(analogRead(A0)/1024.0));
  IBus.setSensorMeasurement(LEFT, ((int)50*(analogRead(A1)/1024.0)));

}
