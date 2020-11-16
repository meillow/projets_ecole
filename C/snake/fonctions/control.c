#include <stdio.h>
#include <stdlib.h>
#include <graph.h>

#define FIN 0
#define HAUT 6
#define BAS 5
#define GAUCHE 4
#define DROITE 3
#define PAUSE 2

/*renvoie l'etat d'avancement du jeu en fonction de la touche appuyee*/
int ChoixTouche (int numero_touche_utilise, int derniere_touche){
  
	int numero_touche;
  
	if(ToucheEnAttente()){
		numero_touche=Touche();
		if (numero_touche==XK_space){
			if (numero_touche_utilise!=PAUSE){
				numero_touche_utilise = PAUSE;
			}else{
				numero_touche_utilise=derniere_touche;
			}
		} else if (numero_touche==XK_Escape ){
				numero_touche_utilise=FIN;
				
		}else if (numero_touche==XK_Left ){
				numero_touche_utilise=GAUCHE;
				
		}else if (numero_touche==XK_Right ){
				numero_touche_utilise=DROITE;
				
		}else if (numero_touche==XK_Up ){
				numero_touche_utilise=HAUT;
				
		}else if (numero_touche==XK_Down){
				numero_touche_utilise=BAS;
		}
	}
	return numero_touche_utilise;
}

