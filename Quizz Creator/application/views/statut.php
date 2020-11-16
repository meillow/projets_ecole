<head>
  <meta charset="UTF-8" />
  <title>Modification Statut</title>
</head>
<body>
  <div align=center>
    
    Le statut actuel de votre quizz est : 
    <b><?php echo $statut=$this->session->userdata('statut');//récupération données de session statut du quizz?></b>.<br>

    Choisissez le nouveau statut 
    <i><font size='3em'>(attention une fois périmé, le statut et le quizzne pourront plus être modifiés)</font></i> :

      <FORM action='<?php echo site_url('user/consulter_quizz')?>' method='post'>
        <SELECT name='statut' size='1'>
          <OPTION>périmé
          <?php 
            if ($statut==='en préparation'){
              echo " <OPTION>actif";//affichage du choix de changement de statut en fonction du statut actuel
            }
          ?>
        </SELECT>
      <br> 
    
        </div>
      <br>
        <div class="_mts" align=center>
          <button id="envoyer" name="envoyer">Valider </button>
        </div>
        <br>
      </FORM>
</body>



 