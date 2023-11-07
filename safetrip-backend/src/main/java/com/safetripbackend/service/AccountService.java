package com.safetripbackend.service;

import com.safetripbackend.dto.AccountDTO;
import com.safetripbackend.exception.AccountRegistrationException;
import com.safetripbackend.mappers.AccountMapper; //puede reemplazarse con subscription, pero lo dejo con Account por mientras
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.entity.Account; //puede reemplazarse con subscription, pero lo dejo con Account por mientras
import com.safetripbackend.entity.Users;
import com.safetripbackend.repository.AccountRepository; //puede reemplazarse con subscription, pero lo dejo con Account por mientras
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserMapper userMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, UserMapper userMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.userMapper=userMapper;
    }

    @Transactional
    public AccountDTO registerAccount(AccountDTO accountDTO) {
        User user = userMapper.mapToModel(accountDTO.getUser());
        if (accountRepository.existsByUser(user)) {
            throw new AccountRegistrationException("Ya existe una cuenta registrada para este usuario");
        }

        Account account = accountMapper.mapToModel(accountDTO);
        account.setBalance(BigDecimal.ZERO);
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setUser(user);

        Account savedAccount = accountRepository.save(account);

        return accountMapper.mapToDTO(savedAccount);
    }
}

