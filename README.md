# Spring-Framework-Essentials
Projects used in Spring Framework Essentials video course

See http://shop.oreilly.com/product/0636920046837.do for videos (also available on Safari)

* Updated Feb 2017 to use Spring 4.3.6.RELEASE and Gradle wrapper 3.3

Note: Jdbc, JPA, and SpringData projects require a MySQL database with the following properties:
- URL is jdbc:mysql://localhost:3306/spring
- username is 'root'
- no password

If you change those properties, update the file `prod.properties` in the `src/main/resources` folder.

All projects are now independent Gradle builds. Just run `gradle build`
at a command prompt in each root (for the database projects, once the MySQL DB is configured) and everything should run correctly.

You can send me questions at `ken.kousen@kousenit.com`.

Ken Kousen

Marlborough, CT

Feb 2016
