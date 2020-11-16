#include <graph.h>
#include "timer.h"

/*Initialise le timer*/
unsigned long int CreerTimer(void) {
	unsigned long int timer_init = Microsecondes();
	return timer_init;
}

/*Calcule les secondes écoulés depuis l'initialisation du timer*/
unsigned long int CalculerSecondes(unsigned long int timer_init) {
	unsigned long int secondes = (Microsecondes() - timer_init) / 1000000;
	return secondes;
}