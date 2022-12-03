/**
 * @description A program to manage the word list for the GRE quiz
 * @author Hengyi Li
 * @copyright Copyright (c). 2022 Hengyi Li. All rights reserved
 * @version 40.32.1.0 Beta
 */
"use strict";
// a global variable to prevent race
let xhr = null;
// Get the element by using the id
function get_by_id(id)
{
  return document.getElementById(id);
}
// Get the element by using the name
function get_by_name(name)
{
  return document.getElementsByName(name);
}
get_by_id("logout").onclick = jump_logout;
/**
 * This function is to load with a empty div
 */
window.onload = function ()
{
  create_new_div_entry();
  get_by_id("user_manager").onchange = function ()
  {
    let password_id = "password_new";
    password_validation(password_id);
  };
  get_by_id("change_pass_re").onchange = function ()
  {
    let origin_set_password = get_by_id("change_pass_re").value;
    let re_set_password = get_by_id("password_new").value;
    if (origin_set_password !== re_set_password)
    {
      window.alert("They does not match!");
      get_by_id("change_pass_re").value = "";
    }
    else
    {
      get_by_id("notification_password_re").classList.add("pass_sign_style");
      get_by_id("notification_password_re").innerHTML = "&check;";
    }
  };
};

/**
 * This is control the add section for validation
 */
get_by_id("add_word").onchange = function ()
{
  clear_delete();
  clean_previous_entry();
  get_by_id("search_delete").value = "";
  new_word_validate();
  speech_validate();
  new_definition_validate();
  let new_definition = get_by_id("def_new_word").value;
  let index = 0;
  while (index < new_definition.length)
  {
    if (new_definition[index].charCodeAt() > 127)
    {
      get_by_id("definition_prompt").classList.remove("warning_sign_style");
      get_by_id("definition_prompt").classList.remove("pass_sign_style");
      get_by_id("definition_prompt").classList.add("warning_prompt_style");
      get_by_id("definition_prompt").innerHTML =
        "Please only input English character and symbols";
      clear_add();
      new_word_validate();
      speech_validate();
    }
    index += 1;
  }
  get_by_id("add_button").disabled = form_validation_add();
};

/**
 * This control the submit a new entry
 */
get_by_id("add_button").onclick = function ()
{
  clear_delete();
  get_by_id("search_delete").value = "";
  clean_previous_entry();
  let new_word = get_by_id("new_word").value;
  let new_speech = get_by_id("speech").value;
  let new_definition = get_by_id("def_new_word").value;
  let index = 0;
  while (index < new_definition.length)
  {
    if (new_definition[index] === "&")
    {
      new_definition = new_definition.replace("&", "π");
    }
    else if (new_definition[index] === "+")
    {
      new_definition = new_definition.replace("+", "√");
    }
    index += 1;
  }
  add_new_entry(new_word, new_speech, new_definition);
  clear_add();
  get_by_id("search_delete").value = "";
  new_word_validate();
  speech_validate();
  new_definition_validate();
};

/* Disabling the delete submit button if the delete word field is empty. */
get_by_id("delete_word").onchange = function ()
{
  clear_add();
  new_word_validate();
  speech_validate();
  new_definition_validate();
  get_by_id("delete_submit").disabled = form_validation_delete();
};

/**
 * This control the search to delete clean
 */
get_by_id("search_delete").onkeyup = function ()
{
  clean_previous_entry();
  let search_string = get_by_id("search_delete").value;
  load_words_from_SQL(search_string);
};

/**
 * This handle the clean up after the delete
 */
get_by_id("delete_submit").onclick = function ()
{
  delete_word_button();
  clean_previous_entry();
  get_by_id("search_delete").value = "";
};

/**
 * It checks if the password is valid, and if it is, it displays a check mark, if it isn't, it displays
 * a cross mark.
 * @param password_id - The id of the password input field.
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

/**
 * This function is to find the duplicate
 * @param {string} words is the words to find duplicate
 * @param {string} part is the part of the words to find duplicate
 * @param {string} definition is the definition of the words to find duplicate
 */
function add_new_entry(words, part, definition)
{
  if (words !== "" && part !== "" && definition !== "")
  {
    xhr = new XMLHttpRequest();
    const search_string = [words, part, definition];
    const new_entry_send = `validate=${ JSON.stringify(search_string) }`;
    xhr.open("POST", "add_word.php");
    xhr.setRequestHeader
      ("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    xhr.onload = function ()
    {
      let duplicate_result = JSON.parse(xhr.responseText);
      if (duplicate_result === "True")
      {
        window.alert(" The entry you entered is already exists!");
        clear_add();
        new_word_validate();
        speech_validate();
        new_definition_validate();
      }
      else if (duplicate_result === "False")
      {
        window.alert("Add successful");
      }
    };
    xhr.send(new_entry_send);
  }
}

/**
 * It checks if the input is empty, if it's not empty, it checks if the input is only lowercase
 * alphabet letters, if it is, it displays a check mark, if it's not, it displays a warning sign and a
 * warning message.
 */
function new_word_validate()
{
  let regex_word = new RegExp("^[a-z]+$");
  if (get_by_id("new_word").value === "")
  {
    get_by_id("word_prompt").classList.remove("pass_sign_style");
    get_by_id("word_prompt").classList.add("warning_sign_style");
    get_by_id("word_prompt").innerHTML = "&excl;";
  }
  else if (!regex_word.test(get_by_id("new_word").value))
  {
    get_by_id("word_prompt").classList.remove("pass_sign_style");
    get_by_id("word_prompt").classList.add("warning_prompt_style");
    get_by_id("word_prompt").innerHTML =
      "Only lowercase alphabet letters is accepted";
    clear_add();
  }
  else
  {
    get_by_id("word_prompt").classList.remove("warning_sign_style");
    get_by_id("word_prompt").classList.remove("warning_prompt_style");
    get_by_id("word_prompt").classList.add("pass_sign_style");
    get_by_id("word_prompt").innerHTML = "&check;";
  }
}


/**
 * If the speech textarea is not empty, then remove the warning sign class and add the pass sign class,
 * and change the prompt to a check mark. Otherwise, remove the pass sign class and add the warning
 * sign class, and change the prompt to an exclamation mark.
 */
function speech_validate()
{
  if (get_by_id("speech").value !== "")
  {
    get_by_id("speech_prompt").classList.remove("warning_sign_style");
    get_by_id("speech_prompt").classList.add("pass_sign_style");
    get_by_id("speech_prompt").innerHTML = "&check;";
  }
  else
  {
    get_by_id("speech_prompt").classList.remove("pass_sign_style");
    get_by_id("speech_prompt").classList.add("warning_sign_style");
    get_by_id("speech_prompt").innerHTML = "&excl;";
  }
}


/**
 * It validates the input of the new definition.
 */
function new_definition_validate()
{
  if (get_by_id("def_new_word").value !== "")
  {
    get_by_id("definition_prompt").classList.remove("warning_sign_style");
    get_by_id("definition_prompt").classList.remove("warning_prompt_style");
    get_by_id("definition_prompt").classList.add("pass_sign_style");
    get_by_id("definition_prompt").innerHTML = "&check;";
  }
  else
  {
    get_by_id("definition_prompt").classList.remove("warning_sign_style");
    get_by_id("definition_prompt").classList.remove("pass_sign_style");
    get_by_id("definition_prompt").classList.add("warning_prompt_style");
    get_by_id("definition_prompt").innerHTML = "&excl;";
  }
}

/**
 * If the input fields are empty, then return true. If the input fields are not empty, then return
 * false.
 * @returns the value of the variable "form_validation_add".
 */
function form_validation_add()
{
  if (get_by_id("new_word").value === "" &&
    get_by_id("speech").value === "" &&
    get_by_id("def_new_word").value === "")
  {
    return true;
  }
  else if (get_by_id("new_word").value === "")
  {
    get_by_id("new_word").focus();
    return true;
  }
  else if (get_by_id("speech").value === "")
  {
    get_by_id("speech").focus();
    return true;
  }
  else if (get_by_id("def_new_word").value === "")
  {
    get_by_id("def_new_word").focus();
    return true;
  }
  else
  {
    return false;
  }
}

/**
 * This function is to determine whether the checkbox is checked or not
 * @returns true if the delete check box is empty,
 * return false if the checkbox is not empty
 */
function form_validation_delete()
{
  let index = 0;
  let empty = true;
  let delete_choice = get_by_name("choice_to_delete[]");
  while (index < delete_choice.length)
  {
    if (delete_choice[index].checked === true)
    {
      empty = false;
    }
    index += 1;
  }
  return empty;
}

/**
 * This function is to clean the addition
 */
function clear_add()
{
  get_by_id("new_word").value = "";
  get_by_id("speech").value = "";
  get_by_id("def_new_word").value = "";
}

/**
 * This function is to load from database
 * @param {string} search_string is the string to search
 */
function load_words_from_SQL(search_string)
{
  if (search_string !== "")
  {
    let response = [];
    xhr = new XMLHttpRequest();
    const url = "get_words.php?search=" + search_string;
    xhr.open("GET", url, true);
    xhr.onload = function ()
    {
      const results = JSON.parse(xhr.responseText);
      let index = 0;
      while (index < results.length)
      {
        response.push(results[index]);
        index += 1;
      }
      display(response);
    };
    xhr.send();
  }
}

/**
 * This function is to display the entry
 * @param {array} response is the array that received from the SQL
 */
function display(response)
{
  let index = 0;
  while (index < response.length)
  {
    let checkbox = document.createElement("input");
    checkbox.setAttribute("type", "checkbox");
    checkbox.setAttribute("name", "choice_to_delete[]");
    checkbox.setAttribute("value", index);
    let new_word_line = document.createElement("span");
    new_word_line.innerHTML =
      " <b><ins>" + response[index].word + "</ins>:</b> \t" +
      "<b>(" + response[index].part + ")</b> \t" +
      response[index].definition;
    new_word_line.classList.add("word_list");
    new_word_line.setAttribute("id", index);
    let original_div = get_by_id("display");
    let newline = document.createElement("p");
    original_div.appendChild(checkbox);
    original_div.appendChild(new_word_line);
    original_div.appendChild(newline);
    index += 1;
  }
}

/**
 * It takes the text from the display and sets it to an empty string.
 */
function clean_previous_entry()
{
  let original_text = get_by_id("display");
  original_text.innerHTML = "";
}

/**
 * Create_new_div_entry() creates a new div element and appends it to the delete_word div element.
 */
function create_new_div_entry()
{
  let display_section = document.createElement("div");
  display_section.setAttribute("id", "display");
  let father_node = get_by_id("delete_word");
  father_node.appendChild(display_section);
}

/**
 * This function is to clear the delete section
 */
function clear_delete()
{
  let index = 0;
  while (index < get_by_name("choice_to_delete[]").length)
  {
    get_by_name("choice_to_delete[]")[index].checked = false;
    index += 1;
  }
  get_by_id();
}

/**
 * It takes the words that the user has selected to delete, and sends them to the server.
 */
function delete_word_button()
{
  let index = 0;
  let lines_to_delete = [];
  let words_to_delete = [];
  let selected_word = get_by_name("choice_to_delete[]");
  while (index < selected_word.length)
  {
    if (selected_word[index].checked === true)
    {
      lines_to_delete.push(get_by_id(index).innerHTML);
    }
    index += 1;
  }
  lines_to_delete.forEach(function (elements)
  {
    let split_array = elements.split("\t");
    words_to_delete.push(split_array[0]);
    words_to_delete.push(split_array[1]);
  });

  xhr = new XMLHttpRequest();
  const delete_string = `delete_word=${ JSON.stringify(words_to_delete) }`;
  xhr.open("POST", "delete_words.php");
  xhr.setRequestHeader
    ("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
  xhr.send(delete_string);
}

/**
 * If the user is inactive for more than 30 minutes, then log them out.
 */
function jump_logout()
{
  window.location.href = "../Portal/logout.php";
}

