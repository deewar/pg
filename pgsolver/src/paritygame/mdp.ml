open Tcsmaths;;
open Tcsstrings;;
open Paritygame;;

type generalized_mdp_node = Controller of int array
                 		  | Randomizer of (BigFloat.t * int) array
                 		  | Reward of (BigFloat.t * int)
                 		  | Sink
                 
type generalized_mdp = generalized_mdp_node array

let parity_game_to_generalized_mdp pg min_even_prio is_epsilon =
	let n = Array.length pg in
	let real_n = ref 0 in
	let s = ref (-1) in
	let p = ref 0 in
	let mp = ref 0 in
	Array.iteri (fun i (pr,_,tr,_) ->
		mp := max !mp pr;
		if pr >= min_even_prio then incr real_n;
		if pr = 1 then s := i
		else if Array.length tr > 1 && pr >= min_even_prio
		then p := !p + Array.length tr;
	) pg;
	let epsilon = BigFloat.of_big_ints BigInt.one (BigInt.int_power_int !real_n (!mp-min_even_prio+2+1)) in
	let mdp = Array.make (n + !p) Sink in
	let q = ref n in
	for i = 0 to n - 1 do
		let (pr,pl,tr,_) = pg.(i) in
		if pr = 1 then (
			mdp.(i) <- Sink;
		)
		else if Array.length tr = 1 then
			mdp.(i) <- if pr >= min_even_prio
			           then Reward (BigFloat.of_big_int (BigInt.int_power_int (- !real_n) (pr - min_even_prio + 2)), tr.(0))
			           else Reward (BigFloat.of_int 0, tr.(0))
		else (
			let b = if pr >= min_even_prio then !q else 0 in
			if pr >= min_even_prio then (
				Array.iter (fun j ->
					mdp.(!q) <- Reward (BigFloat.of_big_int (BigInt.int_power_int (- !real_n) (pr - min_even_prio + 2)), j);
					incr q
				) tr
			);
			if pl = 0 then (
				mdp.(i) <- Controller (Array.mapi (fun l j -> (if b=0 then j else l + b)) tr)
			)
			else (
				mdp.(i) <- Randomizer (Array.mapi (fun l j ->
					if is_epsilon i j then (epsilon, (if b=0 then j else l + b))
					else (BigFloat.div (BigFloat.sub BigFloat.one epsilon) (BigFloat.of_int (Array.length tr - 1)), (if b=0 then j else l + b))
				) tr)
			)
		)
	done;
	mdp




type mdp = ((BigFloat.t * BigFloat.t * int) array) array array

let generalized_mdp_to_mdp mdp =
	let get_controller_actions i =
		let rec get_action i =
			match mdp.(i) with
				Controller succs -> [|(BigFloat.zero, BigFloat.one, i)|]
			|	Sink -> [|(BigFloat.zero, BigFloat.one, -1)|]
			|	Reward (a, j) -> Array.map (fun (rew, probab, k) -> (BigFloat.add a rew, probab, k)) (get_action j)
			|	Randomizer succs ->
					Array.of_list (
						List.flatten (
							List.map
								(fun (probab, j) ->
									Array.to_list (Array.map (fun (rew, pro, k) -> (rew, BigFloat.mult pro probab, k)) (get_action j))
								)
								(Array.to_list succs)
						)
					)
		in
		match mdp.(i) with
			Controller succs -> List.map get_action (Array.to_list succs)
		|	_ -> []
	in
	let n = Array.length mdp in
	let controllers = ref [] in
	Array.iteri (fun i x ->
		match x with Controller _ -> controllers := i::!controllers
		| _ -> ()
	) mdp;
	let m = List.length !controllers in
	let x = Array.make n (-1) in
	let y = Array.make m (-1) in
	Array.iteri (fun i j ->
		y.(i) <- j;
		x.(j) <- i
	) (Array.of_list (List.rev !controllers));
	Array.init m (fun i -> Array.of_list (
		List.map (Array.map (fun (rew, pro, k) -> (rew, pro, (if k<0 then k else x.(k))))
				)
			(get_controller_actions y.(i)))
	)
	
let print_mdp =
	Array.iteri (fun i actions ->
		print_string (string_of_int i ^ " ---->\n");
		Array.iter (fun action ->
			print_string ("  Action: \n");
			Array.iter (fun (rew, pro, k) ->
				print_string("       to " ^ string_of_int k ^ " with r=" ^ BigFloat.format_fraction rew ^ " and p=" ^ BigFloat.format_fraction pro ^ "\n");
			) action;
		) actions
	)



type lp_objective = Maximize | Minimize

type lp_constraint_type = LPEq | LPGeq

type lp = lp_objective * (BigFloat.t array) * (BigFloat.t array * BigFloat.t * lp_constraint_type) array


let unichain_mdp_to_primal_lp mdp =
	let m = Array.length mdp in
	let n = ref 0 in
	let act_base = Array.make m 0 in
	Array.iteri (fun i actions ->
		act_base.(i) <- !n;
		n := !n + Array.length actions
	) mdp;
	let n = !n in
	let constr_base = Array.init m (fun i ->
		let vars = Array.make n BigFloat.zero in
		Array.iteri (fun j _ ->
			vars.(j + act_base.(i)) <- BigFloat.add vars.(j + act_base.(i)) BigFloat.one
		) mdp.(i);
		Array.iteri (fun l ->
			Array.iteri (fun j ->
				Array.iter (fun (_, pro, k) ->
					if k = i then (
						vars.(j + act_base.(l)) <- BigFloat.sub vars.(j + act_base.(l)) pro
					)
				)
			)
		) mdp;
		(vars, BigFloat.one, LPEq)
	) in
	let constr_non_neg = Array.init n (fun i -> 
		(Array.init n (fun j -> if i = j then BigFloat.one else BigFloat.zero), BigFloat.zero, LPGeq)
	) in
	let obj = Array.make n BigFloat.zero in
	Array.iteri (fun i ->
		Array.iteri (fun j ->
			Array.iter (fun (rew, pro, _) ->
				obj.(act_base.(i) + j) <- BigFloat.add obj.(act_base.(i) + j) (BigFloat.mult rew pro)
			)
		)
	) mdp;
	(Maximize, obj, Array.of_list ((Array.to_list constr_base) @ (Array.to_list constr_non_neg)))
	

let unichain_mdp_to_dual_lp mdp =
	let n = Array.length mdp in
	let m = ref 0 in
	Array.iter (fun actions -> m := !m + Array.length actions) mdp;
	let m = !m in
	let constr = Array.init m (fun _ -> (Array.make n BigFloat.zero, BigFloat.zero, LPGeq)) in
	let idx = ref 0 in
	Array.iteri (fun i actions ->
		Array.iter (fun action ->
			let (con, _, _) = constr.(!idx) in
			con.(i) <- BigFloat.one;
			Array.iter (fun (rew, pro, j) ->
				if j >= 0 then con.(j) <- BigFloat.mult rew pro
			) action;
			incr idx;
		) actions
	) mdp;
	(Minimize, Array.make n BigFloat.one, constr)	

	

let print_lp (objective, obj_func, matrix) =
	let maxlen = ref 0 in
	let len_of x =
		maxlen := max !maxlen (String.length (BigFloat.format_fraction x))
	in
	Array.iter (fun (line, x, _) ->
		len_of (BigFloat.mult (BigFloat.of_int (-1)) x);
		Array.iter (fun f -> len_of f) line;
	) matrix;

	let fmt f =
		StringUtils.fillup_left (BigFloat.format_fraction f) (!maxlen + 1) ' '
	in
	let m = Array.length matrix in
	let n = Array.length obj_func in
	let eqs = ref [] in
	Array.iteri (fun i (_, _, kind) ->
		if kind = LPEq
		then eqs := i::!eqs;
	) matrix;
	print_string "H-representation\n";
	if !eqs != [] then (
		print_string ("linearity " ^ string_of_int (List.length !eqs));
		List.iter (fun i ->
			print_string (" " ^ string_of_int (i+1));
		) (List.rev !eqs)
	);
	print_string "\n";
	print_string "begin\n";
	print_string (string_of_int (m+n) ^ " " ^ string_of_int (n + 1) ^ " rational\n");
	Array.iter (fun (line, x, _) ->
		print_string (fmt (BigFloat.mult (BigFloat.of_int (-1)) x));
		Array.iter (fun f -> print_string (fmt f)) line;
		print_string "\n";
	) matrix;
	print_string "end\n";
	print_string (if objective = Maximize then "maximize 0" else "minimize 0");
	Array.iter (fun f -> print_string (" " ^ fmt f)) obj_func;
	print_string "\n";
	print_string "*lponly\n"

