#include <stdio.h>
#include <stdlib.h>
#include "math.h"

void heavy_task(int task_num) {
    printf("tSequential task #%d startedn", task_num);

    int result = 0;

    for (int i = 0; i < 1e8; i++) {
        sqrt(i);
    }

    printf("\tSequential task #%d finishedn", task_num);
}


void loop(int sequence_len) {
    for (int i = 0; i < sequence_len; i++) {
        printf("MAIN starting sequential task %dn", i);
        heavy_task(i);
    }
}

int main(int argc, char** argv) {
    int threads_num = atoi(argv[1]);
    loop(threads_num);

    return 0;
}