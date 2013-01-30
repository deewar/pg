type token =
  | INT of (int)
  | ANN of (string)
  | SEMICOLON
  | COMMA
  | BRACKETOPEN
  | BRACKETCLOSE
  | ALPHABETCALL
  | ALPHABETRET
  | UNDERSCORE
  | EOF
  | AUTOMATON
  | ALPHABET
  | STATES
  | INITIAL
  | TRANSITIONS
  | STACK
  | EPSILON

open Parsing;;
# 3 "src/automata/parser/tcsautoparser.mly"

  open Tcsautomataparserinternal ;;
  
# 26 "src/automata/parser/tcsautoparser.ml"
let yytransl_const = [|
  259 (* SEMICOLON *);
  260 (* COMMA *);
  261 (* BRACKETOPEN *);
  262 (* BRACKETCLOSE *);
  263 (* ALPHABETCALL *);
  264 (* ALPHABETRET *);
  265 (* UNDERSCORE *);
    0 (* EOF *);
  266 (* AUTOMATON *);
  267 (* ALPHABET *);
  268 (* STATES *);
  269 (* INITIAL *);
  270 (* TRANSITIONS *);
  271 (* STACK *);
  272 (* EPSILON *);
    0|]

let yytransl_block = [|
  257 (* INT *);
  258 (* ANN *);
    0|]

let yylhs = "\255\255\
\001\000\001\000\001\000\001\000\002\000\003\000\003\000\008\000\
\008\000\009\000\009\000\009\000\007\000\007\000\010\000\010\000\
\011\000\004\000\012\000\012\000\013\000\013\000\005\000\006\000\
\014\000\014\000\015\000\015\000\015\000\015\000\016\000\017\000\
\018\000\018\000\019\000\020\000\020\000\021\000\021\000\021\000\
\021\000\000\000"

let yylen = "\002\000\
\006\000\005\000\007\000\006\000\003\000\003\000\003\000\002\000\
\003\000\002\000\003\000\003\000\003\000\003\000\002\000\003\000\
\002\000\003\000\002\000\003\000\002\000\003\000\003\000\003\000\
\002\000\003\000\001\000\001\000\001\000\001\000\003\000\003\000\
\004\000\004\000\003\000\001\000\003\000\005\000\007\000\003\000\
\005\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\042\000\000\000\000\000\000\000\000\000\
\005\000\000\000\000\000\000\000\000\000\006\000\000\000\007\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\010\000\000\000\000\000\000\000\000\000\018\000\000\000\000\000\
\000\000\000\000\000\000\000\000\002\000\000\000\000\000\011\000\
\012\000\009\000\000\000\000\000\023\000\000\000\024\000\000\000\
\027\000\028\000\029\000\030\000\013\000\000\000\014\000\000\000\
\001\000\000\000\004\000\022\000\020\000\000\000\000\000\000\000\
\000\000\017\000\000\000\003\000\000\000\000\000\031\000\032\000\
\000\000\000\000\035\000\026\000\016\000\000\000\033\000\000\000\
\034\000\037\000\000\000\000\000\000\000\000\000\000\000\041\000\
\000\000\039\000"

let yydgoto = "\002\000\
\004\000\005\000\008\000\013\000\022\000\023\000\024\000\016\000\
\017\000\055\000\056\000\030\000\031\000\047\000\048\000\049\000\
\050\000\051\000\052\000\071\000\072\000"

let yysindex = "\004\000\
\255\254\000\000\024\255\000\000\023\255\033\255\029\255\028\255\
\000\000\038\255\041\255\040\255\010\255\000\000\005\255\000\000\
\042\255\043\255\045\255\044\255\030\255\034\255\049\000\007\255\
\000\000\048\255\049\255\041\255\051\255\000\000\050\255\052\255\
\053\255\054\255\055\255\058\000\000\000\034\255\059\000\000\000\
\000\000\000\000\058\255\043\255\000\000\002\255\000\000\059\255\
\000\000\000\000\000\000\000\000\000\000\061\255\000\000\062\255\
\000\000\061\000\000\000\000\000\000\000\003\255\063\255\065\255\
\053\255\000\000\055\255\000\000\013\255\066\255\000\000\000\000\
\065\255\064\255\000\000\000\000\000\000\065\255\000\000\031\255\
\000\000\000\000\068\255\067\255\069\255\071\255\070\255\000\000\
\071\255\000\000"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\060\255\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\074\255\014\255\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\070\000\000\000\025\255\000\000\075\255\000\000\000\000\000\000\
\000\000\075\255\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\076\255\000\000\000\000\077\255\000\000\
\000\000\000\000"

let yygindex = "\000\000\
\000\000\000\000\000\000\000\000\057\000\234\255\000\000\045\000\
\000\000\015\000\000\000\039\000\000\000\019\000\000\000\000\000\
\000\000\000\000\000\000\193\255\189\255"

let yytablesize = 84
let yytable = "\036\000\
\075\000\039\000\062\000\069\000\001\000\079\000\025\000\070\000\
\003\000\081\000\063\000\026\000\027\000\074\000\082\000\058\000\
\078\000\064\000\088\000\019\000\020\000\090\000\019\000\020\000\
\021\000\006\000\019\000\019\000\019\000\010\000\034\000\011\000\
\035\000\007\000\083\000\009\000\084\000\015\000\015\000\012\000\
\014\000\015\000\018\000\029\000\028\000\032\000\033\000\020\000\
\037\000\040\000\041\000\043\000\044\000\046\000\045\000\054\000\
\053\000\057\000\059\000\060\000\068\000\065\000\066\000\073\000\
\067\000\074\000\080\000\078\000\085\000\025\000\086\000\008\000\
\042\000\089\000\087\000\070\000\021\000\036\000\040\000\038\000\
\038\000\077\000\061\000\076\000"

let yycheck = "\022\000\
\064\000\024\000\001\001\001\001\001\000\069\000\002\001\005\001\
\010\001\073\000\009\001\007\001\008\001\001\001\078\000\038\000\
\004\001\016\001\086\000\013\001\014\001\089\000\013\001\014\001\
\015\001\002\001\013\001\014\001\015\001\001\001\001\001\003\001\
\003\001\011\001\004\001\003\001\006\001\013\001\014\001\012\001\
\003\001\001\001\003\001\001\001\003\001\001\001\003\001\014\001\
\000\000\002\001\002\001\001\001\003\001\001\001\003\001\001\001\
\003\001\000\000\000\000\002\001\000\000\003\001\002\001\001\001\
\003\001\001\001\001\001\004\001\001\001\000\000\004\001\012\001\
\028\000\004\001\006\001\005\001\003\001\003\001\003\001\003\001\
\024\000\067\000\044\000\065\000"

let yynames_const = "\
  SEMICOLON\000\
  COMMA\000\
  BRACKETOPEN\000\
  BRACKETCLOSE\000\
  ALPHABETCALL\000\
  ALPHABETRET\000\
  UNDERSCORE\000\
  EOF\000\
  AUTOMATON\000\
  ALPHABET\000\
  STATES\000\
  INITIAL\000\
  TRANSITIONS\000\
  STACK\000\
  EPSILON\000\
  "

let yynames_block = "\
  INT\000\
  ANN\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 5 : 'automaton) in
    let _2 = (Parsing.peek_val __caml_parser_env 4 : 'alphabet) in
    let _3 = (Parsing.peek_val __caml_parser_env 3 : 'states) in
    let _4 = (Parsing.peek_val __caml_parser_env 2 : 'initial) in
    let _5 = (Parsing.peek_val __caml_parser_env 1 : 'transitions) in
    Obj.repr(
# 31 "src/automata/parser/tcsautoparser.mly"
                                                    ( )
# 179 "src/automata/parser/tcsautoparser.ml"
               : unit))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 4 : 'automaton) in
    let _2 = (Parsing.peek_val __caml_parser_env 3 : 'alphabet) in
    let _3 = (Parsing.peek_val __caml_parser_env 2 : 'states) in
    let _4 = (Parsing.peek_val __caml_parser_env 1 : 'transitions) in
    Obj.repr(
# 32 "src/automata/parser/tcsautoparser.mly"
                                             ( )
# 189 "src/automata/parser/tcsautoparser.ml"
               : unit))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 6 : 'automaton) in
    let _2 = (Parsing.peek_val __caml_parser_env 5 : 'alphabet) in
    let _3 = (Parsing.peek_val __caml_parser_env 4 : 'states) in
    let _4 = (Parsing.peek_val __caml_parser_env 3 : 'stack) in
    let _5 = (Parsing.peek_val __caml_parser_env 2 : 'initial) in
    let _6 = (Parsing.peek_val __caml_parser_env 1 : 'transitions) in
    Obj.repr(
# 33 "src/automata/parser/tcsautoparser.mly"
                                                           ( )
# 201 "src/automata/parser/tcsautoparser.ml"
               : unit))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 5 : 'automaton) in
    let _2 = (Parsing.peek_val __caml_parser_env 4 : 'alphabet) in
    let _3 = (Parsing.peek_val __caml_parser_env 3 : 'states) in
    let _4 = (Parsing.peek_val __caml_parser_env 2 : 'stack) in
    let _5 = (Parsing.peek_val __caml_parser_env 1 : 'transitions) in
    Obj.repr(
# 34 "src/automata/parser/tcsautoparser.mly"
                                                   ( )
# 212 "src/automata/parser/tcsautoparser.ml"
               : unit))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : string) in
    Obj.repr(
# 36 "src/automata/parser/tcsautoparser.mly"
                                   ( !__automaton_type _2 )
# 219 "src/automata/parser/tcsautoparser.ml"
               : 'automaton))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    Obj.repr(
# 39 "src/automata/parser/tcsautoparser.mly"
                             ( !__automaton_int_alphabet _2 )
# 226 "src/automata/parser/tcsautoparser.ml"
               : 'alphabet))
; (fun __caml_parser_env ->
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'alphabetlist) in
    Obj.repr(
# 40 "src/automata/parser/tcsautoparser.mly"
                                    ( )
# 233 "src/automata/parser/tcsautoparser.ml"
               : 'alphabet))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'alphabetitem) in
    Obj.repr(
# 43 "src/automata/parser/tcsautoparser.mly"
                             ( )
# 240 "src/automata/parser/tcsautoparser.ml"
               : 'alphabetlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'alphabetitem) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'alphabetlist) in
    Obj.repr(
# 44 "src/automata/parser/tcsautoparser.mly"
                                        ( )
# 248 "src/automata/parser/tcsautoparser.ml"
               : 'alphabetlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 47 "src/automata/parser/tcsautoparser.mly"
          ( !__automaton_alphabet_add _1 _2 AlphabetInternal )
# 256 "src/automata/parser/tcsautoparser.ml"
               : 'alphabetitem))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 48 "src/automata/parser/tcsautoparser.mly"
                        ( !__automaton_alphabet_add _1 _3 AlphabetCall )
# 264 "src/automata/parser/tcsautoparser.ml"
               : 'alphabetitem))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 49 "src/automata/parser/tcsautoparser.mly"
                       ( !__automaton_alphabet_add _1 _3 AlphabetRet )
# 272 "src/automata/parser/tcsautoparser.ml"
               : 'alphabetitem))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    Obj.repr(
# 52 "src/automata/parser/tcsautoparser.mly"
                          ( !__automaton_int_stack _2 )
# 279 "src/automata/parser/tcsautoparser.ml"
               : 'stack))
; (fun __caml_parser_env ->
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'stacklist) in
    Obj.repr(
# 53 "src/automata/parser/tcsautoparser.mly"
                              ( )
# 286 "src/automata/parser/tcsautoparser.ml"
               : 'stack))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'stackitem) in
    Obj.repr(
# 56 "src/automata/parser/tcsautoparser.mly"
                          ( )
# 293 "src/automata/parser/tcsautoparser.ml"
               : 'stacklist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'stackitem) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'stacklist) in
    Obj.repr(
# 57 "src/automata/parser/tcsautoparser.mly"
                                  ( )
# 301 "src/automata/parser/tcsautoparser.ml"
               : 'stacklist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 59 "src/automata/parser/tcsautoparser.mly"
                   ( !__automaton_stack_add _1 _2 )
# 309 "src/automata/parser/tcsautoparser.ml"
               : 'stackitem))
; (fun __caml_parser_env ->
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'statelist) in
    Obj.repr(
# 61 "src/automata/parser/tcsautoparser.mly"
                                      ( )
# 316 "src/automata/parser/tcsautoparser.ml"
               : 'states))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'stateitem) in
    Obj.repr(
# 64 "src/automata/parser/tcsautoparser.mly"
                          ( )
# 323 "src/automata/parser/tcsautoparser.ml"
               : 'statelist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'stateitem) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'statelist) in
    Obj.repr(
# 65 "src/automata/parser/tcsautoparser.mly"
                                  ( )
# 331 "src/automata/parser/tcsautoparser.ml"
               : 'statelist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 68 "src/automata/parser/tcsautoparser.mly"
            ( !__automaton_state_add _1 _2 None )
# 339 "src/automata/parser/tcsautoparser.ml"
               : 'stateitem))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 69 "src/automata/parser/tcsautoparser.mly"
                ( !__automaton_state_add _1 _2 (Some _3) )
# 348 "src/automata/parser/tcsautoparser.ml"
               : 'stateitem))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    Obj.repr(
# 71 "src/automata/parser/tcsautoparser.mly"
                                  ( !__automaton_initial_state _2 )
# 355 "src/automata/parser/tcsautoparser.ml"
               : 'initial))
; (fun __caml_parser_env ->
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'transitionlist) in
    Obj.repr(
# 73 "src/automata/parser/tcsautoparser.mly"
                                                     ( )
# 362 "src/automata/parser/tcsautoparser.ml"
               : 'transitions))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'transitionitemdecider) in
    Obj.repr(
# 76 "src/automata/parser/tcsautoparser.mly"
                                      ( )
# 369 "src/automata/parser/tcsautoparser.ml"
               : 'transitionlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'transitionitemdecider) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'transitionlist) in
    Obj.repr(
# 77 "src/automata/parser/tcsautoparser.mly"
                                                   ( )
# 377 "src/automata/parser/tcsautoparser.ml"
               : 'transitionlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'transitionitem) in
    Obj.repr(
# 80 "src/automata/parser/tcsautoparser.mly"
                  ( )
# 384 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemdecider))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'transitionitemcall) in
    Obj.repr(
# 81 "src/automata/parser/tcsautoparser.mly"
                      ( )
# 391 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemdecider))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'transitionitemret) in
    Obj.repr(
# 82 "src/automata/parser/tcsautoparser.mly"
                     ( )
# 398 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemdecider))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'transitionitemepsilon) in
    Obj.repr(
# 83 "src/automata/parser/tcsautoparser.mly"
                         ()
# 405 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemdecider))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'succs) in
    Obj.repr(
# 86 "src/automata/parser/tcsautoparser.mly"
                ( !__automaton_transition_add _1 _2 _3 )
# 414 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitem))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'pairsuccs) in
    Obj.repr(
# 89 "src/automata/parser/tcsautoparser.mly"
                    ( !__automaton_transition_call_add _1 _2 _3 )
# 423 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemcall))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : int) in
    let _2 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _4 = (Parsing.peek_val __caml_parser_env 0 : 'succs) in
    Obj.repr(
# 92 "src/automata/parser/tcsautoparser.mly"
                    ( !__automaton_transition_ret_add _1 _3 (Some _2) _4 )
# 433 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemret))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : int) in
    let _4 = (Parsing.peek_val __caml_parser_env 0 : 'succs) in
    Obj.repr(
# 93 "src/automata/parser/tcsautoparser.mly"
                            ( !__automaton_transition_ret_add _1 _3 None _4 )
# 442 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemret))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'succs) in
    Obj.repr(
# 96 "src/automata/parser/tcsautoparser.mly"
                    ( !__automaton_transition_epsilon_add _1 _3 )
# 450 "src/automata/parser/tcsautoparser.ml"
               : 'transitionitemepsilon))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 99 "src/automata/parser/tcsautoparser.mly"
                            ( [ _1 ] )
# 457 "src/automata/parser/tcsautoparser.ml"
               : 'succs))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : int) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'succs) in
    Obj.repr(
# 100 "src/automata/parser/tcsautoparser.mly"
                            ( _1::_3 )
# 465 "src/automata/parser/tcsautoparser.ml"
               : 'succs))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 3 : int) in
    let _4 = (Parsing.peek_val __caml_parser_env 1 : int) in
    Obj.repr(
# 103 "src/automata/parser/tcsautoparser.mly"
                                                               ( [(_2,_4)] )
# 473 "src/automata/parser/tcsautoparser.ml"
               : 'pairsuccs))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 5 : int) in
    let _4 = (Parsing.peek_val __caml_parser_env 3 : int) in
    let _7 = (Parsing.peek_val __caml_parser_env 0 : 'pairsuccs) in
    Obj.repr(
# 104 "src/automata/parser/tcsautoparser.mly"
                                                               ( (_2,_4)::_7 )
# 482 "src/automata/parser/tcsautoparser.ml"
               : 'pairsuccs))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : int) in
    Obj.repr(
# 105 "src/automata/parser/tcsautoparser.mly"
                                                               ( [(_2,_2)] )
# 489 "src/automata/parser/tcsautoparser.ml"
               : 'pairsuccs))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 3 : int) in
    let _5 = (Parsing.peek_val __caml_parser_env 0 : 'pairsuccs) in
    Obj.repr(
# 106 "src/automata/parser/tcsautoparser.mly"
                                                               ( (_2,_2)::_5 )
# 497 "src/automata/parser/tcsautoparser.ml"
               : 'pairsuccs))
(* Entry auto *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
|]
let yytables =
  { Parsing.actions=yyact;
    Parsing.transl_const=yytransl_const;
    Parsing.transl_block=yytransl_block;
    Parsing.lhs=yylhs;
    Parsing.len=yylen;
    Parsing.defred=yydefred;
    Parsing.dgoto=yydgoto;
    Parsing.sindex=yysindex;
    Parsing.rindex=yyrindex;
    Parsing.gindex=yygindex;
    Parsing.tablesize=yytablesize;
    Parsing.table=yytable;
    Parsing.check=yycheck;
    Parsing.error_function=parse_error;
    Parsing.names_const=yynames_const;
    Parsing.names_block=yynames_block }
let auto (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : unit)
