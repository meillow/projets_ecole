<?php
class Form_model extends CI_Model {
  
    public function __construct()
    {
        $this->load->database();
    }
     
    public function get_email($data)
    {
        $query = $this->db->get_where('membre', $data);
        if($query){   
         return $query->row();
        }
        return false;
    }
    public function auth_check($data){

         if (password_verify($data)) {
            return true;
        } else {
            return false;
        }
    
    }

}
?>