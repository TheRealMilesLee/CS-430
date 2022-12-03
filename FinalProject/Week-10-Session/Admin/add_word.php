<?php

/**
 * This file is to accept the POST data in JSON format
 * @author Hengyi Li <hl3265@truman.edu>
 * @version 0.21.0
 * @copyright 2022 Hengyi Li. All rights reserved
 */
require '../Database/dblogin.php';
$db = new PDO(
  "mysql:host=$db_host;dbname=hl3265;charset=utf8mb4",
  $db_user,
  $db_pass,
  array(
    PDO::ATTR_EMULATE_PREPARES => false,
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
  )
);


if (isset($_POST) && isset($_POST['validate']))
{
  /* Getting the data from the POST request and assigning it to variables. */
  $json_data = json_decode($_POST['validate']);
  $words_entry = htmlspecialchars($json_data[0]);
  $speech_entry = $json_data[1];
  $json_data[2] =  str_replace("π", "&", $json_data[2]);
  $json_data[2] = str_replace("√", "+", $json_data[2]);
  $def_entry = htmlspecialchars($json_data[2]);

  /* Checking if the word and part of speech already exist in the database. */
  $search = $db->prepare("select word.word, part.part from word
        join part on word.part_id = part.id
        where word.word = :duplicate_word and part.part = :duplicate_part;");
  $search->bindValue(':duplicate_word', $words_entry, PDO::PARAM_STR);
  $search->bindValue(':duplicate_part', $speech_entry, PDO::PARAM_STR);
  $search->execute();
  $search_results = $search->fetchAll();
  if (empty($search_results))
  {
    $duplicate = "False";
    if ($speech_entry == "adjective")
    {
      $insert_speech = 1;
    }
    else if ($speech_entry == "adverb")
    {
      $insert_speech = 2;
    }
    else if ($speech_entry == "noun")
    {
      $insert_speech = 3;
    }
    else
    {
      $insert_speech = 4;
    }
    /* Inserting the data into the database. */
    $insert = $db->prepare('insert into word (word, part_id, definition)
        values (:word, :part_id, :definition);');
    $insert->bindValue(':word', $words_entry, PDO::PARAM_STR);
    $insert->bindValue(':part_id', $insert_speech, PDO::PARAM_INT);
    $insert->bindValue(':definition', $def_entry, PDO::PARAM_STR);
    $insert->execute();
  }
  else
  {
    $duplicate = "True";
  }
}
?>
<?= json_encode($duplicate) ?>
