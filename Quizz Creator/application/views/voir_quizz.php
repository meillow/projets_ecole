<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Mes quizz</title>
</head>
<body>
  <div align=center>
    Voici votre quizz :
    <br>
  </div>

  <div align=center>
  <br>
    <table class='quizz'>
      <tr><th colspan=4><h3><?php echo $this->session->userdata('quizz_titre')?></h3></th><tr>
      <tr><th>Numéro</th><th>Libele</th><th>Image</th><th>Réponses</th><tr>
      
      <?php // chargement des données de session
        $clé_quizz=$this->session->userdata('quizz_clé');
        $ques_numero=$this->session->userdata('ques_numero');
        $ques_libele=$this->session->userdata('ques_libele') ;
        $ques_image=$this->session->userdata('ques_image') ;
        
        foreach ($ques_numero as $value=>$key){
          $valuetemp=$value+1;//calcul de numéro de la question
          echo "<tr><td><p>".$valuetemp."<p></td>
          <td>".$ques_libele[$value]."</td>
          <td><img id='image' width='80%' src=".$ques_image[$value]." ></td><td><ul>";//affichage libelle question,image et numéro

          $data2=$this->users_model->get_reponses($clé_quizz,$ques_numero[$value]); //acquisition des réponses possibles aux questions
          
          foreach ($data2 as $value2=>$key2){ //affichage des réponses possibles une à une
            $réponse= $data2[$value2]['rep_libele'];
            $statut= $data2[$value2]['rep_statut'];
          
            if($statut==="1"){// changemnt de couleur en fonction de si bonne ou mauvaise question
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


 