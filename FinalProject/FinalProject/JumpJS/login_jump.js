"use strict";
function get_by_id(id)
{
  return document.getElementById(id);
}
get_by_id("login_page").onclick = jump_login;

/**
 * It redirects the user to the login page if they are not logged in
 */
function jump_login()
{
  window.location.href = "../Portal/managewords_portal_login.php";
}
