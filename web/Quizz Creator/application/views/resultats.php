<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>

<?php 
  $liste_eleve=$this->session->userdata('liste_eleve') ;
  $quizz_clé=$this->session->userdata('quizz_clé') ;
  $score_quizz=$this->session->userdata('score_quizz') ;
?>

<div align=center>
  <br><b>Ce quizz a une moyenne de <?php echo $score_quizz ; ?>%.</b>
  <br><br>

  <FORM action='<?php echo site_url('user/consulter_resultat');?>' method='post'>
    Quelles réponses souhaitez-vous consulter en détail ?<br>
    <SELECT name='modification' size='1'>
      <?php //affiche liste des élèves ayant répondu au quizz
        $ques_numero=$this->session->userdata('ques_numero');
        foreach($liste_eleve as $value=>$key){
            echo "<OPTION>".$liste_eleve[$value]['eleve_nom']." ".$liste_eleve[$value]['eleve_prenom'];
        }
      ?>
    </SELECT>
    <button id="envoyer" name="envoyer">Valider </button>
  </FORM>

</div>
  <br> <br><br> 



 