# Spring-Framework-Essentials
Projects used in Spring Framework Essentials video course

See http://shop.oreilly.com/product/0636920046837.do for videos (also available on Safari)

Note: Jdbc and JPA projects require a MySQL database with the following properties:
- URL is jdbc:mysql://localhost:3306/spring
- username is 'root'
- no password

If you change those properties, update the file `prod.properties` in the `src/main/resources` folder.

All projects except for `BootAccounts` are part of a multi-project Gradle build to make testing easier. Just run `gradle build` 
at a command prompt (once the MySQL DB is configured) and everything should run correctly.

The Spring Boot project is not part of the same build. It has its own build file and configuration.

You can send me questions at `ken.kousen@kousenit.com`.

Ken Kousen

Marlborough, CT

Feb 2016
