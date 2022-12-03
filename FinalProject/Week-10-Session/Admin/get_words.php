<?php

/**
 * @abstract This file is to get words from the file
 * @author  Hengyi Li
 * @copyright 2022 Truman State University. All rights reserved
 * @version 1.0.4
 */

require '../Database/dblogin.php';
error_reporting(E_ALL);
ini_set('display_errors', '1');

/**
 * @abstract This function read a file of tab-separated lines into an array of arrays
 * @param string $filename the name of the file to read
 * @return array is the array of arrays with the contents of the file
 */
function read_database_into_array($db_word)
{
	$index = 0;
	$array = array();
	while ($index < count($db_word))
	{
		$array[] =
			array(
				'word' => $db_word[$index]["word"],
				'part' => $db_word[$index]["part"],
				'definition' => $db_word[$index]["definition"]
			);
		$index++;
	}
	return $array;
}

if (
	isset($_GET) && isset($_GET['search'])
	&& preg_match('/^[a-z]+$/', $_GET['search'])
)
{
	$search = $_GET['search'];
	$db = new PDO(
		"mysql:host=localhost:3306;dbname=gre_db;charset=utf8mb4",
		$db_user,
		$db_pass,
		array(
			PDO::ATTR_EMULATE_PREPARES => false,
			PDO::ATTR_ERRMODE =>
			PDO::ERRMODE_EXCEPTION
		)
	);
	$word = $db->prepare('select word.word, part.part, word.definition
		from word join part on word.part_id = part.id
		where word like :search order by word asc;');
	$word->bindValue(':search', $search . '%', PDO::PARAM_STR);
	$word->execute();
	$db_word = $word->fetchAll();
	$word_list = read_database_into_array($db_word);
}
?>
<?= json_encode($word_list) ?>
