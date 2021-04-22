#include "node.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    
}
void switcher(long a, long b, long c, long *dest)
{
    long val;
    switch (a)
    {
    case 5: /* Case A */
        c = 15 ^ a;
    /* Fall through */
    case 0: /* Case B */
        val = c + 112;
        break;
    case 2: /* Case C */
    case 7: /* Case D */
        val = (c + a) << 2;
        break;
    case 4: /* Case E */
        val = b;
        break;
    default:
        val = a;
    }
    *dest = val;
}
