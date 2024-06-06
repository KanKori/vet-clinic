package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            Client client = addClient();
            if (client != null) {
                clients.add(client);
            }

        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientsByLocation = groupClients(clients);
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
     * Method of grouping clients
     * @param clients list of clients
     * @return grouped list of clients
     */
    private Map<Client.Location, List<Client>> groupClients(List<Client> clients) {

        List<Client> fromKyiv = new ArrayList<>();
        List<Client> fromLviv = new ArrayList<>();
        List<Client> fromOdesa = new ArrayList<>();
        List<Client> unknownLocation = new ArrayList<>();

        for(Client client : clients) {
            switch (client.getLocation()) {

                case KYIV -> fromKyiv.add(client);
                case LVIV -> fromLviv.add(client);
                case ODESA -> fromOdesa.add(client);
                case UNKNOWN -> unknownLocation.add(client);
            }
        }

        Map<Client.Location, List<Client>> clientsByLocation = new HashMap<>();
        clientsByLocation.put(Client.Location.KYIV, fromKyiv);
        clientsByLocation.put(Client.Location.LVIV, fromLviv);
        clientsByLocation.put(Client.Location.ODESA, fromOdesa);
        clientsByLocation.put(Client.Location.UNKNOWN, unknownLocation);

        return clientsByLocation;
    }

    /**
     * Method of adding a new client
     */
    private Client addClient() {

        Client client = clientService.registerNewClient();

        if (client != null) {

            registerPet(client);
            System.out.println(client);

        }

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