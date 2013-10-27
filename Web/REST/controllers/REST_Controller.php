<?php

require_once (__DIR__."/../connections/FortrabbitConnection.php");
require_once (__DIR__."/../daos/DAO_User.php");


class RESTController{
	
	public function __construct(){		
	}
	
	public function doLogin(){
		$connection = new FortrabbitConnection();
		if($connection->openConnection()){
			$daoUser = new DAO_User ();
		
			$user = $_POST['user'];
			$password = $_POST['password'];		
			
			$response = $daoUser->doLogin($connection->gdb, $user, $password);		
			return json_encode($response);
			
		}

		return "error";
		
	}
	
}		

?>