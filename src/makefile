All: parser main

main: parser
	gcc main.c parser.o -o main

parser:
	gcc -c parser.c -o parser.o

clean:
	rm -rf *.o
	rm -rf *~
	rm -rf \#*\#