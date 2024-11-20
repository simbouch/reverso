/**
 * This is the Prospect class of my
 * application. A class representing a
 * prospect, a potential client who has
 * shown interest in the company's
 * products or services. Inherits from
 * the Societe class.
 * @auhtor Bouchaib Khribech
 * @version 1.0
 */

package com.bouchaib.entity;


import com.bouchaib.entity.entityException.ReversoException;


import java.io.Serializable;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;


public class Prospect extends Societe implements Serializable
{


    private static Integer counter = 1;


    private LocalDate dateProspection;

    private String estInteresse;


    /**
     * Constructs a new Prospect object
     * with the specified parameters.
     *
     * @param raisonSociale  the name of
     *     the company.
     * @param numRue         the street
     *     number of the company's
     *     address.
     * @param nomRue         the street
     *     name of the company's address.
     * @param codePostal     the postal
     *     code of the company's address.
     * @param ville          the city of
     *     the company's address.
     * @param telephone      the phone
     *     number of the company.
     * @param adresseMail    the email
     *     address of the company.
     * @param commentaires   additional
     *     comments about the company.
     * @param dateProspection the date
     *     when the prospect
     *                        was
     * contacted by the company.
     * @param estInteresse   a flag
     *     indicating whether
     *                       the prospect
     * is interested in the company's
     * products or services.
     * @throws Exception if there is an
     *     error in the input parameters.
     */

    public Prospect (
            String raisonSociale,
            String numRue, String nomRue,

            String codePostal, String ville,
            String telephone,

            String adresseMail,
            String commentaires,

            LocalDate dateProspection,

            String estInteresse)
            throws ReversoException
    {

        super (raisonSociale, numRue, nomRue,
                codePostal, ville, telephone,

                adresseMail, commentaires);

        this.setId (counter++);

        this.setDateProspection (
                dateProspection);

        this.setEstInteresse (estInteresse);

    }

    public Prospect ()
    {

        super ();

    }
    /**
     * Gets the date when the prospect was
     * contacted by the company.
     *
     * @return the date when the prospect
     *     was contacted by the company.
     */
    public LocalDate
    getDateProspection ()
    {

        return dateProspection;

    }


    /**
     * Sets the date when the prospect was
     * contacted by the company.
     *
     * @param dateProspection the date
     *     when the prospect was
     *     contacted.
     * @throws ReversoException if the
     *     input parameter is null.
     */
    public void setDateProspection (LocalDate dateProspection)
            throws ReversoException
    {

        if (dateProspection == null)
        {

            throw new ReversoException ("La date de prospection " +
                    "doit être renseignée.");

        }

        try
        {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern ("dd/MM/yyyy");

            LocalDate.parse (dateProspection.format (formatter), formatter);

        }
        catch (DateTimeParseException e)
        {

            throw new ReversoException ("La date de prospection " +
                    "doit être au format jj/mm/aaaa.");

        }

        this.dateProspection = dateProspection;

    }


    /**
     * Checks if the prospect is
     * interested in the company's
     * products.
     *
     * @return true if the prospect is
     *     interested, false otherwise.
     */
    public String getEstInteresse ()
    {

        return estInteresse;

    }


    /**
     * Sets the interest status of the
     * prospect.
     *
     * @param estInteresse the interest
     *     status of the prospect.
     * @throws ReversoException if the
     *     input parameter is empty.
     */
    public void setEstInteresse (String estInteresse)
            throws ReversoException
    {

        if (estInteresse.isEmpty ())
        {

            throw new
                    ReversoException
                    (
                            "L'indication d'intérêt ne peut pas être vide.");

        }

        this.estInteresse = estInteresse;

    }

    @Override
    public String toString ()
    {

        return "Prospect{" +
                super.toString () +
                ", dateProspection=" +
                dateProspection +
                ", estInteresse='" + estInteresse + '\'' +
                '}';

    }

}



