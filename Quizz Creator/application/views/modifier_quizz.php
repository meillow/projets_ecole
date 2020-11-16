<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Modification Quizz</title>
</head>
<body>
  <div align=center>
    Que souhaitez vous faire ?

    <FORM action='<?php echo site_url('user/choix_modif')?>' method='post'>
      
      <SELECT name='modification' size='1'>
        <OPTION>Ajouter une question
        <OPTION>Supprimer une question
        <OPTION>Modifier une question
      </SELECT>
      
      <button id="envoyer" name="envoyer">Valider </button>
      <br> <br>
      Voici votre quizz :
      <br>

    </FORM>

  </div>

<div align=center>
  <br>
  <table class='quizz'>
    <tr><th colspan=4><h3><?php echo $this->session->userdata('quizz_titre')?></h3></th><tr>
    <tr><th>Numéro</th><th>Libele</th><th>Image</th><th>Réponses</th><tr>

    <?php //chargement données de session du quizz choisi
      $clé_quizz=$this->session->userdata('quizz_clé');
      $ques_numero=$this->session->userdata('ques_numero');
      $ques_libele=$this->session->userdata('ques_libele') ;
      $ques_image=$this->session->userdata('ques_image') ;

      foreach ($ques_numero as $value=>$key){ // affichage une à une des données
        $valuetemp=$value+1;
        echo "<tr><td><p>".$valuetemp."<p></td>
        <td>".$ques_libele[$value]."</td>
        <td><img id='image' width='80%' src=".$ques_image[$value]." ></td><td><ul>";

        $data2=$this->users_model->get_reponses($clé_quizz,$ques_numero[$value]); // récupération réponses possibles

        foreach ($data2 as $value2=>$key2){ // affichage réponse une à une par question
          $réponse= $data2[$value2]['rep_libele'];
          $statut= $data2[$value2]['rep_statut'];

          if($statut==="1"){
            echo "<li><font color='green'>".$réponse."</font></li>";
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


 