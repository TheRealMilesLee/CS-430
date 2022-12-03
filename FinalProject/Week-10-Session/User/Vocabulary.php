<?php

/**
 * A program to serve a GRE vocabulary quiz
 * This program runs in two modes.
 * Mode 1: nothing has been submitted; this is the first time
 * Mode 2: this is not the first submission
 * @author Jon Beck
 * @version 1 February 2022
 */

error_reporting(E_ALL);
ini_set('display_errors', '1');
session_start();

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


function read_words($db_word)
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


function read_database_into_array($db_word)
{
  $index = 0;
  $array = array();
  while ($index < count($db_word))
  {
    $array[] =
      array(
        'username' => $db_word[$index]["username"],
        'screen_name' => $db_word[$index]["screen_name"],
        'user_password' => $db_word[$index]["user_password"],
        'password_id' => $db_word[$index]["password_id"],
        'is_administrator' => $db_word[$index]["is_administrator"]
      );
    $index++;
  }
  return $array;
}

if (isset($_SESSION['is_administrator']))
{
  if ($_SESSION['is_administrator'] == 0 || $_SESSION['is_administrator'] == 1)
  {
    if (isset($_POST['nickname_new']) && isset($_POST['password_new']))
    {
      $new_nickname = $_POST['nickname_new'];
      $new_password = $_POST['password_new'];
      if (empty($new_password))
      {
        $statement = $db->prepare('update username set screen_name = :screen_name where username = :username;');
        $statement->bindValue(':username', $_SESSION['username']);
        $statement->bindValue(':screen_name', $new_nickname);
        $statement->execute();
        $_SESSION['screen_name'] = $new_nickname;
      }
      else if (empty($new_nickname))
      {
        $password_modification = $db->prepare('update user_password set user_password = :user_password where user_password.id = :password_id;');
        $hashed_password = password_hash($new_password, PASSWORD_DEFAULT);
        $password_modification->bindValue(':password_id', $_SESSION['password_id']);
        $password_modification->bindValue(':user_password', $hashed_password);
        $password_modification->execute();
      }
      else
      {
        $sql = 'update username set screen_name = :screen_name where username = :username;';
        $statement = $db->prepare($sql);
        $statement->bindValue(':username', $_SESSION['username']);
        $statement->bindValue(':screen_name', $new_nickname);
        $statement->execute();
        $_SESSION['screen_name'] = $new_nickname;
        $password_change = 'update user_password set user_password = :user_password where user_password.id = :password_id;';
        $password_modification = $db->prepare($password_change);
        $hashed_password = password_hash($new_password, PASSWORD_DEFAULT);
        $password_modification->bindValue(':password_id', $_SESSION['password_id']);
        $password_modification->bindValue(':user_password', $hashed_password);
        $password_modification->execute();
      }
    }
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <title>GRE Vocab Quiz</title>
      <meta name="author" content="Jon Beck" />
      <meta charset="utf-8" />
      <link rel="stylesheet" href="Vocabulary.css" />
    </head>

    <body>
      <div class="vocabulary">
        <h1>Zelda&rsquo;s GRE Vocabulary</h1>
        <div class=" display_word">
          <?php
          $word = $db->prepare('select word.word, part.part, word.definition
          from word join part on word.part_id = part.id order by word asc;');
          $word->execute();
          $db_word = $word->fetchAll(PDO::FETCH_ASSOC);
          $word_list = read_words($db_word);
          $index = 0;
          while ($index < count($word_list))
          {
          ?>
            <?= "<p>" ?>
            <?= "<b><ins>" . $word_list[$index]["word"] . "</ins>:</b> \t <b>(" . $word_list[$index]["part"] . ")</b> \t" . $word_list[$index]["definition"] ?>
            <?= "</p>" ?>
          <?php
            $index++;
          }
          ?>
        </div>
      </div>
      <div class="manage_account">
        <h2> Manage your account </h2>
        <p id="prompt"> Fill out the form below to change user status </p>
        <form method="post" action="Vocabulary.php">
          <fieldset class="nickname">
            <label class="input_label" for="nickname_new"> Please input your new nickname </label>
            <input class="input" type="text" id="nickname_new" name="nickname_new" placeholder="new nickname" />
          </fieldset>
          <fieldset class="password">
            <label class="input_label" for="password_new"> Please input your new Password </label>
            <input class="input" type="password" id="password_new" name="password_new" placeholder="new password" />
            <b id="notification_password"> &bull; </b>
          </fieldset>
          <fieldset class="password">
            <label class="input_label" for="password_new_re"> Please confirm your new Password </label>
            <input class="input" type="password" id="password_new_re" name="password_new_re" placeholder="new password" />
            <b id="notification_password_re"> &bull; </b>
          </fieldset>
          <button type="submit" class="submit" name="submit"> Submit </button>
        </form>
        <div>
          <h3> Welcome, <?= $_SESSION['screen_name'] ?>. Your current account status is
            <?php
            if ($_SESSION['is_administrator'] === 1)
            {
            ?>
              <?= "Administrator" ?>
            <?php
            }
            else
            {
            ?>
              <?= "Common user" ?>
            <?php
            }
            ?>
          </h3>
        </div>
        <div class="logout_button">
          <p> Click to log out</p>
          <button type="submit" class="submit" id="logout"> Log Out </button>
        </div>
      </div>
    <?php
  }
  else
  {
    ?>
      <p> Nice try, but you are not logged in</p>
      <button type="submit" class="submit" id="login"> Take me to login </button>
    <?php
  }
}
else
{
    ?>
    <p> Nice try, but you shall not pass! </p>
  <?php
}
  ?>
  <script src="Vocabulary.js"></script>
    </body>

    </html>
