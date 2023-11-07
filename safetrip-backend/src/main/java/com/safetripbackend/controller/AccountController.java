package com.safetripbackend.controller;

import com.safetripbackend.dto.AccountDTO; //se podria reemplazar con subscription
import com.safetripbackend.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Account", description = "Account management APIs")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AccountDTO> registerAccount(@Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO registeredAccount = accountService.registerAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredAccount);
    }

}
