#ifndef UTILS_H
#define UTILS_H

#include "grille.h"
#include "carte.h"

/* permet de détecter si la souris est au dessus d'un item*/
int SourisAuDessus(int souris_x, int souris_y, int test_x, int test_y,
 int largeur, int hauteur);

/* permet de détecter si la souris est au dessus d'une carte*/
short int SourisAuDessusCartes(int souris_x, int souris_y, int nombre_cartes,
 carte liste_cartes[]);

/* Test si toutes les cartes sont retournées, si oui, retourne 1 (vrai)*/
int TestVictoire(carte liste_cartes[], int nombre_cartes);

/*mode debug : affiche dans la console le tableau d'id*/
void AfficherListeId(unsigned int liste_id[][COLONNES], unsigned int lignes,
 unsigned int colonnes);

#endif /* UTILS_H */