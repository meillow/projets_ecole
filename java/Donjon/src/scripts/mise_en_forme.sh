#
# Remet en forme le code généré par StarUML.
# !/bin/bash
#


for i in $(ls *.java)
do
  # Pour l'import inutile.
  sed -i 's/import java\.util\.\*;//g' $i

  # Pour les accolades.
  sed -i 's/^\( *\)\(.*\) {/\1\2\n\1{/g' $i

  # Pour la javadoc.
  sed -i 's/^\( *\)\* @\(.*\)$/\1\*\n\1\* @\2/g' $i

  # Pour la version.
  sed -i 's/^\( *\)\* @author \(.*\)$/\1\* @version 1\.0\n\1\* @author \2/g' $i

  # Pour le constructeur.
  j=$(expr substr $i 1 $(($(expr length $i)-5))) # Le nom de la classe.
  sed -i "s/public void $j/public $j/g" $i

  # Pour la méthode actionPerformed().
  sed -i 's/^\( *\)\(public void actionPerformed\)/\1@Override\n\1\2/g' $i
done

exit 0
