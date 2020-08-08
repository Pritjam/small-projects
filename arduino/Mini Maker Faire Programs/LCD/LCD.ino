//This is C++, the language that Aruinos use.
//It's easy to use but very powerful. Try it out!

#include <LiquidCrystal.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
void setup() {
  lcd.begin(16, 2);
  // Put your message here! Replace the blue text!
    
  

  //Put your message in the blue text above!
}
String messages[] = {"Take a card! -->", "Hello world!", "How are you?", "Learn to code!", "Learn with Lily!", "WfO welcomes you!", "Testing...", "Your text here", "Try it yourself!"};

void loop() {
  // set the cursor to column 0, line 1
  // print the strings
  lcd.setCursor(0,0);
  if ((((millis() / 50) % 200) == 0)){
    lcd.clear();
    lcd.print (messages[random(9)]);
  }
  // (note: line 1 is the second row, since counting begins with 0):
  lcd.setCursor(0, 1);
  // print the number of seconds since reset:
  lcd.print(millis()*.001);
  delay(50);
}
