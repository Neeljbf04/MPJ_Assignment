<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Complaint | SmartTracker</title>
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
        <div class="card">
            <h1>Submit a New Complaint</h1>
            <p>Please fill out the form below. We'll get back to you shortly.</p>
            
            <form action="${pageContext.request.contextPath}/complaint?action=submit" method="POST" onsubmit="return validateForm()">
                <div style="margin-bottom: 1rem;">
                    <label>Full Name</label><br>
                    <input type="text" name="userName" id="userName" required style="width:100%; padding:8px; margin-top:5px;">
                </div>
                
                <div style="margin-bottom: 1rem;">
                    <label>Email Address</label><br>
                    <input type="email" name="email" id="email" required style="width:100%; padding:8px; margin-top:5px;">
                </div>

                <div style="margin-bottom: 1rem;">
                    <label>Issue Type</label><br>
                    <select name="issueType" style="width:100%; padding:8px; margin-top:5px;">
                        <option value="Technical">Technical Issue</option>
                        <option value="Billing">Billing & Payments</option>
                        <option value="Feedback">General Feedback</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div style="margin-bottom: 1rem;">
                    <label>Description</label><br>
                    <textarea name="description" id="desc" maxlength="500" required rows="5" style="width:100%; padding:8px; margin-top:5px;"></textarea>
                    <small id="charCount">0/500 characters</small>
                </div>

                <button type="submit" class="btn">Submit Complaint</button>
            </form>
        </div>
    </div>

    <script>
        // Character counter
        const textarea = document.getElementById('desc');
        textarea.addEventListener('input', () => {
            document.getElementById('charCount').innerText = textarea.value.length + "/500 characters";
        });

        // Client-side validation
        function validateForm() {
            const email = document.getElementById('email').value;
            const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if(!regex.test(email)) {
                alert("Please enter a valid email.");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
