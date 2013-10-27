<?php

class User {
	public $id;
	public $playerName;
	public $name;
	public $password;
	public $email;
	public $surname;
	public $birth;
	public $gender;
	public $level;
	public $exp;
	public $expNextLevel;
	public $creatures;
	
	public function __construct($id, $playerName, $name, $password, $email, $surname, $birth, $gender, $level, $exp, $expNextLevel, $creatures){
		$this->id=$id;
		$this->playerName=$playerName;
		$this->name=$name;
		$this->password=$password;
		$this->email=$email;
		$this->surname=$surname;
		$this->birth=$birth;
		$this->gender=$gender;
		$this->level=$level;
		$this->exp=$exp;
		$this->expNextLevel=$expNextLevel;
		$this->creatures=$creatures;
	}


}


?>
