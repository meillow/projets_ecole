<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>
  <div align=center>
    Voici le lien de partage de votre quizz :
    <br><br>

    <?php 
      $clé=$this->session->userdata('quizz_clé');
      echo "<b><a href=".site_url('user/fiche_reponse/'.$clé.'').">".site_url('user/fiche_reponse/'.$clé.'')."</a></b>"//lien de partage du quizz;
    ?>

  </div>

  <br>
  <div class="_mts" align=center>
    <a href="<?php echo site_url('user/accueil') ?>"><button id="envoyer" name="envoyer" >Acceuil </button>
  </div>
  <br>
</body>
</html>


 