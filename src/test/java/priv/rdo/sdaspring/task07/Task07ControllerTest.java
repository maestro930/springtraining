package priv.rdo.sdaspring.task07;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.rdo.sdaspring.SdaSpringApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SdaSpringApplication.class)
public class Task07ControllerTest {
    @LocalServerPort
    private Integer port;

    @Test
    public void shouldThrowAnException() throws Exception {
        //@formatter:off
        given()
        .when()
                .get("/task07_exception")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(418)
                    .body("message",
                            is("you'll always get an exception here"));
        //@formatter:on
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .port(port)
                .auth().preemptive().basic("admin", "password")
                .log().all();
    }
}