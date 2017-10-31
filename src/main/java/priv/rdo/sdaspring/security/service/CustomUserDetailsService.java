package priv.rdo.sdaspring.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import priv.rdo.sdaspring.security.model.Account;
import priv.rdo.sdaspring.security.repository.AccountRepository;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException("No account found with username: " + username);
        }

        return new User(account.getUsername(), account.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
    }
}
