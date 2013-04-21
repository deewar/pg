#ifndef LIST_H
#define LIST_H



typedef struct List{
  int size;
  int count;
  int *items;
}List;


inline List* initList();
inline void  addToList( List *list , int value);
inline int   popFromList(List *list);




#endif
