package priv.rdo.sdaspring.task05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Task05Controller {
    private final ApplicationContext ctx;
    private final NotManagedBySpring notManagedBySpring;
    private final String importantString;

    @Autowired
    public Task05Controller(ApplicationContext ctx,
                            @Qualifier("notManagedBySpring") NotManagedBySpring nowManaged,
                            @Qualifier("veryImportant") String importantString) {
        this.ctx = ctx;
        this.notManagedBySpring = nowManaged;
        this.importantString = importantString;
    }

    @GetMapping(value = "context")
    public String task04() {
        System.out.println("notManaged field = " + notManagedBySpring.getBlabla());
        System.out.println("some string = " +importantString);
        return "fafarafa";
    }
}
