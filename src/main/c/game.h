
#ifndef GAME_H
#define GAME_H
#include "list.h"





typedef struct Node{
  int id;
  int priority;
  int owner;
  List *succ;
  char*name; //optional
  List *pred; 
}Node;



typedef struct SortNode{
  int nodeId;
  int priority;  
}SortNode;


typedef struct NodeLookup{
  int index;
}NodeLookup;

typedef struct Game {
  int maxIndex ;
  int nodeCount;
  SortNode *sortedNodes;
  int lookupSize;
  Node **nodes;
  NodeLookup *lookup;
  List *w0;
  List *w1;
}Game;


inline Node * getNodeById(Game *game,int id);


inline void removeFromGame(Game *game,List *l);





#endif

