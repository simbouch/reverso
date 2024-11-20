/**
 ProspectDao is a class that provides CRUD
 (Create, Read, Update, Delete) operations
 for the Prospect entity.
 @author Bouchaib
 @version 1.0
 */
package com.bouchaib.dao.daoSql;

import com.bouchaib.dao.Dao;

import com.bouchaib.entity.Prospect;

import com.bouchaib.entity.entityException.ReversoException;

import java.sql.*;

import java.util.ArrayList;

import java.util.logging.Level;


import utilities.CrudEnum;


import javax.swing.*;


import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;


public class ProspectDao
        extends Dao < Prospect >
{

    /**
     Finds all Prospect objects in the
     database.
     @return an ArrayList of Prospect objects.
     @throws ReversoException if an error
     occurs
     while
     retrieving the prospects from the
     database.
     @throws ReversoDaoException if an error
     occurs
     while
     performing the database operation.
     */

    public ArrayList < Prospect > findAll ()
            throws ReversoException,
            ReversoDaoException
    {

        ArrayList < Prospect > prospects =
                new ArrayList <> ();

        Connection connection =
                ConnectionManager.getInstance ();


        try
        {

            PreparedStatement statement =

                    connection.prepareStatement (
                            "SELECT * FROM Prospect");

            ResultSet resultSet =
                    statement.executeQuery ();

            while (resultSet.next ())
            {

                Prospect prospect = new Prospect ();

                prospect.setId (
                        resultSet.getInt ("prospect_id"));

                prospect.setRaisonSociale (
                        resultSet.
                                getString (
                                        "prospect_raison_sociale"));

                prospect.setNumRue (
                        resultSet.getString (
                                "prospect_num_rue"));

                prospect.setNomRue (
                        resultSet.getString (
                                "prospect_nom_rue"));

                prospect.setCodePostal (
                        resultSet.
                                getString (
                                        "prospect_code_postal"));

                prospect.setVille (
                        resultSet.getString (
                                "prospect_ville"));

                prospect.setTelephone (
                        resultSet.
                                getString (
                                        "prospect_telephone"));

                prospect.setAdresseMail (
                        resultSet.
                                getString (
                                        "prospect_adresse_mail"));

                prospect.setCommentaires (
                        resultSet.
                                getString (
                                        "prospect_commentaires"));

                prospect.setDateProspection (
                        resultSet
                                .
                                getDate (
                                        "prospect_date_prospection")

                                .toLocalDate ());

                prospect.setEstInteresse (
                        resultSet.
                                getString (
                                        "prospect_est_interesse"));

                prospects.add (prospect);

            }

        }
        catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors du traitement " +
                                "des données. L'application va s'arrêter.");

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

        return prospects;

    }

    /**

     Finds a single Prospect object based on
     the ID.
     @param id the ID of the Prospect object to
     be
     retrieved.
     @return the retrieved Prospect object, or
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
    public Prospect find (int id)
            throws ReversoException,

            ReversoDaoException
    {

        Connection connection =
                ConnectionManager.getInstance ();

        Prospect prospect = null;

        try
        {

            PreparedStatement statement =

                    connection.
                            prepareStatement (
                                    "SELECT * FROM Prospect WHERE prospect_id = ?");

            statement.setInt (1, id);

            ResultSet resultSet =
                    statement.executeQuery ();

            if (resultSet.next ())
            {

                prospect = new Prospect ();

                prospect.setId (
                        resultSet.getInt ("prospect_id"));

                prospect.setRaisonSociale (
                        resultSet.
                                getString (
                                        "prospect_raison_sociale"));

                prospect.setNumRue (
                        resultSet.getString (
                                "prospect_num_rue"));

                prospect.setNomRue (
                        resultSet.getString (
                                "prospect_nom_rue"));

                prospect.setCodePostal (
                        resultSet.
                                getString (
                                        "prospect_code_postal"));

                prospect.setVille (
                        resultSet.getString (
                                "prospect_ville"));

                prospect.setTelephone (
                        resultSet.
                                getString (
                                        "prospect_telephone"));

                prospect.setAdresseMail (
                        resultSet.
                                getString (
                                        "prospect_adresse_mail"));

                prospect.setCommentaires (
                        resultSet.
                                getString (
                                        "prospect_commentaires"));

                prospect.setDateProspection (
                        resultSet
                                .
                                getDate (
                                        "prospect_date_prospection")

                                .toLocalDate ());

                prospect.setEstInteresse (
                        resultSet.
                                getString (
                                        "prospect_est_interesse"));

            }

        }
        catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors"
                                +" de traitement des donnCées. "
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

        return prospect;

    }

    /**
     Saves a Prospect object to the database.
     @param prospect the Prospect object to be
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
    public void save (Prospect prospect,
                      CrudEnum selectedCrud)
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
                        "INSERT INTO Prospect "
                                +"(prospect_raison_sociale,"

                                +" prospect_num_rue,"
                                +" prospect_nom_rue,"
                                +" prospect_code_postal,"

                                +" prospect_ville,"
                                +" prospect_telephone, "
                                +"prospect_adresse_mail,"

                                +" prospect_commentaires,"

                                +" prospect_date_prospection,"

                                +" prospect_est_interesse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            }
            else if (selectedCrud
                    == CrudEnum.UPDATE)
            {

                statement =
                        connection.
                                prepareStatement
                                        (
                                                "UPDATE Prospect SET prospect_raison_sociale = ?,"
                                                        +" prospect_num_rue = ?,"
                                                        +" prospect_nom_rue = ?, "
                                                        +"prospect_code_postal = ?,"
                                                        +" prospect_ville = ?, "
                                                        +"prospect_telephone = ?, "
                                                        +"prospect_adresse_mail = ?, "
                                                        +"prospect_commentaires = ?,"
                                                        +"prospect_date_prospection = ?,"

                                                        +"prospect_est_interesse = ? WHERE prospect_id = ?");

                statement.setInt (
                        11, prospect.getId ());

            }
            else
            {

                throw new IllegalArgumentException (
                        "Invalid operation: "
                                +selectedCrud);

            }


            statement.setString (
                    1, prospect.getRaisonSociale ());

            statement.setString (
                    2, prospect.getNumRue ());

            statement.setString (
                    3, prospect.getNomRue ());

            statement.setString (
                    4, prospect.getCodePostal ());

            statement.setString (
                    5, prospect.getVille ());

            statement.setString (
                    6, prospect.getTelephone ());

            statement.setString (
                    7, prospect.getAdresseMail ());

            statement.setString (
                    8, prospect.getCommentaires ());

            statement.setObject (
                    9, prospect.getDateProspection ());

            statement.setString (
                    10, prospect.getEstInteresse ());

            statement.executeUpdate ();


        }
        catch (SQLException e)
        {

            if (e
                    instanceof SQLIntegrityConstraintViolationException)
            {

                throw new ReversoDaoException (1,

                        "Impossible d'effectuer l'action demandée :" +
                                "des informations liées au client " +
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

                            "L'application va s'arrêter.");

            LOGGER.log (Level.SEVERE, ex.getMessage (), ex);

            ex.printStackTrace ();

            System.exit (1);

        }

    }

    /**
     Deletes a Prospect object from the
     database.
     @param prospect the Prospect object to be
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
    public void delete (Prospect prospect)
            throws ReversoDaoException,
            ReversoException
    {

        Connection connection =
                ConnectionManager.getInstance ();

        try
        {

            PreparedStatement statement =

                    connection.
                            prepareStatement (
                                    "DELETE FROM Prospect WHERE prospect_id=?");

            statement.setInt (1, prospect.getId ());

            statement.executeUpdate ();

        } catch (SQLException e)
        {

            {

                throw new ReversoDaoException (5,
                        "Une erreur s'est produite lors"
                                +" de traitement des donnC)es. "
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
