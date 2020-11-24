<head>
  <link rel="stylesheet" href="css/style.css">
  <title>Creation</title>
</head>
<body>

<div clas="main" align=center>
            <br>
            <p> Veuillez le nombre de réponses disponibles : </p>
            <form action= "<?php echo site_url("user/get_num_rep")?>" method="POST" action="?">
            <select name="nombre_rep">
            <option value="deux">2</option>
            <option value="trois">3</option>
            <option value="quatre">4</option>
            <option value="cinq">5</option>
            </select>
            <br>
            <input type='hidden' name='que_frm_cle' value="<?php echo $que_frm_cle ?>">
      <input type='hidden' name='que_frm_titre' value="<?php echo $que_frm_titre ?>">
    <input type='hidden' name='que_numero' value="<?php echo $que_numero ?>">
    <input type='hidden' name='que_libelle' value="<?php echo $que_libelle ?>">
<button type="submit" align="center" class="submit">Choisir les réponses</button>
 </div>
 </form>