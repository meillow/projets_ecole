<head>
  <meta charset="UTF-8" />
  <title>Index Quizz</title>
</head>
<div class="welcome_page">
<body>
  <h1> Mes Quizz </h1>

  <table>
    <?php 
      $data=$this->session->userdata('quizz_titre');
      $statut=$this->session->userdata('quizz_statut') ;
      $clé=$this->session->userdata('quizz_clé') ;

      //affichage en tableau ligne par ligne de chaque quizz disponibles 
      echo "<tr><th>NOM</th>
                <th>STATUT<a class='infobulle'> 
                  <i class='fa fa-info-circle' aria-hidden='true'></i>
                  <span>
                    <b>Périmé :</b> le quizz n'est plus disponible, il ne peut plus être 
                    modifié/partagé et réactivé. <br>
                    <br><b>En préparation :</b> le quizz est en cours de modifications, il n'est plus disponible.
                    <br><br><b>Actif :</b> le quizz est disponible, il a un lien de partage, il n'est plus modifiable.
                  </span> 
                  </a>
                </th>

                <th>MODIFICATIONS</th>

                <th>RESULTATS<a class='infobulle'> 
                  <i class='fa fa-info-circle' aria-hidden='true'></i>
                  <span>
                    Consulter la moyenne générale des réponses à vos quizz. Ainsi que le détails des réponses
                    <br> de chaque élève.
                  </span> 
                  </a>
                </th>

                <th><P>CHANGEMENT STATUT</p></th>

                <th><p>PARTAGE</p><a class='infobulle'> 
                  <i class='fa fa-info-circle' aria-hidden='true'></i>
                  <span>
                    Le lien de partage de votre quizz<br> n'est disponible que
                    si votre quizz est actif
                  </span> 
                  </a>
                </th>
            </tr> ";
              
      foreach ($data as $value=>$key){ //affichage des données des quizz du professeur une à une, ligne par ligne.
              echo "<tr><th><p>".$data[$value]."</p></th> ";
              
              if ($statut[$value]==='en préparation'){ //si état en préparation alors affiche ces données
                
                  echo "<td><font color=orange > EN PREPARATION</font></td>
                  <td> 
                    <form action=".site_url('user/modifier_quizz')." method='post'>
                      <button type='submit' id='bouton_liste' 
                      value=".$clé[$value]
                      ." name='bouton_clic'>Modifier</button>
                    </form>
                  </td>

                  <td><p><font size='2em'><i>Non disponible</i></font></p></td>

                  <td> <form action=".site_url('user/statut')." method='post'>

                  <button type='submit' id='bouton_liste' 
                    value=".$clé[$value]
                    ." name='bouton_clic'>Changer le statut</button></form></td>
                  <td></td></tr>";

              }elseif ($statut[$value]==='actif'){ //si quizz actif alors
                  echo "<td><font color=green> ACTIF</font></td>
                  
                        <td class='share'><a class='infobulle' href="
                          .site_url('user/voir_quizz/'.$clé[$value].'')
                          ." ><i class='fa fa-search fa-2x' aria-hidden='true'></i>
                          <span>
                            Cliquez pour voir ce quizz.
                          </span> 
                          </a>
                        </td>

                        <td> <p><font size='2em'><i>Non disponible</i></font></p></td>

                        <td> 
                          <form action=".site_url('user/statut')." method='post'>
                            <button type='submit' id='bouton_liste' 
                            value=".$clé[$value]
                            ." name='bouton_clic'>Changer le statut</button>
                          </form> 
                        </td>

                        <td class='share'><a href="
                          .site_url('user/generation_quizzLien/'.$clé[$value].'')
                          ." ><i class='fa fa-share-alt fa-2x' 
                          aria-hidden='true' ></i></a>
                        </td>
                    </tr>";

              }else{
                  echo "<td><font color=red> PERIME</td>

                  <td class='share'>
                    <a class='infobulle' href="
                      .site_url('user/voir_quizz/'.$clé[$value].'')
                      ." ><i class='fa fa-search fa-2x' aria-hidden='true'></i>
                      <span>
                        Cliquez pour voir ce quizz.
                      </span> 
                    </a>
                  </td>
                  <td> 
                  <form action=".site_url('user/resultat_quizz')." method='post'>
                    <button type='submit' id='bouton_liste' 
                    value=".$clé[$value]
                    ." name='bouton_clic'>Résultats</button>
                    </form>
                  </td>
                  <td><p><font size='2em'><i>Non disponible quand périmé</i></font></p></td><td></td></font><br>";
              }
                      
    }?>
    <br>
  </table>
  </div>
<body>