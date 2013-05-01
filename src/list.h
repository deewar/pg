#ifndef LIST_H
#define LIST_H

#include <stdbool.h>

typedef struct List{
  int size;
  int count;
  int *items;
}List;


inline List* initList();
inline void  addToList_u( List *list , int value , int unique);
inline void  addToList( List *list , int value);
inline int   popFromList(List *list);
inline void deleteList(List **list);
inline void compressList(List *list);
inline bool listContains(List * list, int value);
inline void printList(List * list);
inline void resizeList(List *list, int size);
inline void clearList(List *list);


#endif
