#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "./fonctions/jeu.h"
#include "./fonctions/control.h"

#define LARGEUR 992
#define HAUTEUR 768

int main(void) {

	int numero_touche_utilise=4;
	short int lancement=1;
	InitialiserGraphique();
	CreerFenetre(300, 10, LARGEUR, HAUTEUR);
	ChoisirTitreFenetre("Snake 1.0");
	ChargerImageFond("./images/acceuil.png");

	while(lancement==1) {
		/*menu d'acceuil*/
		/*si on appuie sur le bouton alors l'écran de jeu va se lancer*/
		SourisPosition();
		if (SourisCliquee()){
			    if (_X>=348 && _X<=668 && _Y>=436 && _Y<=488){
                  	/*lancement de la fonction du plateau de jeu*/
			    	lancement=jeu(lancement,numero_touche_utilise);
			    }
		}
		/*si touche echap alors le jeu se quitte*/
		if(ToucheEnAttente()){
			numero_touche_utilise=ChoixTouche(numero_touche_utilise,1);
			if(numero_touche_utilise == 0){
				lancement=0;
			}
		} 
	}
	FermerGraphique();
	return EXIT_SUCCESS;
}