<?php
class Formulaires extends CI_Controller {

        // On charge les données depuis les bases de données néncessaires 
        public function __construct()
        {
                parent::__construct();
                $this->load->model('formulaire_model');
                $this->load->model('reponse_participant_model');
                $this->load->model('question_model');
                $this->load->helper('url_helper');
        }


        public function index()
        {
                $data['formulaires'] = $this->formulaire_model->get_all();
                
            $this->load->view('templates/header');
            $this->load->view('formulaires/index',$data);
            $this->load->view('templates/footer');

        }

        // Fonction dont le but est de charger les données d'un formulaire 
        public function view($cle = NULL)
        {
                $data['formulaire'] = $this->formulaire_model->get_all($cle);

                $questions   = $this->question_model->find_data($cle); 
                foreach($questions as &$question) {
                        $question['reponses'] = $this->reponse_participant_model->find_data($cle,$question['que_numero']);
                        $question['counts'] = $this->reponse_participant_model->find_counts($cle,$question['que_numero']);
                        $chart_data = []; 
                        $chart_data['que_numero']= $question['que_numero'];
                        $chart_data['que_libelle'] = $question['que_libelle']; 
                        foreach($question['counts'] as $reponse) {
                                $chart_data['label'][] = $reponse['rpp_valeur'];
                                $chart_data['data'][] = (int) $reponse['total'];
                        }
            
                        $question['chart_data'] = $chart_data;  
                }

                $data['questions'] = $questions; 

       
              

                $this->load->view('templates/header'); 
                $this->load->view('formulaires/view', $data);
        }   
        
        //Fonction dont le but est de suprimer un formulaire 
        public function delete($cle = NULL) {
                $this->formulaire_model->delete($cle);
                $this->load->view('formulaires/delete');
                $this->load->view('templates/header'); 
        }

}