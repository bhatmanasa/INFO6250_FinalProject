/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Manasa
 */
@Entity
@Table(name="user_report_tbl")
public class Report {
        @Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
   int rId;

    @Column(name="Statement")
    String statement;
    @Column(name="Time")
    Date time;
    @Column(name="Status")
    int status; 
    @Column(name="reportId")
    int reporterId;
    @Column(name="reportedId")
    int reportedId;
    @Transient
    UserProfile reporterProfile;
    @Transient
    UserProfile reportedProfile;
//    public UserProfile getReporter() {
//        return reporter;
//    }
//
//    public void setReporter(UserProfile reporter) {
//        this.reporter = reporter;
//    }

//    public UserProfile getReportedProfile() {
//        return reportedProfile;
//    }
//
//    public void setReportedProfile(UserProfile reportedProfile) {
//        this.reportedProfile = reportedProfile;
//    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getReportedId() {
        return reportedId;
    }

    public void setReportedId(int reportedId) {
        this.reportedId = reportedId;
    }

    public UserProfile getReporterProfile() {
        return reporterProfile;
    }

    public void setReporterProfile(UserProfile reporterProfile) {
        this.reporterProfile = reporterProfile;
    }

    public UserProfile getReportedProfile() {
        return reportedProfile;
    }

    public void setReportedProfile(UserProfile reportedProfoile) {
        this.reportedProfile = reportedProfoile;
    }
    
}
