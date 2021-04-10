#include <stdio.h>
#include <ctype.h>
#include <string.h>

int main(int argc, char const *argv[]) {
    printf("hello");
    char s[16] = {0}, *b;
    unsigned int *i = (unsigned int *)s;
    int (*f)(int) = toupper;
    short *p = (short *)s + 2;

    b = s + 2;
    strcpy(b, "C S 429 Exam");
    printf("s = %s\n", s); // Line 0
    *i = (unsigned int)*(s + 8);
    printf("%04x %s\n", *i, (char *)i);             // Line 1
    printf("%hu\n", (unsigned short)(p[0] + p[1])); // Line 2
    strncpy(b - 1, "ello", 4);
    char *q = s;
    for (int j = 0; j < 16; j += 3, q += 3) {
        s[j] = f((int)*q);
    }
    printf("%s\n", s); // Line 3
    return 0;
}