<?php
class Formulaire_model extends CI_Model
{
        public function __construct()
        {
                parent::__construct();
                $this->load->database();
        }


        public function get_all($frm_cle = NULL)
        {
                if (!$frm_cle) {
                        $query = $this->db->get('formulaire');
                        return $query->result_array();
                }

                $query = $this->db->get_where('formulaire', array('frm_cle' => $frm_cle));
                // $query = $this->db->get('formulaire');
                return $query->row_array();
        }


        public function delete($cle = NULL)
        {
                $this->db->delete('reponseparticipant', array('rpp_frm_cle' => $cle));
                $this->db->delete('reponse', array('rep_frm_cle' => $cle));
                $this->db->delete('question', array('que_frm_cle' => $cle));
                $this->db->delete('formulaire', array('frm_cle' => $cle));
        }
}
