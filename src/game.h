
#ifndef GAME_H
#define GAME_H

typedef struct Node{
  int id;
  int priority;
  int owner;
  int succCount;
  int predSize;
  int predCount;
  int **succ;
  char**name; //optional
  int **pred; 
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
  int *w0;
  int w0Count;
  int w0Size;
  int *w1;
  int w1Count;
  int w1Size;
}Game;




#endif

