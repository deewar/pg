
#ifndef GAME_H
#define GAME_H

struct Node{
  int id;
  int priority;
  int owner;
  int* succ;
  char* name; //optional

};


struct Game{
  int maxId;
  struct Node* nodes;
};


#endif
