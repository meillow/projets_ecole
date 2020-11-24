<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Modification quizz</title>
</head>
<body>
  <div align=center>
    Combien de question souhaitez-vous ajouter ?

    <FORM action='<?php 
      echo site_url('user/ajout_question')?>' method='post'>
      <input type="number" name="howmuch">
      <button id="envoyer" name="envoyer">Valider </button>
   
    </FORM>
  </div>
</body>
</html>


 