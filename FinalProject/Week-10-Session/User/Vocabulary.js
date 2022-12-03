"use strict";
function get_by_id(id)
{
  return document.getElementById(id);
}
get_by_id("logout").onclick = jump_logout;

function jump_logout()
{
  window.location.href = "../Portal/logout.php";
}

function jump_login()
{
  window.location.href = "managewords_portal_login.php";
}

get_by_id("password_new_re").onchange = function ()
{
  let origin_set_password = get_by_id("password_new").value;
  let re_set_password = get_by_id("password_new_re").value;
  if (origin_set_password !== re_set_password)
  {
    window.alert("They does not match!");
    get_by_id("password_new_re").value = "";
  }
  else
  {
    get_by_id("notification_password_re").classList.add("pass_sign_style");
    get_by_id("notification_password_re").innerHTML = "&check;";
  }
};
get_by_id("password_new").onchange = function ()
{
  let password_id = "password_new";
  password_validation(password_id);
};
/**
 * This function is to validate the password
 * @param {string} password_id  is the id of the password
 */
function password_validation(password_id)
{
  let validator
    = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{6,}$");
  if (get_by_id(password_id).value === "")
  {
    get_by_id("notification_password").classList.remove("warning_sign_style");
    get_by_id("notification_password").classList.remove("warning_prompt_style");
    get_by_id("notification_password").classList.remove("pass_sign_style");
    get_by_id("notification_password").innerHTML = "&bull;";
  }
  else if (validator.test(get_by_id(password_id).value) === false)
  {
    get_by_id("notification_password").innerHTML = "&times;";
    get_by_id("notification_password").classList.remove("pass_sign_style");
    get_by_id("notification_password").classList.add("warning_prompt_style");
    get_by_id(password_id).value = "";
  }
  else
  {
    get_by_id("notification_password").classList.remove("warning_sign_style");
    get_by_id("notification_password").classList.remove("warning_prompt_style");
    get_by_id("notification_password").classList.add("pass_sign_style");
    get_by_id("notification_password").innerHTML = "&check;";
  }
}
