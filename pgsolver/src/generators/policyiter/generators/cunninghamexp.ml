open Paritygame;;
open Stratimprgenerators;;
open Mdp;;

type gamenode =
	FinalCycle
|	CycleExit of int
|	UpperSelector of int
|	CycleSelector of int
|	CycleCenter of int
|	CycleNodePaced of int
|	CycleNodeLaned of int
|	CycleEntry of int
|	PacerNode of int
|	PacerEntry of int

let symb_to_str = function 
|	CycleCenter i -> "e" ^ string_of_int i
|	CycleNodePaced i -> "d" ^ string_of_int i
|	CycleNodeLaned i -> "b" ^ string_of_int i
|	PacerNode i -> "a" ^ string_of_int i
|	CycleExit i -> "y" ^ string_of_int i
|	CycleEntry i -> "q" ^ string_of_int i
|	CycleSelector i -> "u" ^ string_of_int i
|	UpperSelector i -> "w" ^ string_of_int i
|	FinalCycle -> "t"
|	PacerEntry i -> "p" ^ string_of_int i

let generator_game_func arguments =

    if (Array.length arguments != 1) then (failwith "specify index of game");
	let n = int_of_string arguments.(0) in

	let pg = SymbolicParityGame.create_new FinalCycle in

	let addnode sy pr pl li = SymbolicParityGame.add_node pg sy pr pl (Array.of_list li) (Some (symb_to_str sy)) in
	
	addnode FinalCycle 1 1 [FinalCycle];
	addnode (UpperSelector n) 3 1 [FinalCycle];
	addnode (PacerEntry 0) 8 1 [PacerNode (n-1)];
	addnode (PacerEntry 1) 3 1 [PacerEntry 0];
	
	for i = 0 to n - 1 do
		addnode (CycleExit i) (2 * i + 10) 1 [UpperSelector (i + 1)];
		addnode (CycleEntry i) (2 * i + 9) 1 [CycleCenter i];
		addnode (CycleCenter i) 6 1 (if i > 0 then [CycleNodePaced i; CycleNodeLaned i; CycleExit i] else [CycleNodePaced i; CycleExit i]);
		addnode (CycleCenter (i+n)) 5 1 [CycleCenter i];
		if i < n - 1 then addnode (UpperSelector (i+1)) 3 0 [UpperSelector (i+2); CycleEntry (i+1)];
		if i < n - 1 then addnode (CycleSelector (i+1)) 3 0 (if i > 0 then [CycleSelector i; CycleEntry i] else [CycleEntry 0]);
		addnode (PacerNode i) 3 0 (if i = 0 then [CycleEntry 0] else [PacerNode (i-1); CycleEntry i]);
		addnode (CycleNodePaced i) 5 0 [(if i = 0 then PacerEntry 1 else PacerEntry 0); CycleCenter i];
		if i > 0 then addnode (CycleNodeLaned i) 5 0 [CycleSelector i; CycleCenter (i+n)];
	done;

	SymbolicParityGame.to_paritygame pg;;

let generator_mdp_func arguments =
	let game = generator_game_func arguments in
	parity_game_to_generalized_mdp game 8 (fun _ j -> pg_get_pr game j >= 8);;

register_strat_impr_gen {
	ident = "cunninghamexp";
	description = "Binary-Case Exponential Lower Bound for Cunningham's rule";
	parity_game = Some generator_game_func;
	generalized_mdp = Some generator_mdp_func;
}
