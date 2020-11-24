<html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>

<?php // chargement données de session de la question
  $num_question=$this->session->userdata('ques_numero_modif') ;
  $ques_numero=$this->session->userdata('ques_numero') ;
  $ques_libele=$this->session->userdata('ques_libele') ;
  $ques_image=$this->session->userdata('ques_image') ;
  $rep_libele=$this->session->userdata('rep_libele') ;
  $rep_statut=$this->session->userdata('rep_statut') ;
?>

<form method="post" action="<?php echo site_url('user/question_update') ?>">
  <div align=center>

    <div>
      <label  for="ques_libele">Libelle question</label>
      <input id="ques_libele" name="ques_libele" required type="text" value="<?php echo  $ques_libele[$num_question];?>",>
      <input type='hidden' name='ques_numero' value="<?php echo $num_question ?>">
      <label  for="ques_image">Choisissez votre image (optionnel)</label>
      <input type="text" name="ques_image" value="<?php  echo  $ques_image[$num_question];?>">
    </div>

    <br>
    <label  for="ques_libele">Entrez les réponses possibles (de 2 à 4), 
    et choisissez la/les ou aucune bonne réponse. </label>

  </div>       
  <div class="reponses">

    <div class="libelle">
      <input id="rep_libelle<?php echo $rep_statut['0'];?>" name="rep_libelle[1]" value="<?php echo $rep_libele['0'];?>" required type="text">
      <input id="rep_libelle<?php echo $rep_statut['1'];?>" name="rep_libelle[2]" value="<?php echo $rep_libele['1'];?>" required type="text">
      <input id="rep_libelle<?php if(isset($rep_statut['2'])){ echo $rep_statut['2'];}?>" 
             name="rep_libelle[3]" value="<?php if(isset($rep_libele['2'])) { //affiche les réponses si elles sont déjà présentes dans la base de données
                                                  echo $rep_libele['2'];
                                                }else{echo "";};
                                          ?>"  type="text">

      <input id="rep_libelle<?php if(isset($rep_statut['3'])){ echo $rep_statut['3'];}?>" 
             name="rep_libelle[4]" value="<?php if(isset($rep_libele['3'])){
                                                  echo $rep_libele['3'];
                                                }else{echo "";} ;
                                          ?>"  type="text">
    </div>      
          
    <div class="checkbox_creation">
      <input type="checkbox" id="checkbox" name="check_list[1]" value="1"><br/>
      <input type="checkbox" id="checkbox" name="check_list[2]" value="2"><br/>
      <input type="checkbox" id="checkbox" name="check_list[3]" value="3"><br/>
      <input type="checkbox" id="checkbox" name="check_list[4]" value="4"><br/>
    </div>

  </div>
  <input type='hidden' name='quizz_nombreQues' value="<?php echo $this->session->userdata('ques_actuel') ; ?>">
      
  <div align=center>
    <div class="_mts">
      <button id="envoyer" name="envoyer">Modifier</button>
    </div>
  </div>
  
</form>

</body>
 
</html>
 