<?php
/**
* Hengyi Li
* This file is to delete words
*/


$host = '127.0.0.1'; //Address of your database
$name = 'root'; // account to login to MysQL
$pwd = 
$db = 'Team_Member'; //name of db
$con = mysqli_connect($host, $name, $pwd) or die("connection failed");
mysqli_select_db($con, $db) or die("db selection failed");

$result = mysqli_query($con, "SELECT * FROM TeamMember"); //personnel is the name of table

// Going to parse the result set
while ($row = mysqli_fetch_assoc($result))
{
  $tmp[] = $row;
}
// Put into json, 'data' is the name
echo json_encode(array("data" => $tmp));
mysqli_close($con);


?>
