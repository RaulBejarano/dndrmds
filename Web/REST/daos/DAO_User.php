<?php

require_once (__DIR__."/../model/User.php");
require_once (__DIR__."/DAO_Creature.php");

class DAO_User {
	
	public function __construct(){
		
	}

	public function doLogin($connection, $usr, $pass){
		$user = NULL;
		$sql="SELECT * FROM User WHERE playerName = '".$usr."' AND password = '".$pass."'";
		
		try{
			$resp = $connection->query($sql);

			
			if ($row = $resp->fetch(PDO::FETCH_ASSOC)) {
				$user = new User (
					$row['id'],
					$row['playerName'],
					$row['name'],
					NULL,
					$row['email'],
					$row['surname'],
					$row['birth'],
					$row['gender'],
					$row['level'],
					$row['exp'],
					$row['expNextLevel'],
					NULL
				);
				
				$daoCreature = new DAO_Creature();
				$user->creatures = $daoCreature->getCreatureArrayByUser($connection, $user);
				

			}
				
		} catch (PDOException $e){
	
		}
		
		return $user;
	}

}




?>
