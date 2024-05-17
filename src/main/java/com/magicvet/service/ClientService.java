package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for {@link Client} service
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class ClientService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-z0-9.-]+\\.[a-zA-Z]{2,}$";

    /*
    Checking that the string contains 3 or more characters
    and only Latin characters - no Cyrillic, numbers or special characters,
    except for the hyphen, as it is used when writing a double name or surname
    */
    private static final String NAME_PATTERN = "[A-Z-a-z]{3,}";


    /**
     * Method for registration a new client
     * @return new {@link Client} object
     */
    public Client registerNewClient() {

        Client client = null;

        System.out.println("Please, provide client detail: ");
        System.out.print("Email: ");
        String email = Main.SCANNER.nextLine();

        while (!emailValid(email)) {
            System.out.println("Provided information is invalid. Please, try again");
            email = Main.SCANNER.nextLine();
        }

        System.out.print("First Name: ");
        String firstName = Main.SCANNER.nextLine();

        while (!nameValid(firstName)) {
            System.out.println("Provided information is invalid. Please, try again");
            firstName = Main.SCANNER.nextLine();
        }

        System.out.print("Last Name: ");
        String lastName = Main.SCANNER.nextLine();

        while (!nameValid(lastName)) {
            System.out.println("Provided information is invalid. Please, try again");
            lastName = Main.SCANNER.nextLine();
        }

        if (emailValid(email) && nameValid(firstName) && nameValid(lastName)) {

            client = buildClient(email, firstName, lastName);

            System.out.println("New client: "
                    + client.getFirstName() + " "
                    + client.getLastName()
                    + " (" + client.getEmail() + ")");

        } else {

            System.out.println("Provided information is invalid");

        }

        return client;

    }

    /**
     * Method for building a new client
     * @param email client's email adress
     * @param firstName client's first name
     * @param lastName client's last name
     * @return new {@link Client} object
     */
    public static Client buildClient(String email, String firstName, String lastName) {

        Client client = new Client();

        client.setEmail(email);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        return client;
    }

    /**
     * Validating the client's email
     * @param email client's email address
     * @return is client's email valid
     */
    public static boolean emailValid(String email) {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    /**
     * Validating the client's name
     * @param name client's name
     * @return is client's name valid
     */
    public static boolean nameValid(String name) {

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();

    }

}
