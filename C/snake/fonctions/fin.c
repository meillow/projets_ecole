#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "fin.h"
#include "control.h"

#define TEXTE_CHAR 5
#define QUITTE 0

/* affiche l'ecran "game over" du jeu */
int fin(int score,int temps) {

	short int lancement=1;
	int numero_touche_utilise;
	char score_str[TEXTE_CHAR],temps_str[TEXTE_CHAR];
	couleur couleur_texte = CouleurParNom("black");
	ChoisirCouleurDessin(couleur_texte);
	
	while(lancement==1) {
		ChargerImageFond("./images/fin.png");
		numero_touche_utilise=ChoixTouche(1,1);
		sprintf(score_str, "%d", score);
		EcrireTexte(485, 375, score_str, 2);
		sprintf(temps_str, "%d", temps);
		EcrireTexte(485, 568, temps_str, 2);
	
      	/* si touche echap appuyee alors quitte le jeu */
		if(numero_touche_utilise == QUITTE){
			lancement=QUITTE;
		}
	}
	return lancement;
}