### VARIABLES ###
 
CC = gcc
CFLAGS = -Wall \
         -std=c89
EXE = Snake
OFILES = ./fonctions/jeu.o \
		./fonctions/control.o\
		./fonctions/fin.o\
		./fonctions/affichage.o\
        main.o
 
### BUT PAR DEFAUT ###
 
but : ${EXE}
 
### REGLES ESSENTIELLES ###
 
jeu.o : jeu.h  control.h fin.h affichage.h

fin.o : fin.h control.h

control.o : control.h

affichage.o : affichage.h

main.o : main.c 
 
${EXE} : ${OFILES}
	$(CC) $(CFLAGS) -o ${EXE} ${OFILES} -lgraph

### REGLES OPTIONNELLES ###
 
clean :
	-rm -f ${OFILES}

run : 
	./$(EXE)
	
### FIN ###
