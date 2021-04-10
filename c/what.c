#include <stdio.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

int explode_bomb() {
  printf("The bomb blew up! You'd lose half a point on the real thing. Better luck next time!");
  exit(-1); //terminate program
}

char *input_phase_1() {
  printf("Input first line:\n\t>");
  char *input_string = malloc(256); //empty array that can hold 256 characters, it's our input buffer
  scanf("%[^\n]s", input_string); //a command that reads 1 line into input_string
  printf("\tYour input was: %s\n", input_string);
  return input_string;
}

void phase_1(char *input) {
  if(strcmp(input, "Hello World!") != 0) {
    explode_bomb();
  }
  return;
}


//in the real deal, this is all you can see
int main(int argc, char const *argv[]) {
  printf("Welcome to the Demo Bomb!\n");

  char *input = input_phase_1();
  phase_1(input);
  printf("Congrats! You defused Phase 1! That's all this demo has. Good luck on the real deal!\n");
  return 0;
}