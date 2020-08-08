//This is C++, the language that Aruinos use.
//It's easy to use but very powerful. Try it out!

#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
void setup() {
  lcd.begin(16, 2);
  // Put your message here! Replace the blue text!
    
  randomSeed(analogRead(0));

  //Put your message in the blue text above!
}
String messages[] = {"Take a card! -->", "Hello world!", "16 Character Max", "Learn to code!", "Learn with Lily!", 
"WfO welcomes you!", "Testing...", "Your text here", "Try it yourself!", "Welcome to MMF!"};

int last;
int current = 0;

void loop() {
  // set the cursor to column 0, line 1
  // print the strings
  lcd.setCursor(0,0);
  if ((((millis() / 50) % 100) == 0)){
    lcd.clear();
    last = current;
    randomstuff:
    current = random (10);
    if (current == last){
     goto randomstuff;
    }
    lcd.print (messages[current]);
  }
  // (note: line 1 is the second row, since counting begins with 0):
  lcd.setCursor(0, 1);
  // print the number of seconds since reset:
  lcd.print((millis()/1000)/60);
  lcd.print("m ");
  lcd.print((millis()/1000) % 60);
  lcd.print("s");
  delay(50);
}
