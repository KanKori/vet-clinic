package main.java.com.magicvet.component;

/**
 * Class to run the application
 * @author Anastasiia Voshchenko
 * @since 2024
 * @version %I%, %G%
 */
public class ApplicationRunner {

    private final EntityRegister register = new EntityRegister();

    /**
     * Main method to run the app
     */
    public void run() {

        if (Authenticator.auth()) {
            register.registerClients();
        }

    }

}
