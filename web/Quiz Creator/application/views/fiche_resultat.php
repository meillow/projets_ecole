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
    $resultat=round($resultat);
  ?>

  <br><b>Votre score est de : <?php echo $resultat?> %</b>
  <br><br> 
  Voici la correction pour chaque question:
  <br><br>

  <table class='quizz'>

    <tr><th colspan=4><h3><?php echo $this->session->userdata('quizz_titre')?></h3></th><tr>
    <tr><th>Numéro</th><th>Libele</th><th>Image</th><th>Réponses</th><th>Vos réponses</th><tr>

    <?php $clé_quizz=$this->session->userdata('quizz_clé'); //chargement données des questions du quizz sélectionnée
      $ques_numero=$this->session->userdata('ques_numero');
      $ques_libele=$this->session->userdata('ques_libele') ;
      $ques_image=$this->session->userdata('ques_image') ;
      $id=$this->session->userdata('eleve_id') ;

      foreach ($ques_numero as $value=>$key){
        $valuetemp=$value+1;
        echo "<tr><td><p>".$valuetemp."<p></td>
        <td>".$ques_libele[$value]."</td>
        <td><img id='image' width='80%' src=".$ques_image[$value]." ></td><td><ul>";
        $data2=$this->users_model->get_reponses($clé_quizz,$ques_numero[$value]); //chargement des réponses possibles

        foreach ($data2 as $value2=>$key2){
          $réponse= $data2[$value2]['rep_libele'];
          $statut= $data2[$value2]['rep_statut'];

          if($statut==="1"){ // changement de la couleur d'affichage de la réponse en fonction si vraie ou fausse
            echo "<li><font color='green'>".$réponse."</font></li>";
          }else{
            echo "<li>".$réponse."</li>";
          }
        }

        echo "</ul></td><td>";
        $data3=$this->users_model->get_reponses_eleve($clé_quizz,$ques_numero[$value],$id); // chargement des réponses de l'élèves
        
        if($data3==!null){

          foreach ($data3 as $value3=>$key3){ //affichage de seulement les réponses choisies
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


 