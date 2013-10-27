<?php

class Creature {
	
	public $id;
	public $name;
	public $level;
	public $exp;
	public $expNextLevel;
	public $selected;
	public $strength;
	public $defense;
	public $speed;
	public $feed;
	public $maxFeed;
	public $starveSpeed;
	public $life;
	public $maxLife;
	public $happiness;
	public $Creature_Base_id;

		
	public function __construct($id, $name, $level, $exp, $expNextLevel, $selected, $strength, $defense, $speed, $feed, $maxFeed, $starveSpeed, $life, $maxLife, $happiness, $Creature_Base_id){
		$this->id=$id;
		$this->name=$name;
		$this->level=$level;
		$this->exp=$exp;
		$this->expNextLevel=$expNextLevel;
		$this->selected=$selected;
		$this->strength=$strength;
		$this->defense=$defense;
		$this->speed=$speed;
		$this->feed=$feed;
		$this->maxFeed=$maxFeed;
		$this->starveSpeed=$starveSpeed;
		$this->life=$life;
		$this->maxLife=$maxLife;
		$this->happiness=$happiness;
		$this->Creature_Base_id=$Creature_Base_id;
	}


}


?>
