<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>
<div align=center>

  <?php 
    $resultat=$this->session->userdata('resultat') ;
    $nom=$this->session->userdata('nom_eleve') ;
    ?>

    <br><b>Le score de <?php echo $nom ;?> est de : <?php echo $resultat?> %</b>
    <br><br> 
    Voici la correction et les réponses de <?php echo $nom ;?> :
    <br><br>

    <table class='quizz'>
      <tr><th colspan=4><h3><?php echo $this->session->userdata('quizz_titre')?></h3></th><tr>
      <tr><th>Numéro</th><th>Libele</th><th>Image</th><th>Réponses</th><th>Vos réponses</th><tr>

      <?php // chargement données du quizz sélectionné
        $clé_quizz=$this->session->userdata('quizz_clé');
        $ques_numero=$this->session->userdata('ques_numero');
        $ques_libele=$this->session->userdata('ques_libele') ;
        $ques_image=$this->session->userdata('ques_image') ;
        $id=$this->session->userdata('eleve_id') ;

        foreach ($ques_numero as $value=>$key){ //affichage données des questions une à une
          $valuetemp=$value+1;
          echo "<tr><td><p>".$valuetemp."<p></td>
          <td>".$ques_libele[$value]."</td>
          <td><img id='image' width='80%' src=".$ques_image[$value]." ></td><td><ul>";

          $data2=$this->users_model->get_reponses($clé_quizz,$ques_numero[$value]); //chargement des réponses disponibles pour chaque question

          foreach ($data2 as $value2=>$key2){ //affichage réponses disponibles pour chaque questions du quizz
            $réponse= $data2[$value2]['rep_libele'];
            $statut= $data2[$value2]['rep_statut'];

            if($statut==="1"){ //changement de couleur de la réponse en fonction si vraie ou fausse
            echo "<li><font color='green'>".$réponse."</font></li>";
            }else{
              echo "<li>".$réponse."</li>";
            }
          }
          echo "</ul></td><td>";

          $data3=$this->users_model->get_reponses_eleve($clé_quizz,$ques_numero[$value],$id); //chargement réponses de l'élève
          
          if($data3==!null){

            foreach ($data3 as $value3=>$key3){ //affiche que les réponses saisies
              $réponse_eleve= $data3[$value3]['repEleve_rep_numero'];
              echo "<li>".$data2[$réponse_eleve-1]['rep_libele']."</li>";
            }
          }
          echo "</ul></td></tr>";
        }
      ?>
    </table>
    
  </div>
<br>

<br>
</body>
</html>


 