<div grid>
	<div column="+3 6">
		<?php echo validation_errors(); ?>
		<?php echo form_open('contacts/create',array()); ?>
		<fieldset>
			<legend>Activation compte</legend>

			<div>
				<label  for="prof_nom">Nom</label>
				<input value="<?=set_value('prof_nom')?>" id="prof_nom" name="prof_nom" placeholder="Nom"  required type="text">
			</div>
		
			<div >
				<label  for="mem_passwd">Mot de passe</label>
				<input value="<?=set_value('prof_mdp')?>" id="prof_mdp" name="prof_mdp" placeholder="Mot de passe"  required type="password">
			</div>

			<div >
				<label  for="prof_numen">Numen</label>
				<input value="<?=set_value('prof_numen')?>" id="prof_numen" name="prof_numen" placeholder="Numen" required type="text">
			</div>

			<div class="_mts">
				<button id="envoyer" name="envoyer">Créer</button>
		
			</div>

		</fieldset>
	</form>
</div>
</div>

