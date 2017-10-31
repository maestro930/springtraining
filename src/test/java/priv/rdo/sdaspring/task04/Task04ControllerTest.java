package priv.rdo.sdaspring.task04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import priv.rdo.sdaspring.SdaSpringApplication;
import test.config.StubbedAnimalServiceConfig;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SdaSpringApplication.class)
@ContextConfiguration(classes = StubbedAnimalServiceConfig.class)
public class Task04ControllerTest {
    @LocalServerPort
    private Integer port;

    @Test
    public void shouldFindSomeAnimal() throws Exception {
        //@formatter:off
        given()
                .port(port)
                .auth().preemptive().basic("admin", "password")
                .log().all()
        .when()
                .get("/task04")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("name", is("Azazel"))
                    .body("age", is(666));
        //@formatter:on
    }
}