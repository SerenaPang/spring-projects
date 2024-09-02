

# Example of Spring JDBC template

This project shows how to use spring jdbc template. The goals are:

- Configure maven with spring jdbc template dependencies
- Use data sources provided by Spring framework
- Execute queries using Spring jdbc template.

## How to build and run Spring Boot

```
 # Command to build the project.
 $ mvn install

 # Command to start spring boot.
 $ mvn spring-boot:run
```


## Database

The table structure that will use is:

```
mysql> show tables;
+----------------------+
| Tables_in_authorbook |
+----------------------+
| AUTHOR               |
| BOOK                 |
+----------------------+
2 rows in set (0.00 sec)

mysql> describe AUTHOR;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+
2 rows in set (0.00 sec)

mysql> describe BOOK;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | int          | NO   | PRI | NULL    | auto_increment |
| name      | varchar(255) | YES  |     | NULL    |                |
| isbn      | varchar(255) | YES  |     | NULL    |                |
| id_author | int          | YES  | MUL | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
4 rows in set (0.00 sec)
```