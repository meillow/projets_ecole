<!-- page dont le but est d'imprimer les résultats d'un formulaire sous forme de statistiques -->
<h2><?php echo $formulaire['frm_titre']; ?></h2>
<p> <?php echo $formulaire['frm_description']; ?> </p>

<h2>   Resultat </h2>


<?php foreach ($questions as $question): ?>
        
        <?php $this->load->view('formulaires/pie_chart',['chart_data' => $question['chart_data']]); ?>

<?php endforeach; ?>

