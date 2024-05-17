#include <stdio.h>
#include <stdlib.h>
#include "math.h"

void *heavy_task() {
    for (int i = 0; i < 1e8; i++) {
        sqrt(i);
    }
}

void openmp(int thread_num) {
#pragma omp parallel for num_threads(thread_num)
    for (int i=0; i < thread_num; i++){
        heavy_task();
    }
}


void loop(int sequence_len) {
    for (int i = 0; i < sequence_len; i++) {
        printf("MAIN starting sequential task %dn", i);
        heavy_task();
    }
}

int main(int argc, char** argv) {

    int threads_num = atoi(argv[1]);
    openmp(threads_num);

    return 0;
}