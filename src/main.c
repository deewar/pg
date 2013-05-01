#include "parser.h"
#include "solver.h"

int main (int argc, char *argv[]){
  char *name = "games/game";
  if  ( argc >= 2 ){
    name = argv[1];
  }
  Game* g = loadGame(name);
  puts("attractors");
  psol(g);
  
  printGame(g);
  deleteGame(g);
  return 0;
}
