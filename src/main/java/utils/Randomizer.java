package utils;

import org.apache.commons.lang3.RandomStringUtils;
import pages.requests.CreateUserRequest;

public class Randomizer {


//    private   String email =
//    private  String text =

    public static CreateUserRequest createRandomUser(){
        String email = String.format("%s@yandex.ru", RandomStringUtils.randomAlphabetic(15));
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new CreateUserRequest(email,password,name);
    }

    public static String getEmail() {
        return String.format("%s@yandex.ru", RandomStringUtils.randomAlphabetic(15));
    }

    public static String getText() {
        return  RandomStringUtils.randomAlphabetic(10);
    }
}
