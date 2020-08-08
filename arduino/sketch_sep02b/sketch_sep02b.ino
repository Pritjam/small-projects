#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

int numSpaces;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.read() == " ") {
    numSpaces++;
  }
  lcd.setCursor(0,1);
  lcd.print(numSpaces);
}
