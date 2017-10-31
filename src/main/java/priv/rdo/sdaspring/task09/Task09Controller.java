package priv.rdo.sdaspring.task09;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import priv.rdo.sdaspring.security.command.CreateAccountCommand;
import priv.rdo.sdaspring.security.model.Account;
import priv.rdo.sdaspring.security.service.AccountService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class Task09Controller {
    private final AccountService accountService;

    public Task09Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateAccountCommand command) {
        Account account = accountService.registerNewAccount(command);

        return ResponseEntity.created(pathWithId(account.getId())).build();
    }

    @GetMapping
    public List<Account> findAllUsers() {
        return accountService.findAll();
    }

    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }
}
