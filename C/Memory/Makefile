### VARIABLES ###
 
CC = gcc
CFLAGS = -Wall \
         -std=c89
EXE = main
OFILES = ./src/jeu.o \
		 ./src/carte.o \
		 ./src/utils.o \
		 ./src/grille.o \
		 ./src/menu.o \
		 ./src/timer.o \
         main.o
 
### BUT PAR DEFAUT ###
 
but : ${EXE}
 
### REGLES ESSENTIELLES ###
 
jeu.o : jeu.h carte.h utils.h grille.h timer.h

carte.o : carte.h utils.h timer.h

utils.o : utils.h carte.h 

grille.o : grille.h carte.h 

menu.o : menu.h

timer.o : timer.h

main.o : main.c
 
${EXE} : ${OFILES}
	$(CC) $(CFLAGS) -o ${EXE} ${OFILES} -lgraph
 
### REGLES OPTIONNELLES ###
 
clean :
	-rm -f ${OFILES}

run : 
	./$(EXE)
	
### FIN ###
