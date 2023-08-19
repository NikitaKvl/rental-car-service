# Rental Car Service

# :closed_book: General info:
The rental car service application is a web application built on SOLID principles, with connection to the database via JDBC

### :globe_with_meridians: You can use this project [online](http://rental-car-service.eu-north-1.elasticbeanstalk.com)

# :eyes: About project:
This is service, where we first need to register or authenticate and after we can:

- Add/Delete/ShowAll `Renter`
- Add/Delete/ShowAll `Manufacturer`
- Add/Delete/ShowAll `Rental Car`
- Add `Renter` to `Rental Car`

# :abacus: Technologies used:

- Java 11
- Apache Maven
- Apache Tomcat 9
- Custom Injector
- Java Servlets
- JDBC
- MySQL

# :computer: If you want to run this project on your computer, you need:
1. To have or install MySQL and Apache Tomcat 9.0.50
2. Clone this project:
```bash
git clone https://github.com/NikitaKvl/rental-car-service.git
```
3. Create DB schema and tables using `init_db.sql` file from `resources` directory
4. Configure `ConnectionUtil` class to create connection to db:
```java
public class ConnectionUtil {
    private static final String URL = "YOUR_DB_URL";
    private static final String USERNAME = "YOUR_LOGIN";
    private static final String PASSWORD = "YOUR_PASSWORD";
}
```
6. Add Tomcat configuration to your project. Use `/` as your Tomcat application context

After all these steps you will be able to run this project locally.