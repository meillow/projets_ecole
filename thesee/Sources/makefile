JFLAGS = -g -implicit:none
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		FenetreOrigine.java \
		CancelBouton.java \
		EcrireBit.java \
		Commencer.java \
		Fenetre.java \
		ExistanteGrille.java \
		NouvelleGrille.java \
		DessinCase.java \
		OkButton.java \
		Formulaire.java \
		ConfigurationCase.java \
		Sauvegarde.java \
		EtapeSuivante.java \
		Deroulement.java \
		Grille.java \
		Itineraire.java \
		Infos.java \
		Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		rm -rf $(RM) *.class
run : 
	java Main $(EXEC)
	
java Main : run 
	java Main $(EXEC)