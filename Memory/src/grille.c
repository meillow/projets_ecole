#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include <time.h>
#include "carte.h"
#include "grille.h"

#define LONGUEUR_CHEMIN 20

/* Génère un tableau avec des id placés de manière aléatoire */
void RemplirGrilleId(unsigned int liste_id[][COLONNES], unsigned int lignes,
 unsigned int colonnes) {
	int i, j, k, l = 0;
	int id_generee = 0;
	int nb_id_identique = 0;
	int id = 0;

	srand(time(NULL));

	for(i = 0; i < lignes; i++) {
		for(j = 0; j < colonnes; j++) {
			while(id_generee == 0) {
				id = rand()%((lignes*colonnes)/2)+1;
				/*Vérifie si l'id est présent plus de 2 fois*/
				for(k = 0; k < lignes; k++) {
					for(l = 0; l < colonnes; l++) {
						if(liste_id[k][l] == id) {
							nb_id_identique++;
						}
					}
				}
				if(nb_id_identique < 2) {
					liste_id[i][j] = id;
					id_generee = 1;
					nb_id_identique = 0;
				} else {
					nb_id_identique = 0;
				}
			}
			id_generee = 0;
		}
	}
}

/* Rempli tableau avec les sprites qui prennent l'id de la case correspondante */
void RemplirGrilleCartes(carte liste_cartes[],unsigned int liste_id[][COLONNES],
unsigned int lignes, unsigned int colonnes) {
	int i, j = 0;
	int id_affichage = 0;
	int index_liste_cartes = 0;

	/* affichage centré des cartes (on calcul l'espace non occupé par la grille puis
	on divise par 2)*/
	unsigned int origin_x = (1024 - ((colonnes*64)+((colonnes-1)*58))) / 2; 
	unsigned int origin_y = ((1000 - ((lignes*64)+((lignes-1)*58))) / 2) - 100;

	for(i = 0; i < lignes; i++) {
		for (j = 0; j < colonnes; j++) {
			/* charge tous les sprites nécessaires */
			char chemin[LONGUEUR_CHEMIN];
			sprintf(chemin, "./Sprites/%d.png", liste_id[i][j]); 
			id_affichage = ChargerSprite(chemin);
			liste_cartes[index_liste_cartes] = CreerCarte(origin_x + j*(64+58),
			 origin_y + i*(64+58), liste_id[i][j], id_affichage);
			index_liste_cartes++;
		}
	}
}

/* Affiche la grille remplie de cartes en fonction de leur positionnement */
void AfficherGrilleCartes(carte liste_cartes[], unsigned int lignes,
unsigned int colonnes, int default_sprite_id, unsigned int mode_triche_activee){
	int i = 0;

	/* affiche un certains nombre de carte en fonction du niveau(voir jeu.c)*/
	if(mode_triche_activee == 0) {
		for(i = 0; i < lignes*colonnes; i++) {
			if(liste_cartes[i].est_retournee == 1) {
				AfficherSprite(liste_cartes[i].id_affichage, 
				liste_cartes[i].pos_x,liste_cartes[i].pos_y);
			} else {/* affiche les cartes de dos*/
				AfficherSprite(default_sprite_id, liste_cartes[i].pos_x,
				 liste_cartes[i].pos_y);
			}
		}	
	}
	/* retourne toutes les cartes */
	if(mode_triche_activee == 1) {
		for(i = 0; i < lignes*colonnes; i++) {
			AfficherSprite(liste_cartes[i].id_affichage, liste_cartes[i].pos_x,
			liste_cartes[i].pos_y);
		}
	}
}

/* Libère tout les sprites chargés */
void LibererSpriteGrille(carte liste_cartes[],unsigned int nb_cartes){
	int i=0;
	for(i = 0; i < nb_cartes; i++) {
		LibererSprite(liste_cartes[i].id_affichage);
	}
}