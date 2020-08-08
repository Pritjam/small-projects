#include <FastLED.h>
#define NUM_LEDS 96
#define DATA_PIN 9
//program for the tiny Arduino Leonardo (see spreadsheet for more)
int interruptPin = 3; //Pin connected to RX aux, controlled by a dial on the TX. Pin 3 is the SCL pin.
volatile unsigned long pulseLength;  // When accessing this in setup or loop (or anything called by either) interrupts must be disabled
int hue;
CRGB leds[NUM_LEDS];

void setup() {
  pinMode(interruptPin, INPUT);
  attachInterrupt(digitalPinToInterrupt(interruptPin), updateColor, CHANGE);  //runs the updateColor function whenever the interruptPin changes from LOW to HIGH or vice versa
  FastLED.clear();
}

void loop() {
  //hue = map(pulseLength, fromLow, fromHigh, 0, 255);
    leds[0].setHue(hue);
    leds[1].setHue(hue);
    leds[2].setHue(hue);
  for (int i = 0; i < (NUM_LEDS - 3); i++) {
    //set the next led to on
    leds[i+3].setHue(hue);
    //clear the previous one
    leds[i] = 0x000000;
    FastLED.show();
  }
}

void updateColor() {
  unsigned long riseTime;  //Time of front raising, or going from LOW to HIGH
  unsigned long fallTime;  //Time of front falling, or going from HIGH to LOW

  if (digitalRead(interruptPin) == HIGH) {
    riseTime = micros(); //get time when pulse went up
  }
  else {
    fallTime = micros();  //get time when pulse went down
    pulseLength = riseTime - fallTime;  //measure time between down and up, and save it as pulseLength, which is later accessed from loop()
  }
}
