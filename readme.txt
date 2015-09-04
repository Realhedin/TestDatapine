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