<html>
<head>
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>Connexion</title>
</head>
<?php echo form_error('prof_numen'); ?> 
<body>
  <div class="main">
    <p class="sign" align="center">Connexion</p>
    <form action="<?php echo site_url('user/login') ?>" method="post" accept-charset="utf-8">
      <input class="un " type="text" align="center" name="prof_numen" placeholder="numen">
      <input class="pass" type="password" align="center" name="prof_mdp" placeholder="mot de passe">
      <button type="submit" align="center" class="submit">Se connecter</button>
      <i>Avant de vous connecter pour la première fois, veuillez activer votre compte </i><a href=<?php echo site_url('contacts/index'); ?>>ici.</a>
   </form>                
  </div>
      
</body>
 
</html>