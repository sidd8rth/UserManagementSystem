# UserManagementSystem
This is a simple project that helps owners of a franchise to be able to monitor their business in different locations. 
The Project was developed using JSP, Servlet, CSS.
Database used for data storage is MySQL.

### Prerequisites
#### Create database<br>
 create database franchisemanagement;

In this database make two tables<br>
1. companycredentials<br>
 ```
 create table companycredentials(
    id int not null auto_increment
    firm_name varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    address varchar(255) not null,
    contact varchar(255) not null
 );
 ```
 2. company_dist<br>
 ```
 create table company_dist(
    dist_username varchar(255) not null,
    dist_password varchar(255) not null,
    company_username varchar(255) not null
 );
 ```