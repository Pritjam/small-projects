#include "pitches.h"
// notes in the melody:
int melody[] = {
  NOTE_FS6, NOTE_E6, 0, NOTE_D6, 0, NOTE_CS6, 0, NOTE_A5, 0, NOTE_FS5, NOTE_B5, 0, NOTE_CS6, 0, NOTE_D6, 0, NOTE_E6, 0, NOTE_CS6, 0, 
  NOTE_B5, 0, NOTE_FS5, 0, NOTE_D5, NOTE_CS5, NOTE_B4, 0, NOTE_B4, NOTE_CS5, NOTE_D5, NOTE_B4, NOTE_A4, NOTE_B4, NOTE_FS4, 0,
  NOTE_B5, 0, NOTE_FS5, 0, NOTE_D5, NOTE_CS5, NOTE_B4, NOTE_B4, NOTE_CS5, NOTE_D5, NOTE_E5, NOTE_CS5, NOTE_A4, NOTE_B4, NOTE_FS4, NOTE_B4, 0,
  NOTE_B5, 0, NOTE_FS5, 0, NOTE_D5, NOTE_CS5, NOTE_B4, 0, NOTE_B4, NOTE_CS5, NOTE_D5, NOTE_B4, NOTE_A4, NOTE_B4, NOTE_FS4, 0,
  NOTE_B5, 0, NOTE_FS5, 0, NOTE_D5, NOTE_CS5, NOTE_B4, NOTE_B4, NOTE_CS5, NOTE_D5, NOTE_E5, NOTE_CS5, NOTE_A4, NOTE_B4, NOTE_FS4, NOTE_B4
};
// note durations: 4 = quarter note, 8 = eighth note, etc.:

int noteDurations[] = {
  4, 16, 16, 16, 16, 16, 16, 16, 16, 4, 16, 16, 16, 16, 16, 16, 16, 16, 4, 4,
  4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
  4, 4, 4, 4, 4, 4, 4, 8, 8, 4, 4, 4, 4, 4, 4, 4, 4,
  4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
  4, 4, 4, 4, 4, 4, 4, 8, 8, 4, 4, 4, 4, 4, 4, 4
};

void setup() {
  // iterate over the notes of the melody:

}
void loop() {
    for (int thisNote = 0; thisNote < 85; thisNote++) {
    // to calculate the note duration, take one second
    // divided by the note type.
    //e.g. quarter note = 1000 / 4, eighth note = 1000/8, etc.
    int noteDuration = 800 / noteDurations[thisNote];
    tone(5, melody[thisNote], noteDuration);
    //pause for the note's duration plus 30 ms:
    delay(noteDuration + 30);
  }
  delay(10000);
}
