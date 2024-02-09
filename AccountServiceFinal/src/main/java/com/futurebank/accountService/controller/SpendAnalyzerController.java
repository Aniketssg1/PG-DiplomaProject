package com.futurebank.accountService.controller;

import com.futurebank.accountService.service.SpendAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/spend-analyzer")
public class SpendAnalyzerController {

    @Autowired
    private SpendAnalyzerService spendAnalyzerService;

    @GetMapping("/analyze")
    public Map<String, BigDecimal> analyzeSpending() {
        return spendAnalyzerService.analyzeSpending();
    }
}
