package com.example.callreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * 고객 지원 Call Report 한 건. 컬럼은 src/main/resources/schema.sql의 call_report 테이블과 같다.
 */
@Entity
@Table(name = "call_report")
public class CallReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 지원월 (예: 2026-11) */
    @Column(name = "support_month", length = 10)
    private String supportMonth;

    /** 담당팀 */
    @Column(name = "team", length = 50)
    private String team;

    /** 담당자 */
    @Column(name = "manager", length = 50)
    private String manager;

    /** 고객명 */
    @Column(name = "customer_name", length = 100)
    private String customerName;

    /** 고객사 담당자 */
    @Column(name = "customer_manager", length = 100)
    private String customerManager;

    /** 지원일자 */
    @Column(name = "support_date")
    private LocalDate supportDate;

    /** 완료여부 */
    @Column(name = "status", length = 20)
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSupportMonth() { return supportMonth; }
    public void setSupportMonth(String supportMonth) { this.supportMonth = supportMonth; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerManager() { return customerManager; }
    public void setCustomerManager(String customerManager) { this.customerManager = customerManager; }

    public LocalDate getSupportDate() { return supportDate; }
    public void setSupportDate(LocalDate supportDate) { this.supportDate = supportDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
