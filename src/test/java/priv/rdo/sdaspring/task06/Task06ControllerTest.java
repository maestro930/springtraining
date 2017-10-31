package priv.rdo.sdaspring.task06;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.rdo.sdaspring.SdaSpringApplication;
import priv.rdo.sdaspring.task02.Animal;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SdaSpringApplication.class)
public class Task06ControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private AnimalsRepository animalsRepository;

    private Animal marek;

    @Before
    public void setUp() throws Exception {
        marek = animalsRepository.save(new Animal("Marek", 45));
    }

    @Test
    public void shouldFindAnimalById() throws Exception {
        //@formatter:off
        given()
                .port(port)
                .auth().preemptive().basic("admin", "password")
                .pathParam("id", marek.getId())
                .log().all()
        .when()
                .get("/task06_animals/{id}")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("name", is("Marek"))
                    .body("age", is(45));
        //@formatter:on
    }

    @Test
    public void shouldNotFindAnimalWithWrongId() throws Exception {
        //@formatter:off
        given()
                .port(port)
                .auth().preemptive().basic("admin", "password")
                .pathParam("id", Long.MAX_VALUE)
                .log().all()
        .when()
                .get("/task06_animals/{id}")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(404);
        //@formatter:on
    }

    @Test
    public void shouldCreateAnimal() throws Exception {
        Animal fafik = new Animal("Fafik", 77);

        //@formatter:off
        given()
                .port(port)
                .auth().preemptive().basic("admin", "password")
                .log().all()
                .body(fafik)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .when()
                .post("/task06_animals")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(CREATED.value())
                    .header("Location",
                            containsString("localhost:8080/task06_animals/"));
        //@formatter:on
    }

    @Test
    public void shouldReturnErrorIfAnimalAgeIsTooHighAndNameIsTooShort() throws Exception {
        Animal fafik = new Animal("Fa", 666);

        //@formatter:off
        given()
                .port(port)
                 .auth().preemptive().basic("admin", "password")
                .log().all()
                .body(fafik)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .when()
                .post("/task06_animals")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(UNPROCESSABLE_ENTITY.value())
                    .body("details.message", hasItems("Name cant be too short! 3 chars minimum", "Too old for that"))
                    .body("details.field", hasItems("name", "age"))
                    .body("details.rejectedValue", hasItems("Fa", 666));
        //@formatter:on
    }

}