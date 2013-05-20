#ifndef SOLVER_H
#define SOLVER_H

#include <stdio.h>
#include "game.h"
#include <stdlib.h>





void psol(Game *game);
void psolb(Game *game);

void generateAttractors(Game *game , Node *node, int player,  List *list);

/*Todo move list stuff out and refacter parser to use list instead of keeping counts everywhere*/

#endif
