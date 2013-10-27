<?php 
   
try {
	require 'controllers/REST_Controller.php';
	$controller = new RESTController();	
	$resp = $controller->doLogin();	
	echo $resp;	

}catch(Exception $e){
	echo $e;
}


?>
