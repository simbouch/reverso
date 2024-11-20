/**
 * This is the Societe class of my application.
 *
 * @author Bouchaib Khribech
 * @version 1.0
 */
package com.bouchaib.entity;


import com.bouchaib.entity.entityException.ReversoException;


import java.io.Serializable;


/**
 * The Societe class represents a company.
 * It contains information such as the company's name,
 * address, and contact details.
 */
public abstract class Societe implements Serializable
{

    private Integer id;

    private String raisonSociale;

    private String numRue;

    private String nomRue;

    private String codePostal;

    private String ville;

    private String telephone;

    private String adresseMail;

    private String commentaires;


    /**
     * Constructs a new `Societe` object.
     * Also assigns a unique ID to the object.
     * Creates a new Societe object with the specified parameters.
     * @param raisonSociale the name of the company
     * @param numRue the street number of the company's address
     * @param nomRue the name of the street where the company is located
     * @param codePostal the postal code of the company's address
     * @param ville the city where the company is located
     * @param telephone the company's phone number
     * @param adresseMail the company's email address
     * @param commentaires additional comments about the company
     * @throws ReversoException if any of the parameters are
     * invalid (null, empty, or incorrect format)
     */
    public Societe (String raisonSociale,
                    String numRue,
                    String nomRue,

                    String codePostal,
                    String ville,
                    String telephone,

                    String adresseMail,

                    String commentaires)
            throws ReversoException
    {

        this.setRaisonSociale (raisonSociale);

        this.setNumRue (numRue);

        this.setNomRue (nomRue);

        this.setCodePostal (codePostal);

        this.setVille (ville);

        this.setTelephone (telephone);

        this.setAdresseMail (adresseMail);

        this.setCommentaires (commentaires);

    }


    public Societe ()
    {


    }


    public int getId ()
    {

        return id;

    }


    public void setId (int id)
    {

        this.id = id;

    }

    public String getRaisonSociale ()
    {

        return raisonSociale;

    }

    /**
     * Sets the name of the company.
     * @param raisonSociale the new name of the company
     * @throws ReversoException if the name is null or empty
     */
    public void setRaisonSociale (String raisonSociale) throws ReversoException
    {

        if (raisonSociale == null || raisonSociale.trim ().isEmpty ())
        {

            throw new ReversoException ("La raison sociale ne peut pas être vide ou null.");

        }

        this.raisonSociale = raisonSociale;

    }


    public String getNumRue ()
    {

        return numRue;

    }

    /**
     * Sets the street number of the company's address.
     * @param numRue the new street number of the company's address
     * @throws ReversoException if the street number is null or empty
     */

    public void setNumRue (String numRue) throws ReversoException
    {

        if (numRue == null || numRue.trim ().isEmpty ())
        {

            throw new
                    ReversoException ("Le numéro de rue doit être renseigné.");

        }

        this.numRue = numRue;

    }


    public String getNomRue ()
    {

        return nomRue;

    }

    /**
     * Sets the nom de la rue of the Societe.
     *
     * @param nomRue the new nom de la rue of the Societe, must not be null or empty
     * @throws ReversoException if the nom de la rue is null or empty
     */

    public void setNomRue (String nomRue) throws ReversoException
    {

        if (nomRue == null || nomRue.trim ().isEmpty ())
        {

            throw new
                    ReversoException ("Le nom de la rue doit être renseigné.");

        }

        this.nomRue = nomRue;

    }


    public String getCodePostal ()
    {

        return codePostal;

    }

    /**
     * Sets the street number of the company.
     *
     * @param codePostal the street number of the company
     * @throws ReversoException if the provided postal code is null or empty
     */

    public void
    setCodePostal (String codePostal)
            throws ReversoException
    {

        if (codePostal == null || codePostal.trim ().isEmpty ()
                || codePostal.length () != 5)

        {

            throw new
                    ReversoException
                    ("Le code postal doit être renseigné et doit" +
                            " avoir une longueur de 5 caractères.");

        }

        this.codePostal = codePostal;

    }


    public String getVille ()
    {

        return ville;

    }

    /**
     * Sets the ville of the Societe.
     * @param ville the new ville of the Societe, must not be null or empty
     * @throws ReversoException if the ville is null or empty
     */
    public void setVille (String ville) throws ReversoException
    {

        if (ville == null || ville.trim ().isEmpty ())
        {

            throw new ReversoException ("La ville doit être renseignée.");

        }

        this.ville = ville;

    }


    public String getTelephone ()
    {

        return telephone;

    }

    /**
     * Sets the telephone of the Societe.
     *
     * @param telephone the new telephone of the Societe, must not be null or empty and have at least 10 characters
     * @throws ReversoException if the telephone is null or empty or has less than 10 characters
     */

    public void setTelephone (String telephone) throws ReversoException
    {

        if (telephone == null || telephone.length () < 10)
        {

            throw new ReversoException ("Le numéro de téléphone doit être" +
                    " renseigné et avoir au moins 10 caractères.");

        }

        this.telephone = telephone;

    }


    public String getAdresseMail ()
    {

        return adresseMail;

    }

    /**
     * Sets the adresse mail of the Societe.
     *
     * @param adresseMail the new adresse mail of the Societe, must not be null or empty and contain '@' character
     * @throws ReversoException if the adresse mail is null or empty or doesn't contain '@' character
     */

    public void setAdresseMail (String adresseMail) throws ReversoException
    {

        if (adresseMail == null || adresseMail.trim ().isEmpty ()
                || !adresseMail.contains ("@"))
        {

            throw new
                    ReversoException
                    ("L'adresse email doit être renseignée et contenir le caractère @.");

        }

        this.adresseMail = adresseMail;

    }

    public String getCommentaires ()
    {

        return commentaires;

    }


    public void setCommentaires (String commentaires)
    {

        this.commentaires = commentaires;

    }

    @Override
    public String toString ()
    {

        return "Societe{" +
                "id=" + id +
                ", raisonSociale='" + raisonSociale +
                '\'' +
                ", numRue='" + numRue + '\'' +
                ", nomRue='" + nomRue + '\'' +

                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +

                ", telephone='" + telephone + '\'' +
                ", adresseMail='" + adresseMail +
                '\'' +
                ", commentaires='" + commentaires + '\'' +
                '}';

    }

}
