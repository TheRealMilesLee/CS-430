"use strict";
function get_by_id(id)
{
  return document.getElementById(id);
}

get_by_id("admin_page").onclick = jump_admin;
/**
 * It redirects the user to the managewords.php page.
 */
function jump_admin()
{
  window.location.href = "../Admin/managewords.php";
}
