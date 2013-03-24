#include "parser.h"

int main (int argc, char *argv[]){
  char *name = "games/game";
  if  ( argc >= 2 ){
    name = argv[1];
  }
  Game* g = loadGame(name);
  printGame(g);
  deleteGame(g);
  return 0;
}
