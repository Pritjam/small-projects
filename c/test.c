#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <stdbool.h>

int main(int argc, char const *argv[]) {
    int a = 5;
    int b = a;
    a = 6;
    printf("%d", b);
}