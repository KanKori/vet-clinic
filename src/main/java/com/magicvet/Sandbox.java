package main.java.com.magicvet;

import main.java.com.magicvet.model.Dog;

import java.util.Arrays;
import java.util.Comparator;

public class Sandbox {

    public static void main(String[] args) {

        Dog[] dogs = {
                new Dog(Dog.Size.XL),
                new Dog(Dog.Size.L),
                new Dog(Dog.Size.L),
                new Dog(Dog.Size.S),
                new Dog(Dog.Size.XS)

        };

        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getSize().getValue() - dog2.getSize().getValue();
            }
        });

        for (Dog dog : dogs) {
            System.out.println(dog.getSize());
        }

    }

}
