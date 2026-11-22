package com.example.callreport.controller;

import com.example.callreport.model.CallReport;
import com.example.callreport.service.CallReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CallReportController {
    @Autowired
    private CallReportService callReportService;

    @GetMapping("/callreportlist")
    public String getCallReportList(Model model) {
        List<CallReport> reports = callReportService.getAllReports();
        model.addAttribute("reports", reports);
        return "callreportlist";
    }
}


