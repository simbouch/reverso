/**
 The {@code ContratDao} class is responsible
 for handling all the database operations
 related to the {@code Contrat} entity. It
 provides a constructor to initialize the
 connection to the database and a method to
 retrieve the contracts of a client by their
 id.
 @author Bouchaib
 @version 1.0
 @since 2023-03-26
 */
package com.bouchaib.dao.daoSql;


import com.bouchaib.dao.Dao;

import com.bouchaib.entity.Contrat;

import com.bouchaib.entity.entityException.ReversoException;

import java.sql.*;

import java.util.ArrayList;

import java.util.logging.Level;


import utilities.CrudEnum;


import javax.swing.*;


import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;


public class ContratDao
        extends Dao < Contrat >
{

    /**
     * Retrieves the contracts of a client by
     * their id.
     *
     * @param client_id the id of the client
     * @return the list of contracts
     * @throws ReversoDaoException if an error
     *     occurs
     *                             while
     * processing the data
     * @throws ReversoException    if an error
     *     occurs
     *                             while
     * retrieving the contracts
     */
    public ArrayList < Contrat > findByIdClient (
            Integer client_id)
            throws ReversoDaoException,
            ReversoException
    {

        ArrayList < Contrat > contrats =
                new ArrayList <> ();

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            PreparedStatement statement =

                    connection.
                            prepareStatement (
                                    "SELECT * FROM Contrat WHERE client_id = ?");

            statement.setInt (1, client_id);

            ResultSet resultSet =
                    statement.executeQuery ();


            while (resultSet.next ())
            {

                String libelleContrat =
                        resultSet.getString (
                                "libelle_contrat");

                double montantContrat =
                        resultSet.getDouble (
                                "montant_contrat");


                Contrat contrat =
                        new Contrat (
                                client_id, libelleContrat,
                                montantContrat);

                contrats.add (contrat);

            }
        } catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors"
                                +" de traitement des données. "
                                +"L'application va  s'arrêter.");

            }
        } catch (Exception ex)
        {

            JOptionPane.showMessageDialog (null,

                    "Une erreur s'est produite. " +

                            "L'application va s'arreter.");

            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);

            ex.printStackTrace ();

            System.exit (1);

        }
        return contrats;

    }

    /**
     Finds all Contrat objects in the database.
     @return an ArrayList of Contrat objects.
     @throws ReversoException if an error
     occurs
     while
     retrieving the Contrat from the database.
     @throws ReversoDaoException if an error
     occurs
     while
     performing the database operation.
     */
    public ArrayList < Contrat > findAll ()
            throws ReversoDaoException,
            ReversoException
    {

        String sql = "SELECT * FROM Contrat";

        PreparedStatement statement = null;

        ArrayList < Contrat > contrats =
                new ArrayList <> ();

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            statement =
                    connection.prepareStatement (sql);

            ResultSet resultSet =
                    statement.executeQuery ();


            while (resultSet.next ())
            {

                Contrat contrat = new Contrat ();

                contrat.setContrat_id (
                        resultSet.getInt ("contrat_id"));

                contrat.setClient_id (
                        resultSet.getInt ("client_id"));

                contrat.setLibelleContrat (
                        resultSet.
                                getString (
                                        "libelle_contrat"));

                contrat.setMontantContrat (
                        resultSet.
                                getDouble (
                                        "montant_contrat"));

                contrats.add (contrat);

            }

        }
        catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors"
                                +" de traitement des données. "
                                +"L'application va  s'arrêter.");

            }

        }
        catch (Exception ex)
        {

            JOptionPane.showMessageDialog (null,

                    "Une erreur s'est produite. " +

                            "L'application va  s'arrêter.");

            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);

            ex.printStackTrace ();

            System.exit (1);

        }

        return contrats;

    }

    /**
     Finds a single Contrat object based on the
     ID.
     @param id the ID of the  Contrat object to
     be
     retrieved.
     @return the retrieved Contrat object, or
     null
     if no
     object was found.
     @throws ReversoException if an error
     occurs
     while
     retrieving the prospect from the database.
     @throws ReversoDaoException if an error
     occurs
     while
     performing the database operation.
     */
    public Contrat find (int id)
            throws ReversoDaoException,

            ReversoException
    {

        String sql =
                "SELECT * FROM Contrat WHERE contrat_id = ?";

        PreparedStatement statement = null;

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            statement =
                    connection.prepareStatement (sql);

            statement.setInt (1, id);

            ResultSet resultSet =
                    statement.executeQuery ();

            if (resultSet.next ())
            {

                Contrat contrat = new Contrat ();

                contrat.setContrat_id (
                        resultSet.getInt ("contrat_id"));

                contrat.setClient_id (
                        resultSet.getInt ("client_id"));

                contrat.setLibelleContrat (
                        resultSet.
                                getString (
                                        "libelle_contrat"));

                contrat.setMontantContrat (
                        resultSet.
                                getDouble (
                                        "montant_contrat"));

                return contrat;

            }
            else
            {

                return null;

            }

        }
        catch (SQLException e)
        {

            throw new ReversoDaoException (5,
                    "Une erreur s'est produite lors"
                            +" de traitement des données. "
                            +"L'application va  s'arrêter.");

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

        return null;

    }

    /**
     Saves a Contrat object to the database.
     @param contrat the Contrat object to be
     saved.
     @param selectedCrud the type of CRUD
     operation
     to be
     performed.
     @throws ReversoDaoException if an error
     occurs
     while
     performing the database operation.
     */
    public void save (
            Contrat contrat, CrudEnum selectedCrud)
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
                        "INSERT INTO Contrat "
                                +"(libelle_contrat, montant_contrat,"
                                +

                                " client_id) VALUES (?, ?, ?)");

            }
            else
            {

                statement = connection.prepareStatement (
                        "UPDATE Contrat SET "
                                +"libelle_contrat = ?, montant_contrat = ?, "
                                +

                                "client_id = ? WHERE contrat_id = ?");

                statement.setInt (
                        4, contrat.getContrat_id ());

            }


            statement.setString (
                    1, contrat.getLibelleContrat ());

            statement.setDouble (
                    2, contrat.getMontantContrat ());

            statement.setInt (
                    3, contrat.getClient_id ());

            statement.executeUpdate ();

        }
        catch (SQLException e)
        {

            if (e
                    instanceof SQLIntegrityConstraintViolationException)
            {

                throw new ReversoDaoException (1,

                        "Impossible d'effectuer l'action demandée " +
                                ": des informations liées au client " +
                                "empêchent cette opération.");

            }
            else
            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite"
                                +" lors de traitement des données. "

                                +"L'application va s'arrêter."
                                +e.getMessage ());

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

    }

    /**
     Deletes a Contrat object from the
     database.
     @param contrat the Contrat  object to be
     deleted.
     @throws ReversoDaoException if an error
     occurs
     while
     performing the database operation.
     @throws ReversoException if an error
     occurs
     while
     deleting the prospect from the database.
     */
    public void delete (Contrat contrat)
            throws ReversoDaoException
    {

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            PreparedStatement statement =

                    connection.
                            prepareStatement (
                                    "DELETE FROM Contrat WHERE contrat_id=?");

            statement.setInt (
                    1, contrat.getContrat_id ());

            statement.executeUpdate ();

        } catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors"
                                +" de traitement des données. "
                                +"L'application va s'arrêter.");

            }
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
