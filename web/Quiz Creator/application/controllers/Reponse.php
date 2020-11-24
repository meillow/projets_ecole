<?php
class Reponse extends CI_Controller {

        public function __construct()
        {
                parent::__construct();
                $this->load->model("question_model");
                $this->load->helper('url_helper');
        }

        
        public function view($rpp_frm_cle = NULL)
        {
                $data['question'] = $this->question_model->get_all($rpp_frm_cle);
                $this->load->view('reponse/index', $data);
        }       

}

