#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include <time.h>

/* charge le menu principal/les menus puis retourne le niveau choisit */
short int Menu(int* go_on) 
{
	srand(time(NULL));

	short int etat_menu = 0;
	short int niveau = 0;

	/*défini les différents écrans de choix*/
	ChoisirEcran(3);
	ChargerImageFond("./Fonds/fond_niveaux.png");
	ChoisirEcran(2);
	ChargerImageFond("./Fonds/fond_instructions.png");
	ChoisirEcran(1);
	ChargerImageFond("./Fonds/fond_menu.png");
	ChoisirEcran(0);

	CopierZone(1,0,0,0,1024,1000,0,0);

	while(1)
	{
		SourisPosition();

		if(ToucheEnAttente() && Touche() == XK_Escape) {
			*go_on = 0;
			return niveau;
		}

		if (SourisCliquee())
		{
			if(etat_menu==0) /*menu principal*/
			{
			    if (_X>=313 && _X<=715 && _Y>=449 && _Y<=548)
			    {
			      CopierZone(0,1,0,0,1024,1000,0,0);
			      CopierZone(3,0,0,0,1024,1000,0,0); /*affiche menu choix niveaux*/
			      etat_menu=1;
			    }
			    if (_X>=313 && _X<=715 && _Y>=694 && _Y<=793)
			    {
			     CopierZone(0,1,0,0,1024,1000,0,0);
			     CopierZone(2,0,0,0,1024,1000,0,0); /*affiche menu instructions*/ 
			     etat_menu=2;
			    }

			}
			  
			else if (etat_menu==1) /*menu niveaux*/
			{
				if (_X>=358 && _X<=659 && _Y>=288 && _Y<=363)
				{	
					niveau=1;
					return niveau;			
				}

				if (_X>=358 && _X<=659 && _Y>=426 && _Y<=501)
				{
					niveau=2;
					return niveau;				
				}

				if (_X>=358 && _X<=659 && _Y>=566 && _Y<=641)
				{		
					niveau=3;
					return niveau;	
				}

				if (_X>=358 && _X<=659 && _Y>=700 && _Y<=775)
				{
					niveau=4;
					return niveau;	
				}

				if (_X>=321 && _X<=695 && _Y>=855 && _Y<=944)
				{
					CopierZone(0,3,0,0,1024,1000,0,0);
					CopierZone(1,0,0,0,1024,1000,0,0);
					
					/*renvoie au menu principal*/
					etat_menu=0;
				}
			}

			else if (etat_menu==2) /*menu instructions*/
			{
				if (_X>=362 && _X<=660 && _Y>=898 && _Y<=972)
				{
					CopierZone(0,2,0,0,1024,1000,0,0);

					CopierZone(1,0,0,0,1024,1000,0,0);
					/*renvoie au menu principal*/
					etat_menu=0;
				}
			}
		}
	}
}
