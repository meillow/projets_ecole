
<head>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>Creation</title>
  <?php $que_numero=+1;?>
</head>
<body>
<div align=center>
    <form method="post" action="<?php echo site_url('user/question') ?>">
    <input type='hidden' name='que_frm_cle' value="<?php echo $que_frm_cle ?>">
      <input type='hidden' name='que_frm_titre' value="<?php echo $que_frm_titre ?>">
    <input type='hidden' name='que_numero' value="<?php echo $que_numero ?>">
    <p> Entrez le libellé de la question : </p>
    <input type="text" id="que_libelle" name="que_libelle">
    <p> Veuillez choisir le type de votre question  <?php echo $que_numero ?>:</p>
    <select name="que_type">
	<option value="Date">Date</option>
	<option value="Text">Text</option>
	<option value="Radio">Radio</option>
    <option value="Choix">Choix</option>
    <option value="Liste">Liste</option>
	</select>

    <br>
	<button type="submit" align="center" class="submit">Choisir les réponses</button>
    </div></div>
	</form>
    </div>
</body>