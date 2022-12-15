<?php
session_start();

$_SESSION = array(); //Clean all the data from the session
if (session_id() != '' || isset($_COOKIE[session_name()]))
{
  setcookie(session_name(), '', time() - 3600, '/');
}
session_destroy();
header('Location: managewords_portal_login.php');
?>
