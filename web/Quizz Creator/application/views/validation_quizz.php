<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>
  <div align=center>
    <div class=felicitations> Félicitations ! </div>
    Votre quizz a bien été créé.
    <br> 
    Vous pouvez dès à présent le consulter/modifier ou bien le valider afin
    de générer le lien de partage.
  </div>

  <?php $clé=$this->session->userdata('quizz_clé') //récupération donnée de session;?>

  <div class="_mts" align=center>
      <a href="<?php echo site_url('user/consulter_quizz') ?>" ><button id="envoyer" name="envoyer" >Consulter mes quizz </button></a>
      <a href="<?php echo site_url('user/generation_quizzLien/'.$clé.'') ?>" ><button id="envoyer" name="envoyer">Générer le lien</button></a>
  </div>
</body>
</html>


 