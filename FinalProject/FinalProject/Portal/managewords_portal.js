"use strict";
// a global variable to prevent race
let xhr = null;
// Get the element by using the id

function get_by_id(id)
{
  return document.getElementById(id);
}


window.onload = function ()
{
  if (get_by_id("signup_button") !== null)
  {
    get_by_id("signup_button").onclick = function ()
    {
      jump_signup();
    };
  }
  if (get_by_id("login_button") !== null)
  {
    get_by_id("login_button").onclick = function ()
    {
      jump_login();
    };
  }
  if (get_by_id("password_signup_re") !== null)
  {
    get_by_id("password_signup_re").onchange = function ()
    {
      let origin_set_password = get_by_id("password_signup").value;
      let re_set_password = get_by_id("password_signup_re").value;
      if (origin_set_password !== re_set_password)
      {
        window.alert("They does not match!");
        get_by_id("password_signup_re").value = "";
      }
    };
  }

  if (get_by_id("login") !== null)
  {
    get_by_id("login").onchange = function ()
    {
      let username = "username_login";
      username_validation(username);
      let password_id = "password_login";
      password_validation(password_id);
    };
  }
  else if (get_by_id("signup") !== null)
  {
    get_by_id("signup").onchange = function ()
    {
      let username = "username_signup";
      username_validation(username);
      let password_signup = "password_signup";
      password_validation(password_signup);
    };
  }
};

/**
 * This function is to validate the username for the account
 * @param {string} username is the username of the account
 */
function username_validation(username)
{
  let regex_word = new RegExp("\\w+");

  if (get_by_id(username).value === "")
  {
    get_by_id("notification_username").classList.remove("warning_sign_style");
    get_by_id("notification_username").classList.remove("warning_prompt_style");
    get_by_id("notification_username").classList.remove("pass_sign_style");
    get_by_id("notification_username").innerHTML = "&bull;";
  }
  else if (!regex_word.test(get_by_id(username).value))
  {
    get_by_id("notification_username").innerHTML = "&times;";
    get_by_id("notification_username").classList.remove("pass_sign_style");
    get_by_id("notification_username").classList.add("warning_prompt_style");
    clean_input();
  }
  else
  {
    get_by_id("notification_username").classList.remove("warning_sign_style");
    get_by_id("notification_username").classList.remove("warning_prompt_style");
    get_by_id("notification_username").classList.add("pass_sign_style");
    get_by_id("notification_username").innerHTML = "&check;";
  }
}

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

function clean_input()
{
  get_by_id("username_login").value = "";
  get_by_id("password_login").value = "";
}

function jump_signup()
{
  window.location.href = "managewords_portal_signup.php";
}

function jump_login()
{
  window.location.href = "managewords_portal_login.php";
}
