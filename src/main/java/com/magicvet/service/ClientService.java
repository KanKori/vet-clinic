package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Client registerNewClient() {

        Client client = null;

        System.out.println("Please, provide client detail: ");
        System.out.print("Email: ");
        String email = Main.SCANNER.nextLine();

        if(emailValid(email)) {
            client = buildClient(email);
            System.out.println("New client: "
                    + client.getFirstName() + " "
                    + client.getLastName()
                    + " (" + client.getEmail() + ")");
        } else {
            System.out.println("Provided email is invalid");
        }

        return client;
    }


    public static Client buildClient(String email) {
        Client client = new Client();
        client.setEmail(email);

        System.out.print("First Name: ");
        client.setFirstName(Main.SCANNER.nextLine());

        System.out.print("Last Name: ");
        client.setLastName(Main.SCANNER.nextLine());

        return client;
    }

    public static boolean emailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
