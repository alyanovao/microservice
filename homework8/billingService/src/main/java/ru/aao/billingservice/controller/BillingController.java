package ru.aao.billingservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aao.billingservice.model.AccountResponse;
import ru.aao.billingservice.model.dto.AccountInfo;
import ru.aao.billingservice.model.dto.AccountRequest;
import ru.aao.billingservice.model.dto.TransferInfo;
import ru.aao.billingservice.service.BillingService;

import java.util.List;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/createAccount")
    public ResponseEntity<AccountInfo> createAccount(@RequestBody AccountRequest request) {
        val res = billingService.createAccount(request);
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/getAccountInfo")
    public ResponseEntity<List<AccountInfo>> getAccountInfo(@RequestParam Long clientId) {
        val accountInfo = billingService.getAccountInfo(clientId);
        return ResponseEntity.status(200).body(accountInfo);
    }

    @PostMapping("/debitAccount")
    public ResponseEntity<String> debitAccount(@RequestBody TransferInfo transferInfo) {
        billingService.debitAccount(transferInfo);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/creditAccount")
    public ResponseEntity<String> creditAccount(@RequestBody TransferInfo transferInfo) {
        billingService.creditAccount(transferInfo);
        return ResponseEntity.status(204).build();
    }
}
