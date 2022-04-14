package users.createUser;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import users.BasicTest;
import dto.User;
import rest.UserRestClient;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ReadUserTest extends BasicTest {

    private static JsonPath response;
    private static UserRestClient restClient = UserRestClient.getInstance();

    @BeforeAll
    public static void createUser() {
        String username = "user-" + getRandomString();
        String password = "password";
        String email = username + "@mail.com";
        response = restClient.createUser(new User(
                username, password, email))
                .assertThat().statusCode(200)
                .extract().jsonPath();
    }

    @Test
    public void testReadUser() {
        restClient.readUser(response.getString("account.id"))
                .assertThat().statusCode(200)
                .assertThat().body("account.username", equalToIgnoringCase(response.getString("account.username")))
                .assertThat().body("account.id", equalTo(response.getString("account.id")))
                .assertThat().body("account.email", equalTo(response.getString("account.email")))
                .assertThat().body("account.created", equalTo(response.getString("account.created")))
                .assertThat().body("account.updated", equalTo(response.getString("account.created")))
                .assertThat().body("account.verified", equalTo(response.getBoolean("account.verified")))
                .assertThat().body("account.verification_date", equalTo(response.getString("account.verification_date")));
    }

    @Test
    public void testReadNotExistingUser() {
        restClient.readUser("0")
                .assertThat().statusCode(400);
    }
}
