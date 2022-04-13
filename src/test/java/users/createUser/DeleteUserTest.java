package users.createUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.BasicTest;
import users.User;

public class DeleteUserTest extends BasicTest {

    private String id;

    @BeforeEach
    public void createUser() {
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

}
