package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

/**
 * Class for {@link Pet} service
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class PetService {

    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";

    /**
     * Method for registration a new pet
     * @return new {@link Pet} object
     */
    public Pet registerNewPet() {

        Pet pet = null;

        System.out.println("Type (dog / cat): ");
        String type = Main.SCANNER.nextLine();

        if (DOG_TYPE.equals(type) || CAT_TYPE.equals(type)) {

            pet = buildPet(type);

        } else {

            System.out.println("Unknown pet type: " + type);

        }

        return pet;
    }

    /**
     * Method for building a new pet
     * @param type type of animal to be created
     * @return new {@link Pet} object
     */
    public Pet buildPet(String type) {

        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();
        pet.setType(type);

        System.out.println("Age: ");
        pet.setAge(Integer.parseInt(Main.SCANNER.nextLine()));

        System.out.println("Name: ");
        pet.setName(Main.SCANNER.nextLine());

        System.out.println("Sex (male / female): ");
        pet.setSex(Main.SCANNER.nextLine());

        System.out.println("Health state (GOOD, BAD, CRITICAL, EXTRA_CRITICAL): ");
        Pet.HealthState healthState;
        String healthStateInput = Main.SCANNER.nextLine();
        try {

            healthState = Pet.HealthState.valueOf(healthStateInput);

        } catch (IllegalArgumentException e) {

            healthState = Pet.HealthState.UNKNOWN;
            System.out.println("Unable to parse value '" + healthStateInput
                    + "'. Using default value: " + Pet.HealthState.UNKNOWN);
        }

            pet.setHealthState(healthState);

        if (type.equals(DOG_TYPE)) {
            System.out.println("Size (XS / S / M / L / XL): ");
            Dog.Size size;
            String sizeInput = Main.SCANNER.nextLine();
            try {

                size = Dog.Size.valueOf(sizeInput);

            } catch (IllegalArgumentException e) {

                size = Dog.Size.UNKNOWN;
                System.out.println("Unable to parse value '" + sizeInput
                        + "'. Using default value: " + Dog.Size.UNKNOWN);

            }

            ((Dog) pet).setSize(size);
        }

        return pet;
    }

}
