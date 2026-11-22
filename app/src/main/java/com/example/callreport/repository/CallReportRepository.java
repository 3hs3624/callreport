package com.example.callreport.repository;

import com.example.callreport.model.CallReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallReportRepository extends JpaRepository<CallReport, Long> {
}


