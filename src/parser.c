#include "parser.h"




struct Game* loadGame(  const char *filename ){
  size_t lineSize = 1000;
  char* line = (char*) malloc( sizeof(char)*lineSize);
  FILE *file=  fopen ( filename, "r" );
  if ( file == NULL){
    puts("input file could not be opened");
    exit(-1);
  }
  
  Game *game = (Game *) malloc( sizeof(Game));
  game->sortedNodes = NULL;
  game->w0Size = 5; //initially 5 nodes
  game->w0Count = 0;
  game->w0 = malloc(sizeof (int)*game->w0Size);
  game->w1Size = 5; //initially 5 nodes
  game->w1Count = 0;
  game->w1 = malloc(sizeof (int)*game->w1Size);

  int gameSize = 200;
  if (file != NULL){
    getline(&line,&lineSize,file);
    if ( strstr(line,"parity") == NULL){
      //file does not have max parity;
      printf("Max nodes must be specified or this file does not have the correct format\n");
      exit(-1);
      rewind(file);
    }else{ 
      char *tok = strtok(line," ");
      tok = strtok(NULL," ");
      gameSize = game->maxIndex = atoi(tok);
    }
    
    game->nodes = (Node **) malloc(sizeof(Node *) *gameSize);

    game->nodeCount = 0;
    //allocate memory to create the lookup
    game->lookupSize = gameSize;
    game->lookup = (NodeLookup *) malloc(sizeof(NodeLookup)*game->lookupSize);
									
    while ( getline(&line,&lineSize,file) >= 0 ){
      char* tmp = (char *) malloc(sizeof(char)*lineSize);

      //copying required cause of segfaults when closing the files/
      memcpy(tmp ,line,lineSize);

      Node *node = parseNode(line);
      free(tmp);
      //printf("node %d parsed \n", game->nodeCount);    
            
      int nodeCount = game->nodeCount;
      
      if ( nodeCount >= gameSize ){
	//more nodes than anticipated.. 
	gameSize = game->maxIndex = gameSize *2;
	game->nodes = (Node **) realloc(game->nodes,sizeof(Node *) * gameSize);
      }
      game->nodes[nodeCount] = node;
      

      while (  game->lookupSize <= node->id ){
	//need a bigger lookup double the size
	//printf("reloccing for %d  %p \n",node->id, game->lookup);
	game->lookupSize *= 2;
	NodeLookup *tmp = (NodeLookup *) realloc(game->lookup,sizeof(NodeLookup)*game->lookupSize);
	game->lookup = tmp;
      }
      game->lookup[node->id].index = game->nodeCount;

      game->nodeCount++;
    }
				   
  }
  sortNodes(game);
  generatePredecessors(game);
  fclose(file);
  free( line);
  return game;
}



struct Node* parseNode(char* line){
  //remove semi colon from the end.
  int i ;
  for ( i = strlen(line)-1; line[i] != ';'; i--);
  line[i] ='\0';
  struct Node* node = (struct Node*) malloc(sizeof(Node));
  node->succCount = 0;
  node->predSize = 0;
  node->predCount = 0;
  node->succ = NULL;
  node->name = NULL;
  node->pred = NULL;

  
  char *begin = strchr(line,'"');
  char *end = strrchr(line,'"');
  int strSize = end-begin;
  
  if ( begin != NULL && strSize > 0 ){
    
    node->name = (char *) malloc(sizeof(char)*(strSize));
      
    //copy name across;
    int i;
    for ( i = 0 ; i < strSize-1; i++){
      (node->name)[i]= begin[i+1];
    }
    (node->name)[strSize-1]= '\0';
  }


  char* token = strtok(line, " ");
  //parse the id;
  node->id = atoi(token);
  token=strtok(NULL," ");
  //parse the priority
  node->priority = atoi(token);
  token=strtok(NULL," ");
  //parse the owner
  node->owner = atoi(token);
  if( node->owner != 1 && node->owner != 0 ){
    printf("invalid owner for node id %d", node->id);
    exit(-1);    
  }
  
  
  //parse successors
  
  int size = 4;
  int *succ = (int*) malloc(sizeof(int)*size);
  node->succCount = 0;
  while((token=strtok(NULL,","))){
    //if identifier prensent handle it
    if (strchr(token,'"')!= NULL){
      //the last succ
      char* rest = NULL;
      token  = strtok_r(token," ",&rest);
      //token still contains the last id
      
    }
    
    if( node->succCount >= size ){
      size =2*size;
      
       succ = realloc(succ, sizeof(int)*size);
      
      if (succ == NULL){
	printf("realloc failed");
	exit(-1);
      }
    }
    
    succ[node->succCount] =atoi(token);
    
    node->succCount++;
  }
  node->succ = succ;
  

  //printf("nodeid %d , node succount %d\n", node->id , node->succCount);
  //printf("%d\n",succ[node->succCount-1]);
  //printf("%d\n",*(node->succ)[node->succCount-1]);


   
  return node;
}


//prints the game in sorted order
void printGame(Game* game){
  printf( "parity %d\n" , game->maxIndex);
  int curr = 0;
  for( ; curr < game->nodeCount; curr ++){
    int n = game->sortedNodes[curr].nodeId;
    n = game->lookup[n].index;
    Node* node = game->nodes[n];
    printf("ID:=%d PRI:=%d OWNER:=%d ", node->id, node->priority,node->owner);
    int i = 0;
    printf(" SUCC: ");
    while( i < node->succCount){
      printf("%d ",(node->succ)[i]);
      i++;
    }
    
    printf(" PRED: ");
    i = 0;
    while ( i < node->predCount){
      printf("%d ", (node->pred)[i]);
      i++;
    }
    if ( node->name != NULL){
      printf("NAME:= \"%s\";",node->name);
    }
    puts("");
  }
  
}




void printSortNodes( SortNode *s,  int count){
  int i;
  for ( i = 0 ; i < count ; i++){
    printf("Node id :%d , priority %d \n" , s[i].nodeId, s[i].priority);
  }
}


static int compareNode(const void *node1, const void *node2){
  SortNode * n1 = (SortNode *) node1;
  SortNode * n2 = (SortNode *) node2;
  //printf("comparing %d , %d \n" ,n1->nodeId ,  n2->nodeId);
  return n2->priority -  n1->priority;
}



void sortNodes(Game *game){
   
  int i;
  SortNode *sortedNodes = malloc(sizeof(SortNode)*game->nodeCount);
  for ( i = 0; i < game->nodeCount; i++){
    sortedNodes[i].nodeId = game->nodes[i]->id;
    sortedNodes[i].priority = game->nodes[i]->priority;    
  }

  game->sortedNodes= sortedNodes;
  //printSortNodes(sortedNodes,game->nodeCount);

  qsort(sortedNodes,game->nodeCount,sizeof(SortNode),compareNode);  
  //printSortNodes(game->sortedNodes, game->nodeCount);
  
}



void generatePredecessors( Game *game){
  
  int i;
  for (i = 0; i < game->nodeCount ; i ++ ){
    int j;
    Node *node = game->nodes[i];
    //node->predSize = 0;
    //node->pred = NULL;
    //printf("%d\n", node->id);
    for ( j = 0 ; j < node->succCount; j ++){
      int succId = (node->succ)[j];
      //find the successor from the lookup
      int succ = game->lookup[succId].index;
      Node *n = game->nodes[succ];
      addPred(n,node->id);
    }
  }
   
}


void addPred(Node *node , int id){
  
  if ( node->predSize == 0){

    node->predSize = 4;
    node->pred = (int *) malloc(sizeof(int)*node->predSize);
  }
  
  if( node->predCount >= node->predSize){
    node->predSize = node->predSize*2;
    node->pred = realloc(node->pred,sizeof(int)*node->predSize);
  }

  //check if link already present
  int i ;
  for ( i = 0 ; i < node->predCount ; i++){
    if ( (node->pred)[i] == id){
      return;
    }
  }
  
  (node->pred)[node->predCount] = id;
  node->predCount ++;
  

}

void deleteNodes(Node ** nodes, int count){
  int i ;
  for ( i = 0 ; i < count;  i++){
    Node *node = nodes[i];
    if (node->succ!= NULL){
      /*if( *node->succ != NULL){
	free(*node->succ);
      }*/
      free(node->succ);
      node->succ = NULL;
    }
    if ( node ->name != NULL){
      /*if ( *node->name !=NULL){
	free(*node->name);
	}*/
      free(node->name);
      node->name = NULL;
    }
    if ( node ->pred != NULL){
      /*if ( *node->pred != NULL){
	free(*node->pred);
	}*/
      free(node->pred);
      node->pred = NULL;
    }

    if ( node != NULL){
      free(node);
    }
  }

  *nodes = NULL;
}



void deleteGame(Game *game){
  
  if (game->sortedNodes!= NULL){
    free(game->sortedNodes);
  }
  
  if ( game->lookup != NULL){
    free(game->lookup);
  }
  
  deleteNodes(game->nodes,game->nodeCount);
  
  free(game->nodes);
  free(game->w0);
  free(game->w1);
  free(game);
}
