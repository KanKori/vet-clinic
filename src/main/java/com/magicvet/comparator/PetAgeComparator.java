package main.java.com.magicvet.comparator;

import main.java.com.magicvet.model.Pet;

import java.util.Comparator;

public class PetAgeComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet pet1, Pet pet2) {
        return convertAge(pet1.getAge()) - convertAge(pet2.getAge());
    }

    private static int convertAge(String age) {
        return Integer.parseInt(age);
    }
}
