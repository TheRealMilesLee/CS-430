<?php

/**
 * @author Hengyi Li <hl3265@truman.edu>
 * @abstract This file is to delete words
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

if (isset($_POST) && isset($_POST['delete_word']))
{
  $delete_entry = json_decode($_POST['delete_word']);
  $index = 0;
  while ($index < count($delete_entry) - 1)
  {
    $clean_word = htmlspecialchars($delete_entry[$index]);
    $clean_part = htmlspecialchars($delete_entry[$index + 1]);
    if ($clean_part == "adjective")
    {
      $delete_speech = 1;
    }
    else if ($clean_part == "adverb")
    {
      $delete_speech = 2;
    }
    else if ($clean_part == "noun")
    {
      $delete_speech = 3;
    }
    else
    {
      $delete_speech = 4;
    }
    $delete_action = $db->prepare('delete from word
      where word.word = :del_word and word.part_id = :del_part;');
    $delete_action->bindValue(':del_word', $clean_word, PDO::PARAM_STR);
    $delete_action->bindValue(':del_part', $delete_speech, PDO::PARAM_INT);
    $delete_action->execute();
    $index++;
  }
}
