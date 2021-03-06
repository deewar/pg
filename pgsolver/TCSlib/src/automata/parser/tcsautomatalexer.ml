# 3 "src/automata/parser/tcsautomatalexer.mll"
 

open Tcsautoparser ;;        (* The type token is defined in tcsautoparser.mli *)
open Tcsautomataparserinternal ;;


# 9 "src/automata/parser/tcsautomatalexer.ml"
let __ocaml_lex_tables = {
  Lexing.lex_base = 
   "\000\000\000\000\000\000\001\000\001\000\243\255\001\000\245\255\
    \246\255\247\255\248\255\249\255\250\255\251\255\252\255\015\000\
    \254\255\255\255\244\255\000\000\003\000\000\000\004\000\001\000\
    \004\000\010\000\012\000\242\255\000\000\002\000\002\000\000\000\
    \007\000\241\255\005\000\008\000\001\000\002\000\010\000\240\255\
    \237\255\001\000\010\000\022\000\031\000\021\000\239\255\033\000\
    \021\000\017\000\028\000\018\000\030\000\025\000\027\000\023\000\
    \238\255";
  Lexing.lex_backtrk = 
   "\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\002\000\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255";
  Lexing.lex_default = 
   "\255\255\255\255\255\255\255\255\255\255\000\000\006\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\255\255\
    \000\000\000\000\000\000\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\000\000\255\255\255\255\255\255\255\255\
    \255\255\000\000\255\255\255\255\255\255\255\255\255\255\000\000\
    \000\000\255\255\255\255\255\255\255\255\255\255\000\000\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \000\000";
  Lexing.lex_trans = 
   "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\016\000\017\000\255\255\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \016\000\000\000\006\000\018\000\000\000\000\000\000\000\000\000\
    \013\000\012\000\000\000\000\000\014\000\000\000\000\000\000\000\
    \015\000\015\000\015\000\015\000\015\000\015\000\015\000\015\000\
    \015\000\015\000\000\000\008\000\011\000\000\000\010\000\015\000\
    \015\000\015\000\015\000\015\000\015\000\015\000\015\000\015\000\
    \015\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\009\000\
    \000\000\004\000\024\000\030\000\031\000\032\000\035\000\038\000\
    \029\000\002\000\042\000\036\000\040\000\019\000\041\000\022\000\
    \028\000\023\000\047\000\003\000\001\000\034\000\020\000\021\000\
    \025\000\026\000\027\000\033\000\037\000\039\000\043\000\044\000\
    \045\000\046\000\048\000\049\000\050\000\051\000\052\000\053\000\
    \054\000\055\000\056\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\007\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \005\000\255\255\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000";
  Lexing.lex_check = 
   "\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\000\000\000\000\006\000\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \000\000\255\255\000\000\006\000\255\255\255\255\255\255\255\255\
    \000\000\000\000\255\255\255\255\000\000\255\255\255\255\255\255\
    \000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
    \000\000\000\000\255\255\000\000\000\000\255\255\000\000\015\000\
    \015\000\015\000\015\000\015\000\015\000\015\000\015\000\015\000\
    \015\000\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\000\000\
    \255\255\000\000\023\000\029\000\030\000\031\000\034\000\037\000\
    \028\000\000\000\041\000\035\000\036\000\004\000\002\000\021\000\
    \019\000\022\000\001\000\000\000\000\000\003\000\004\000\020\000\
    \024\000\025\000\026\000\032\000\035\000\038\000\042\000\043\000\
    \044\000\045\000\047\000\048\000\049\000\050\000\051\000\052\000\
    \053\000\054\000\055\000\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\000\000\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \000\000\006\000\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
    \255\255\255\255";
  Lexing.lex_base_code = 
   "";
  Lexing.lex_backtrk_code = 
   "";
  Lexing.lex_default_code = 
   "";
  Lexing.lex_trans_code = 
   "";
  Lexing.lex_check_code = 
   "";
  Lexing.lex_code = 
   "";
}

let rec token lexbuf =
    __ocaml_lex_token_rec lexbuf 0
and __ocaml_lex_token_rec lexbuf __ocaml_lex_state =
  match Lexing.engine __ocaml_lex_tables __ocaml_lex_state lexbuf with
      | 0 ->
# 11 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_line; __lexer_character := 0; token lexbuf )
# 135 "src/automata/parser/tcsautomatalexer.ml"

  | 1 ->
# 12 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; token lexbuf )
# 140 "src/automata/parser/tcsautomatalexer.ml"

  | 2 ->
let
# 13 "src/automata/parser/tcsautomatalexer.mll"
                  lxm
# 146 "src/automata/parser/tcsautomatalexer.ml"
= Lexing.sub_lexeme lexbuf lexbuf.Lexing.lex_start_pos lexbuf.Lexing.lex_curr_pos in
# 13 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length lxm); INT(int_of_string lxm) )
# 150 "src/automata/parser/tcsautomatalexer.ml"

  | 3 ->
# 14 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; COMMA )
# 155 "src/automata/parser/tcsautomatalexer.ml"

  | 4 ->
# 15 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; BRACKETOPEN )
# 160 "src/automata/parser/tcsautomatalexer.ml"

  | 5 ->
# 16 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; BRACKETCLOSE )
# 165 "src/automata/parser/tcsautomatalexer.ml"

  | 6 ->
# 17 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; ALPHABETCALL )
# 170 "src/automata/parser/tcsautomatalexer.ml"

  | 7 ->
# 18 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; ALPHABETRET )
# 175 "src/automata/parser/tcsautomatalexer.ml"

  | 8 ->
# 19 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; UNDERSCORE )
# 180 "src/automata/parser/tcsautomatalexer.ml"

  | 9 ->
# 20 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; SEMICOLON )
# 185 "src/automata/parser/tcsautomatalexer.ml"

  | 10 ->
# 21 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; EPSILON )
# 190 "src/automata/parser/tcsautomatalexer.ml"

  | 11 ->
let
# 22 "src/automata/parser/tcsautomatalexer.mll"
                             lxm
# 196 "src/automata/parser/tcsautomatalexer.ml"
= Lexing.sub_lexeme lexbuf (lexbuf.Lexing.lex_start_pos + 1) (lexbuf.Lexing.lex_curr_pos + -1) in
# 22 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length lxm) + 2; ANN(lxm) )
# 200 "src/automata/parser/tcsautomatalexer.ml"

  | 12 ->
# 23 "src/automata/parser/tcsautomatalexer.mll"
                                       ( incr __lexer_character; EOF )
# 205 "src/automata/parser/tcsautomatalexer.ml"

  | 13 ->
# 24 "src/automata/parser/tcsautomatalexer.mll"
                        ( __lexer_character := !__lexer_character + (String.length "AUTOMATON"); AUTOMATON )
# 210 "src/automata/parser/tcsautomatalexer.ml"

  | 14 ->
# 25 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length "ALPHABET"); ALPHABET )
# 215 "src/automata/parser/tcsautomatalexer.ml"

  | 15 ->
# 26 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length "STATES"); STATES )
# 220 "src/automata/parser/tcsautomatalexer.ml"

  | 16 ->
# 27 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length "INITIAL"); INITIAL )
# 225 "src/automata/parser/tcsautomatalexer.ml"

  | 17 ->
# 28 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length "TRANSITIONS"); TRANSITIONS )
# 230 "src/automata/parser/tcsautomatalexer.ml"

  | 18 ->
# 29 "src/automata/parser/tcsautomatalexer.mll"
                                       ( __lexer_character := !__lexer_character + (String.length "STACK"); STACK )
# 235 "src/automata/parser/tcsautomatalexer.ml"

  | __ocaml_lex_state -> lexbuf.Lexing.refill_buff lexbuf; __ocaml_lex_token_rec lexbuf __ocaml_lex_state

;;

