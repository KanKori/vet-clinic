package main.java.com.magicvet;

import main.java.com.magicvet.component.ApplicationRunner;

import java.util.Scanner;

/**
 * Class for starting main program logic
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class Main {

    public static Scanner SCANNER = new Scanner(System.in);

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {

        ApplicationRunner runner = new ApplicationRunner();
        runner.run();

    }

}