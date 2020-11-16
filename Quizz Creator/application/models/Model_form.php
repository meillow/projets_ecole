<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_form extends CI_Model {
	public function __construct(){
	}
	public function set_contact($contact){
		//$email = $contact['email'];
		$data = array(
		//	'email'=>$email,
			'mem_pseudo' => $contact['mem_pseudo'],
			'mem_passwd' => $contact['mem_passwd'],
			'mem_email' => $contact['mem_email']
		);

		//$this->db->where('email', $email);
		return $this->db->update('membre', $data);
		//return $this->db->replace('contacts',$data);

	}

	public function create_form($data){
		return	$this->db->insert('formulaire', $data);	

	}
}
