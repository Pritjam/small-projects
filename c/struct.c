#include "node.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

// static node_t *build_leaf(int value) {
//     nptr_t returnNode = malloc(sizeof(node_t));
//     int arr[16] = calloc(16, sizeof(int));
//     int arrTwo[32] = realloc(arr, 32);

//     int myInt = 5;
//     returnNode->tok = 5;
//     returnNode->node_type = NT_LEAF;
//     returnNode->val.ival = value;
//     returnNode->type = 2;


//     return returnNode;
// }

int main(int argc, char const *argv[])
{
    node_t **entries = calloc(100, sizeof(node_t *));
    printf("2nd element: %b", entries[1] == NULL);
    return 0;
}