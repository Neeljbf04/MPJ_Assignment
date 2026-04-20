package com.complaint.dao;

import com.complaint.model.Complaint;
import com.complaint.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO adapted for PostgreSQL ENUM types
 */
public class ComplaintDAO {

    private static final String INSERT_SQL = "INSERT INTO complaints (user_name, email, issue_type, description) VALUES (?, ?, ?::issue_category, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM complaints ORDER BY date_submitted DESC";
    private static final String UPDATE_STATUS_SQL = "UPDATE complaints SET status = 'Resolved'::complaint_status WHERE complaint_id = ?";

    public boolean submitComplaint(Complaint complaint) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, complaint.getUserName());
            ps.setString(2, complaint.getEmail());
            ps.setString(3, complaint.getIssueType()); // Casted to ::issue_category in SQL
            ps.setString(4, complaint.getDescription());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Complaint> getAllComplaints() throws SQLException {
        List<Complaint> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Complaint c = new Complaint();
                c.setComplaintId(rs.getInt("complaint_id"));
                c.setUserName(rs.getString("user_name"));
                c.setEmail(rs.getString("email"));
                c.setIssueType(rs.getString("issue_type"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setDateSubmitted(rs.getTimestamp("date_submitted"));
                list.add(c);
            }
        }
        return list;
    }

    public boolean resolveComplaint(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
