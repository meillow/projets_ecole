<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_contact extends CI_Model {
	public function __construct(){
	}
	public function set_contact($contact){
		//$email = $contact['email'];
		$data = array(
		//	'email'=>$email,
			'prof_nom' => $contact['prof_nom'],
			'prof_mdp' => $contact['prof_mdp'],
			'prof_numen' => $contact['prof_numen']
		);

		//$this->db->where('email', $email);
		return $this->db->update('professeur', $data);
		//return $this->db->replace('contacts',$data);

	}
public function get_contact_page($page,$total){
			$this->db->select('*')
				->from('professeur')
				->limit($total,$total*($page-1));
			$query = $this->db->get();
			return $query->result();
	}
	
	public function create_contact($data){
		return	$this->db->insert('professeur', $data);	

	}
	public function delete_contact($nom){
		return $this->db
			->where('prof_nom',$nom)
			->delete("professeur");

	}
	public function check_email($email){
	$this->db->select('prof_numen')
		->from('professeur')
		->where('prof_numen',$email);
	$query = $this->db->get();
	return ($query->num_rows() <= 1); 

	}
}
