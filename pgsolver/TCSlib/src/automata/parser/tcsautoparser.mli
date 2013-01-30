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

val auto :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> unit
