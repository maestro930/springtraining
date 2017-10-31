package priv.rdo.sdaspring.security.command;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CreateAccountCommand {
    @NotBlank
    @Length(min = 6, max = 20)
    private String username;

    @NotBlank
    @Length(min = 6, max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
