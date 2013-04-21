#include "solver.h"
#include "list.h"


void generateAttractors(Game *game , int k ){
  List *nodesToTest  = initList();//the nodes which need to be tested
  List *attractorSet = initList(); // the nodes which belong to the attractor.

  Node *node = game->nodes[k];
  /*
    for each node in preds check if any node points to this node.
   */
  int i ;
  for ( i = 0 ; i < node->predCount ; i ++){
    addToList(nodesToTest,node->pred[i]);
  }
  
  while( nodesToTest->count > 0){
    int nodeId = popFromList(nodesToTest);
    //printf("%d was found \n" , nodeId);
    // node was deleted at some point .. dont need to care about it.
    Node *curr;
    if ((curr = game->nodes[nodeId] )== NULL) continue;
    
    //continue processing node
    if ( curr->owner == node->owner){
      //owner is the same atleast one edge must go to the attractor set.
      /* Confirm priority bit*/
      

    }else{
      //all edges must go to the attractor set.
    }
   
    
  }
  
}



