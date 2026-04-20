package com.complaint.servlet;

import com.complaint.dao.ComplaintDAO;
import com.complaint.model.Complaint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Front Controller Servlet - Implements PRG Pattern
 */
public class ComplaintServlet extends HttpServlet {
    private ComplaintDAO complaintDAO;

    @Override
    public void init() {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "view";

        try {
            switch (action) {
                case "view":
                    showComplaints(request, response);
                    break;
                case "form":
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                default:
                    showComplaints(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("submit".equals(action)) {
                handleSubmit(request, response);
            } else if ("updateStatus".equals(action)) {
                handleUpdateStatus(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Database error occurred: " + e.getMessage());
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void showComplaints(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<Complaint> list = complaintDAO.getAllComplaints();
        request.setAttribute("complaintList", list);
        request.getRequestDispatcher("jsp/viewComplaints.jsp").forward(request, response);
    }

    private void handleSubmit(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String type = request.getParameter("issueType");
        String desc = request.getParameter("description");

        // Server-side validation
        if (name == null || email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=invalidInput");
            return;
        }

        Complaint c = new Complaint();
        c.setUserName(name);
        c.setEmail(email);
        c.setIssueType(type);
        c.setDescription(desc);

        boolean success = complaintDAO.submitComplaint(c);
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "Complaint submitted successfully!");
        } else {
            session.setAttribute("error", "Failed to submit complaint.");
        }
        // PRG Pattern: Redirect after Post
        response.sendRedirect(request.getContextPath() + "/complaint?action=view");
    }

    private void handleUpdateStatus(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = complaintDAO.resolveComplaint(id);
        
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "Complaint #" + id + " marked as Resolved.");
        }
        response.sendRedirect(request.getContextPath() + "/complaint?action=view");
    }
}
