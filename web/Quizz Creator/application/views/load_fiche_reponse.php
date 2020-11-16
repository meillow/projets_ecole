<html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
  <title>Creation</title>
</head>
<body>

  <?php //chargement donnée de la question du quizz correspondant
    $num_ques=$this->session->userdata('num_ques') ;
    $rep_libele=$this->session->userdata('rep_libele') ;
    $ques_libele=$this->session->userdata('ques_libele') ;
    $ques_image=$this->session->userdata('ques_image') ;;
    $titre=$this->session->userdata('titre_quizz') ;
  ?>

  <form method="post" action="<?php echo site_url('user/load_fiche_reponse2') ?>">
    
    <div align=center>
      <div>
        <h2><?php echo $titre;?></h2></div>
        <br>  
        
        <?php echo "question ".$num_ques." :";?>

        <h4><?php echo $ques_libele[0] ; ?></h4>

        <?php if(isset($ques_image[0])){
              echo "<img src=".$ques_image[0].">";}
        ?>

        <br><br>
        <label  for="choix">Cochez la/les ou aucune des réponses suivantes : </label>
        <br>

        <?php foreach($rep_libele as $value=>$key){ //affiche les réponses possibles
                $valutTemp=$value+1;
                echo "<div class='choix'>" .$rep_libele[$value]['rep_libele']."
                <input type='checkbox' id='checkboxChoix' name='check_list[".$valutTemp."]' value=".$valutTemp."></div>";
              }
        ?>    
      </div>
    </div>
         
    <div align=center>
      <div class="_mts">
      <button id="envoyer" name="envoyer">Valider</button>
    </div>

  </form>

</body>
 
</html>
 