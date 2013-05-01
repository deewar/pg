#include "solver.h"
#include "list.h"
#include "game.h"




void psol(Game *game){
  int i ;
  List* attractorSet = initList();
  for ( i = 0; i < game->nodeCount; i ++){
    clearList(attractorSet);
    int nodeId = game->sortedNodes[i].nodeId;
  
    Node *node = getNodeById(game,nodeId);
    if (node == NULL) continue;
    int player = (node->priority)%2;


    generateAttractors(game,node,player,attractorSet);
    if (listContains(attractorSet,nodeId)){
      puts("fatal attractor found");
      printList(attractorSet);
      continue;
    }

    //remove edges
  }
}




void generateAtractorsForAttractors(Game *game, List *attractors , int player){
  List *nodesToTest  = initList();//the nodes which need to be tested
  List *nodesForNextIteration = initList();
  List *nodesChecked =  initList();
  
  //populate nodes to check
  int i;
  for(   i = 0 ; i <  attractors->count; i++){
    int id = attractors->items[i]; 
    Node *node = getNodeById(game,id);
    if (node == NULL) continue;

    int j ;

    for ( j = 0 ; j < node->pred->count ;  j ++){
      addToList(nodesToTest,node->pred->items[j]);
    }
  }

  resizeList(nodesForNextIteration,nodesToTest->size);


  bool attractorSetModified;
  
  do {
    attractorSetModified = false;
    
    int size = nodesToTest->count;
    
    for( i = 0 ; i < size ; i ++){
      
    }

  }while(attractorSetModified);
  
}





void  generateAttractors(Game *game , Node  *node , int winner  , List *attractorSet){
  List *nodesToTest  = initList();//the nodes which need to be tested
  List *nodesForNextIteration = initList();
  List *nodesChecked =  initList();
  int nodeId = node->id;
  int i ;
  for ( i = 0 ; i < node->pred->count ; i ++){
    addToList(nodesToTest,node->pred->items[i]); //test nodes that can reach k
  }
  
  resizeList(nodesForNextIteration,nodesToTest->size);
  
  bool  attractorSetModified;
  do{
    attractorSetModified = false;
    //    printList(nodesToTest);
    int k;
    int size = nodesToTest->count;
    for( k = 0 ; k < size ;  k ++){
      int currNodeId = popFromList(nodesToTest);
      //nodesToTest->count --;
      addToList(nodesChecked,currNodeId);
      //printf("%d was found \n" , currNodeId);
      // node was deleted at some point .. dont need to care about it.
      Node *curr;
      if ((curr = getNodeById(game,currNodeId) )== NULL) continue;
      //already removed from game.

      if( curr->priority >  node->priority ) continue;
      //this node cannot be added to the attractor since its prioirity is too damn high

      if ( curr->owner == winner){
	//owner is the same atleast one edge must go to the attractor set.
	addToList(attractorSet , currNodeId);
	int j;
	for ( j = 0 ; j < curr->pred->count; j++ ){
	  if (!listContains(nodesChecked,curr->pred->items[j]) &&
	      !listContains(attractorSet,curr->pred->items[j])){
	    addToList(nodesForNextIteration,curr->pred->items[j]);
	  }
	}
	
	attractorSetModified = true;
      }else{
	//all edges must go to the attractor set.
	int i;
	bool allLinks = true;
	for (  i  = 0 ; i < curr->succ->count ; i++){
	  int id = curr->succ->items[i];
	  if ( !(listContains( attractorSet , id) || id == nodeId)) {
	    //atleast one link not into attractor set.. ignore this for now maybe later it can be added.
	    allLinks = false;
	    break;
	  } 
	}
      
	
	if (allLinks) {
	  addToList(attractorSet , currNodeId);
	  int j;
	  for ( j = 0 ; j < curr->pred->count; j++ ){
	    if (!listContains(nodesChecked,curr->pred->items[j]) &&
		!listContains(attractorSet,curr->pred->items[j])){
	      addToList(nodesForNextIteration,curr->pred->items[j]);
	    }
	  }

	  attractorSetModified = true;
	}else{
	  addToList(nodesForNextIteration,currNodeId);
	}
      }
   
    }
    
    //List *tmp = nodesToTest;
    nodesToTest = nodesForNextIteration;
    nodesForNextIteration = initList();// tmp;
    clearList(nodesForNextIteration);


  }while(attractorSetModified);



  deleteList(&nodesToTest);
  deleteList(&nodesForNextIteration);
  deleteList(&nodesChecked);
  
}



