/**
 * The {@code ConnectionManager} class is a
 * singleton that provides a single instance of
 * the connection to the database throughout the
 * application. It provides methods to open and
 * close the connection.
 * @author khribech bouchaib
 * @version 1.0
 * @since 29/03/2023
 */
package com.bouchaib.dao.daoSql;


import javax.swing.*;


import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;



import java.io.File;


import java.io.FileInputStream;


import java.io.IOException;


import java.sql.Connection;


import java.sql.DriverManager;


import java.sql.SQLException;


import java.util.Properties;


import java.util.logging.Level;


public class ConnectionManager
{
    private static Connection connection = null;
    /**

     Creates a new instance of the {@code
    ConnectionManager} class and establishes a
     connection to the database. If the connection
     already exists, it is not re-created.
     @throws ReversoDaoException if an error occurs
     while establishing the connection.
     */
    private ConnectionManager ()
            throws ReversoDaoException
    {


        try
        {


            final Properties dataProperties =
                    new Properties ();


            File file = new File ("database.Properties");


            FileInputStream input =
                    new FileInputStream (file);


            dataProperties.load (input);


            Class.forName (dataProperties.getProperty (
                    "jdbc.driver.class"));


            String url =
                    dataProperties.getProperty ("url");


            String login =
                    dataProperties.getProperty ("login");


            String password =
                    dataProperties.getProperty ("password");


            connection = DriverManager.getConnection (
                    url, login, password);


        } catch (ClassNotFoundException e)
        {


            throw new ReversoDaoException (
                    5,


                    "Une erreur s'est produite lors du"
                            +


                            " chargement de la classe de pilote JDBC.");


        } catch (IOException e)
        {


            throw new ReversoDaoException (
                    5,


                    "Une erreur s'est produite lors " +


                            "de la tentative de connexion" +


                            "  à la base de données");


        } catch (SQLException e)
        {


            throw new ReversoDaoException (
                    5,


                    "Une erreur s'est produite" +


                            " lors de la tentative d'C)tablissement "

                            +

                            "d'une connexion à la base de données");


        } catch (Exception e)
        {

            JOptionPane.showMessageDialog (null,
                    e.getMessage (), "Erreur grave",

                    JOptionPane.ERROR_MESSAGE);

            LOGGER.log (Level.SEVERE, e.getMessage (), e);

            e.printStackTrace ();

            System.exit (1);

        }
    }
    /**
     * Returns the connection to the database. If
     * the connection is not established, a new
     * connection is created.
     *
     * @return the connection to the database
     * @throws ReversoDaoException if an error
     *     occurs while establishing the connection
     */

    public static Connection getInstance ()
            throws ReversoDaoException
    {


        if (connection == null)

        {


            new ConnectionManager ();


        }


        return connection;


    }


    /**
     * Closes the connection to the database.
     *
     * @throws ReversoDaoException if an error
     *     occurs while closing the connection
     */

    public static void closeConnection () throws ReversoDaoException
    {

        if (connection != null)
        {

            try
            {

                connection.close ();

                connection = null;

            }
            catch (SQLException e)
            {

                throw new ReversoDaoException (5,

                        "Une erreur est survenue lors de la fermeture " +
                                "de la connexion à la base de données.");

            }
            catch (Exception e)
            {

                JOptionPane.showMessageDialog (null,
                        e.getMessage (),
                        "Erreur grave",

                        JOptionPane.ERROR_MESSAGE);

                LOGGER.log (Level.SEVERE, e.getMessage (), e);

                e.printStackTrace ();

                System.exit (1);

            }

        }

    }


    /**
     * Closes the connection to the database when
     * the application shuts down.
     */
    static
    {


        Runtime.getRuntime ().addShutdownHook (
                new Thread ()
                {


                    @Override
                    public void run ()
                    {


                        try
                        {


                            closeConnection ();


                        } catch (ReversoDaoException
                                e)
                        {


                            LOGGER.log (
                                    Level.SEVERE,


                                    "Erreur lors "
                                            +


                                            "de la fermeture de "

                                            +
                                            "la connexion"
                                            +


                                            " à  la base de données.",


                                    e);


                        } catch (Exception e)
                        {

                            JOptionPane.showMessageDialog (null,
                                    e.getMessage (),
                                    "Erreur grave",

                                    JOptionPane.ERROR_MESSAGE);

                            LOGGER.log (Level.SEVERE, e.getMessage (), e);

                            e.printStackTrace ();

                            System.exit (1);

                        }
                    }
                });


    }
}
