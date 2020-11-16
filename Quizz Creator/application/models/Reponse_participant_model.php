<?php
class Reponse_participant_model extends CI_Model {
        public function __construct()
            {
                parent::__construct();
                $this->load->database(); 
            }
        

        public function find_all() 
        {
            $query = $this->db->get('reponseparticipant');
            return $query->result_array();
        }

        public function find_data($rpp_frm_cle, $rpp_que_numero) 
        {

            $query = $this->db->get_where('reponseparticipant', array('rpp_frm_cle' => $rpp_frm_cle, 'rpp_que_numero' => $rpp_que_numero));
            return $query->result_array();

        }

        public function find_counts($rpp_frm_cle, $rpp_que_numero) 
        {

       $query = $this->db
            ->select('rpp_valeur, COUNT(rpp_valeur) as total')
            ->where(array('rpp_frm_cle' => $rpp_frm_cle))
            ->where(array('rpp_que_numero' => $rpp_que_numero))
            ->group_by('rpp_valeur')
            ->order_by('total', 'desc')
            ->get('reponseparticipant', 10);

        return $query->result_array();

        }

}

