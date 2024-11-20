/**
 * This is the main class of my application.
 *
 * @author Bouchaib Khribech
 * @version 1.0
 */
import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;


import com.bouchaib.entity.loggers.FormatterLog;

import frames.WelcomeFrame;

import java.util.logging.FileHandler;

public class Main
{

    public static void main (String[]args)
    {

        new WelcomeFrame();

        try
        {

            FileHandler fileHandler =
                    new FileHandler ("app.log", true);

            fileHandler.setFormatter (
                    new FormatterLog ());

            LOGGER.addHandler (fileHandler);

        } catch (Exception e)
        {

            LOGGER.severe (
                    "L'application va se fermer" +

                            " à cause d'une erreur inattendue:");

            e.printStackTrace ();

            System.exit (1);

        }

    }
}

