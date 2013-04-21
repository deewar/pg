#include "list.h"
#include <stdlib.h>


//does not check count..
inline int popFromList(List *list){
  list->count --;
  return list->items[list->count];
}


inline void addToList( List *list , int value){
  if ( list->count >= list->size){
    list->size = list->size * 2;
    //printf ("resizing list to size %d", list->size);
    list->items = realloc ( list->items , sizeof ( int) * list->size);
  }

  list->items[list->count] = value;
  list->count ++; 
}


inline List* initList(){
  List* list = malloc(sizeof(List));
  list->size = 5;
  list->count = 0;
  list->items = malloc(sizeof(int) * 5);
  return list;
}



