#include <SPI.h>
#include <LPD8806.h>

int nLEDs = 20;
int interruptPin = 3; //Pin connected to RX aux, controlled by a dial on the TX
volatile unsigned long pulseLength;  // When accessing this in setup or loop (or anything called by either) interrupts must be disabled
LPD8806 strip = LPD8806(nLEDs); //data needs to go to MOSI, clock to SCK
int rgb, r, g, b;


void setup() {
  pinMode(interruptPin, INPUT);
  attachInterrupt(digitalPinToInterrupt(interruptPin), updateColor, CHANGE);  //runs the updateColor function whenever the interruptPin changes from LOW to HIGH or vice versa
  strip.begin();
  strip.show();
}

void loop() {
  //rgb = map(pulseLength, fromLow, fromHigh, 0, 768); //will add this in once I know what fromHigh and fromLow actually are

  colorWipe(Wheel(rgb)); //bam, sets the whole strip to the color specified by the dial in one go
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

// Fill the dots along the strip.
void colorWipe(uint32_t c) {
  for (int i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, c);
  }
  strip.show();
}

uint32_t Wheel(uint16_t wheelPos) {
  byte r, g, b;
  switch (wheelPos / 128) { //each case is 1/6 of the possible inputs:
    case 0:
      r = 127;
      g = wheelPos % 128;
      b = 0;
      break;
    case 1:
      r = 127 - (wheelPos % 128);
      g = 127;
      b = 0;
      break;
    case 2:
      r = 0;
      g = 127;
      b = wheelPos % 128;
      break;
    case 3:
      r = 0;
      g = 127 - (wheelPos % 128);
      b = 127;
      break;
    case 4:
      r = wheelPos % 128;
      g = 0;
      b = 127;
      break;
    case 5:
      r = 127;
      g = 0;
      b = 127 - (wheelPos % 128);
      break;
  }
  return (strip.Color(r, g, b));
}
