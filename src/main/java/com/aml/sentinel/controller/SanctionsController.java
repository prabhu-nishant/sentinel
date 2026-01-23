package com.aml.sentinel.controller;

import com.aml.sentinel.service.SanctionsService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/compliance")
@RequiredArgsConstructor
public class SanctionsController {

    private final SanctionsService sentinelService;

    @PostMapping("/check")
    public SanctionsService.SanctionsDecision checkSanctions(@RequestBody String paymentDetails,
                                                             @RequestParam String flaggedName) {

        return sentinelService.screenTransaction(paymentDetails, flaggedName);
    }
}
