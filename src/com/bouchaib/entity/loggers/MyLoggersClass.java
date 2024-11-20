/**
 MyLoggersClass class provides logging
 functionality for the application. <p> The
 class uses the java.util.logging.Logger to
 log messages to a file. The log messages are
 formatted using FormatterLog. The logging
 level is set to SEVERE. <p> The class logs
 the start and close messages of the
 application. In case of an error during the
 opening of the log file, the class throws a
 ReversoDaoException.
 @version 1.0
 @author Bouchaib Khribech
 @since 2023-03-26
 */
package com.bouchaib.entity.loggers;


import java.util.logging.Logger;


public class MyLoggersClass
{


    public static final Logger LOGGER =


            Logger.getLogger (
                    MyLoggersClass.class.getName ());


}
