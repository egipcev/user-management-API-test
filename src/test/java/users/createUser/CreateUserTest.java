package users.createUser;

import org.junit.jupiter.api.Test;
import users.BasicTest;
import users.User;
import users.UserRestClient;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BasicTest {

    private UserRestClient restClient = UserRestClient.getInstance();

    @Test
    public void testCreateUserMandatoryFields() {
        String username = "user-" + getRandomString();
        String password = "password";
        String email = username + "@mail.com";
        restClient.createUser(new User(
                username, password, email))
                .assertThat().statusCode(200)
                .assertThat().body("account.username", equalToIgnoringCase(username))
                .assertThat().body("account.email", equalToIgnoringCase(email))
                .assertThat().body("account.id", notNullValue())
                .assertThat().body("account.created", notNullValue())
                .assertThat().body("account.updated", notNullValue())
                .assertThat().body("account.verification_date", notNullValue())
                .assertThat().body("account.verified", equalTo(false))
                .assertThat().body("account", hasKey("profile"));
    }
}
