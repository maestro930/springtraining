package priv.rdo.sdaspring.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import priv.rdo.sdaspring.security.command.CreateAccountCommand;
import priv.rdo.sdaspring.security.model.Account;
import priv.rdo.sdaspring.security.model.UsernameExistsException;
import priv.rdo.sdaspring.security.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account registerNewAccount(CreateAccountCommand command) {
        if (usernameExists(command.getUsername())) {
            throw new UsernameExistsException(command.getUsername());
        }

        Account account = new Account();
        account.setUsername(command.getUsername());
        account.setPassword(passwordEncoder.encode(command.getPassword()));

        return accountRepository.save(account);

    }

    private boolean usernameExists(String username) {
        return accountRepository.findByUsername(username) != null;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
