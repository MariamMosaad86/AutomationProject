package pages;


import com.github.javafaker.Faker;

public class DataTest {

    private static final Faker faker= new Faker();

    public static String fakerEmail =faker.internet().safeEmailAddress();
}
