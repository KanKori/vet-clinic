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
    private final PetService petService = new PetService();

    /**
     * Main method to run the app
     */
    public void run() {

        if (Authenticator.auth()) {

            Client client = clientService.registerNewClient();

            if (client != null) {

                registerPet(client);
                System.out.println(client);

            }

        }

    }

    /**
     * Method of registering a new pet
     * @param client whose pet is that
     */
    private void registerPet(Client client) {

        boolean continueAddPets = true;

        while (continueAddPets) {

            addPet(client);

            System.out.println("Do you want to add more pets for the current client? (y/n): ");
            String answer = Main.SCANNER.nextLine();

            if ("n".equals(answer)) {
                continueAddPets = false;
            }

        }

    }

    /**
     * Method of adding a new pet
     * @param client whose pet is that
     */
    private void addPet(Client client) {

        System.out.println("Adding a new pet");

        Pet pet = petService.registerNewPet();

        if (pet != null) {

            client.addPet(pet);
            pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
            System.out.println("Pet has been added.");
        }

    }
}
