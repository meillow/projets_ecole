#ifndef GRILLE_H
#define GRILLE_H

#define COLONNES 8

#include "carte.h"

/* Génère un tableau avec des id placés de manière aléatoire */
void RemplirGrilleId(unsigned int liste_id[][COLONNES], unsigned int lignes,
 unsigned int colonnes);

/* Rempli tableau avec les sprites qui prennent l'id de la case correspondante */
void RemplirGrilleCartes(carte liste_cartes[], unsigned int liste_id[][COLONNES],
 unsigned int lignes, unsigned int colonnes);

/* Affiche la grille remplie de cartes en fonction de leur positionnement */
void AfficherGrilleCartes(carte liste_cartes[], unsigned int lignes,
 unsigned int colonnes, int default_sprite_id, unsigned int mode_triche_activee);

/* Libère tout les sprites chargés */
void LibererSpriteGrille(carte liste_cartes[], unsigned int nb_cartes);

#endif /* GRILLE_H */