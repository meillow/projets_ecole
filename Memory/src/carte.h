#ifndef CARTE_H
#define CARTE_H

/* structure définissant les cartes*/
typedef struct {
	unsigned int pos_x;
	unsigned int pos_y;
	unsigned int id;
	unsigned int id_affichage;
	unsigned int est_retournee;
} carte;

/*Retourne la carte correspondante à l'id passé en paramètre*/
void RetournerCarte(int id_carte, carte liste_cartes[], int nombre_cartes,
 int souris_x, int souris_y);

/*Créer une structure carte et la retourne*/
carte CreerCarte(unsigned int pos_x, unsigned int pos_y, unsigned int id,
 unsigned int id_affichage);

/*Test si deux cartes sont identiques, si oui, elles restent découvertes*/
void CarteIdentiques(carte liste_cartes[], int nombre_cartes, int id_carte_test1,
 int id_carte_test2);

/*Compare la position de la souris avec les cartes de liste_cartes et retourne
l'id si une carte est cliquée*/
int ObtenirId(int souris_x, int souris_y, carte liste_cartes[],
 int nombre_cartes);

#endif /* CARTE_H */