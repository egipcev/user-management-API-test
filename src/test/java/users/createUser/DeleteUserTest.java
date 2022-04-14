package users.createUser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import users.BasicTest;
import dto.User;
import rest.UserRestClient;

public class DeleteUserTest extends BasicTest {

    private static String id;
    private static UserRestClient restClient = UserRestClient.getInstance();

    @BeforeAll
    public static void createUser() {
        String username = "user-" + getRandomString();
        String password = "password";
        String email = username + "@mail.com";
        id = restClient.createUser(new User(
                username, password, email))
                .assertThat().statusCode(200)
                .extract().path("account.id").toString();
    }

    @Test
    public void deleteUser() {
        restClient.deleteUser(id)
                .assertThat().statusCode(200);//add db check
    }

    @Test
    public void deleteNotExistingUser() {
        restClient.deleteUser("0")
                .assertThat().statusCode(400);
    }

}
