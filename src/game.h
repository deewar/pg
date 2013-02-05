
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



typedef struct SortNode{
  int nodeId;
  int priority;  
}SortNode;


typedef struct Game {
  int maxIndex;
  int nodeCount;
  SortNode *sortedNodes;
  struct Node** nodes;
}Game;




#endif

