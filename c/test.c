#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <stdbool.h>

int func4(int param_1,int param_2,int param_3)

{
  int iVar1;
  int iVar2;
  
  iVar2 = (param_3 - (int)param_2) / 2 + (int)param_2;
  if (param_1 < iVar2) {
    iVar1 = func4(param_1,param_2,iVar2 + -1);
    iVar1 = iVar1 * 2;
  }
  else {
    iVar1 = 0;
    if (iVar2 < param_1) {
      iVar2 = func4(param_1,iVar2 + 1);
      iVar1 = iVar2 * 2 + 1;
    }
  }
  return iVar1;
}


int main(int argc, char const *argv[]) {
    int int_one = 3;
    printf("result of func4(%d, 0, 0xe): %d\n", int_one, func4(int_one, 0, 0xe));
}