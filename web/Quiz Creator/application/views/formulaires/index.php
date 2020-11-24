<div class="welcome_page">
<h2> Mes Formulaires </h2>


<!-- Boucle dont le but est d'imprimer les formulaires un à un  -->
<?php foreach ($formulaires as $formulaire): ?>

        <h3><?php echo $formulaire['frm_titre']; ?></h3>
        <div class="main">
                <?php echo $formulaire['frm_description']; ?>
        </div>

        
        <div id="buttonC">
                <a class="button1" href="<?php echo site_url('formulaires/view/'.$formulaire['frm_cle']); ?>" >  Voir résultat </a>
                <a class="button2" href="<?php echo site_url('formulaires/delete/'.$formulaire['frm_cle']); ?>" >  Supprimer formulaire </a>
        </div>

<?php endforeach; ?>


</div> 