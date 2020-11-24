#
# Remet en forme le code généré par StarUML.
# !/bin/bash
#


for i in $(find ./src/fr/iutfbleau/ -type f -name *.java)
do
  sed -i 's/"\/home\/antoni\/Documents\/projetIHM2020\/img\/\(.*\)"/Thread.currentThread().getContextClassLoader().getResource("img\/\1")/g' $i
done

exit 0
