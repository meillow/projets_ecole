<div grid>
	<div column="+3 6">
		<?php echo validation_errors(); ?>
		<?php echo form_open('contacts/create',array()); ?>
		<fieldset>
			<legend>Inscription</legend>

			<!-- Text input-->
			<div>
				<label  for="mem_pseudo">Pseudo</label>
				<input value="<?=set_value('mem_pseudo')?>" id="mem_pseudo" name="mem_pseudo" placeholder="Pseudo"  required type="text">
			</div>

			<!-- Text input-->
			<div >
				<label  for="mem_passwd">Mot de passe</label>
				<input value="<?=set_value('mem_passwd')?>" id="mem_passwd" name="mem_passwd" placeholder="Mot de passe"  required type="password">
			</div>

			<!-- mem_email input-->
			<div >
				<label  for="mem_email">Email</label>
				<input value="<?=set_value('mem_email')?>" id="mem_email" name="mem_email" placeholder="Adresse Mail" required type="email">
			</div>

			<!-- Button -->
			<div class="_mts">
				<button id="envoyer" name="envoyer">Créer</button>
		
			</div>

		</fieldset>
	</form>
</div>
</div>
