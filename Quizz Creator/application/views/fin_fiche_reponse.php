<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>
  
  <div align=center>

    <h3>Bravo ! Vous avez terminé le quizz !</h3>
    <br>
    Vous pourez consultez la correction ici (<i>seulement si le quizz est périmé</i>) :
    <br><br>
    
    <?php $clé=$this->session->userdata('quizz_clé');
      $id=$this->session->userdata('eleve_id');
      echo "<b><a href=".site_url('user/fiche_resultat/'.$clé.'/'.$id.'').">".site_url('user/fiche_resultat/'.$clé.'/'.$id.'')."</a></b>";
      $this->session->sess_destroy();
    ?>

  </div>

<br><br>
</body>
</html>


 