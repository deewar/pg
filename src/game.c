

#include "game.h"




inline Node * getNodeById(Game *game,int id){
  int index =  game->lookup[id].index;
  return game->nodes[index];
}
