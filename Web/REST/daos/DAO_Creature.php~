<?php

require_once (__DIR__."/../model/Creature.php");

class DAO_Creature {
	
	public function __construct(){
	}


	public function getCreatureArrayByUser($connection, $user){
		$creatureList = array();	
		$sql = "SELECT * FROM Creature WHERE User_id = ".$user->id;
				
		try{
			$resp = $connection->query($sql);
			
			$creatureAux = NULL;
			while ($row = $resp->fetch(PDO::FETCH_ASSOC)){
				$creatureAux = new Creature (
					$row['id'],
					$row['name'],
					$row['level'],
					$row['exp'],
					$row['expNextLevel'],
					$row['selected'],
					$row['strength'],
					$row['defense'],
					$row['speed'],
					$row['feed'],
					$row['maxFeed'],
					$row['starveSpeed'],
					$row['life'],
					$row['maxLife'],
					$row['happiness'],
					$row['Creature_Base_id']
				);
				
				array_push($creatureList, $creatureAux);
			}
		
		} catch (PDOException $e){
	
		}

		return $creatureList;
	}


}




?>
