/**
 * The Client class represents a client, a company that is a customer.
 * It inherits from the Societe class.
 *
 * @version 1.0
 * @auhtor Bouchaib Khribech
 */
package com.bouchaib.entity;


import com.bouchaib.entity.entityException.ReversoException;


import java.io.Serializable;

import java.util.ArrayList;


/**
 * A class representing a client, a company that is a customer of the company.
 * Inherits from the {@link Societe} class.
 */
public class Client extends Societe implements Serializable
{


    private static Integer counter = 1;

    private double chiffreAffaires;

    private int nbEmployes;

    private ArrayList < Contrat > contrats;



    /**
     * Constructs a new Client object with the specified parameters.
     *
     * @param raisonSociale  the name of the company.
     * @param numRue         the street number of the company's address.
     * @param nomRue         the street name of the company's address.
     * @param codePostal     the postal code of the company's address.
     * @param ville          the city of the company's address.
     * @param telephone      the phone number of the company.
     * @param adresseMail    the email address of the company.
     * @param commentaires   additional comments about the company.
     * @param chiffreAffaires the annual turnover of the client.
     * @param nbEmployes     the number of employees of the client.
     * @throws ReversoException if there is an error in the input parameters.
     */
    public Client (String raisonSociale,
                   String numRue,
                   String nomRue,

                   String codePostal,
                   String ville,
                   String telephone,

                   String adresseMail,
                   String commentaires,

                   double chiffreAffaires,

                   int nbEmployes) throws ReversoException
    {

        super (raisonSociale,
                numRue,
                nomRue,
                codePostal,
                ville,
                telephone,

                adresseMail,
                commentaires);

        this.setId (counter++);

        this.setChiffreAffaires (chiffreAffaires);

        this.setNbEmployes (nbEmployes);

        this.contrats = new ArrayList < Contrat > ();

    }
    public Client ()
    {

        super ();

    }

    /**
     * Gets the annual turnover of the client.
     *
     * @return the annual turnover of the client.
     */
    public double getChiffreAffaires ()
    {

        return chiffreAffaires;

    }


    /**
     * Sets the annual turnover of the client.
     *
     * @param chiffreAffaires the annual turnover of the client.
     * @throws ReversoException if the input parameter is less than 200.
     */
    public void setChiffreAffaires (double chiffreAffaires)
            throws ReversoException
    {

        if (chiffreAffaires < 200)
        {

            throw new
                    ReversoException
                    ("Le chiffre d'affaires doit être supérieur à 200.");

        }

        this.chiffreAffaires = chiffreAffaires;

    }


    /**
     * Gets the number of employees of the client.
     *
     * @return the number of employees of the client.
     */
    public int getNbEmployes ()
    {

        return nbEmployes;

    }


    /**
     * Sets the number of employees of the client.
     *
     * @param nbEmployes the number of employees of the client.
     * @throws ReversoException if the input parameter is smaller than 0.
     */
    public void setNbEmployes (int nbEmployes) throws ReversoException
    {

        if (nbEmployes <= 0)
        {

            throw new
                    ReversoException
                    ("Le nombre d'employés doit être supérieur à zéro.");

        }

        this.nbEmployes = nbEmployes;

    }

    public ArrayList < Contrat > getContrats ()
    {

        return contrats;

    }


    public void setContrats (ArrayList < Contrat > contrats)
    {

        this.contrats = contrats;

    }

    @Override
    public String toString ()
    {

        return "Client{" +
                super.toString () +
                ", chiffreAffaires=" +
                chiffreAffaires +
                ", nbEmployes=" + nbEmployes +
                '}';

    }


}



