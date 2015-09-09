________________Implemented________________
1. UserDAOImpl (realization of UserDAO interface)
2. changes in cofigurations files (to enable component scan, annotations, transactions and so on).
3. UserServieImpl (realization of UserService interface)
4. Implementation of UserController logic for Users with necessary JSPs (instead analog logic for Items)
5. Added logging with Log4j. (As required used for showing logging attempts + starting info)
6. Added Spring Security with 2 predefined users.
7. Added 3 roles for Security: /- open for everyone, /users/ and /users/show - open for registered,
/users/add, /users/update and /users/delete - open only for ROLE_ADMIN
8. Added session management.
9. Added security annotation for methods.
10. Added 2 JUnit tests (Testing.java - test shows that communication with DB works properly,
TestSecurity.java - test shows predefined roles work properly to prevent action if role is not enough to perform it).

11. Added Item domen and ItemDAO with ItemDAOImpl.
12. Added business logic for Item according to User.
13. Added Spring Security covering for /items/ urls.
14. Added input validation on client side on login page

____________________________________________
Text for Pre-Email to applicant:
You will receive an application skeleton with a number of tasks to complete. The application is a web application, which uses Maven, JPA and Spring. It comes with an embedded H2 database and the Maven Jetty plugin configured.

Text for Email to applicant:
Clone the git repository and work on the following tasks.

The application uses Maven and Jetty server, so running is should be easy.
Your tasks:
* Implement the UserDAO using JPA.
* Implement the UserService using the UserDAO and transactions.
* Implement a RESTful UserController to manage users.
* Implement a simple user login dialog to login a user. Use HttpSession to store the user credentials to save time, Spring Security can also be used.
* Use Spring AOP to log all login attempts via console output or log4j.

Big Bonus:
in addition to  securing the URLs, secure access to ItemDAO(has to be created along side with Item domain) via spring security.
-restrict ItemDAO access if user is not authenticated by session.  filter  any findBy~ and findAll method within ItemDAO. ACL has to be used in order to secure domain.
-you can either create an API or prepare jUnit tests for adding&viewing Item object.(only authenticated users can insert and view objects)

here is a basic flow
--
when a user is registered, create domain object as it is.

when an item is added, create domain object as well as an Acl entry for item domain
when item object is requested via find* methods filter out non authorized items.
//item object must linked to user domain.(acl_sid table has  User.id as sid)

ACL domain security is actively used in datapine backend, so completing big bonus is most likely to be decisive for our application process. ItemDAO can include any attribute(it is just used to demonstrate ACL usage). 

Good Luck!!
