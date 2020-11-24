<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>
  <div class="main">
    <p class="create" align="center">Creation</p>

    <form action="<?php echo site_url('user/creer_form') ?>" method="post" accept-charset="utf-8">
      <p classe="title" align="center"> Entrez le titre de votre quizz : </p>
      <input class="Titre " type="text" align="center" name="quizz_titre" placeholder="titre">

      <p classe="title" align="center"> Entrez le nombre de questions souhaitées : </p>
      <input class="Description " type="number" align="center" name="quizz_nombreQues" placeholder="Nombre Question">
      <div align="center">
        
      <input type='hidden' name='num_question' value="1">

      <button type="submit" align="center" class="submit">Commencer</button>    
     </div>
    </form>                
    
  </div>
      
</body>
</html>