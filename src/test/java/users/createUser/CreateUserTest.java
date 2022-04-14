package users.createUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import users.BasicTest;
import dto.User;
import rest.UserRestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BasicTest {

    private final UserRestClient restClient =  UserRestClient.getInstance();

    @Test
    @DisplayName("create user with mandatory fields only")
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

    @Test
    @DisplayName("create user with all fields")
    public void testCreateUserAllFields() {
        String username = "user-" + getRandomString();
        String password = "password";
        String email = username + "@mail.com";
        Map<String, String> profile = new HashMap<>();
        profile.put("key1", "value1");
        profile.put("key2", "value2");
        String id = UUID.randomUUID().toString();
        restClient.createUser(new User(
                username, password, id, email, profile))
                .assertThat().statusCode(200)
                .assertThat().body("account.username", equalToIgnoringCase(username))
                .assertThat().body("account.email", equalToIgnoringCase(email))
                .assertThat().body("account.id", equalTo(id))
                .assertThat().body("account.created", notNullValue())
                .assertThat().body("account.updated", notNullValue())
                .assertThat().body("account.verification_date", notNullValue())
                .assertThat().body("account.verified", equalTo(false))
                .assertThat().body("account.profile.key1", equalTo("value1"))
                .assertThat().body("account.profile.key2", equalTo("value2"));
    }

    @Test
    @DisplayName("Try to create already existing user")
    public void testCreateExistingUserAllFieldsNegative() {
        String username = "user-" + getRandomString();
        String password = "password";
        String email = username + "@mail.com";
        Map<String, String> profile = new HashMap<>();
        profile.put("key1", "value1");
        profile.put("key2", "value2");
        String id = UUID.randomUUID().toString();
        restClient.createUser(new User(
                username, password, id, email, profile))
                .assertThat().statusCode(200);
        restClient.createUser(new User(
                username, password, id, email, profile))
                .assertThat().statusCode(400)
                .assertThat().body("detail", equalTo("account already exists"));
    }
}
