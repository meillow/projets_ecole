 <div class="main">
                 <form action="<?php echo site_url("user/get_rep1")?>" method="post" accept-charset="utf-8">
                      <p classe="title" align="center"> Entrez le le libellé de la réponse 3 : </p>
                   <input class="Titre " type="text" align="center" name="rep2" placeholder="réponse">
                    <input type="hidden" name="rep_frm_cle" value="<?php echo $rep_frm_cle ?>">
                    <input type="hidden" name="rep_que_numero" value="<?php echo $rep_que_numero ?>">
                    <input type="hidden" name="rep_frm_titre" value="<?php echo $rep_frm_titre ?>">
                    <input type="hidden" name="rep_numero" value="<?php echo $rep_numero ?>">
                   <button type="submit" class="submit">Question suivante</button>    
                 </div>
                   </form>                
                 </div> 