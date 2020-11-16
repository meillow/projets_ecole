<?php
class Results extends CI_Controller {

        public function __construct()
        {
                parent::__construct();
                $this->load->model("Reponse_participant_model");
                $this->load->helper('url_helper');
        }

        
        public function view($rpp_frm_cle = NULL)
        {
                $data['reponseparticipant'] = $this->reponse_participant_model->get_all($rpp_frm_cle);
                $this->load->view('results/index', $data);
        }       

}

