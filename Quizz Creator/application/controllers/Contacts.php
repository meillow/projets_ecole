<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Contacts extends CI_Controller {


	public function create(){
		$this->load->helper('form');
		$this->load->library('form_validation');
		$this->load->model('users_model');

		$this->form_validation->set_rules('prof_nom', 'Nom', 'required|trim');
		$this->form_validation->set_rules('prof_mdp', 'Mot de passe', 'required|trim');
		$this->form_validation->set_rules('prof_numen', 'Numen', 'is_unique[professeur.prof_numen]');
		
		if ($this->form_validation->run() == false){
			$this->load->view('templates/header');
			$this->load->view('inscription');
			$this->load->view('templates/footer');
		}else{

			$nom = $this->input->post('prof_nom');
			$pass = md5($this->input->post('prof_mdp'));
			$numen = $this->input->post('prof_numen');

			$data=array(
				'prof_nom'=>$nom,
				'prof_mdp'=>$pass,
				'prof_numen'=>$numen
			);

			if	($this->users_model->create_contact($data)){
				$this->load->view('templates/header');
				$this->load->view('ajouter_success', $data);
				$this->load->view('templates/footer');
			}
		}
	}

	public function index(){
		$this->load->helpers('form');
		$this->load->model('users_model');
		$this->load->library('table');
		$this->load->view('templates/header');
		$this->load->view('inscription');
		$this->load->view('templates/footer');
	}
	
}
?>
