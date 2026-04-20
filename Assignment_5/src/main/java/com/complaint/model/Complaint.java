package com.complaint.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Complaint POJO - Follows JavaBean Standards
 */
public class Complaint implements Serializable {
    private int complaintId;
    private String userName;
    private String email;
    private String issueType;
    private String description;
    private String status;
    private Timestamp dateSubmitted;

    public Complaint() {}

    // Getters and Setters
    public int getComplaintId() { return complaintId; }
    public void setComplaintId(int complaintId) { this.complaintId = complaintId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIssueType() { return issueType; }
    public void setIssueType(String issueType) { this.issueType = issueType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(Timestamp dateSubmitted) { this.dateSubmitted = dateSubmitted; }
}
