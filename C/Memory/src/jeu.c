#include <stdio.h>	
#include <stdlib.h>	
#include <graph.h>
#include <time.h>


#include "timer.h"
#include "utils.h"
#include "carte.h"
#include "grille.h"
#include "jeu.h"

#define delta 10000L
#define LIGNES_FACILE 2
#define LIGNES_MOYEN 4
#define	LIGNES_DIFFICILE 6
#define SECONDES_CHAR 5

/*Charge et exécute les niveaux/le jeu*/
void Jeu(short int niveau) {

	/* Variable grille de cartes*/
	unsigned int lignes = LIGNES;
	unsigned int colonnes = COLONNES;

	/*Charge un nombre de lignes différentes en fonction du mode choisi*/
	if(niveau == 1) {
		lignes = LIGNES_FACILE;
	} else if(niveau == 2) {
		lignes = LIGNES_MOYEN;
	} else if(niveau == 3)  {
		lignes = LIGNES_DIFFICILE;
	} else if(niveau == 4) {
		srand(time(NULL));
		lignes = rand()%6+1;
	}
	
	unsigned int liste_id[LIGNES][COLONNES];
	unsigned int nb_cartes = lignes*colonnes;
	carte liste_cartes[NB_CARTES_MAX];
	int nb_carte_teste = 0;
	int id_carte_test1, id_carte_test2 = 0;

	int go_on = 1;

	unsigned long int suivant;

	/*Variables Timer*/
	unsigned long int timer_init = Microsecondes();
	unsigned long int secondes = 0;
	unsigned long int timer_init_pause = 0;
	unsigned long int secondes_pause = 0;
	unsigned long int secondes_derniere_pause = 0;
	int timer_init_pause_initialisee = 0;
	char secondes_str[SECONDES_CHAR];

	couleur couleur_texte = CouleurParNom("white");
	ChoisirCouleurDessin(couleur_texte);

	suivant = Microsecondes()+delta;
	int default_sprite_id = ChargerSprite("./Sprites/0.png");
	ChargerImageFond("./Fonds/fond_jeu.png");
	RemplirGrilleId(liste_id, lignes, colonnes);

	/*Rempli la grille chargée avec un nombre de cartes correspondant au
	 nombre de lignes/colonnes*/
	RemplirGrilleCartes(liste_cartes, liste_id, lignes, colonnes);

	
	short int pause = 0;

	while(go_on) {

		if(pause == 0) {
			timer_init_pause_initialisee = 0;
			secondes_derniere_pause = secondes_pause;
			secondes = CalculerSecondes(timer_init) - secondes_pause;;

			sprintf(secondes_str, "%ld", secondes);
		} else if(pause == 1) {
			if(timer_init_pause_initialisee == 0) {
				timer_init_pause = CreerTimer();
				timer_init_pause_initialisee = 1;
			}
			secondes_pause = CalculerSecondes(timer_init_pause) + secondes_derniere_pause;
		}

		if(SourisCliquee() == 1 && pause == 0) {
			/*Si clique sur retour libère tout les sprites précédemment
			 chargés*/
			if(SourisAuDessus(_X, _Y, 21, 875, 231, 59)) {
				LibererSpriteGrille(liste_cartes, nb_cartes);
              	LibererSprite(default_sprite_id);
				return;
			}

			/*Retourne les cartes en fonction de leur id*/
			if((SourisAuDessusCartes(_X, _Y, lignes*colonnes, liste_cartes))
				&& nb_carte_teste == 0) {

				id_carte_test1 = ObtenirId(_X, _Y, liste_cartes,
					lignes*colonnes);
				RetournerCarte(id_carte_test1, liste_cartes,
					lignes*colonnes, _X, _Y);
				nb_carte_teste++;
			} else if((SourisAuDessusCartes(_X, _Y, lignes*colonnes,
				liste_cartes)) && nb_carte_teste == 1) {

				id_carte_test2 = ObtenirId(_X, _Y, liste_cartes,
					lignes*colonnes);
				RetournerCarte(id_carte_test2, liste_cartes,
					lignes*colonnes, _X, _Y);
				nb_carte_teste = 2;
			}
		}
		
		/*Affichage du jeu*/ 	
		if (Microsecondes()>suivant) {
			suivant=Microsecondes()+delta;
			/*Recharge le fond par dessus le timer à chaque framerate pour pas 
			que l'affichage du timer se cumule*/
			ChargerImageFond("./Fonds/fond_jeu.png");
			AfficherGrilleCartes(liste_cartes, lignes, colonnes, default_sprite_id,
			 pause);
			
			if(pause == 0) {
				EcrireTexte(505, 916, secondes_str, 2);
			} else {
				EcrireTexte(480, 916, "Pause", 2);
			}	
		}

		/*Si deux cartes sont retournées, test si elles sont identiques, si oui
		les laisser découvertes*/
		if(nb_carte_teste == 2) {
			CarteIdentiques(liste_cartes, lignes*colonnes, id_carte_test1,
			id_carte_test2);
			nb_carte_teste = 0;
		}

		/*Test si toutes les cartes sont retournées et 
		affiche le menu si la condition est remplie*/
		if(TestVictoire(liste_cartes, lignes*colonnes) == 1) {
			LibererSpriteGrille(liste_cartes, nb_cartes);
          	LibererSprite(default_sprite_id);
			ChargerImageFond("./Fonds/fond_victoire.png");
			EcrireTexte(735, 557, secondes_str, 2);
			unsigned long int timer_victoire = CreerTimer();
			unsigned long int secondes_victoire = CalculerSecondes(timer_victoire);
			while(secondes_victoire < 10 && !(SourisCliquee() && 
				SourisAuDessus(_X, _Y, 297, 797, 476, 126))) {
				secondes_victoire = CalculerSecondes(timer_victoire);
			}
			return;
		}
		
		/*Activation du mode triche/debug*/
		if((ToucheEnAttente() && Touche() == XK_t)) {
			if(pause == 0) {
				timer_init = secondes + timer_init;
				AfficherListeId(liste_id, lignes, colonnes);
				pause = 1;
			} else if(pause == 1) {
				pause = 0;
			}
		}
	}
}