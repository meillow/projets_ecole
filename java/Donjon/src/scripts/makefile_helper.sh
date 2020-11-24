#
# Ajoute les régles les plus simples pour l'API dans le Makefile.
# !/bin/bash
#


for i in $(find ./src/fr/iutfbleau/projetIHM2020FI2/ -type f -name *.java)
do
  # Le chemin
  chemin=$(sed 's/\.\/src\/fr\/iutfbleau\/projetIHM2020FI2\/\(.*\)\/.*\.java/\1/g' <<< $i)
  # et le nom du fichier.
  nom=$(sed "s/\.\/src\/fr\/iutfbleau\/projetIHM2020FI2\/$chemin\/\(.*\)\.java/\1/g" <<< $i)

  echo "" >> Makefile # Au cas où le Makefile existe déjà.

  # Ajout des règles simples.
  echo "\$(B_${chemin^^})${nom}.class: \$(S_${chemin^^})${nom}.java" >> Makefile
  echo '  $(JC) $(JCFLAGS) $<' >> Makefile
done

exit 0
