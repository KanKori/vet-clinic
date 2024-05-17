package main.java.com.magicvet;

import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.service.PetService;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class for testing
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class Sandbox {

    public static void main(String[] args) {

        System.out.println(new Client());
        System.out.println(new Cat());

        PetService petService = new PetService();

        Dog[] dogs = {
                new Dog(Dog.Size.XL),
                new Dog(Dog.Size.L),
                new Dog(Dog.Size.L),
                new Dog(Dog.Size.S),
                new Dog(Dog.Size.XS)

        };

        //compare dog size
        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getSize().getValue() - dog2.getSize().getValue();
            }
        });

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

        //compare cat age
        Arrays.sort(cats, new Comparator<Cat>() {
            @Override
            public int compare(Cat cat1, Cat cat2) {
                return cat1.getAge() - cat2.getAge();
            }
        });

        for (Cat cat : cats) {
            System.out.println(cat.getAge());
        }

        Arrays.sort(cats, new Comparator<Cat>() {
            @Override
            public int compare(Cat cat1, Cat cat2) {
                return cat1.getHealthState().getValue() - cat2.getHealthState().getValue();
            }
        });

        for (Cat cat : cats) {
            System.out.println(cat.getHealthState());
        }

    }

}
