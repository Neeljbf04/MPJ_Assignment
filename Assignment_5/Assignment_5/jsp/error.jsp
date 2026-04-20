<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error | SmartTracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container" style="text-align: center; padding-top: 5rem;">
        <div class="card">
            <h1 style="color: var(--danger);">Oops! Something went wrong.</h1>
            <p>An unexpected error occurred. Please try again later.</p>
            <c:if test="${not empty error}">
                <p style="background: #fee2e2; padding: 10px; border-radius: 5px;">
                    <code>${error}</code>
                </p>
            </c:if>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn">Back to Home</a>
        </div>
    </div>
</body>
</html>
