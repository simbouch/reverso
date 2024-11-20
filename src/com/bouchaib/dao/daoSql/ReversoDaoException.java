/**
 ReversoDaoException is a custom exception class for the Reverso application.
 It is used to handle the exceptions that occur during the database operations.
 @author Khribech Bouchaib
 @version 1.0
 */
package com.bouchaib.dao.daoSql;



public class ReversoDaoException extends Exception
{


    private int gravity;


    /**
     * Constructs a new ReversoDaoException with a custom error message.
     *
     * @param gravity the gravity of the exception.
     * @param message the error message to be displayed.
     */
    public ReversoDaoException (int gravity, String message)
    {


        super (message);


        this.gravity = gravity;


    }
    /**
     * Gets the gravity  of the exception.
     *
     * @return the gravity  of the exception.
     */
    public int getGravity()
    {


        return gravity;


    }


}




