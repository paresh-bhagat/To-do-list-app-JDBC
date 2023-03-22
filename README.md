# TaskBox a To-do list app in Java with MySQL

A To-Do List app using java. User can create account, login, add tasks, change or delete tasks, change password and delete account. It connects to app database (MySQL) with JDBC (Java Database Connectivity) through the Java code. JDBC is one of the standard APIs for database connectivity, using it we can easily run our query, statement, and also fetch data from the database.

## Required Packages
* Swing (GUI widget toolkit for Java)
* JDK
* MySQL
* MySQL Connector to the Java (JAR file), the ‘JAR’ file must be in classpath while compiling and running the code of JDBC

## IDE Used
* IntelliJ IDEA

## How to run

* In IDE

```
* Add jar file to your project (included in lib directory).
* Open MySQL create a database named to_do_list_app. Create table user_info (2 columns user_id( primary key) and user_passsword )
* In Main.java. Change mysql_url, mysql_username and mysql_password to your url, username and password.
* Run Main.java in your IDE.
```

* Ubuntu

```
* Install mysql-server : sudo apt-get install mysql-server
* Start mysql : sudo mysql -u root -p ( password - root)
* To create database : CREATE DATABASE to_do_list_app;
* Connect to database : connect to_do_list_app;
* Create user_info table : CREATE TABLE user_info( user_id varchar(20), user_password varchar(20), PRIMARY KEY (user_id));
* Now that table is created lets start the app
* first export the jar file - export CLASSPATH="/path/to/file":${CLASSPATH}
* Compile - javac Main.java
* Run - java Main

## Images

* Login page

![Login](home.PNG)

* Register

![Register](create_Account.PNG)

* View and add Task

![Register](Task.PNG)
