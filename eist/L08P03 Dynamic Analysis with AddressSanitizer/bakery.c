#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct bakery {
  char *name;
  char *pastries;
};

struct bakery *bakeries;

int main(int argc, char **argv) {
  /*
   * Do not change anything in the switch
   */
  int count = 0;
  switch (argc) {
  case 2:
    if (strcmp(argv[1], "-h") == 0) {
      printf(
          "This program accepts the following arguments:\n-h\n-c <count> "
          "<bakery names separated by space> <pastries separated by space>\n");
    }
    return 0;
  case 5:
    if (strcmp(argv[1], "-c") == 0 && sscanf(argv[2], "%i", &count) == 1) {
      printf("%i Bakeries are created\n", count);
    }
    break;
  default:
    return EXIT_FAILURE;
  }

  char *s;

  // Read the given names argument
  char **names = malloc(count * sizeof(char *));
  s = strtok(argv[3], " ");
  int k = 0;
  while (s != NULL) {
    if (k >= count) {
      printf("Too many names have been given\n");
      free(names);
      return EXIT_FAILURE;
    }
    names[k] = s;
    s = strtok(NULL, " ");
    k++;
  }
  if (k != count) {
    printf("Not enough names have been given\n");
    free(names);
    return EXIT_FAILURE;
  }

  // Read the given pastry argument
  char **pastries = malloc(count * sizeof(char *));
  s = strtok(argv[4], " ");
  k = 0;
  while (s != NULL) {
    if (k >= count) {
      printf("Too many pastries have been given\n");
      free(names);
      free(pastries);
      return EXIT_FAILURE;
    }
    pastries[k] = s;
    s = strtok(NULL, " ");
    k++;
  }
  if (k != count) {
    printf("Not enough pastries have been given\n");
    free(names);
    free(pastries);
    return EXIT_FAILURE;
  }

  bakeries = malloc(count * sizeof(struct bakery));

  struct bakery *tempBakery;

  // Run count times to initialize the structs
  for (int i = 0; i < count; i++) {
    tempBakery = malloc(sizeof(struct bakery));
    tempBakery->name = names[i];
    tempBakery->pastries = pastries[i];
    printf("Creating Bakery %s with pastries %s\n", tempBakery->name,
           tempBakery->pastries);
    bakeries[i] = *tempBakery;
    free(tempBakery);
  }

  free(names);
  free(pastries);
  free(bakeries);
  return EXIT_SUCCESS;
}
