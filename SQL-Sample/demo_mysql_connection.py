import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="123456",
  database="android_connection_demo"
)

print(mydb)

mycursor = mydb.cursor()

mycursor.execute("SELECT * FROM personnel")

myresult = mycursor.fetchall()

for x in myresult:
  print(x)