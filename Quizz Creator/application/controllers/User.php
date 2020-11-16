    <?php
    defined('BASEPATH') OR exit('No direct script access allowed');
     
    class User extends CI_Controller {
     
    	function __construct(){
    		parent::__construct();
    		$this->load->helper('url');
    		$this->load->model('users_model');
    	}

    	public function login(){
    		
            $this->load->library('form_validation');
    
            $this->form_validation->set_rules('prof_mdp', 'Mot de passe', 'required|trim');
            $this->form_validation->set_rules('prof_numen', 'Numen', 'required|trim');
        
        if ($this->form_validation->run() == false){
            $this->load->view('templates/header');
            $this->load->view('accueil');
            $this->load->view('templates/footer');

        }else{
    		$numen =  $this->input->post('prof_numen');
            $passwd =  $this->input->post('prof_mdp'); 
    		$row = $this->users_model->login($numen, $passwd);
            
    		if($row==4){
                $nom=$this->users_model->get_nom($numen);
                $data=array(
                    'prof_nom'=>$nom,
                    'prof_numen'=>$numen,
                );  
                $this->session->set_userdata($data);
                $this->load->view('templates/header');
                $this->load->view('homepage/index');
                $this->load->view('templates/footer2');
    		
            }elseif ($row==1) {
                
                $this->load->view('templates/header');
                $this->load->view('accueil');
                $this->load->view('templates/footer');
                $this->session->set_flashdata('error','Invalid numen. User not found');
            }
    		elseif ($row==2){
                
    			$this->load->view('templates/header');
                $this->load->view('accueil');
                $this->load->view('templates/footer');
    			$this->session->set_flashdata('error','Invalid login. User not found');
    		}
    	}
    }
     
    	public function home(){

    		$this->load->library('session');
    		$this->load->view('templates/header');
            $this->load->view('accueil');
            $this->load->view('templates/footer');
    	}
     
    	public function logout(){
    		
    		$this->session->sess_destroy();
    		$this->load->view('templates/header');
            $this->load->view('accueil');
            $this->load->view('templates/footer');
        }

        public function accueil(){
            $array_items = array('liste_eleve','score_quizz','quizz_selec');
            $this->session->unset_userdata($array_items);
            $this->session->unset_userdata('quizz_clé');
    		$this->load->view('templates/header');
            $this->load->view('homepage/index');
            $this->load->view('templates/footer2');
        }

        public function load_creer(){
            $this->load->library('form_validation');
            $this->load->view('templates/header');
            $this->load->view('creation_formulaire');
            $this->load->view('templates/footer2');
        }

        public function creer_form(){
            $this->load->helper('form');
            $this->load->library('form_validation');
            $this->load->library('encryption');
            $this->load->model('users_model');
    
            $this->form_validation->set_rules('quizz_titre', 'Titre', 'required|trim');
            $this->form_validation->set_rules('quizz_nombreQues', 'Nombre question', 'required|trim');
            
            if ($this->form_validation->run() == false){
                $this->load->view('templates/header');
                $this->load->view('creation_formulaire');
                $this->load->view('templates/footer2');
            }else{
                $numen=$this->session->userdata('prof_numen');
                $titre = $this->input->post('quizz_titre');      
                $quizz_nombreQues = $this->input->post('quizz_nombreQues');
                $num_question = $this->input->post('num_question');
                $statut='en préparation';
                $cle=bin2hex($this->encryption->create_key(6));
                
                $data=array(
                    'quizz_titre'=>$titre,
                    'quizz_clé'=>$cle,
                    'quizz_prof_numen'=>$numen,
                    'quizz_statut'=>$statut,
                    'quizz_nombreQues'=>$quizz_nombreQues

                    
                );   

                if  ($this->users_model->create_quizz($data)){
                    $data=array(
                        'num_question'=>$num_question,
                        'quizz_nombreQues'=>$quizz_nombreQues,    
                    );  

                    $cle_quizz=array('quizz_clé'=>$cle) ;
                    $this->session->set_userdata($cle_quizz);
                    $this->load->view('templates/header');
                    $this->load->view('question',$data);
                    $this->load->view('templates/footer2');
                }else{
                    $this->load->view('templates/header');
                    $this->load->view('validation_quizz');
                    $this->load->view('templates/footer2');
                }
            }
        }

        public function enregistrer_question(){
            $num_question = $this->input->post('num_question');
            $quizz_cle = $this->session->userdata('quizz_clé'); 
            $nbQuestion = $this->input->post('quizz_nombreQues');
            $data=array(
                'num_question'=>$num_question,
                'quizz_nombreQues'=>$nbQuestion,
                'quizz_clé'=>$quizz_cle,
            );
            
            $this->users_model->envoi_donnee($quizz_cle,$num_question);
           
            if($num_question<=$nbQuestion){
            $this->load->view('templates/header');
            $this->load->view('question',$data);
            $this->load->view('templates/footer2');
            }else{
             
                $this->load->view('templates/header');
                $this->load->view('validation_quizz');
                $this->load->view('templates/footer2');
            }
        
        }
        public function enregistrer_question_update(){
            $num_question = $this->input->post('num_question');
            $quizz_cle = $this->session->userdata('quizz_clé'); 
            $nbQuestion = $this->input->post('quizz_nombreQues');
            $data=array(
                'num_question'=>$num_question,
                'quizz_nombreQues'=>$nbQuestion,
                'quizz_clé'=>$quizz_cle,
            );

            
            $this->users_model->envoi_donnee_ajout($quizz_cle,$num_question);
           
            if($num_question<=$nbQuestion){
            $this->load->view('templates/header');
            $this->load->view('question_ajout',$data);
            $this->load->view('templates/footer2');   
            }else{
             
                echo "<font color='green'><i>Ajouts effectués.</i></font>";
                $array_items = array('ques_numero', 'ques_libele','ques_image','num_question');
                $this->session->unset_userdata($array_items);
                $data=$this->session->userdata('quizz_clé');
                $data=$this->users_model->get_questions($data); 
                $this->session->set_userdata($data);
                $this->load->view('templates/header');
                $this->load->view('modifier_quizz');
                $this->load->view('templates/footer2');
            }
        
        }
        public function question_update(){
           
            $quizz_cle = $this->session->userdata('quizz_clé'); 
            $num_question = $this->input->post('quizz_nombreQues');
           
            $this->users_model->envoi_donnee_update($quizz_cle,$num_question);
             
            echo "<font color='green'><i>Modifications effectuées.</i></font>";
            $array_items = array('ques_numero', 'ques_libele','ques_image','num_question');
            $this->session->unset_userdata($array_items);
            $data=$this->session->userdata('quizz_clé');
            $data=$this->users_model->get_questions($data); 
            $this->session->set_userdata($data);
            $this->load->view('templates/header');
            $this->load->view('modifier_quizz');
            $this->load->view('templates/footer2');
        
        }
        public function consulter_quizz(){
            $statut = $this->input->post('statut');
            $quizz_clé = $this->session->userdata('quizz_selec'); 
            if($statut !=null){
              $this->users_model->set_quizz_statut($statut,$quizz_clé);
                $statut=null;
                echo "<font color='green'><i>le statut a bien été modifié</i></font>";  
            }
            $this->session->unset_userdata('quizz_clé');
            $numen = $this->session->userdata('prof_numen'); 
            $data=$this->users_model->get_quizz_user($numen);     
            if ($data!=0){   
           $this->session->set_userdata($data);
            $this->load->view('templates/header');    
            $this->load->view('index_quizz_user');
            $this->load->view('templates/footer2');
            $array_items = array('quizz_statut', 'quizz_clé','quizz_titre','quizz_selec','statut');
            $this->session->unset_userdata($array_items);
            }else{
                $this->load->view('templates/header'); 
                echo "<font color='red'><i>Vous n'avez pas encore de quizz, veuillez en créer un.</i></font>";   
                $this->load->view('homepage/index');
                $this->load->view('templates/footer2');
                
            }
        }
        public function voir_quizz($clé){
            
            $data=$this->users_model->get_questions($clé); 
            $data2=$this->users_model->get_titre($clé);
            $data2=array(
                "quizz_titre"=>$data2,
                "quizz_clé"=>$clé,
            );
            $this->session->set_userdata($data);
            $this->session->set_userdata($data2);
            $this->load->view('templates/header');
            $this->load->view('voir_quizz');
            $this->load->view('templates/footer2');
        }

        public function generation_quizzLien($clé){
            $this->session->set_userdata('quizz_clé',$clé);
        
            $this->load->view('templates/header');
            $this->load->view('quizz_lien');
            $this->load->view('templates/footer2');
        }
        public function fiche_reponse($clé){
            
           $array_items = array('prof_nom');
            $this->session->unset_userdata($array_items);
             
            $statut=$this->users_model->get_quizz_statut($clé);
            if( $statut=='actif' ){
                $this->session->set_userdata('quizz_clé',$clé);
                $nom_prof= $this->users_model->get_nom_fiche($clé); 
                $this->session->set_userdata('nom_prof',$nom_prof);
             
                $this->load->view('templates/header');
                $this->load->view('log_fiche_reponse');
                $this->load->view('templates/footer3');
            }else{
                echo "<font color='red'><i>error</i></font>";
                $this->load->view('templates/header');
                $this->load->view('log_fiche_reponse_indisponible');
                $this->load->view('templates/footer3');
            }
        }
        public function load_fiche_reponse(){
            $eleve_nom = $this->input->post('eleve_nom');
            $eleve_prénom = $this->input->post('eleve_prénom');
            $clé=$this->session->userdata('quizz_clé');
            $test= $this->users_model->verif_log_eleve($eleve_nom,$eleve_prénom,$clé);
            $this->session->set_userdata('eleve_id',$test);
            if($test!=null){
                $num_ques=1;
                $this->session->set_userdata('num_ques',$num_ques);
                $num_ques_array= $this->users_model->get_Arraynum_ques($clé);
                $this->session->set_userdata('nombre_ques_array',$num_ques_array);
           
                $ques_actuelle=$num_ques_array[intval($num_ques)-1];
                $ques_actuelle=$ques_actuelle['ques_numero'];
                $this->session->set_userdata('ques_actuelle',$ques_actuelle);
                $question=$this->users_model->get_question($clé,$ques_actuelle); 
                $reponses=$this->users_model->get_reponses_libele($clé,$ques_actuelle); 
                $this->session->set_userdata($question);
                $this->session->set_userdata('rep_libele',$reponses);
          
                    $titre=$this->users_model->get_titre($clé);
                $data=$this->session->set_userdata('titre_quizz',$titre);
           
                
                    $this->load->view('templates/header');
                    $this->load->view('load_fiche_reponse');
                    $this->load->view('templates/footer3');
            }else{
                    echo "<font color='red'><i>error</i></font>";  
                    $this->load->view('templates/header');
                    $this->load->view('load_fiche_reponse_indisponible');
                    $this->load->view('templates/footer3');
            
            }
        }

        public function load_fiche_reponse2(){
            $clé=$this->session->userdata('quizz_clé');
            $id=$this->session->userdata('eleve_id');
            $this->session->set_userdata('eleve_id',$id);
            $total_ques=$this->users_model->get_total_line($clé);
            $num_ques=$this->session->userdata('num_ques');
            $ques_actuelle=$this->session->userdata('ques_actuelle');
            $nombre_ques_array=$this->session->userdata('nombre_ques_array');
           
            $this->users_model->add_reponse($clé,$id,$ques_actuelle);
            $num_ques=$num_ques+1;
           $this->session->set_userdata('num_ques',$num_ques);
          
            if($num_ques<=$total_ques){
                $ques_actuelle=$nombre_ques_array[intval($num_ques)-1];
                
                $ques_actuelle=$ques_actuelle['ques_numero'];
                $this->session->set_userdata('ques_actuelle',$ques_actuelle);
                $question=$this->users_model->get_question($clé,$ques_actuelle); 
                $reponses=$this->users_model->get_reponses_libele($clé,$ques_actuelle); 
                $this->session->set_userdata($question);
                $this->session->set_userdata('rep_libele',$reponses);
                $this->load->view('templates/header');
                $this->load->view('load_fiche_reponse');
                $this->load->view('templates/footer3');
            }else{
                $resultat_eleve=$this->users_model->get_resultat_eleve($clé,$id); 
                $this->users_model->set_score($id,$clé,$resultat_eleve);
                $this->load->view('templates/header');
                $this->load->view('fin_fiche_reponse');
                $this->load->view('templates/footer3');
            }
        }
        
        
        public function modification_question(){

           $choix = $this->input->post('modification');
              
                $clé = $this->session->userdata('quizz_clé');
                $ques_numero=$this->session->userdata('ques_numero');
                $choix2=$ques_numero[intval($choix)-1];
                $choix=$choix-1;
                
       
                $num_ques=array(
                    'ques_numero_modif'=>$choix,
                    'ques_actuel'=>$choix2

                );
                $this->session->set_userdata($num_ques);

  
            
            $data2=$this->users_model->get_reponse($clé,$choix2); 
           $this->session->set_userdata($data2);
      
           
            $this->load->view('templates/header');
            $this->load->view('modification_question');
            $this->load->view('templates/footer2');
        }
        public function modifier_quizz(){
            $value_bouton = $this->input->post('bouton_clic');
            $data=$this->users_model->get_questions($value_bouton); 
            $data2=$this->users_model->get_titre($value_bouton);
            $data2=array(
                "quizz_titre"=>$data2,
                "quizz_clé"=>$value_bouton,
            );
            $this->session->set_userdata($data);
            $this->session->set_userdata($data2);
            
          
            $this->load->view('templates/header');
            $this->load->view('modifier_quizz');
            $this->load->view('templates/footer2');
 
        }
        public function statut(){
            $quizz_selec = $this->input->post('bouton_clic');
            $statut=$this->users_model->get_quizz_statut($quizz_selec);
            $data=array(
                'statut'=>$statut,
                'quizz_selec'=>$quizz_selec
            );
     
            $this->session->set_userdata($data); 
            $this->load->view('templates/header');
            $this->load->view('statut');
            $this->load->view('templates/footer2');
        }
        public function resultat_quizz(){
            $clé = $this->input->post('bouton_clic');
            $liste_eleve=$this->users_model->get_eleve($clé);
            if($liste_eleve!=0){
                $score_quizz=$this->users_model->get_score_quizz($clé);
                $this->session->set_userdata('score_quizz',$score_quizz);
                $this->session->set_userdata('quizz_clé',$clé);
                $this->session->set_userdata('liste_eleve',$liste_eleve);
            
                $this->load->view('templates/header');
                $this->load->view('resultats');
                $this->load->view('templates/footer2');
            }else {
                $this->load->view('templates/header');
                $this->load->view('resultats_indisponible');
                $this->load->view('templates/footer2');
            }
        }
        public function choix_modif(){

            $choix = $this->input->post('modification');
            
            if($choix==='Ajouter une question'){
                $this->load->view('templates/header');
                $this->load->view('modif_ajout_question');
                $this->load->view('templates/footer2');

            }elseif($choix=='Supprimer une question'){
                $clé = $this->session->userdata('quizz_clé');
                $num_question=$this->users_model->get_num_ques($clé);
                $data=array('num_question'=>$num_question);
                $this->session->set_userdata($data); 
                $this->load->view('templates/header');
                $this->load->view('modif_supprimer_question');
                $this->load->view('templates/footer2');

            }else{
                
                $this->load->view('templates/header');
                $this->load->view('modif_modif_question');
                $this->load->view('templates/footer2');
            }
        }

        public function supprimer_quest(){

            $choix = $this->input->post('modification');
            $clé = $this->session->userdata('quizz_clé');
            $ques_numero=$this->session->userdata('ques_numero');
            $choix=$ques_numero[intval($choix)-1];
            
            $data=$this->users_model->del_rep($choix,$clé);
            $data=$this->users_model->del_ques($choix,$clé);
            
          if($data=1){
                echo "<font color='green'><i>Supression effectuée.</i></font>";
                $array_items = array('ques_numero', 'ques_libele','ques_image','num_question');
                $this->session->unset_userdata($array_items);
                $data=$this->session->userdata('quizz_clé');
                $data=$this->users_model->get_questions($data); 
                $this->session->set_userdata($data);
                $this->load->view('templates/header');
                $this->load->view('modifier_quizz');
                $this->load->view('templates/footer2');

           }else{
            echo "<font color='red'><i>Erreur dans la suppression</i></font>";  
                $this->load->view('templates/header');
                $this->load->view('modifier_quizz');
                $this->load->view('templates/footer2');
            }
        }
        public function ajout_question(){
                $nbQuestion = $this->input->post('howmuch');
                
                 $clé = $this->session->userdata('quizz_clé'); 
                 $num_question=$this->users_model->get_num_ques($clé);
                 $nbQuestion=$num_question+$nbQuestion;

                $num_question=$num_question+1;
                
                $data=array(
                    'num_question'=>$num_question,
                    'quizz_nombreQues'=>$nbQuestion,   
                );
               
                $this->load->view('templates/header');
                $this->load->view('question_ajout',$data);
                $this->load->view('templates/footer2');
          
        }
        public function fiche_resultat($clé,$id){
            $statut=$this->users_model->get_quizz_statut($clé);
            if( $statut=='périmé'){
                $data=$this->users_model->get_questions($clé); 
                $data2=array(
                    "quizz_clé"=>$clé,
                );

                $this->session->set_userdata($data);
                $this->session->set_userdata($data2);
                $this->session->set_userdata('eleve_id',$id);
                
                $clé = $this->session->userdata('quizz_clé'); 
                $resultat_eleve=$this->users_model->get_resultat_eleve($clé,$id); 
                $this->session->set_userdata('resultat',$resultat_eleve);
                $this->users_model->set_score($id,$clé,$resultat_eleve);

                $this->load->view('templates/header');
                $this->load->view('fiche_resultat');
                $this->load->view('templates/footer3');
                
            }else{
                echo "<font color='red'><i>error</i></font>";
                $this->load->view('templates/header');
                $this->load->view('fiche_resultat_indisponible');
                $this->load->view('templates/footer3');
                
            }
          
        }
        public function consulter_resultat(){
                $nom = $this->input->post('modification');
                $id=str_replace(' ','',$nom);
                $clé = $this->session->userdata('quizz_clé'); 
                $data=$this->users_model->get_questions($clé); 
                $data2=array(
                    "quizz_clé"=>$clé,
                );
              $array_items = array('liste_eleve');
              $this->session->unset_userdata($array_items);
                $clé = $this->session->userdata('quizz_clé'); 
                $this->session->set_userdata('nom_eleve',$nom);
                $this->session->set_userdata('eleve_id',$id);
                $resultat_eleve=$this->users_model->get_score($clé,$id);
                $this->session->set_userdata('resultat',$resultat_eleve);
              
            
                $this->session->set_userdata($data);
                $this->session->set_userdata($data2);
                $this->load->view('templates/header');
                $this->load->view('fiche_resultat_eleve');
                $this->load->view('templates/footer2');
        
            
          
        }
}
     
    
?>