package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class for working with the entity register
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class EntityRegister {

    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    /**
     * Method of registering a new client
     */
    public void registerClients() {

        List<Client> clients = new ArrayList<>();
        String message = "Do you want to register more clients? (y/n): ";

        do {

            Optional<Client> client = addClient();
            client.ifPresent(clients::add);

        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientsByLocation = clients.stream()
                .collect(Collectors.groupingBy(Client::getLocation));
        printClients(clientsByLocation);
    }

    /**
     * Method that print list of clients
     * @param clientsByLocation last of clients by location
     */
    private void printClients(Map<Client.Location, List<Client>> clientsByLocation) {
        for (Map.Entry<Client.Location, List<Client>> clients :  clientsByLocation.entrySet()) {
            String content = "\nLocation " + clients.getKey()
                    + "\nClients (" + clients.getValue().size() + "):"
                    + "\n\t" + clients.getValue();

            System.out.println(content);
        }
    }

    /**
     * Method of adding a new client
     */
    private Optional<Client> addClient() {

        Optional<Client> client = clientService.registerNewClient();
        client.ifPresent(this::registerPet);

        return client;
    }

    /**
     * Method of registering a new pet
     * @param client whose pet is that
     */
    private void registerPet(Client client) {

        String message = "Do you want to add more pets for the current client? (y/n): ";

        do {
            addPet(client);
            System.out.println(client);
        } while (verifyRepeating(message));
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

    /**
     * A method that verifies the repetition of an action. Contains recursion
     * @param message message for user
     * @return true if user wants to repeat an action, otherwise false
     */
    private boolean verifyRepeating(String message) {

        System.out.println(message);
        String answer = Main.SCANNER.nextLine();

        if ("y".equals(answer)) {

            return true;

        } else if ("n".equals(answer)) {

            return false;

        } else {

            System.out.println("Incorrect answer. Please, try again");
            return verifyRepeating(message);

        }
    }
}
