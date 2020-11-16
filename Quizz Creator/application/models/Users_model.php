    <?php
    	class Users_model extends CI_Model {
    		function __construct(){
    			parent::__construct();
    			$this->load->database();
    		}
    		public function login($numen, $passwd){
    		
                $pass=md5($passwd);
                $query = $this->db->query("SELECT prof_numen FROM professeur where prof_numen='$numen'");
                $query=$query->row();
                
                if ($query !== null){
             
                $querys = $this->db->query("SELECT prof_mdp FROM professeur where prof_numen='$numen'");
                foreach ($querys->row() as $querys){}
                   
                    if ($querys !== null){
                        if ($querys===$pass) {
                            return 4;
                        }else {  
                            echo "<i><font color='red'>Le mot de passe ne correspond pas.</font></i>";       
                            return 2 ;
                        }
                    }else {
                        return 0;
                }
                }else {
                    echo "<i><font color='red'>Le numéro de numen n'est pas valide</font></i>";
                    return 1;
                }
            }      
    public function get_nom($numen){
        $query = $this->db->select('prof_nom')->from('professeur')->where('prof_numen', $numen)->get();
        return $query->row()->prof_nom;
    }
    public function get_nom_fiche($clé){
        $query = $this->db->query('select DISTINCT prof_nom FROM professeur JOIN quizz
                                    WHERE prof_numen =(SELECT quizz_prof_numen
                                                        from quizz
                                                        WHERE quizz_clé="'.$clé.'");');
        return $query->row()->prof_nom;
        
    }

    public function create_quizz($data){
        return  $this->db->insert('quizz', $data); 
    }
    public function create_question($data){
        return  $this->db->insert('question', $data); 
        }
    public function get_num_repp($data){
        return  $this->db->insert('reponse', $data); 
    }
    public function get_num_ques($data){
        $query = $this->db->select_max('ques_numero')->from('question')->where('ques_quizz_clé', $data)->get();
        return $query->row()->ques_numero; 
    }
    public function get_quizz_statut($data){
        $query = $this->db->select('quizz_statut')->from('quizz')->where('quizz_clé', $data)->get();
        return $query->row()->quizz_statut; 
    }
    public function get_score($clé,$id){
        $query = $this->db->select('élève_score')->from('eleve')
        ->where('eleve_repEleve_rep_ques_quizz_clé', $clé)
        ->where('eleve_id', $id)->get();
        return $query->row()->élève_score; 
    }
    public function get_score_quizz($clé){
        $query = $this->db->select_AVG('élève_score')->from('eleve')
        ->where('eleve_repEleve_rep_ques_quizz_clé', $clé)->get();
         $query=$query->row()->élève_score; 
         return $query=round($query);
        
    }
    public function get_id_eleve($clé,$id){
        $query = $this->db->select('élève_score')->from('eleve')
        ->where('eleve_repEleve_rep_ques_quizz_clé', $clé)
        ->where('eleve_id', $id)->get();
        return $query->row()->quizz_statut; 
    }

    public function set_quizz_statut($statut,$quizz_clé){
        $data = array(
            'quizz_statut' => $statut, 
        );
        $this->db->where('quizz_clé', $quizz_clé);
        $this->db->update('quizz', $data); 
        return 1;
    }
    public function del_rep($nombre_ques,$quizz_clé){

        $this->db->where('rep_ques_numero', $nombre_ques);
        $this->db->where('rep_ques_quizz_clé', $quizz_clé);
        $this->db->delete('reponse');
        return 1;
    }
    public function del_ques($nombre_ques,$quizz_clé){

        $this->db->where('ques_quizz_clé', $quizz_clé);
        $this->db->where('ques_numero', $nombre_ques);
        $this->db->delete('question');
        
        return 1;
    }
         
    public function create_contact($data){
        return	$this->db->insert('professeur', $data);	

    }
 

    public function add_question($nombre_ques,$quizz_clé){
        $query = $this->db->select('quizz_nombreQues')->from('quizz')->where('quizz_clé', $quizz_clé)->get();
        $query=$query->row()->quizz_nombreQues;
        $nombre_ques=$query+$nombre_ques;
        $data = array(
            'quizz_nombreQues' => $nombre_ques, 
        );
           $this->db->where('quizz_clé', $quizz_clé);
            $this->db->update('quizz', $data);
            return $nombre_ques;
        }
    

    public function envoi_donnee($quizz_cle,$num_question){
        
            $titre=$_POST['ques_libele'];
            $image=$_POST['ques_image'];
            $num_question=$num_question-1;
            $data = array(
                'ques_libele' => $titre, 
                'ques_image' => $image, 
            );
            $this->db->where('ques_quizz_clé', $quizz_cle);
            $this->db->where('ques_numero',$num_question);
            $this->db->update('question', $data);

            $num_rep=0;
            if(!empty($_POST['rep_libelle'])){
                foreach ($_POST['rep_libelle'] as $rep_libele) {
                    
                    if ($rep_libele!==""){
                        $num_rep=$num_rep+1;
                        $statut=0;
                        $data = array(
                            'rep_numero' => $num_rep, 
                            'rep_statut' => $statut, 
                            'rep_ques_numero' => $num_question, 
                            'rep_ques_quizz_clé' => $quizz_cle,
                            'rep_libele' => $rep_libele,
                        );
                       
                        $this->db->insert('reponse', $data);
                    }
                }
            }      
        
         if(!empty($_POST['check_list'])){
          
            foreach($_POST['check_list'] as $selected){
            $statut=1;
            $data = array(
                'rep_statut' => $statut, 
            );
                $this->db->where('rep_ques_numero', $num_question);
                $this->db->where('rep_numero', $selected);
                $this->db->update('reponse', $data);
            }
        }  
    }
    public function envoi_donnee_ajout($quizz_cle,$num_question){
        
        $titre=$_POST['ques_libele'];
        $image=$_POST['ques_image'];
        $num_question=$num_question-1;
        $data = array(
            'ques_numero'=>$num_question,
            'ques_libele' => $titre, 
            'ques_image' => $image, 
            'ques_quizz_clé'=>$quizz_cle
        );

        $this->db->insert('question', $data);

        $num_rep=0;
        if(!empty($_POST['rep_libelle'])){
            foreach ($_POST['rep_libelle'] as $rep_libele) {
                
                if ($rep_libele!==""){
                    $num_rep=$num_rep+1;
                    $statut=0;
                    $data = array(
                        'rep_numero' => $num_rep, 
                        'rep_statut' => $statut, 
                        'rep_ques_numero' => $num_question, 
                        'rep_ques_quizz_clé' => $quizz_cle,
                        'rep_libele' => $rep_libele,
                    );
                   
                    $this->db->insert('reponse', $data);
                }
            }
        }      
    
     if(!empty($_POST['check_list'])){
        
        foreach($_POST['check_list'] as $selected){
        $statut=1;
        $data = array(
            'rep_statut' => $statut, 
        );
            $this->db->where('rep_ques_numero', $num_question);
            $this->db->where('rep_numero', $selected);
            $this->db->update('reponse', $data);
        }
    }  
}
    public function add_reponse($clé,$id,$num_ques_actuelle){
        if(!empty($_POST['check_list'])){

          
            foreach(($rep=$_POST['check_list']) as $selected){
            
            $data = array(
                'repEleve_libelle' => $rep[$selected],
                'repEleve_eleve_id' => $id, 
                'repEleve_rep_numero' => $rep[$selected], 
                'repEleve_rep_quizz_clé' => $clé, 
                'repEleve_rep_ques_numero' => $num_ques_actuelle, 
            );
                $this->db->insert('reponseeleve', $data);
            }
        }  
    
    }
    public function get_eleve($clé){
        $data=NULL;
        $liste_eleve=$this->db->select('eleve_nom,eleve_prenom,eleve_id')->from('eleve')
        ->where('eleve_repEleve_rep_ques_quizz_clé', $clé)
        ->where('élève_score!=', $data)
        ->get();
        if($liste_eleve->result_array()!=null){
            return $liste_eleve->result_array();
        }else{
            return 0;
        }
        
    }
    public function get_resultat_eleve($clé,$id){
        $total_question=$this->users_model->get_total_line($clé);
        $num_ques_array= $this->users_model->get_Arraynum_ques($clé); 
        $nb_reponse_juste=0;
        foreach($num_ques_array as $value=>$key){
            $ques_actuelle=$num_ques_array[$value]['ques_numero'];
            $reponses_justes=$this->db->select('rep_numero')->from('reponse')
            ->where('rep_ques_quizz_clé', $clé)
            ->where('rep_ques_numero', $ques_actuelle)
            ->where('rep_statut', 1)->get();
            $reponses_justes=$reponses_justes->result_array();
            $réponses_eleve=$this->users_model->get_reponses_eleve($clé,$ques_actuelle,$id);
             
               $reponses_justes_temp="";
               $réponses_eleve_temp=""; 
               $reponses_array=null;
               $reponses_eleve_array=null;
               foreach($reponses_justes as $value2=>$key2){
                    if(isset($reponses_justes[$value2]['rep_numero'])){
                        $reponses_justes_temp = $reponses_justes[$value2]['rep_numero'];
                        
                        $reponses_array[$value2]=array(
                            'num_rep_juste'=>$reponses_justes_temp,
                        );
                    }
                    if(isset($réponses_eleve[$value2]['repEleve_rep_numero'])){
                        $réponses_eleve_temp= $réponses_eleve[$value2]['repEleve_rep_numero'];
                        $reponses_eleve_array[$value2]=array(
                            'num_rep_juste'=>$réponses_eleve_temp,
                        );
                        
                    }     
                }
               
                if($reponses_array==$reponses_eleve_array){
                   
                   $nb_reponse_juste=$nb_reponse_juste+1;
                }   
           
        }
        if($nb_reponse_juste!=0){
            $total=($nb_reponse_juste*100)/$total_question;
        }else {
            $total = 0;
        }
        return $total;
    }
    public function set_score($id,$clé,$score){
        $data=array(
            'élève_score'=>$score
        );
        $this->db->where('eleve_id', $id);
        $this->db->where('eleve_repEleve_rep_ques_quizz_clé', $clé);
        $this->db->update('eleve', $data);
    }
    public function get_total_line($data){
        $max_rep=$this->db->select('ques_numero')->from('question')->where('ques_quizz_clé', $data)->get();
            return $max_rep=$max_rep->num_rows(); 
    }
    public function envoi_donnee_update($quizz_cle,$num_question){
        
        $titre=$_POST['ques_libele'];
        $image=$_POST['ques_image'];
        $data = array(
            'ques_numero' => $num_question, 
            'ques_libele' => $titre, 
            'ques_image' => $image, 
            'ques_quizz_clé' => $quizz_cle,
        );
        $this->db->where('ques_numero', $num_question);
        $this->db->where('ques_quizz_clé', $quizz_cle);
        $this->db->update('question', $data);

        $num_rep=0;
        if(!empty($_POST['rep_libelle'])){
            $max_rep=$this->db->select('rep_numero')->from('reponse')
                            ->where('rep_ques_quizz_clé', $quizz_cle)
                            ->where('rep_ques_numero', $num_question) ->get();
                   $max_rep=$max_rep->num_rows(); 
            foreach ($_POST['rep_libelle'] as $rep_libele) {
                
                if ($rep_libele!==""){
                    $num_rep=$num_rep+1;
                    if( $num_rep<=$max_rep){
                        $statut=0;
                        $data = array(
                            'rep_numero' => $num_rep, 
                            'rep_statut' => $statut, 
                            'rep_ques_numero' => $num_question, 
                            'rep_ques_quizz_clé' => $quizz_cle,
                            'rep_libele' => $rep_libele,
                        );
                    
                    
                            $this->db->where('rep_ques_numero', $num_question);
                            $this->db->where('rep_numero', $num_rep);
                            $this->db->where('rep_ques_quizz_clé', $quizz_cle);
                            $this->db->update('reponse', $data);
                    }elseif($num_rep>$max_rep){
                        
                        $statut=0;
                        $data = array(
                            'rep_numero' => $num_rep, 
                            'rep_statut' => $statut, 
                            'rep_ques_numero' => $num_question, 
                            'rep_ques_quizz_clé' => $quizz_cle,
                            'rep_libele' => $rep_libele,
                        );
                    
                        $this->db->insert('reponse', $data);
                    }

                }elseif($rep_libele==""){
                    
                    $num_rep=$num_rep+1;
                    $this->db->where('rep_ques_numero', $num_question);
                    $this->db->where('rep_numero', $num_rep);
                    $this->db->where('rep_ques_quizz_clé', $quizz_cle);
                    $this->db->delete('reponse');
                    
                }
                
            }
        }      
    
     if(!empty($_POST['check_list'])){
       
        foreach($_POST['check_list'] as $selected){
        $statut=1;
        $data = array(
            'rep_statut' => $statut, 
        );
        $this->db->where('rep_ques_numero', $num_question);
         $this->db->where('rep_numero', $selected);
        $this->db->update('reponse', $data);
        }
    }  
}
    
    public function get_quizz_user($numen){
        $query = $this->db->select('quizz_clé,quizz_titre,quizz_statut')->from('quizz')->where('quizz_prof_numen', $numen)->get();
        $query=$query->result();
        if ($query != null){
       
        foreach($query as $key => $value){
           $titre[$key]=$query[$key]->quizz_titre;
           $statut[$key]=$query[$key]->quizz_statut;
           $cle[$key]=$query[$key]->quizz_clé;
           $data = array(
               'quizz_clé'=>$cle,
               'quizz_titre'=>$titre,
                'quizz_statut'=>$statut,
           );
        }
        return $data;
    }else{
        return 0;
    }
}
    public function get_titre($data){
        $titre =$this->db->select('quizz_titre')->from('quizz')->where('quizz_clé', $data)->get();
        return $titre->row()->quizz_titre;
    }
    public function get_reponses($quizz_cle,$num_ques){
        $reponses = $this->db->select('rep_libele,rep_statut')->from('reponse')->where('rep_ques_quizz_clé', $quizz_cle)->where('rep_ques_numero', $num_ques)->get();
        $reponses=$reponses->result_array();
        return $reponses;
    }
    public function get_reponses_eleve($quizz_cle,$num_ques,$id){
        
        $reponses = $this->db->select('	repEleve_rep_numero')->from('reponseeleve')
        ->where('repEleve_rep_ques_numero', $num_ques)
        ->where('repEleve_eleve_id', $id)
        ->where('repEleve_rep_quizz_clé', $quizz_cle)->get();
        $reponses=$reponses->result_array();
        if(empty($reponses)){
           return null;
        }else{
            return $reponses;
        }
      
    }
    public function get_reponses_libele($quizz_cle,$num_ques){
        $reponses = $this->db->select('rep_libele')->from('reponse')->where('rep_ques_quizz_clé', $quizz_cle)->where('rep_ques_numero', $num_ques)->get();
        $reponses=$reponses->result_array();
        return $reponses;
    }
    public function get_reponse($quizz_cle,$num_ques){
        $reponses = $this->db->select('rep_libele,rep_statut')->from('reponse')->where('rep_ques_quizz_clé', $quizz_cle)->where('rep_ques_numero', $num_ques)->get();
        $reponses=$reponses->result();
        
        if ($reponses != null){
            
          
            foreach($reponses as $key => $value){
               $rep_statut[$key]=$reponses[$key]->rep_statut;
               $libele[$key]=$reponses[$key]->rep_libele;
               $data = array(
                   'rep_statut'=>$rep_statut,
                   'rep_libele'=>$libele,
        
               );
            }
            return $data;
        }else{
            return null;
        }
    }

    public function get_questions($data){
        $questions = $this->db->select('ques_numero,ques_libele,ques_image')->from('question')->where('ques_quizz_clé', $data)->get();
        $questions=$questions->result();
        if ($questions !== null){
       
        foreach($questions as $key => $value){
           $numero[$key]=$questions[$key]->ques_numero;
           $libele[$key]=$questions[$key]->ques_libele;
           $image[$key]=$questions[$key]->ques_image;
           $data = array(
               'ques_numero'=>$numero,
               'ques_libele'=>$libele,
                'ques_image'=>$image,
           );
        }
            return $data;
        }else{
            return 0;
        }
    }
    public function get_Arraynum_ques($data){
        $query = $this->db->select('ques_numero')->from('question')->where('ques_quizz_clé', $data)->get();
        return $query->result_array(); 
    }
  
    public function get_question($clé,$numero){
        $questions = $this->db->select('ques_libele,ques_image')->from('question')
        ->where('ques_quizz_clé', $clé)
        ->where('ques_numero',$numero)->get();
        $questions=$questions->result();
        if ($questions !== null){
       
        foreach($questions as $key => $value){
           $ques_libele[$key]=$questions[$key]->ques_libele;
           $ques_image[$key]=$questions[$key]->ques_image;
           $data = array(
               'ques_libele'=>$ques_libele,
                'ques_image'=>$ques_image,
           );
        }
            return $data;
        }else{
            return 0;
        }
    }

    public function verif_log_eleve($nom,$prénom,$clé){
     
        $id=$nom.$prénom;
        
        $test = $this->db->select('eleve_id')->from('eleve')
        ->where('eleve_repEleve_rep_ques_quizz_clé', $clé)
        ->where('eleve_id', $id)->get();
        $test=$test->row();
        if($test==NULL){
            
            $value=null;
            $data=array(
                'eleve_id'=>$id,
                'eleve_nom'=>$nom,
                'eleve_prenom'=>$prénom,
                'élève_score'=>$value,
                'eleve_repEleve_rep_ques_quizz_clé'=>$clé
            );
            $this->db->insert('eleve', $data); 
            return $id;
        }else{
            return null;
        }

    }

}
?>