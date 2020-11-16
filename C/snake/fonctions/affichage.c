#include <stdio.h>
#include <stdlib.h>
#include <graph.h>

#define CASEVIDE 0
#define POMME 4
#define TETESERPENT 3
#define CORPSSERPENT 2
#define MUR 5
#define CASE 16
#define COLONNE 62
#define LIGNE 42
#define BAS 5
#define HAUT 6
#define GAUCHE 4
#define DROITE 3

/* Fonction qui gère l'affichage des prites en fonction des indices trouvés 
dans le tableau passé en argument*/

int affiche_sprite_jeux (int tab[COLONNE][LIGNE],int pomme,int corps, int up,
                         int down,int left,int right,int numero_touche_utilise,int mur){
	
  	ChargerImageFond("./images/jeu.png");
	int nombre_pomme_presentes=0;
	int x,y,id;
	int tab_x,tab_y;
  
  	/*charge les différents sprite du jeu aux coordonnées correspondantes aux indices définis auparavant*/
	for(y=1;y<41; y++){
		for(x=1;x<61; x++){
			id = tab[x][y];
			
        	if (id==POMME){
				/* comptage des pommes présentes dans le tableau */ 
				if(nombre_pomme_presentes<5){
					nombre_pomme_presentes++;
				}else if (nombre_pomme_presentes>5){
					nombre_pomme_presentes--;
				}
                /* affichage des pommes selon les indices du tableau correspondants */ 
				tab_y=y*CASE;
				tab_x=x*CASE;
				AfficherSprite(pomme,tab_x,tab_y);
				
            /* affichage tete du serpent selon les indices du tableau correspondants */ 
			}else if (id==TETESERPENT){
				tab_y=y*CASE;
				tab_x=x*CASE;
				if(numero_touche_utilise==DROITE){
					AfficherSprite(right,tab_x,tab_y);
				}else if (numero_touche_utilise==GAUCHE){
					AfficherSprite(left,tab_x,tab_y);
				}else if (numero_touche_utilise==BAS){
					AfficherSprite(down,tab_x,tab_y);
				}else if (numero_touche_utilise==HAUT){
					AfficherSprite(up,tab_x,tab_y);
				}
			
              /* affichage du corps du serpent selon les indices du tableau correspondants */ 	
           	}else if (id==CORPSSERPENT){
				tab_y=y*CASE;
				tab_x=x*CASE;
				AfficherSprite(corps,tab_x,tab_y);
			}else if (id==MUR){
				tab_y=y*CASE;
				tab_x=x*CASE;
				AfficherSprite(mur,tab_x,tab_y);
			}
		}
	}
	return nombre_pomme_presentes;
}