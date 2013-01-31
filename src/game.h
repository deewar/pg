
#ifndef GAME_H
#define GAME_H

typedef struct Node{
  int id;
  int priority;
  int owner;
  int succCount;
  int* succ;
  char* name; //optional

}Node;


typedef struct Game {
  int maxIndex;
  int nodeCount;
  struct Node** nodes;
}Game;
#endif
