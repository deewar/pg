#include "parser.h"




struct Game* loadGame(  const char *filename ){
  size_t lineSize = 1000;
  char* line = (char*) malloc( sizeof(char)*lineSize);
  FILE *file = fopen ( filename, "r" );
  
  Game *game = (Game *) malloc( sizeof(Game));

  int gameSize = 200;
  if (file != NULL){
    getline(&line,&lineSize,file);
    if ( strstr(line,"parity") == NULL){
      //file does not have max parity;
      printf("Max nodes must be specified\n");
      exit(-1);
      rewind(file);
    }else{ 
      char *tok = strtok(line," ");
      tok = strtok(NULL," ");
      gameSize = game->maxIndex = atoi(tok);
    }
    
    game->nodes = (Node **) malloc(sizeof(Node *)*gameSize);
 
    while ( getline(&line,&lineSize,file) >= 0 ){
      char* tmp = (char *) malloc(sizeof(char)*lineSize);

      //copying required cause of segfaults when closing the files/
      memcpy(tmp ,line,lineSize);

      Node *node = parseNode(line);

          
      game->nodes[node->id] = node;  
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

  struct Node* node = (struct Node*) malloc(sizeof(Node));
  
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
  
  int size = 1;
  int *succ = (int*) malloc(sizeof(int)*size);
  node->succCount = 0;
  while((token=strtok(NULL," "))){
    //check for identifier
    if (strchr(token,'"')!= NULL){
      break;
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
  node->succ = (int **) malloc(sizeof(int **));
  *(node->succ) = succ;
  //printf("%d",succ[node->succCount-1]);
  //printf("%p %p \n", &succ[0] , &(*(node->succ)[0]));
  //printf("%d",*(node->succ)[node->succCount-1]);

  //identifier present
   if( token != NULL){
    size = strlen(token)-4;
    node->name = malloc(sizeof(char**));
    *(node->name) = (char *) malloc(sizeof(char)*size);
    memcpy(*(node->name),token+1,size);
    
  }else{
    node->name= NULL;
  }
  
  return node;
}


//prints the game in sorted order
void printGame(Game* game){
  printf( "parity %d\n" , game->maxIndex);
  int curr = 0;
  for( ; curr < game->nodeCount; curr ++){
    int n = game->sortedNodes[curr].nodeId;
    Node* node = game->nodes[n];
    printf("%d %d %d ", node->id, node->priority,node->owner);
    int i = 0;
    
    while( i < node->succCount){
      printf("%d ",(*(node->succ))[i]);
      i++;
    }
    
    printf(" pred: ");
    i = 0;
    while ( i < node->predCount){
      printf("%d ", (*(node->pred))[i]);
      i++;
    }
    if ( node->name != NULL){
      printf("name \"%s\";",*(node->name));
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
    //printf ("node %d has successors" , node->id);
    for ( j = 0 ; j < node->succCount; j ++){
      int succ = (*(node->succ))[j];
      addPred(game->nodes[succ],node->id);
    }
  }
  
}


void addPred(Node *node , int id){


  if ( node->predSize == 0){

    node->predSize = 4;
    node->pred = malloc(sizeof(int**));
    *(node->pred) = (int *) malloc(sizeof(int)*node->predSize);
  }
  
  if( node->predCount >= node->predSize){
    node->predSize = node->predSize*2;
    *(node->pred) = realloc(*(node->pred),sizeof(int)*node->predSize);
  }

  //check if link already present
  int i ;
  for ( i = 0 ; i < node->predCount ; i++){
    if ( (*(node->pred))[i] == id){
      return;
    }
  }
  
  (*(node->pred))[node->predCount] = id;
  node->predCount ++;
  

}
