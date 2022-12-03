<?php

/**
 * It takes the database result and put it into an array
 *
 * @param db_word the array of the database
 *
 *  The user is being returned to the managewords_portal_signup.php page.
 */
error_reporting(E_ALL);
ini_set('display_errors', '1');
session_start();



/* Connecting to the database. */
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

if (!(isset($_SESSION['is_administrator'])))
{
  if (
    isset($_POST) && isset($_POST['username_signup'])
    && isset($_POST['nickname_signup'])
    && isset($_POST['password_signup'])
  )
  {
    $username_signup = $_POST['username_signup'];
    $nickname_signup = htmlspecialchars($_POST['nickname_signup']);
    $password_signup = $_POST['password_signup'];
    $search = $db->prepare("select username.username, username.screen_name,
            user_password.user_password, username.password_id, username.is_administrator
            from username join user_password
            on username.password_id = user_password.id
            where username.username = :username;");
    $search->bindValue(':username', $username_signup, PDO::PARAM_STR);
    $search->execute();
    $search_results = $search->fetchAll(PDO::FETCH_ASSOC);
    $user = read_database_into_array($search_results);
    if (count($user) > 0)
    {
      header("Location: duplicate.php");
    }
    else
    {
      // Get the password into the database
      $password_new = $db->prepare('insert into user_password(user_password)
      values(:user_password);');
      $hashed_password = password_hash($password_signup, PASSWORD_DEFAULT);
      $password_new->bindValue(':user_password', $hashed_password);
      $password_new->execute();
      $get_id = $db->prepare('select user_password.id from user_password
      where user_password.user_password = :history;');
      $get_id->bindValue(':history', $hashed_password);
      $get_id->execute();
      $result = $get_id->fetchAll(PDO::FETCH_ASSOC);
      $new_id = $result[0]['id'];
      // Get the name into the database
      $statement = $db->prepare('insert into username(username, screen_name, password_id, is_administrator) values (:username_new, :screen_name_new, :password_id_new, False);');
      $statement->bindValue(':username_new', $username_signup);
      $statement->bindValue(':screen_name_new', $nickname_signup);
      $statement->bindValue(':password_id_new', $new_id);
      $statement->execute();
      $_SESSION['username'] = $username_signup;
      $_SESSION['screen_name'] = $nickname_signup;
      $_SESSION['password_id'] = $new_id;
      $_SESSION['is_administrator'] = 0;
      header('Location: ../User/Vocabulary.php');
    }
  }
?>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="utf-8" />
    <title> Database Management System </title>
    <meta name="author" content="Hengyi Li" />
    <link rel="icon" href="../Resources/favicon.png">
    <link rel="stylesheet" href="managewords_portal.css" />
  </head>

  <body>
    <div class="prompt">
      <h1> Sign Up </h1>
      <p> Create a new account now by type in the username, screen name and the password </p>
    </div>
    <div id="signup" class="prompt">
      <form method="post" action="managewords_portal_signup.php">
        <fieldset class="username">
          <label class="login_input_label" for="username_signup"> Create a username </label>
          <input class="login_input" type="text" id="username_signup" name="username_signup" placeholder="Username" required />
          <b id="notification_username"> &bull; </b>
        </fieldset>
        <br />
        <fieldset class="nickname">
          <label class="login_input_label" for="nickname_signup"> Create a nickname </label>
          <input class="login_input" type="text" id="nickname_signup" name="nickname_signup" placeholder="nickname" required />
        </fieldset>
        <br />
        <fieldset class="password">
          <label class="login_input_label" for="password_signup"> Create your Password </label>
          <input class="login_input" type="password" id="password_signup" name="password_signup" placeholder="Password" required />
          <b id="notification_password"> &bull; </b>
        </fieldset>
        <br />
        <fieldset class="password">
          <label class="login_input_label" for="password_signup_re"> Retype your Password </label>
          <input class="login_input" type="password" id="password_signup_re" name="password_signup_re" placeholder="Password" required />
          <b id="notification_password_re"> &bull; </b>
        </fieldset>
        <br />
        <button type="submit" class="submit" name="submit"> Sign Up Now ! </button>
      </form>
      <br />
      <p> Have an account already? Click below to login</p>
      <button type="submit" class="submit" id="login_button" name="submit"> Log In </button>
    </div>
    <div class="prompt">
      <h2> Password requirement </h2>
      <p>
        Username only allow letters, digits, and underscores. <br />
        Password cannot contain spaces, must be at least 6 characters long,
        must contain at least one each of uppercase, lowercase, digit, and punctuation characters.
      </p>
    </div>
  <?php
}
else
{
  ?>
    <?php
    if ($_SESSION['is_administrator'] == 1)
    {
    ?>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8" />
        <title> Database Management System </title>
        <meta name="author" content="Hengyi Li" />
        <link rel="icon" href="../Resources/favicon.png">
        <link rel="stylesheet" href="managewords_portal.css" />
      </head>

      <body>
        <div class="prompt">
          <h1>Sign Up</h1>
          <p> Nice try, but you have already Logged in</p>
          <button type="submit" class="submit" id="admin_page"> Take me to main page </button>
        </div>
        <script src="../JumpJS/admin_jump.js"></script>
      </body>

      </html>
    <?php
    }
    else
    {
    ?>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8" />
        <title> Database Management System </title>
        <meta name="author" content="Hengyi Li" />
        <link rel="icon" href="../Resources/favicon.png">
        <link rel="stylesheet" href="managewords_portal.css" />
      </head>

      <body>
        <div class="prompt">
          <h1> Sign Up </h1>
          <p> Nice try, but you have already Logged in </p>
          <button type="submit" class="submit" id="user_page"> Take me to Users page </button>
        </div>
        <script src="../JumpJS/user_jump.js"></script>
      </body>

      </html>
  <?php
    }
  }
  ?>
  <script src="managewords_portal.js"></script>
  </body>

  </html>
