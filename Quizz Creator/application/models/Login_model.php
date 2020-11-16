<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/* Author: Jorge Torres
 * Description: Login model class
 */
class Login_model extends CI_Model{
    function __construct(){
        parent::__construct();
    }
    
    public function validate(){
        // grab user input
        $email = $this->input->post('mem_email'));
        $pass = $this->input->post('mem_passwd'));
        
        $query = $this->db->query("SELECT * FROM membre WHERE mem_email =?");
        
        if (password_verify($email, $pass)) {
            return true
        } else {
            return false;
        }
        // If the previous process did not validate
    }
}
?>