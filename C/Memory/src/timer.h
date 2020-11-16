#ifndef TIMER_H
#define TIMER_H

/*Initialise le timer*/
unsigned long int CreerTimer(void);

/*Calcule les secondes écoulés depuis l'initialisation du timer*/
unsigned long int CalculerSecondes(unsigned long int timer_init);

#endif /* TIMER_H */