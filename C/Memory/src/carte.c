#include "utils.h"
#include "carte.h"
#include "timer.h"

/*Retourne la carte correspondante à l'id passé en paramètre*/
void RetournerCarte(int id_carte, carte liste_cartes[], int nombre_cartes,
 int souris_x, int souris_y) {
	int i = 0;

	for(i = 0; i < nombre_cartes; i++) {
		if(liste_cartes[i].id == id_carte && SourisAuDessus(souris_x, souris_y,
		 liste_cartes[i].pos_x, liste_cartes[i].pos_y, 64, 64) == 1) {
			liste_cartes[i].est_retournee = 1;
		}
	}
}

/*Créer une structure carte et la retourne*/
carte CreerCarte(unsigned int pos_x, unsigned int pos_y, unsigned int id,
 unsigned int id_affichage) {
	carte carte = {pos_x, pos_y, id, id_affichage};
	return carte;
}

/*Test si deux cartes sont identiques, si oui, elles restent découvertes*/
void CarteIdentiques(carte liste_cartes[], int nombre_cartes, int id_carte_test1,
 int id_carte_test2) {

	int i = 0;

	if(id_carte_test1 != id_carte_test2) {
		unsigned long int timer_init_test = CreerTimer();
		unsigned long int secondes_test = 0;

		while(secondes_test < 1) {
			secondes_test = CalculerSecondes(timer_init_test);
		}
		
		secondes_test = CalculerSecondes(timer_init_test);

		if(secondes_test >= 1) {
			for(i = 0; i < nombre_cartes; i++) {
				if(liste_cartes[i].id == id_carte_test1
				 || liste_cartes[i].id == id_carte_test2) {
					liste_cartes[i].est_retournee = 0;
				}
			}
		}
	}
}

/*Compare la position de la souris avec les cartes de liste_cartes et retourne
l'id si une carte est cliquée*/
int ObtenirId(int souris_x, int souris_y, carte liste_cartes[],
 int nombre_cartes) {
	int i = 0;
	int id = 0;
	for(i = 0; i < nombre_cartes; i++) {
		if(SourisAuDessus(souris_x, souris_y, liste_cartes[i].pos_x, liste_cartes[i].pos_y,
		 64, 64) == 1) {
			id = liste_cartes[i].id;
			break;
		}
	}
	return id;
}