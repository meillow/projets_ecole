<?php
class Question_model extends CI_Model {
        public function __construct()
            {
                parent::__construct();
                $this->load->database(); 
            }
        

        public function find_data($rpp_frm_cle) 
        {

            $query = $this->db->get_where('question', array('que_frm_cle' => $rpp_frm_cle));
            return $query->result_array();

        }
}