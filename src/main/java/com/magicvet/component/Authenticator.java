package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;

/**
 * Class for authentication
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class Authenticator {

    private static final String PASSWORD = "d";

    /**
     * Main method for authentication
     * @return can you access the system
     */
    public static boolean auth() {

        boolean accepted = false;

        for (int i = 0; i < 3; i++) {
            System.out.print("Password: ");
            String input = Main.SCANNER.nextLine();

            if(PASSWORD.equals(input)) {
                accepted = true;
                break;
            } else {
                System.out.println("Access denied. Please, check your password");
            }
        }

        System.out.println(accepted ? "Welcome to the Magic Vet" : "Application has been blocked");

        return accepted;
    }

}
