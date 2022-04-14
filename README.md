# UserManagementSystem
This is a simple project that helps owners of a franchise to be able to monitor their business in different locations. 
The Project was developed using JSP, Servlet, CSS.
Database used for data storage is MySQL.

### Prerequisites
#### Create database<br>
``` create database franchisemanagement;```

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
<img src="/screenshots/home.png" width="800" style="text-align: center;" />

### Project Setup

1. Install Eclipse IDE for Java EE Developers and Tomcat 10.0 Server.
2. Clone the repository
3. Import this project in eclipse workspace
    - Head to import
    - Existing project into Workspace
    - Browse to respective directory
    - Click Finish (Make sure the click the checkbox of make a new copy of this project)
4. Add tomcat server to your project runtime environment.
5. Right click on proejct name, select Run on Server and follow the process as shown in eclipse.

#### About Us
<p float="left">
  <img src="/screenshots/about-chopra.png" width="400" />
  <img src="/screenshots/about-porwal.png" width="400" height="243" /> 
</p>

#### Contact Us
<img src="/screenshots/contact-us.png" width="800" />

<!-- #### Company Register-Login
<p float="left">
  <img src="/screenshots/company-register.png" width="500" height="235"/>
  <img src="/screenshots/company-login.png" width="500" height="235" /> 
</p>

<img src="/screenshots/distributor-list.png" width="500" />
<img src="/screenshots/article-list.png" width="500"  />  -->



