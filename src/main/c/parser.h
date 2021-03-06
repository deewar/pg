
#ifndef PARSER_H
#define PARSER_H

#include <stdio.h>
#include <string.h>
#include "game.h"
#include <stdlib.h>
#include <ctype.h>

struct Node* parseNode(  char* line);
struct Game* loadGame(  const char *filename );

void printGame(Game *game);
void sortNodes(Game *game);
void generatePredecessors( Game *game);
void addPred(Node *node, int id);
void deleteNodes(Node **nodes, int count);
void deleteGame(Game *game);
#endif
