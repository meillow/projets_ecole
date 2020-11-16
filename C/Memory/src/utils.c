#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "carte.h"
#include "grille.h"
#include "utils.h"

/* permet de détecter si la souris est au dessus des coordonnées en
paramètres */
int SourisAuDessus(int souris_x, int souris_y, int test_x, int test_y,
 int largeur, int hauteur) {
	int souris_au_dessus = 0;

	if((souris_x >= test_x && souris_x <= test_x + largeur) && 
		souris_y >= test_y && souris_y <= test_y + hauteur) {
		souris_au_dessus = 1;
	}

	return souris_au_dessus;
}

/* permet de détecter si la souris est au dessus d'une carte */
short int SourisAuDessusCartes(int souris_x, int souris_y, int nombre_cartes,
 carte liste_cartes[]) {
	int i = 0;
	short int au_dessus = 0;

	for(i = 0; i < nombre_cartes; i++) {
		if(SourisAuDessus(souris_x, souris_y, liste_cartes[i].pos_x, liste_cartes[i].pos_y,
		 64, 64) == 1) {
			au_dessus = 1;
		}
	}
	return au_dessus;
}

/* Test si toutes les cartes sont retournées, si oui, retourne 1 (vrai)*/
int TestVictoire(carte liste_cartes[], int nombre_cartes) {
	int i = 0;
	int test_victoire = 0;

	while((i < nombre_cartes) && liste_cartes[i].est_retournee != 0) {
		i++;
	}

	if(i >= nombre_cartes) {
		test_victoire = 1;
	}
	return test_victoire;
}

/* mode debug : affiche dans la console le tableau d'id */
void AfficherListeId(unsigned int liste_id[][COLONNES], unsigned int lignes,
 unsigned int colonnes) {
	int i, j = 0;
	printf("-------------------------------------------\n");
	for(i = 0; i < lignes; i++) {
		for (j = 0; j < colonnes; j++) {
			printf("%4d ", liste_id[i][j]);
		}
		printf("\n");
	}
}