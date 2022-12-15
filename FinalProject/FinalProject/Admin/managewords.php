<?php

/**
 * @brief This file is to manage words and the user account
 * @author Hengyi Li
 * @copyright 2022. Hengyi Li. All rights reserved
 * @version 23.0.21 beta
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

/**
 * @brief This function is to read the account information
 * from the data base into the array
 * @param array $db_word is the array that the function fetchAll() received
 */
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

// IF login as the administrator
if (isset($_SESSION['is_administrator']))
{
  if ($_SESSION['is_administrator'] === 1)
  {
    /* The above code is checking to see if the user has entered a new nickname and/or password. If the
user has entered a new nickname, the code will update the nickname in the database. If the user has
entered a new password, the code will update the password in the database. If the user has entered
both a new nickname and a new password, the code will update both the nickname and the password in
the database. */
    if (isset($_POST['nickname_new']) && isset($_POST['password_new']))
    {
      $new_nickname = $_POST['nickname_new'];
      $new_password = $_POST['password_new'];
      if (empty($new_password))
      {
        $statement = $db->prepare('update username set screen_name = :screen_name
        where username = :username;');
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
        $statement = $db->prepare('update username set screen_name = :screen_name where username = :username;');
        $statement->bindValue(':username', $_SESSION['username']);
        $statement->bindValue(':screen_name', $new_nickname);
        $statement->execute();
        $_SESSION['screen_name'] = $new_nickname;
        $password_change = 'update user_password set user_password = :user_password where id = :user_id;';
        $password_modification = $db->prepare($password_change);
        $hashed_password = password_hash($new_password, PASSWORD_DEFAULT);
        $password_modification->bindValue(':user_id', $_SESSION['password_id']);
        $password_modification->bindValue(':user_password', $hashed_password);
        $password_modification->execute();
      }
    }
    /* This is to update the user status. */
    if (isset($_POST['username_account']))
    {
      if (isset($_POST['status_choice']))
      {
        $administrator = $_POST['status_choice'];
        $username_change_status = $_POST['username_account'];
        $update_choice = $db->prepare('update username set is_administrator = :is_admin where username = :username;');
        $update_choice->bindValue(':is_admin', $administrator);
        $update_choice->bindValue(':username', $username_change_status);
        $update_choice->execute();
      }
      /* Deleting the user from the database. */
      if (isset($_POST['delete_choice_yes']))
      {
        $user_to_delete = $_POST['username_account'];
        $db_search = $db->prepare('select username.username, username.password_id
      from username where username.username = :username;');
        $db_search->bindValue(':username', $user_to_delete);
        $db_search->execute();
        $search_result_delete = $db_search->fetchAll(PDO::FETCH_ASSOC);
        $password_id = $search_result_delete[0]['password_id'];
        $delete_name = $db->prepare('delete from username
      where username.username = :name;');
        $delete_name->bindValue(':name', $user_to_delete);
        $delete_name->execute();
        $delete_password = $db->prepare('delete from user_password
      where user_password.id = :id');
        $delete_password->bindValue(':id', $password_id);
        $delete_password->execute();
        if ($_SESSION['username'] === $user_to_delete)
        {
          header("Location: ../Portal/logout.php");
        }
      }
    }
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="utf-8" />
      <title> Manage Words </title>
      <meta name="author" content="Hengyi Li" />
      <link rel="icon" href="../Resources/Diary.png">
      <link rel="stylesheet" href="managewords.css" />
    </head>

    <body>
      <div class="manage_word">
        <div id="intro_header">
          <h1 class="word_manager"> Word Manager </h1>
          <p class="prompt">
            Welcome, <?= $_SESSION['screen_name'] ?>. You logged in as Administrator.
            Here is the control panel, enjoy.
          </p>
        </div>
        <div id="add_word">
          <!-- Add word section -->
          <p class="subtitle"> Add a new word </p>
          <!-- Add the new word -->
          <div id="add_new_word">
            <p>
              <label for="new_word"> What's the word? </label>
              <input type="text" id="new_word" name="new_word" />
              <b id="word_prompt" class="prompt_user, warning_sign">&excl;</b>
            </p>
          </div>
          <!-- Add the part of speech -->
          <div>
            <p>
              <label for="speech"> Part of speech is </label>
              <select name="speech" id="speech">
                <option value=""> Make a choice </option>
                <option value="noun"> noun </option>
                <option value="adjective"> adjective </option>
                <option value="adverb"> adverb </option>
                <option value="verb"> verb </option>
              </select>
              <b id="speech_prompt" class="prompt_user, warning_sign">&excl;</b>
            </p>
          </div>
          <!-- Add the definition -->
          <div>
            <p>
              <label for="def_new_word"> Word definition is </label>
              <input type='text' id="def_new_word" name="def_new_word" />
              <b id="definition_prompt" class="prompt_user, warning_sign">
                &excl; </b>
            </p>
          </div>
          <!-- Add word submit button -->
          <div>
            <p>
              <input type="submit" class="submit" id="add_button" value="Add word" name="submit" disabled />
            </p>
          </div>
        </div>
        <!-- Delete word section -->
        <div id="delete_word">
          <p class="subtitle"> Delete a word </p>
          <p> Type in the box below to search for the word that you want to delete
          </p>
          <!-- Delete word button -->
          <div class="search_delete_box">
            <input type="text" id="search_delete" name="search_delete" />
            <input type="submit" class="submit" id="delete_submit" value="Delete!" name="submit" disabled />
          </div>
        </div>
      </div>
      <div id="user_manager">
        <h2 class="word_manager"> Account Manager </h2>
        <p class="prompt">
          Fill out the form below to change user status
          Go down below to log out or go to the quiz page
          If you want to change the user privilege, look below for information.
        </p>
        <form class="name_and_password" method="post" action="managewords.php">
          <!-- Manage user account section -->
          <p class="subtitle"> Manage your account</p>
          <fieldset class="nickname">
            <label class="input_label" for="nickname_new">
              Please input your new nickname
            </label>
            <input class="input" type="text" id="nickname_new" name="nickname_new" placeholder="New nickname" />
          </fieldset>
          <fieldset class="password">
            <label class="input_label" for="password_new">
              Please input your new Password
            </label>
            <input class="input" type="password" id="password_new" name="password_new" placeholder="New password" />
            <b id="notification_password"> &bull; </b>
          </fieldset>
          <fieldset class="password">
            <label class="login_input_label" for="change_pass_re"> Retype your Password </label>
            <input class="login_input" type="password" id="change_pass_re" name="change_pass_re" placeholder="Password" />
            <b id="notification_password_re"> &bull; </b>
          </fieldset>
          <button type="submit" class="submit" id="name_pass_manage" name="submit">
            Submit
          </button>
        </form>
        <form class="privilege" method="post" action="managewords.php">
          <!-- Manage Users section -->
          <p class="subtitle"> Manage Users </p>
          <fieldset class="username_login">
            <label class="input_label" for="username_account"> Please input the username </label>
            <input class="input" type="text" id="username_account" name="username_account" placeholder="Username" required />
            <b id="notification_username"> &bull; </b>
          </fieldset>
          <div class="change_status">
            <p> Set User privilege </p>
            <input type="radio" id="admin_choice" name="status_choice" value=1 />
            <label class="input_label" for="admin_choice">
              Administrator
            </label>
            <input type="radio" id="user_choice" name="status_choice" value=0 />
            <label class="input_label" for="user_choice">
              Normal user
            </label>
          </div>
          <fieldset class="is_admin">
            <p> Do you want to delete the user? </p>
            <input type="radio" id="delete_choice_yes" name="delete_choice_yes" value=1 />
            <label class="input_label" for="delete_choice_yes"> Yes </label>
            <input type="radio" id="delete_choice_no" name="delete_choice_no" value=0 />
            <label class="input_label" for="delete_choice_no"> No </label>
          </fieldset>
          <button type="submit" class="submit" id="account_manage" name="submit"> Submit </button>
        </form>
        <div class="quiz_button">
          <p class="prompt">
            If you finished your modification, click down below to go back to the quiz
          </p>
          <button type="submit" class="submit" id="user_page"> Quiz <script src="../JumpJS/user_jump.js"></script></button>
        </div>
        <div class="logout_button">
          <p class="prompt"> Click to log out</p>
          <button type="submit" class="submit" id="logout"> Log Out </button>
        </div>
      </div>
      <script src="managewords.js"></script>
    <?php
  }
  else
  {
    ?>
      <p> Nice try, but you shall no pass! </p>
    <?php
  }
}
else
{
    ?>
    <p> Nice try, but you shall no pass! </p>
  <?php
}
  ?>
    </body>

    </html>
