
#define myOutputPin 9

void setup ()
{
 pinMode (myOutputPin, OUTPUT);
 TCCR1A = 0;
 TCCR1B = 0;
 TCNT1  = 0;
 OCR1A = 3;   // toggle after counting to 8
 TCCR1A |= (1 << COM1A0);   // Toggle OC1A on Compare Match.
 TCCR1B |= (1 << WGM12);    // CTC mode
 TCCR1B |= (1 << CS10);     // clock on, no pre-scaler
}
void loop () { }
