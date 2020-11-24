<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>

  <FORM action='<?php echo site_url('user/supprimer_quest')?>' method='post'>
    <div align=center>

      Quelle question souhaitez-vous supprimer ?
      <br>

      <SELECT name='modification' size='1'>
        <?php 
          $ques_numero=$this->session->userdata('ques_numero');

          foreach($ques_numero as $value=>$key){
              $valuetemp=$value+1;
              echo "<OPTION>".$valuetemp;
          }
        ?>
      </SELECT>

      <button id="envoyer" name="envoyer">Valider </button>
      <br> <br>
      Voici votre quizz :

      <br>
    </div>
  </FORM>

  <div align=center>
  <br>

    <table class='quizz'>

      <tr><th colspan=4><h3><?php echo $this->session->userdata('quizz_titre')?></h3></th><tr>
      <tr><th>Numéro</th><th>Libele</th><th>Image</th><th>Réponses</th><tr>

      <?php 
      $clé_quizz=$this->session->userdata('quizz_clé'); //chargement données de quizz
      $ques_libele=$this->session->userdata('ques_libele') ;
      $ques_image=$this->session->userdata('ques_image') ;

      foreach ($ques_numero as $value=>$key){ // affichage des questions une à une
        $valuetemp=$value+1;
        echo "<tr><td><p>".$valuetemp."<p></td>
        <td>".$ques_libele[$value]."</td>
        <td><img id='image' width='80%' src=".$ques_image[$value]." ></td><td><ul>"; 

        $data2=$this->users_model->get_reponses($clé_quizz,$ques_numero[$value]); //chargement des réponses

        foreach ($data2 as $value2=>$key2){ // affichage des réponses une à une
          $réponse= $data2[$value2]['rep_libele'];
          $statut= $data2[$value2]['rep_statut'];

          if($statut==="1"){
            echo "<li><font color='green'>".$réponse."</font></li>"; // couleur réponse change en fonction de si vraie ou fausse
          }else{
            echo "<li>".$réponse."</li>";
          }
        }
        echo "</ul></td></tr>";
      }
    ?>
  </table>
</div>
<br> 
</body>
</html>


 