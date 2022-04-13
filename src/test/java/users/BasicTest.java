package users;

import org.apache.commons.lang3.RandomStringUtils;

public class BasicTest {

    public static final String TOKEN = "NTA4Nzc2ODgtZGIyZi00Y2UzLWJkZTMtNDQ4Mzg5ZjhjYWUy";
    public static final String BASE_URI = "https://api.m3o.com/v1/user/";


    public static String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(8);
    }



}
