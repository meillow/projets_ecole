<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>Connexion</title>
</head>
<?php echo form_error('mem_email'); ?> 
<body>
  <div class="main">
    <p class="sign" align="center">Connexion</p>
    <form action="<?php echo site_url('user/login') ?>" method="post" accept-charset="utf-8">
      <input class="un " type="text" align="center" name="mem_email" placeholder="email">
      <input class="pass" type="password" align="center" name="mem_passwd" placeholder="Password">
      <button type="submit" align="center" class="submit">Se connecter</button>
      <i>Pas encore inscrit ? Cliquez </i><a href=<?php echo site_url('contacts/index'); ?>>ici.</a>
            <?php
        if($this->session->flashdata('error')){
          ?>
          <div class="alert alert-danger text-center" style="margin-top:20px;">
            <?php echo $this->session->flashdata('error'); ?>
          </div>
          <?php
        }
      ?>
     </form>                
    </div>
      
</body>
 
</html>