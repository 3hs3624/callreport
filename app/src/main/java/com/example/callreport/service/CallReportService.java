package com.example.callreport.service;

import com.example.callreport.model.CallReport;
import com.example.callreport.repository.CallReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallReportService {
    @Autowired
    private CallReportRepository repository;

    public List<CallReport> getAllReports() {
        return repository.findAll();
    }
}


