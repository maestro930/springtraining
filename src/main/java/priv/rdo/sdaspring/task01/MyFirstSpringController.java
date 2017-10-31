package priv.rdo.sdaspring.task01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstSpringController {

    @GetMapping("hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World!") String name) {
        return "hello " + name;
    }

    @GetMapping("hello2/{number}")
    public String hello2(@PathVariable int number) {
        return "hello " + number + "World!";
    }

    @GetMapping("hello3/{number}")
    public String hello3(@PathVariable int number,
              @RequestParam(value = "name", defaultValue = "World!") String name) {
        return "hello " + number + " " + name;
    }
}
