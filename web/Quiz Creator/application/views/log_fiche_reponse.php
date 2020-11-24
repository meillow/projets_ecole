<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>

  <div align=center>

    <?php 
      $prof_nom=$this->session->userdata('nom_prof');
    ?>

    <h3>Bienvenue sur le quizz de Mme/M <?php echo $prof_nom ;?></h3>
    <br>
    Pour commencer, veuillez vous identifier :

    <form method="post" action="<?php echo site_url('user/load_fiche_reponse') ?>">
      
      <div>
        <label  for="eleve_nom">Entrez votre nom :</label>
        <input id="eleve_nom" name="eleve_nom" required type="text" placeholder="nom",>
        <label  for="eleve_prénom">Entrez votre prénom :</label>
        <input id="eleve_prénom" name="eleve_prénom" required type="text" placeholder="prénom",>
      </div>
      <br>
      <div align=center>
      <button id="envoyer" name="envoyer">Commencer</button>
      </div>
    </form>

  </div>
  
<br><br>
</body>
</html>


 