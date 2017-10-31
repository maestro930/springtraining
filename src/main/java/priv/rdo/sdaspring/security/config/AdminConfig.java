package priv.rdo.sdaspring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import priv.rdo.sdaspring.security.command.CreateAccountCommand;
import priv.rdo.sdaspring.security.service.AccountService;

import javax.annotation.PostConstruct;

@Configuration
public class AdminConfig {
    private final AccountService accountService;

    @Autowired
    public AdminConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    private void registerAdmin() {
        CreateAccountCommand admin = new CreateAccountCommand();
        admin.setUsername("admin");
        admin.setPassword("password");
        accountService.registerNewAccount(admin);
    }
}
