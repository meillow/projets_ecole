<h2> Resultat </h2>

<?php foreach ($reponseparticipants as $reponseparticipant): ?>
        <h3><?php echo $reponseparticipant['rpp_prt_email']; ?></h3>
        <h4><?php echo $reponseparticipant['rpp_valeur']; ?></h4>
<?php endforeach; ?>