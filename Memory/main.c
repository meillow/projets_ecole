#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "./src/timer.h"
#include "./src/utils.h"
#include "./src/carte.h"
#include "./src/grille.h"
#include "./src/menu.h"
#include "./src/jeu.h"

#define LARGEUR 1024
#define HAUTEUR 1000

int main(void) {
	int go_on = 1;
	short int niveau = 0;

	InitialiserGraphique();
	CreerFenetre(10, 10, LARGEUR, HAUTEUR);
	ChoisirTitreFenetre("Memory Noel release 1.0");
	ChoisirCurseur(126);

	while(go_on == 1) {
		niveau = Menu(&go_on);
		if(niveau != 0) {
			Jeu(niveau);
		}
	}
	
	FermerGraphique();
	return EXIT_SUCCESS;
}