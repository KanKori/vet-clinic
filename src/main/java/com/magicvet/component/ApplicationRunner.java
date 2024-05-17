package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to run the application
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class ApplicationRunner {

    private final ClientService clientService = new ClientService();
    private final PetService  petService = new PetService();

    private static final String CHOICE_PATTERN = "[yn]";

    /**
     * Main method to run the app
     */
    public void run() {
        if (Authenticator.auth()) {
            Client client = clientService.registerNewClient();

            if (client != null) {
                if(ifUserWantsToAddAPet()) {

                    System.out.println("Adding a new pet");

                    Pet pet = petService.registerNewPet();

                    if (pet != null) {

                        client.setPet(pet);
                        pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
                        System.out.println("Pet has been added.");
                    }
                }

                System.out.println(client);
            }
        }
    }

    /**
     * Check does user want to add a pet
     * @return does user want to add a pet
     */
    public static boolean ifUserWantsToAddAPet() {

        System.out.println("Do you want to add a pet? (y/n): ");
        String userChoice = Main.SCANNER.nextLine();

        while (!choiceValid(userChoice)) {
            System.out.println("Incorrect input. Please, try again (y/n): ");
            userChoice = Main.SCANNER.nextLine();
        }

        return userChoice.equals("y");

    }

    /**
     * Validating the user's choice
     * @param choice user's choice
     * @return is user's choice valid
     */
    public static boolean choiceValid(String choice) {

        Pattern pattern = Pattern.compile(CHOICE_PATTERN);
        Matcher matcher = pattern.matcher(choice);

        return matcher.matches();

    }

}
