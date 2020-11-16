<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation Quizz</title> 
</head>
<body>
  <form method="post" action="<?php echo site_url('user/enregistrer_question_update') ?>">
    <div align=center>
    
      <div>
        <label  for="ques_libele">Libelle question</label>
        <input id="ques_libele" name="ques_libele" required type="text" placeholder="libelle de la question",>
        <input type='hidden' name='ques_numero' value="<?php echo $num_question ?>">
        <label  for="ques_image">Choisissez votre image (optionnel)</label>
        <input type="text" name="ques_image" placeholder="Entrez l'url de votre image.">
      </div>

      <br>
      <label  for="ques_libele">Entrez les réponses possibles (de 2 à 4), 
      et choisissez la/les ou aucune bonne réponse. </label>

    </div>
            
    <div class="reponses">

      <div class="libelle">
        <input id="rep_libelle" name="rep_libelle[1]" placeholder="entrez la réponse" required type="text">
        <input id="rep_libelle" name="rep_libelle[2]" placeholder="entrez la réponse" required type="text">
        <input id="rep_libelle" name="rep_libelle[3]" placeholder="entrez la réponse"  type="text">
        <input id="rep_libelle" name="rep_libelle[4]" placeholder="entrez la réponse"  type="text">
      </div>      
      <div class="checkbox_creation">
        <input type="checkbox" id="checkbox" name="check_list[1]" value="1"><br/>
        <input type="checkbox" id="checkbox" name="check_list[2]" value="2"><br/>
        <input type="checkbox" id="checkbox" name="check_list[3]" value="3"><br/>
        <input type="checkbox" id="checkbox" name="check_list[4]" value="4"><br/>
      </div>

    </div>

    <input type='hidden' name='num_question' value="<?php echo $num_question+1 ?>">
    <input type='hidden' name='quizz_nombreQues' value="<?php echo $quizz_nombreQues ?>">
        
    <div align=center>
      <div class="_mts">
        <button id="envoyer" name="envoyer">Créer</button>
      </div>
    </div>
  </form>
</div>
</body>
 
</html>
 