// Simple C++ program to display "Hello World"

// Header file for input output functions
#include <iostream>



bool compare_strings(const char* a, const char* b, uint32_t length) {
  for (uint8_t i = 0; i < length; i++) {
    if (*a != *b) return false;
    a++;
    b++;
  }
  return *a == 0;
}
using namespace std;
// main function -
// where the execution of program begins
int main() {
  // prints hello worlda
  cout << compare_strings("Name", "Name", 4  );

  return 0;
}