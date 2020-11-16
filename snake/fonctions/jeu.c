#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "control.h"
#include <time.h>
#include "fin.h"
#include <unistd.h>
#include "affichage.h"

#define CASEVIDE 0
#define POMME 4
#define TETESERPENT 3
#define CORPSSERPENT 2
#define MUR 5
#define CYCLE 10000L
#define SECONDES_CHAR 5
#define LONGUEUR_MAX_SERPENT 2400 /*2400 est la taille maximum que peut avoir le serpent*/
#define FIN 7
#define HAUT 6
#define BAS 5
#define GAUCHE 4
#define DROITE 3
#define PAUSE 2
#define NORMAL 1
#define QUITTE 0
#define COLONNE 62
#define LIGNE 42

/*partie principal du jeu*/

int jeu(short int lancement,int numero_touche_utilise) {
  
  	int z, x, y;
	int chrono_on,score = 0,init_pause = 0;
	unsigned long int initialisation_chrono = Microsecondes(),initialisation_chrono_deux,
  	secondes = 0,secondes_pause = 0,secondes_fin_pause = 0;
	char secondes_str[SECONDES_CHAR], score_str[SECONDES_CHAR];
	couleur couleur_texte = CouleurParNom("black");
	ChoisirCouleurDessin(couleur_texte);
	int pomme = ChargerSprite("./images/pom.png");
	int tete_serpent_up = ChargerSprite("./images/su.png");
	int tete_serpent_down = ChargerSprite("./images/sd.png");
	int tete_serpent_left = ChargerSprite("./images/sl.png");
	int tete_serpent_right = ChargerSprite("./images/sr.png");
	int corps_serpent = ChargerSprite("./images/sh.png");
	int mur = ChargerSprite("./images/mur.png");
	int nombre_pomme_presentes = 0,nombre_pomme = 0;
	int taille_serpent = 10;
	int derniere_touche;
	unsigned long int timer_vitesse=Microsecondes(),timer_vitesse_max;
	int tab[62][42];/* les colonnes 0 et 61 sont réservées pour les murs ainsi que les lignes
	0 et 41*/
	int obstacle_nombre,obstacle_mobile,obstacle_mobile_nombre,efface_valide,obstacle_nombre_present;
	srand(time(NULL));
  
  	/* initialisation des murs du plateau de jeu*/
	for(y=0;y < 42; y++){
		for(x=0;x < 62; x++){
			tab[x][y] = MUR;
		}
	}
  	/*remplit le plateau de case vide entouré par les murs de la boucle précédente*/
	for(y=1;y < 41; y++){
		for(x=1;x < 61;x++){
			tab[x][y] = CASEVIDE;
		}
	}
	/*creation des deux tableaux qui stockent les coordonnées des cases du serpent*/
	int tab_ser_x[2400],tab_ser_y[2400];
  
	for(z=0;z < taille_serpent; z++){
		tab_ser_x[z] = 25 + z;
		tab_ser_y[z] = 20;
	}
	/* génération d'obstacles et atribution aléatoire de leur place 
	14 murs maximum et 6 minimum*/
	obstacle_nombre = rand()%(14-6+1)+6;
	for(z=0;z < obstacle_nombre;z++){
		y = rand()%40 + 1;
		x = rand()%60 + 1;
		if(tab[x][y] == CASEVIDE){
			tab[x][y] = MUR;
		}else if (tab[x][y] != CASEVIDE){
			z--;
		}
	}
	
	while(lancement==1) {
      
		/* attribution des coordonnées des pommes dans la grille*/
		if(nombre_pomme < 5 && nombre_pomme_presentes < 5){
			for(z=0; z < (5-nombre_pomme_presentes); z++){
				y = rand()%40 + 1;
				x = rand()%60 + 1;
				if(tab[x][y] == CASEVIDE){
					tab[x][y] = POMME;
					nombre_pomme++;
				}else if (tab[x][y] != CASEVIDE){
					z--;
				}
			}
		}
      
		/* initialisation de l'utilisation des touches */
		numero_touche_utilise=ChoixTouche(numero_touche_utilise,derniere_touche);
      	/*gestion des touches pour eviter que le serpent ne se retourne sur lui même*/
		if ((derniere_touche==DROITE && numero_touche_utilise == GAUCHE) 
            || (derniere_touche == GAUCHE && numero_touche_utilise == DROITE) 
            || (derniere_touche == BAS && numero_touche_utilise == HAUT)
            || (derniere_touche == HAUT && numero_touche_utilise == BAS)){
          
			numero_touche_utilise=derniere_touche;
		}

		/* gestion du timer */
		/* calcul des secondes ecoulees et affichage du chrono si et seulement l'etat du jeu est normal (non pause/fin)*/
		if( chrono_on == NORMAL){
			EcrireTexte(192, 725, secondes_str, 2);
			init_pause = 0;
			secondes_fin_pause = secondes_pause;
			secondes = ((Microsecondes() - initialisation_chrono) / 1000000)-secondes_pause;
			sprintf(secondes_str, "%02ld", secondes);
		/* Si etat de pause on cree un deuxième chrono qui va permettre de calculer le nombre de secondes
		écoulées depuis la pause et on affiche la pause*/
		}
		if(numero_touche_utilise == PAUSE) {
			chrono_on = 0;
			EcrireTexte(483, 313, "Pause",2);
			if(init_pause == 0) {
				initialisation_chrono_deux= Microsecondes();
				init_pause = 1;
			}
			secondes_pause = ((Microsecondes() - initialisation_chrono_deux) / 1000000) + secondes_fin_pause;
		}
		/* création d'un timer qui va ralentir la progression du serpent*/
		timer_vitesse = timer_vitesse_max;
		timer_vitesse_max = ((Microsecondes() - timer_vitesse) / 100000);

		if (timer_vitesse_max > timer_vitesse && numero_touche_utilise != 2){
			
          	/*partie optionnelle : déplacement (ou non) des obstacles */
          	/*détermine le nombre d'obstacles à déplacer*/
			obstacle_mobile_nombre = rand()%obstacle_nombre; 
			obstacle_mobile = rand()%50;
			if(obstacle_mobile < obstacle_mobile_nombre){ 
              	/*réduis le nombre d'obstacles qui 
				seront déplacés (pour éviter de tous les déplacer, voir en déplacer aucun)*/
				obstacle_mobile_nombre = obstacle_mobile_nombre-3; 
              	/* attribus le positionnement des nouveaux obstacles*/
				for (z=0;z < obstacle_mobile_nombre;z++){
					y = rand()%40 + 1;
					x = rand()%60 + 1;
					if(tab[x][y] == CASEVIDE){
							tab[x][y] = MUR;
					}else if (tab[x][y] != CASEVIDE){
						z--;
					}
				}
				/*effacement aléatoire du nombre de mur déplacés parmi ceux présent*/
				obstacle_nombre_present = obstacle_nombre + obstacle_mobile_nombre;
				for(y=1;y<41 && obstacle_nombre_present > obstacle_nombre; y++ ){
					for(x=1;x < 61; x++){
						efface_valide = rand()%10;
						efface_valide = efface_valide%2;
						if(tab[x][y] == MUR && efface_valide == 1){	
							tab[x][y] = CASEVIDE;
							obstacle_nombre_present--;
						}
					}
				}	
			}
				
          	/*effacement de l'arrière du serpent*/	
			x = tab_ser_x[taille_serpent];
			y = tab_ser_y[taille_serpent];
			tab[x][y] = CASEVIDE;
          
			for(z=taille_serpent; z > 0; z--){
				tab_ser_x[z] = tab_ser_x[z-1];
				tab_ser_y[z] = tab_ser_y[z-1];
			}

			/* si touche droite fait avancer le serpent a droite*/
			 if(numero_touche_utilise == DROITE){
				chrono_on = 1;
               	/*sauvegarde de la position précédente du serpent*/
               	x = tab_ser_x[0] + 1;		
                y = tab_ser_y[0];
				/*gestion des collisions*/
                if((tab[x][y] == MUR) || (tab[x][y] == CORPSSERPENT)){
                    lancement = FIN;
                }
               	/*avancement de la tête du serpent vers la droite*/
				tab_ser_x[0] = tab_ser_x[0] + 1;
				derniere_touche=DROITE;
				
			/* si touche gauche fait avancer le serpent a gauche*/	 
			}else if(numero_touche_utilise == GAUCHE ){
				chrono_on = 1;            
	            x = tab_ser_x[0] - 1;		
	            y = tab_ser_y[0];
	            if((tab[x][y] == MUR) || (tab[x][y] == CORPSSERPENT)){
	                lancement = FIN;
	            }             
				tab_ser_x[0] = tab_ser_x[0] - 1;
				derniere_touche=GAUCHE;
               
			/* si touche bas fait avancer le serpent en bas*/
			}else if(numero_touche_utilise == BAS){
				chrono_on = 1;
                x = tab_ser_x[0];		
                y = tab_ser_y[0] + 1;
                if((tab[x][y] == MUR) || (tab[x][y] == CORPSSERPENT)){
                    lancement = FIN;
                }
				tab_ser_y[0] = tab_ser_y[0] + 1;
				derniere_touche=BAS;
               	
			/* si touche haut fait avancer le serpent en haut*/
			}else if(numero_touche_utilise == HAUT){
				chrono_on = 1;
                x = tab_ser_x[0];		
                y = tab_ser_y[0] - 1;
                if((tab[x][y] == MUR) || (tab[x][y] == CORPSSERPENT)){
                    lancement = FIN;
                }
               
				tab_ser_y[0] = tab_ser_y[0] - 1;
				derniere_touche=HAUT;

            /* si touche escape remet les variables de lancement a zero et quitte le jeu */   
			}else if(numero_touche_utilise == QUITTE){	
				lancement = QUITTE;
			}
          
          	/*déplacement du corps du serpent en fonction de la tête*/
			for (z=1; z < taille_serpent; z++){
				x = tab_ser_x[z];
				y = tab_ser_y[z];
				tab[x][y] = CORPSSERPENT;
			}
			/*attribution de la nouvelle position de la tête*/
			x = tab_ser_x[0];		
			y = tab_ser_y[0];
			tab[x][y] = TETESERPENT;
			
          	/*chargement des sprites en fonction des id enregistrés dans le tableau */
			nombre_pomme_presentes=affiche_sprite_jeux(tab,pomme,corps_serpent,tete_serpent_up,tete_serpent_down,
			tete_serpent_left,tete_serpent_right, numero_touche_utilise,mur);
			
			/* Si il n'y a que 4 pommes sur le plateau, alors une pomme a été mangée 
            donc le score augmente et la taille du serpent aussi*/
			if(nombre_pomme_presentes == 4){
					nombre_pomme = 4;
					score = score+5;
					taille_serpent = taille_serpent + 2;
			}
		}

		/*affichage du score*/
		sprintf(score_str, "%d", score);
		EcrireTexte(760, 725, score_str, 2);
	
	}

	LibererSprite(pomme);
	LibererSprite(mur);
	LibererSprite(corps_serpent);
	LibererSprite(tete_serpent_up);
	LibererSprite(tete_serpent_down);
	LibererSprite(tete_serpent_left);
	LibererSprite(tete_serpent_right);

	if (lancement == FIN){
		fin(score,secondes);
	}

	return lancement;
}
