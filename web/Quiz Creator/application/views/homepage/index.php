<!-- Page dont le but est d'afficher les formulaires de l'utilisateur  -->
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Espace membre</title>
</head>
<?php
 $array_items = array('ques_numero', 'ques_libele','ques_image','quizz_titre','statut',
 '','rep_libele','ques_numero_modif','ques_actuel','rep_statut');
 $this->session->unset_userdata($array_items);?>
<body>
  <div class="welcome_page">
    <h1> Bienvenue Mme/M <?php  print_r($this->session->userdata('prof_nom')); ?> </h1>

    <div align="center" class="main">
      <br>
    <p class="create" align="center">Que voulez-vous faire ?</p>
   
    <form action="<?php echo site_url('user/load_creer') ?>" method="post" accept-charset="utf-8">
  
      <button type="submit" align="center" class="submit">Créer un quizz</button>    

     </form>                
    </div>
    <br>
     <form action="<?php echo site_url('user/consulter_quizz') ?>" method="post" accept-charset="utf-8">
    <div align="center">
      <button type="submit" class="submit">Consulter mes quizz</button> 
    </div>

  </form>
    <br>
     <form action="<?php echo site_url('user/logout') ?>" method="post" accept-charset="utf-8">
    <div align="center">
      <button  type="submit"> Logout</button>        
    </div>
    </form>

  
  </div>

</body>

</html>