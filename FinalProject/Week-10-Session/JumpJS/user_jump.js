"use strict";
function get_by_id(id)
{
  return document.getElementById(id);
}
get_by_id("user_page").onclick = jump_user;

/**
 * It redirects the user to the Vocabulary page
 */
function jump_user()
{
  window.location.href = "../User/Vocabulary.php";
}
