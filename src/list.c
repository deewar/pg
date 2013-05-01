#include "list.h"
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
//does not check count..
inline int popFromList(List *list){
  list->count--;
  return list->items[list->count];
}


inline void clearList(List *list){
  list->count = 0;
}

inline void resizeList(List *list, int size){
  list->size = size;
  if (list-> count > size ) list->count = list->size;
  list->items = realloc(list->items, sizeof(int)* size);
  if (list->items == NULL){
    puts("failed to increase size of list.. :(");
    exit (-1);
  }
}

inline bool  listContains(List *list , int value){
  int i;
  for ( i = 0; i < list->count; i++){
    if ( list->items[i] == value){
      return true;
    }
  }
  return false;
}


inline void  printList(List *list){
  int i;
  for ( i = 0; i < list->count; i++){
    printf("%d " , list->items[i]);
  }
  puts("");
}


inline void addToList( List *list ,int value ){
  addToList_u(list,value,false);
}


inline void addToList_u( List *list , int value , int unique){
  

  if ( list->count >= list->size) resizeList(list, list->size *2);


  if ( unique ){
    int i ;
    for ( i = 0 ; i< list->count; i++){
      if ( list->items[i] == value)
	return;
    }
  }


  list->items[list->count] = value;
  list->count ++; 
}


inline void compressList(List * list){
  list->items = realloc(list->items, sizeof(int)* list->count);
  if (list->items == NULL){
    puts("error squeezing list");
    exit(-1);
  }
  list->size = list->count;
}



inline List* initList(){
  List* list = malloc(sizeof(List));
  list->size = 5;
  list->count = 0;
  list->items = malloc(sizeof(int) * 5);
  return list;
}



inline void deleteList(List **list){
  List *l = *list;
  free( l->items);
  free(l);
  *list =NULL;
}
