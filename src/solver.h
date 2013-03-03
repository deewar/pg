#ifndef SOLVER_H
#define SOLVER_H

#include <stdio.h>
#include "game.h"
void psol(Game *game);
void psolb(Game *game);

void generateAttractors(Game *game , int n);
#endif
