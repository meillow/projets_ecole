<!-- Controlleur dont le but est de charger les données nécessaires à l'affichage des camemberts -->
<?php
 
defined('BASEPATH') OR exit('No direct script access allowed');
 
class Chart extends CI_Controller {
 
    
    public function __construct()
    {
            parent::__construct();
            $this->load->model("reponse_participant_model");
            $this->load->helper('url_helper');
    }

    
    public function index($rpp_frm_cle = NULL)
    {       
            $data['reponseparticipants'] = $this->reponse_participant_model->get_all($rpp_frm_cle);
            $chart_data = []; 
  
            foreach($data['reponseparticipants'] as $reponse) {
                            $chart_data['label'][] = $reponse['rpp_valeur'];
                            $chart_data['data'][] = (int) $reponse['total'];
            }

            $data['chart_data'] = json_encode($chart_data);
            $this->load->view('pie_chart/index',$data);
    }    
     
}


