<div class="welcome_page">
<h2> Mes Formulaires </h2>


<!-- Boucle dont le but est d'imprimer les formulaires un à un  -->
<form method="post" action="<?php echo site_url('user/enregistrer_quizz') ?>">
    <input type='hidden' name='id' value="<?php echo $id+1 ?>">
    <button id="envoyer" name="envoyer">Créer</button>
</form>


</div> 