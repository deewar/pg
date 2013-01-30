#include "parser.h"




struct Game* loadGame(  const char *filename ){
  size_t lineSize = 1000;
  char* line = (char*) malloc( sizeof(char)*lineSize);
  FILE *file = fopen ( filename, "r" );
  

  if (file != NULL){
    int cread;
    cread = getline(&line,&lineSize,file);
    while (cread >= 0 ){
      printf("%s",line);
      cread = getline(&line,&lineSize,file);
    }
				   
  }

}



struct Node* parseNode( const char* line){
  
}


