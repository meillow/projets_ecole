# --------------- Les constantes --------------- #
# --------------- Les sources --------------- #
# --------------- Le package --------------- #
SOURCE      = ./src/
S_FR        = $(SOURCE)fr/
S_IUTFBLEAU = $(S_FR)iutfbleau/

# --------------- L'API --------------- #
S_PROJETIHM2020FI2 = $(S_IUTFBLEAU)projetIHM2020FI2/
S_API              = $(S_PROJETIHM2020FI2)API/
S_MNP              = $(S_PROJETIHM2020FI2)MNP/
S_TEST             = $(S_PROJETIHM2020FI2)test/

# --------------- La fenêtre --------------- #
S_FENETRE = $(S_IUTFBLEAU)fenetre/

# --------------- Les boutons --------------- #
S_BOUTONS = $(S_IUTFBLEAU)boutons/

# --------------- Les menus --------------- #
S_MENU   = $(S_IUTFBLEAU)menu/
S_JOUER  = $(S_MENU)jouer/
S_PARTIE = $(S_MENU)partie/

# --------------- Le jeu --------------- #
S_JEU        = $(S_IUTFBLEAU)jeu/
S_INVENTAIRE = $(S_JEU)inventaire/
S_DONJON     = $(S_JEU)donjon/
S_CARTE      = $(S_JEU)carte/

# --------------- Les instructions --------------- #
S_INSTRUCTIONS = $(S_IUTFBLEAU)instructions/

# --------------- Les modèles persistants --------------- #
S_MP = $(S_IUTFBLEAU)MP/

# --------------- Les classes --------------- #
# --------------- Le package --------------- #
BUILD       = ./class/
B_FR        = $(BUILD)fr/
B_IUTFBLEAU = $(B_FR)iutfbleau/

# --------------- L'API --------------- #
B_PROJETIHM2020FI2 = $(B_IUTFBLEAU)projetIHM2020FI2/
B_API              = $(B_PROJETIHM2020FI2)API/
B_MNP              = $(B_PROJETIHM2020FI2)MNP/
B_TEST             = $(B_PROJETIHM2020FI2)test/

# --------------- La fenêtre --------------- #
B_FENETRE = $(B_IUTFBLEAU)fenetre/

# --------------- Les boutons --------------- #
B_BOUTONS = $(B_IUTFBLEAU)boutons/

# --------------- Les menus --------------- #
B_MENU   = $(B_IUTFBLEAU)menu/
B_JOUER  = $(B_MENU)jouer/
B_PARTIE = $(B_MENU)partie/

# --------------- Le jeu --------------- #
B_JEU        = $(B_IUTFBLEAU)jeu/
B_INVENTAIRE = $(B_JEU)inventaire/
B_DONJON     = $(B_JEU)donjon/
B_CARTE      = $(B_JEU)carte/

# --------------- Les instructions --------------- #
B_INSTRUCTIONS = $(B_IUTFBLEAU)instructions/

# --------------- Les modèles persistants --------------- #
B_MP = $(B_IUTFBLEAU)MP/

# --------------- Les librairies --------------- #
LIBRAIRIES = ./lib/

# --------------- Les images --------------- #
IMAGES = ./img/

# --------------- L'outil de compilation --------------- #
JC      = javac
JCFLAGS = --release 8 -encoding UTF-8 -d $(BUILD) -implicit:none -sourcepath '$(SOURCE)' \
          -classpath '$(BUILD):$(LIBRAIRIES)mariadb-java-client-2.6.2.jar'

# --------------- L'outil d'exécution --------------- #
JVM      = java
JVMFLAGS = -classpath '$(BUILD):$(LIBRAIRIES)mariadb-java-client-2.6.2.jar'

# --------------- L'outil d'archivage --------------- #
JR      = jar
JRFLAGS = cvfem
JRFILE  = projetIHM2020.jar

# --------------- L'outil de suppression --------------- #
RM = rm -f


# --------------- Les règles --------------- #
jar: $(JRFILE)
	$(JR) $(JRFLAGS) $(JRFILE) fr.iutfbleau.Main Manifest.txt -C $(BUILD) ./ \
	                                                          -C ./ $(IMAGES) \
	                                                          -C ./ $(LIBRAIRIES)mariadb-java-client-2.6.2.jar

$(JRFILE): all

all: $(B_IUTFBLEAU)Main.class

test: $(B_MP)TestTexteMP.class


# --------------- L'API --------------- #
# --------------- Les interfaces --------------- #
$(B_API)Descriptible.class: $(S_API)Descriptible.java
	$(JC) $(JCFLAGS) $<

$(B_API)Activable.class: $(S_API)Activable.java
	$(JC) $(JCFLAGS) $<

$(B_API)Direction.class: $(S_API)Direction.java
	$(JC) $(JCFLAGS) $<

$(B_API)EtatPassage.class: $(S_API)EtatPassage.java \
                           $(B_API)Descriptible.class
	$(JC) $(JCFLAGS) $<

$(B_API)TypeTruc.class: $(S_API)TypeTruc.java \
                        $(B_API)Descriptible.class
	$(JC) $(JCFLAGS) $<

$(B_API)Truc.class: $(S_API)Truc.java \
                    $(B_API)Descriptible.class \
                    $(B_API)Activable.class \
                    $(B_API)TypeTruc.class
	$(JC) $(JCFLAGS) $<

$(B_API)ContientTrucs.class: $(S_API)ContientTrucs.java \
                             $(B_API)Truc.class
	$(JC) $(JCFLAGS) $<

$(B_API)TrucFactory.class: $(S_API)TrucFactory.java \
                           $(B_API)TypeTruc.class \
                           $(B_API)Truc.class \
                           $(B_API)ContientTrucs.class
	$(JC) $(JCFLAGS) $<

$(B_API)Piece.class: $(S_API)Piece.java \
                     $(B_API)Descriptible.class \
                     $(B_API)Direction.class \
                     $(B_API)ContientTrucs.class \
                     $(B_API)Passage.class
	$(JC) $(JCFLAGS) $<

$(B_API)Passage.class: $(S_API)Passage.java \
                       $(B_API)Descriptible.class \
                       $(B_API)Activable.class \
                       $(B_API)EtatPassage.class \
                       $(S_API)Piece.java
	$(JC) $(JCFLAGS) $<

$(B_API)PassagePieceFactory.class: $(S_API)PassagePieceFactory.java \
                                   $(B_API)Direction.class \
                                   $(B_API)Piece.class \
                                   $(B_API)Passage.class
	$(JC) $(JCFLAGS) $<

$(B_API)Joueur.class: $(S_API)Joueur.java \
                      $(B_API)Descriptible.class \
                      $(B_API)Activable.class \
                      $(B_API)ContientTrucs.class \
                      $(B_API)Piece.class
	$(JC) $(JCFLAGS) $<


# --------------- Les modèles non persistants --------------- #
$(B_MNP)TrucNP.class: $(S_MNP)TrucNP.java \
                      $(B_API)TypeTruc.class \
                      $(B_API)Truc.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)ContientTrucsNP.class: $(S_MNP)ContientTrucsNP.java \
                               $(B_API)Truc.class \
                               $(B_API)ContientTrucs.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)TrucFactoryNP.class: $(S_MNP)TrucFactoryNP.java \
                             $(B_API)TypeTruc.class \
                             $(B_API)Truc.class \
                             $(B_API)TrucFactory.class \
                             $(B_MNP)TrucNP.class \
                             $(B_MNP)ContientTrucsNP.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)PieceNP.class: $(S_MNP)PieceNP.java \
                       $(B_API)Direction.class \
                       $(B_API)Piece.class \
                       $(B_API)Passage.class \
                       $(B_MNP)ContientTrucsNP.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)PassageNP.class: $(S_MNP)PassageNP.java \
                         $(B_API)EtatPassage.class \
                         $(B_API)TypeTruc.class \
                         $(B_API)Truc.class \
                         $(B_API)Piece.class \
                         $(B_API)Passage.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)PassagePieceFactoryNP.class: $(S_MNP)PassagePieceFactoryNP.java \
                                     $(B_API)Direction.class \
                                     $(B_API)Piece.class \
                                     $(B_API)Passage.class \
                                     $(B_API)PassagePieceFactory.class \
                                     $(B_MNP)PieceNP.class \
                                     $(B_MNP)PassageNP.class
	$(JC) $(JCFLAGS) $<

$(B_MNP)JoueurNP.class: $(S_MNP)JoueurNP.java \
                        $(B_API)TypeTruc.class \
                        $(B_API)Truc.class \
                        $(B_API)Piece.class \
                        $(B_API)Joueur.class \
                        $(B_MNP)ContientTrucsNP.class
	$(JC) $(JCFLAGS) $<


# --------------- Le test de Florent Madelaine --------------- #
$(B_TEST)TestTexteMNP.class: $(S_TEST)TestTexteMNP.java \
                             $(B_API)Truc.class \
                             $(B_API)TrucFactory.class \
                             $(B_API)Piece.class \
                             $(B_API)Passage.class \
                             $(B_API)PassagePieceFactory.class \
                             $(B_API)Joueur.class \
                             $(B_MNP)TrucFactoryNP.class \
                             $(B_MNP)PassagePieceFactoryNP.class \
                             $(B_MNP)JoueurNP.class
	$(JC) $(JCFLAGS) $<


# --------------- Notre code --------------- #
# --------------- Le main --------------- #
$(B_IUTFBLEAU)Main.class: $(S_IUTFBLEAU)Main.java \
                          $(B_FENETRE)Fenetre.class
	$(JC) $(JCFLAGS) $<


# --------------- La fenêtre --------------- #
$(B_FENETRE)Fenetre.class: $(S_FENETRE)Fenetre.java \
                           $(B_MENU)Menu.class \
                           $(B_JEU)Jeu.class \
                           $(B_JEU)Instructions.class
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)Titre.class: $(S_FENETRE)Titre.java
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)Chargement.class: $(S_FENETRE)Chargement.java \
                              $(S_FENETRE)Fenetre.java
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)ChangerFenetre.class: $(S_FENETRE)ChangerFenetre.java \
                                  $(S_FENETRE)Fenetre.java
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)ChargerMenu.class: $(S_FENETRE)ChargerMenu.java \
                               $(S_FENETRE)Fenetre.java \
                               $(B_FENETRE)ChangerFenetre.class
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)CreerPartie.class: $(S_FENETRE)CreerPartie.java \
                               $(B_API)Direction.class \
                               $(B_API)EtatPassage.class \
                               $(B_API)TypeTruc.class \
                               $(B_API)Truc.class \
                               $(B_API)TrucFactory.class \
                               $(B_API)Piece.class \
                               $(B_API)Passage.class \
                               $(B_API)PassagePieceFactory.class \
                               $(B_API)Joueur.class \
                               $(B_MP)TrucFactoryP.class \
                               $(B_MP)PassagePieceFactoryP.class \
                               $(B_MP)JoueurP.class \
                               $(S_FENETRE)Fenetre.java \
                               $(B_FENETRE)Chargement.class \
                               $(B_FENETRE)ChangerFenetre.class \
                               $(B_JEU)Jeu.class
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)ChargerPartie.class: $(S_FENETRE)ChargerPartie.java \
                                 $(S_FENETRE)Fenetre.java \
                                 $(B_FENETRE)ChangerFenetre.class \
                                 $(B_JEU)Jeu.class \
                                 $(B_MP)JoueurP.class
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)ChargerInstructions.class: $(S_FENETRE)ChargerInstructions.java \
                                       $(S_FENETRE)Fenetre.java \
                                       $(B_FENETRE)ChangerFenetre.class
	$(JC) $(JCFLAGS) $<

$(B_FENETRE)Quitter.class: $(S_FENETRE)Quitter.java \
                           $(S_FENETRE)Fenetre.java \
                           $(B_FENETRE)ChangerFenetre.class
	$(JC) $(JCFLAGS) $<


# --------------- Les boutons --------------- #
$(B_BOUTONS)BoutonAbstrait.class: $(S_BOUTONS)BoutonAbstrait.java
	$(JC) $(JCFLAGS) $<

$(B_BOUTONS)BoutonMenu.class: $(S_BOUTONS)BoutonMenu.java \
                              $(B_BOUTONS)BoutonAbstrait.class
	$(JC) $(JCFLAGS) $<

$(B_BOUTONS)BoutonJeu.class: $(S_BOUTONS)BoutonJeu.java \
                             $(B_BOUTONS)BoutonAbstrait.class
	$(JC) $(JCFLAGS) $<


# --------------- Les menus --------------- #
$(B_MENU)Menu.class: $(S_MENU)Menu.java \
                     $(S_FENETRE)Fenetre.java \
                     $(B_MENU)MenuPrincipal.class \
                     $(B_JOUER)MenuJouer.class \
                     $(B_PARTIE)MenuPartie.class \
                     $(B_JEU)Jeu.class
	$(JC) $(JCFLAGS) $<

$(B_MENU)MenuAbstrait.class: $(S_MENU)MenuAbstrait.java \
                             $(B_FENETRE)Titre.class \
                             $(B_BOUTONS)BoutonMenu.class
	$(JC) $(JCFLAGS) $<

$(B_MENU)MenuPrincipal.class: $(S_MENU)MenuPrincipal.java \
                              $(B_FENETRE)ChargerInstructions.class \
                              $(B_FENETRE)Quitter.class \
                              $(S_MENU)Menu.java \
                              $(B_MENU)MenuAbstrait.class \
                              $(B_JOUER)Jouer.class
	$(JC) $(JCFLAGS) $<

$(B_MENU)ChangerMenu.class: $(S_MENU)ChangerMenu.java \
                            $(S_MENU)Menu.java
	$(JC) $(JCFLAGS) $<

$(B_MENU)Retour.class: $(S_MENU)Retour.java \
                       $(S_MENU)Menu.java \
                       $(B_MENU)ChangerMenu.class
	$(JC) $(JCFLAGS) $<


# --------------- Le menu pour jouer --------------- #
$(B_JOUER)MenuJouer.class: $(S_JOUER)MenuJouer.java \
                           $(S_MENU)Menu.java \
                           $(B_MENU)MenuAbstrait.class \
                           $(B_MENU)Retour.class \
                           $(B_PARTIE)MenuPartie.class \
                           $(B_PARTIE)Partie.class \
                           $(B_PARTIE)ChoisirPartie.class
	$(JC) $(JCFLAGS) $<

$(B_JOUER)Jouer.class: $(S_JOUER)Jouer.java \
                       $(S_MENU)Menu.java \
                       $(B_MENU)ChangerMenu.class
	$(JC) $(JCFLAGS) $<


# --------------- Le menu des parties --------------- #
$(B_PARTIE)MenuPartie.class: $(S_PARTIE)MenuPartie.java \
                             $(S_MENU)Menu.java \
                             $(B_PARTIE)Partie.class \
                             $(B_PARTIE)PartieVide.class
	$(JC) $(JCFLAGS) $<

$(B_PARTIE)Partie.class: $(S_PARTIE)Partie.java \
                         $(B_FENETRE)ChargerPartie.class \
                         $(S_MENU)Menu.java \
                         $(B_MENU)MenuAbstrait.class \
                         $(B_MENU)Retour.class \
                         $(B_PARTIE)SupprimerPartie.class
	$(JC) $(JCFLAGS) $<

$(B_PARTIE)PartieVide.class: $(S_PARTIE)PartieVide.java \
                             $(B_FENETRE)Titre.class \
                             $(B_FENETRE)CreerPartie.class \
                             $(B_BOUTONS)BoutonMenu.class \
                             $(S_MENU)Menu.java \
                             $(B_MENU)Retour.class
	$(JC) $(JCFLAGS) $<

$(B_PARTIE)ChoisirPartie.class: $(S_PARTIE)ChoisirPartie.java \
                                $(S_MENU)Menu.java \
                                $(B_MENU)ChangerMenu.class \
                                $(B_PARTIE)MenuPartie.class
	$(JC) $(JCFLAGS) $<

$(B_PARTIE)SupprimerPartie.class: $(S_PARTIE)SupprimerPartie.java \
                                  $(S_MENU)Menu.java \
                                  $(B_MENU)ChangerMenu.class
	$(JC) $(JCFLAGS) $<


# --------------- Le jeu --------------- #
$(B_JEU)Jeu.class: $(S_JEU)Jeu.java \
                   $(B_API)Joueur.class \
                   $(S_FENETRE)Fenetre.java \
                   $(B_FENETRE)Chargement.class \
                   $(B_DONJON)Donjon.class \
                   $(B_INVENTAIRE)Inventaire.class \
                   $(B_CARTE)Carte.class
	$(JC) $(JCFLAGS) $<

$(B_JEU)Instructions.class: $(S_JEU)Instructions.java \
                            $(S_FENETRE)Fenetre.java \
                            $(B_FENETRE)Titre.class \
                            $(B_FENETRE)ChargerMenu.class \
                            $(B_BOUTONS)BoutonJeu.class
	$(JC) $(JCFLAGS) $<

$(B_JEU)EnTete.class: $(S_JEU)EnTete.java \
                      $(B_FENETRE)Titre.class \
                      $(B_BOUTONS)BoutonJeu.class \
                      $(S_JEU)Jeu.java \
                      $(B_DONJON)AfficherDonjon.class
	$(JC) $(JCFLAGS) $<

$(B_JEU)ChangerJeu.class: $(S_JEU)ChangerJeu.java \
                          $(S_JEU)Jeu.java
	$(JC) $(JCFLAGS) $<


# --------------- Le donjon --------------- #
$(B_DONJON)Donjon.class: $(S_DONJON)Donjon.java \
                         $(B_API)Piece.class \
                         $(B_API)Joueur.class \
                         $(S_FENETRE)Fenetre.java \
                         $(S_JEU)Jeu.java \
                         $(B_DONJON)Salle.class \
                         $(B_DONJON)Actions.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)Salle.class: $(S_DONJON)Salle.java \
                        $(B_API)Direction.class \
                        $(B_API)Piece.class \
                        $(B_API)Joueur.class \
                        $(S_FENETRE)Fenetre.java \
                        $(S_DONJON)Donjon.java \
                        $(B_DONJON)Mur.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)Mur.class: $(S_DONJON)Mur.java \
                      $(B_API)Direction.class \
                      $(B_API)Piece.class \
                      $(B_API)Passage.class \
                      $(B_API)Joueur.class \
                      $(S_FENETRE)Fenetre.java \
                      $(S_DONJON)Donjon.java \
                      $(B_DONJON)Porte.class \
                      $(B_DONJON)ChangerPiece.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)Porte.class: $(S_DONJON)Porte.java \
                        $(B_API)EtatPassage.class \
                        $(B_API)Passage.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)Actions.class: $(S_DONJON)Actions.java \
                          $(B_API)Joueur.class \
                          $(S_FENETRE)Fenetre.java \
                          $(B_BOUTONS)BoutonJeu.class \
                          $(S_JEU)Jeu.java \
                          $(B_DONJON)Salle.class \
                          $(B_DONJON)TournerGauche.class \
                          $(B_DONJON)TournerDroite.class \
                          $(B_INVENTAIRE)Ramasser.class \
                          $(B_INVENTAIRE)AfficherInventaire.class \
                          $(B_CARTE)AfficherCarte.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)Tourner.class: $(S_DONJON)Tourner.java \
                          $(B_DONJON)Salle.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)TournerGauche.class: $(S_DONJON)TournerGauche.java \
                                $(B_DONJON)Salle.class \
                                $(B_DONJON)Tourner.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)TournerDroite.class: $(S_DONJON)TournerDroite.java \
                                $(B_DONJON)Salle.class \
                                $(B_DONJON)Tourner.class
	$(JC) $(JCFLAGS) $<

$(B_DONJON)ChangerPiece.class: $(S_DONJON)ChangerPiece.java \
                               $(B_API)EtatPassage.class \
                               $(B_API)Piece.class \
                               $(B_API)Passage.class \
                               $(B_API)Joueur.class \
                               $(S_FENETRE)Fenetre.java \
                               $(B_FENETRE)Chargement.class \
                               $(S_DONJON)Donjon.java
	$(JC) $(JCFLAGS) $<

$(B_DONJON)AfficherDonjon.class: $(S_DONJON)AfficherDonjon.java \
                                 $(S_JEU)Jeu.java \
                                 $(B_JEU)ChangerJeu.class
	$(JC) $(JCFLAGS) $<


# --------------- L'inventaire --------------- #
$(B_INVENTAIRE)Inventaire.class: $(S_INVENTAIRE)Inventaire.java \
                                 $(B_API)Truc.class \
                                 $(B_API)Joueur.class \
                                 $(S_FENETRE)Fenetre.java \
                                 $(S_JEU)Jeu.java \
                                 $(B_JEU)EnTete.class \
                                 $(B_DONJON)Salle.class \
                                 $(B_INVENTAIRE)Poche.class
	$(JC) $(JCFLAGS) $<

$(B_INVENTAIRE)Poche.class: $(S_INVENTAIRE)Poche.java \
                            $(B_API)Truc.class \
                            $(B_BOUTONS)BoutonJeu.class \
                            $(S_INVENTAIRE)Inventaire.java \
                            $(B_INVENTAIRE)Utiliser.class \
                            $(B_INVENTAIRE)Deposer.class
	$(JC) $(JCFLAGS) $<

$(B_INVENTAIRE)Ramasser.class: $(S_INVENTAIRE)Ramasser.java \
                               $(B_API)Truc.class \
                               $(B_API)Joueur.class \
                               $(S_FENETRE)Fenetre.java
	$(JC) $(JCFLAGS) $<

$(B_INVENTAIRE)Utiliser.class: $(S_INVENTAIRE)Utiliser.java \
                               $(B_API)Truc.class \
                               $(S_INVENTAIRE)Inventaire.java
	$(JC) $(JCFLAGS) $<

$(B_INVENTAIRE)Deposer.class: $(S_INVENTAIRE)Deposer.java \
                              $(B_API)Truc.class \
                              $(S_INVENTAIRE)Inventaire.java
	$(JC) $(JCFLAGS) $<

$(B_INVENTAIRE)AfficherInventaire.class: $(S_INVENTAIRE)AfficherInventaire.java \
                                         $(S_JEU)Jeu.java \
                                         $(B_JEU)ChangerJeu.class
	$(JC) $(JCFLAGS) $<


# --------------- La carte --------------- #
$(B_CARTE)Carte.class: $(S_CARTE)Carte.java \
                       $(B_API)Direction.class \
                       $(B_API)Piece.class \
                       $(B_API)Passage.class \
                       $(B_API)Joueur.class \
                       $(S_JEU)Jeu.java \
                       $(B_JEU)EnTete.class \
                       $(B_CARTE)CartePiece.class \
                       $(B_MP)PieceP.class
	$(JC) $(JCFLAGS) $<

$(B_CARTE)CartePiece.class: $(S_CARTE)CartePiece.java \
                            $(B_API)Direction.class \
                            $(B_API)Piece.class \
                            $(B_API)Joueur.class
	$(JC) $(JCFLAGS) $<

$(B_CARTE)AfficherCarte.class: $(S_CARTE)AfficherCarte.java \
                               $(S_JEU)Jeu.java \
                               $(B_JEU)ChangerJeu.class
	$(JC) $(JCFLAGS) $<

# --------------- Les modèles persistants --------------- #
$(B_MP)TrucP.class: $(S_MP)TrucP.java \
                    $(B_API)TypeTruc.class \
                    $(B_API)Truc.class
	$(JC) $(JCFLAGS) $<

$(B_MP)ContientTrucsP.class: $(S_MP)ContientTrucsP.java \
                             $(B_API)Truc.class \
                             $(B_API)ContientTrucs.class \
                             $(B_MP)TrucP.class
	$(JC) $(JCFLAGS) $<

$(B_MP)TrucFactoryP.class: $(S_MP)TrucFactoryP.java \
                           $(B_API)TypeTruc.class \
                           $(B_API)Truc.class \
                           $(B_API)TrucFactory.class \
                           $(B_MP)TrucP.class
	$(JC) $(JCFLAGS) $<

$(B_MP)PieceP.class: $(S_MP)PieceP.java \
                     $(B_API)Direction.class \
                     $(B_API)Truc.class \
                     $(B_API)Piece.class \
                     $(B_API)Passage.class \
                     $(B_MP)ContientTrucsP.class \
                     $(B_MP)PassageP.class
	$(JC) $(JCFLAGS) $<

$(B_MP)PassageP.class: $(S_MP)PassageP.java \
                       $(B_API)EtatPassage.class \
                       $(B_API)TypeTruc.class \
                       $(B_API)Truc.class \
                       $(B_API)Piece.class \
                       $(B_API)Passage.class \
                       $(S_MP)PieceP.java
	$(JC) $(JCFLAGS) $<

$(B_MP)PassagePieceFactoryP.class: $(S_MP)PassagePieceFactoryP.java \
                                   $(B_API)Direction.class \
                                   $(B_API)Piece.class \
                                   $(B_API)Passage.class \
                                   $(B_API)PassagePieceFactory.class \
                                   $(B_MP)PieceP.class \
                                   $(B_MP)PassageP.class
	$(JC) $(JCFLAGS) $<

$(B_MP)JoueurP.class: $(S_MP)JoueurP.java \
                      $(B_API)TypeTruc.class \
                      $(B_API)Truc.class \
                      $(B_API)Piece.class \
                      $(B_API)Joueur.class \
                      $(B_MP)ContientTrucsP.class \
                      $(B_MP)PieceP.class
	$(JC) $(JCFLAGS) $<

# --------------- Le test des modèles persistants --------------- #
$(B_MP)TestTexteMP.class: $(S_MP)TestTexteMP.java \
                          $(B_API)Truc.class \
                          $(B_API)TrucFactory.class \
                          $(B_API)Piece.class \
                          $(B_API)Passage.class \
                          $(B_API)PassagePieceFactory.class \
                          $(B_API)Joueur.class \
                          $(B_MP)TrucFactoryP.class \
                          $(B_MP)PassagePieceFactoryP.class \
                          $(B_MP)JoueurP.class
	$(JC) $(JCFLAGS) $<


# --------------- Les règles optionnelles --------------- #
run: $(JRFILE)
	$(JVM) -jar $(JRFILE)

# run: test
# 	$(JVM) $(JVMFLAGS) fr.iutfbleau.MP.TestTexteMP

clean:
	$(RM) -r $(BUILD)

mrproper: clean
	$(RM) $(JRFILE)

.PHONY: all \
        jar \
        all \
        clean \
        mrproper
