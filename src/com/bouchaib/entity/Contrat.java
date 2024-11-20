package com.bouchaib.entity;


import com.bouchaib.entity.entityException.ReversoException;


public class Contrat
{

    private Integer contrat_id;

    private Integer client_id;

    private String libelleContrat;

    private double montantContrat;

    private static int counter = 1;


    public Contrat (Integer client_id, String libelleContrat,
                    double montantContrat) throws ReversoException
    {

        this.setContrat_id (counter++);

        this.setClient_id (client_id);

        this.setLibelleContrat (libelleContrat);

        this.setMontantContrat (montantContrat);

    }

    public Contrat ()
    {


    }

    public Integer getContrat_id ()
    {

        return contrat_id;

    }


    public void setContrat_id (Integer contrat_id)
    {

        this.contrat_id = contrat_id;

    }

    public Integer getClient_id ()
    {

        return client_id;

    }


    public void setClient_id (Integer client_id)
    {

        this.client_id = client_id;

    }

    public String getLibelleContrat ()
    {

        return libelleContrat;

    }


    public void setLibelleContrat (String libelleContrat) throws
            ReversoException
    {

        if (libelleContrat == null || libelleContrat.isEmpty ())
        {

            throw new
                    ReversoException
                    ("Le libellé du contrat ne peut pas être vide ou null.");

        }

        this.libelleContrat = libelleContrat;

    }


    public double getMontantContrat ()
    {

        return montantContrat;

    }


    public void setMontantContrat (double montantContrat) throws
            ReversoException
    {

        if (montantContrat <= 0)
        {

            throw new
                    ReversoException
                    ("Le montant du contrat doit être supérieur à zéro.");

        }

        this.montantContrat = montantContrat;

    }

}


