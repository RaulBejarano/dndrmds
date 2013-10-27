<?php

class FortrabbitConnection {
	public $gdb = NULL;

	public function __construct(){
	}

	public function openConnection(){
		$dsn = 'mysql:host=dndrmds.mysql.eu1.frbit.com;dbname=dndrmds';
		$nombre_usuario = 'dndrmds';
		$contrasena = 'x4fF6eRKILlYvhN5';
		$opciones = array(
			PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
		); 

		try{
			$this->gdb = new PDO($dsn, $nombre_usuario, $contrasena, $opciones);
			return true;		
		
		}catch(PDOException $e){
			echo "An error ocurred\n", $ex->getMessage();
			return false;
		}
	}


}

?>
