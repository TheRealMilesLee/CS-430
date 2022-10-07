var mysql = require('mysql');

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "123456",
  database: "android_connection_demo"
});

con.connect(function (err)
{
  if (err) throw err;
  con.query("SELECT * FROM personnel", function (err, result, fields)
  {
    if (err) throw err;
    console.log(result);
  });
});
