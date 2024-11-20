/**
 * ClientDao is a class that implements the
 * CRUD operations for a client entity in the
 * database.
 *
 * @author Khribech Bouchaib
 * @version 1.0
 */
package com.bouchaib.dao.daoSql;



import com.bouchaib.dao.Dao;


import com.bouchaib.entity.Client;


import com.bouchaib.entity.Contrat;


import com.bouchaib.entity.entityException.ReversoException;


import java.sql.*;


import java.util.ArrayList;


import java.util.logging.Level;



import utilities.CrudEnum;



import javax.swing.*;



import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;



public class ClientDao extends Dao < Client >
{


    /**
     * Finds all clients from the database.
     *
     * @return A list of all clients stored in
     * the database.
     * @throws ReversoDaoException If a data
     *                             access error occurs during the
     *                             operation.
     */
    public ArrayList < Client > findAll ()
            throws ReversoDaoException, ReversoException
    {


        Connection connection =
                ConnectionManager.getInstance ();


        ArrayList < Client > clients =
                new ArrayList <> ();


        try
        {


            Statement statement =
                    connection.createStatement ();


            ResultSet resultSet =


                    statement.executeQuery (
                            "SELECT * FROM Client");



            while (resultSet.next ())

            {


                Client client = new Client ();


                client.setId (
                        resultSet.getInt ("client_id"));


                client.setRaisonSociale (
                        resultSet.
                                getString (
                                        "client_raison_sociale"));


                client.setNumRue (resultSet.getString (
                        "client_num_rue"));


                client.setNomRue (resultSet.getString (
                        "client_nom_rue"));


                client.setCodePostal (
                        resultSet.
                                getString (
                                        "client_code_postal"));


                client.setVille (resultSet.getString (
                        "client_ville"));


                client.setTelephone (
                        resultSet.getString (
                                "client_telephone"));


                client.setAdresseMail (
                        resultSet.
                                getString (
                                        "client_adresse_mail"));


                client.setCommentaires (
                        resultSet.
                                getString (
                                        "client_commentaires"));


                client.setChiffreAffaires (
                        resultSet.
                                getDouble
                                        (
                                                "client_chiffre_affaires"));


                client.setNbEmployes (
                        resultSet.getInt (
                                "client_nb_employes"));


                clients.add (client);


            }


        }

        catch (SQLException e)
        {


            {


                throw new ReversoDaoException (5,

                        "Une erreur s'est produite lors"
                                +" de traitement des donnC)es. "
                                +"L'application va s'arrêter.");


            }


        }

        catch (Exception ex)
        {


            JOptionPane.showMessageDialog (null,


                    "Une erreur s'est produite. " +


                            "L'application va s'arrêter.");


            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);


            ex.printStackTrace ();


            System.exit (1);


        }


        return clients;


    }



    /**
     * Finds a specific client by its ID.
     *
     * @param id The ID of the client to find.
     * @return The client object with the
     * specified ID, or `null` if no such
     * client exists.
     * @throws ReversoException    If an error
     *                             occurs during the operation.
     * @throws ReversoDaoException If a data
     *                             access error occurs during the
     *                             operation.
     */
    public Client find (int id)
            throws ReversoDaoException
    {


        Client client = null;


        Connection connection =
                ConnectionManager.getInstance ();


        try
        {


            PreparedStatement statement =

                    connection.
                            prepareStatement (
                                    "SELECT * FROM Client WHERE client_id = ?");


            statement.setInt (1, id);


            ResultSet resultSet =
                    statement.executeQuery ();


            if (resultSet.next ())

            {


                client = new Client ();


                client.setId (
                        resultSet.getInt ("client_id"));


                client.setRaisonSociale (
                        resultSet.
                                getString (
                                        "client_raison_sociale"));


                client.setNumRue (resultSet.getString (
                        "client_num_rue"));


                client.setNomRue (resultSet.getString (
                        "client_nom_rue"));


                client.setCodePostal (
                        resultSet.
                                getString (
                                        "client_code_postal"));


                client.setVille (resultSet.getString (
                        "client_ville"));


                client.setTelephone (
                        resultSet.getString (
                                "client_telephone"));


                client.setAdresseMail (
                        resultSet.
                                getString (
                                        "client_adresse_mail"));


                client.setCommentaires (
                        resultSet.
                                getString (
                                        "client_commentaires"));


                client.setChiffreAffaires (
                        resultSet.
                                getDouble
                                        (
                                                "client_chiffre_affaires"));


                client.setNbEmployes (
                        resultSet.getInt (
                                "client_nb_employes"));


                ContratDao contratDao =
                        new ContratDao ();


                ArrayList < Contrat > contrats =
                        contratDao.findByIdClient (id);


                client.setContrats (contrats);


            }


        }

        catch (SQLException e)
        {


            {


                throw new ReversoDaoException (5,

                        "Une erreur s'est produite lors"
                                +" de traitement des données. "
                                +"L'application va s'arrêter.");


            }


        }

        catch (Exception ex)
        {


            JOptionPane.showMessageDialog (null,


                    "Une erreur s'est produite. " +


                            "L'application va s'arrêter..");


            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);


            ex.printStackTrace ();


            System.exit (1);


        }


        return client;


    }



    /**
     * Saves a client to the database.
     *
     * @param client       The client to save.
     * @param selectedCrud The CRUD operation
     *                     to perform (create or update).
     * @throws ReversoDaoException If a data
     *                             access error occurs during the
     *                             operation.
     */
    public void save (
            Client client, CrudEnum selectedCrud)
            throws ReversoDaoException
    {


        Connection connection =
                ConnectionManager.getInstance ();


        PreparedStatement statement;


        try
        {


            if (selectedCrud == CrudEnum.CREATE)

            {


                statement = connection.prepareStatement (
                        "INSERT INTO Client "
                                +"(client_raison_sociale,"


                                +" client_num_rue,"
                                +" client_nom_rue,"
                                +" client_code_postal,"

                                +" client_ville,"
                                +" client_telephone, "
                                +"client_adresse_mail,"

                                +"client_commentaires,"

                                +" client_chiffre_affaires,"


                                +" client_nb_employes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");


            }

            else if (selectedCrud
                    == CrudEnum.UPDATE)

            {


                statement =
                        connection.
                                prepareStatement (
                                        "UPDATE Client SET client_raison_sociale = ?,"


                                                +" client_num_rue = ?,"
                                                +" client_nom_rue = ?, "
                                                +"client_code_postal = ?,"
                                                +" client_ville = ?, "
                                                +"client_telephone = ?, "
                                                +"client_adresse_mail = ?, "
                                                +"client_commentaires = ?, "
                                                +"client_chiffre_affaires = ?,"
                                                +"client_nb_employes = ? WHERE client_id = ?");


                statement.setInt (11, client.getId ());


            }

            else

            {


                throw new IllegalArgumentException (
                        "L'operation est"
                                +" pas possibble!"
                                +selectedCrud);


            }



            statement.setString (
                    1, client.getRaisonSociale ());


            statement.setString (
                    2, client.getNumRue ());


            statement.setString (
                    3, client.getNomRue ());


            statement.setString (
                    4, client.getCodePostal ());


            statement.setString (
                    5, client.getVille ());


            statement.setString (
                    6, client.getTelephone ());


            statement.setString (
                    7, client.getAdresseMail ());


            statement.setString (
                    8, client.getCommentaires ());


            statement.setDouble (
                    9, client.getChiffreAffaires ());


            statement.setInt (
                    10, client.getNbEmployes ());


            statement.executeUpdate ();



        }

        catch (SQLException e)
        {


            if (e
                    instanceof SQLIntegrityConstraintViolationException)

            {


                throw new ReversoDaoException (1,

                        "Impossible d'effectuer l'action " +
                                "demandée : des informations liées " +
                                "au client empêchent cette opération.");


            }

            else

            {


                throw new ReversoDaoException (5,
                        "Une erreur s'est produite"
                                +" lors de traitement des données. "


                                +"L'application va s'arrêter."+e.getMessage());


            }


        }

        catch (Exception ex)
        {


            JOptionPane.showMessageDialog (null,


                    "Une erreur s'est produite. " +


                            "L'application va s'arrêter.");


            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);


            ex.printStackTrace ();


            System.exit (1);


        }


    }



    /**
     * Deletes a client from the database.
     *
     * @param client The client to delete.
     * @throws ReversoDaoException If a data
     *                             access error occurs during the
     *                             operation.
     * @throws ReversoException    If an error
     *                             occurs during the operation.
     */
    public void delete (Client client)
            throws ReversoDaoException,
            ReversoException
    {

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            PreparedStatement statement =

                    connection.prepareStatement (
                            "DELETE FROM Client WHERE client_id=?");

            statement.setInt (1, client.getId ());

            statement.executeUpdate ();

        } catch (SQLException e)
        {

            throw new ReversoDaoException (5,
                    "Une erreur s'est produite lors"
                            +" de traitement des données. "
                            +"L'application va s'arrêter." + e.getMessage());

        } catch (Exception ex)
        {

            JOptionPane.showMessageDialog (null,
                    "Une erreur s'est produite. " +
                            "L'application va s'arrêter.");

            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);

            ex.printStackTrace ();

            System.exit (1);

        }
    }

}
