package main.java.com.magicvet;

import main.java.com.magicvet.comparator.DogSizeComparator;
import main.java.com.magicvet.comparator.PetAgeComparator;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.service.PetService;

import java.util.Arrays;

public class Sandbox {

    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";

    public static void main(String[] args) {

        final PetService petService = new PetService();

        Dog[] dogs = {

                new Dog(Dog.XL),
                new Dog(Dog.L),
                new Dog(Dog.L),
                new Dog(Dog.S),
                new Dog(Dog.XS)

        };

        Arrays.sort(dogs, new DogSizeComparator());

        for (Dog dog : dogs) {
            System.out.println(dog.getSize());
        }

        Cat[] cats = {
                (Cat) petService.buildPet("cat"),
                (Cat) petService.buildPet("cat"),
                (Cat) petService.buildPet("cat"),
                (Cat) petService.buildPet("cat"),
                (Cat) petService.buildPet("cat")
        };

        Arrays.sort(cats, new PetAgeComparator());

        for (Cat cat : cats) {
            System.out.println(cat.getAge());
        }

    }

}
