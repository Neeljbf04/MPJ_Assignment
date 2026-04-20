# Smart Complaint & Issue Tracking System

This is a production-grade JSP/Servlet application following MVC and DAO patterns.

## 🚀 Setup Instructions

1. **Database**: Run `schema.sql` in your MySQL database.
2. **Connection**: Update `src/main/java/com/complaint/util/DBConnection.java` with your MySQL `root` password.
3. **Libraries**: Add `mysql-connector-java.jar`, `jstl.jar`, and `standard.jar` to `src/main/webapp/WEB-INF/lib/`.
4. **Deploy**: Build and deploy to Apache Tomcat.

---

## 🎓 Viva Questions & Answers

### 1. Servlet vs JSP?
- **Servlet**: Java code with HTML inside (Logic-heavy). Better for Controllers.
- **JSP**: HTML code with Java inside (UI-heavy). Better for Views.

### 2. GET vs POST?
- **GET**: Data in URL, limited size, cached. Used for fetching.
- **POST**: Data in body, no size limit, secure. Used for submission.

### 3. GenericServlet vs HttpServlet?
- **GenericServlet**: Protocol-independent.
- **HttpServlet**: Protocol-dependent (specifically HTTP). It provides `doGet()`, `doPost()`, etc.

### 4. Advantages of JSP?
- Easy to design UI.
- No need to recompile manually (Tomcat does it).
- Supports JSTL and EL (Expression Language).

### 5. JDBC Components?
- **DriverManager**: Manages drivers.
- **Connection**: Interface with DB.
- **Statement/PreparedStatement**: Executes queries.
- **ResultSet**: Holds data from queries.

### 6. MVC Architecture?
- **Model**: Data logic (POJO + DAO).
- **View**: Presentation (JSP).
- **Controller**: Routing/Logic (Servlet).

### 7. PreparedStatement vs Statement?
- **PreparedStatement**: Pre-compiled, prevents SQL Injection, better performance for repeated queries.
- **Statement**: Compiled every time, vulnerable to SQL Injection.

### 8. PRG Pattern?
- **Post-Redirect-Get**: After a POST (submission), the server sends a Redirect (302) to a GET page. This prevents "Duplicate Form Submission" on page refresh.
