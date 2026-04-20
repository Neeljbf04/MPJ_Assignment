<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Complaints | SmartTracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <nav>
        <h2 style="color:white; margin:0;">SmartTracker</h2>
        <div>
            <a href="${pageContext.request.contextPath}/index.jsp">New Complaint</a>
            <a href="${pageContext.request.contextPath}/complaint?action=view">View All</a>
        </div>
    </nav>

    <div class="container">
        <!-- Flash Message Logic -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success">
                <c:out value="${sessionScope.message}"/>
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <div class="card">
            <h1>Complaint Logs</h1>
            
            <c:choose>
                <c:when test="${empty complaintList}">
                    <div style="text-align:center; padding:3rem;">
                        <img src="https://cdn-icons-png.flaticon.com/512/4076/4076432.png" width="100" style="opacity:0.3">
                        <p style="color:var(--secondary)">No complaints found. All quiet!</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>User</th>
                                <th>Issue</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="comp" items="${complaintList}">
                                <tr>
                                    <td>#<c:out value="${comp.complaintId}"/></td>
                                    <td>
                                        <strong><c:out value="${comp.userName}"/></strong><br>
                                        <small><c:out value="${comp.email}"/></small>
                                    </td>
                                    <td><c:out value="${comp.issueType}"/></td>
                                    <td>
                                        <c:out value="${fn:substring(comp.description, 0, 40)}"/>...
                                    </td>
                                    <td>
                                        <span class="badge ${comp.status == 'Pending' ? 'badge-pending' : 'badge-resolved'}">
                                            <c:out value="${comp.status}"/>
                                        </span>
                                    </td>
                                    <td>
                                        <c:if test="${comp.status == 'Pending'}">
                                            <form action="${pageContext.request.contextPath}/complaint?action=updateStatus" method="POST">
                                                <input type="hidden" name="id" value="${comp.complaintId}">
                                                <button type="submit" class="btn btn-resolve">Resolve</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
