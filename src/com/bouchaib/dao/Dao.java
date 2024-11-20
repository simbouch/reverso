/**

 The GenericDao class is a generic data access object (DAO)
 class that defines common methods for accessing a database.

 This class provides a set of abstract methods that must be
 implemented by concrete DAO classes.

 The concrete DAO classes are responsible for performing
 operations on a specific entity in the database.

 @param <T> The type of entity the DAO operates on.

 @author Bouchaib khribech

 @version 1.0

 @since 2023-03-26
 */

package com.bouchaib.dao;

import com.bouchaib.dao.daoSql.ReversoDaoException;

import com.bouchaib.entity.entityException.ReversoException;

import java.sql.SQLException;

import java.util.ArrayList;

import utilities.CrudEnum;


public abstract class Dao < T >
{

    private Class < T > entityClass;

    /**

     Constructs a new Dao object with the specified
     entity class.
     @param entityClass The class of the entity the DAO
     operates on.
     */
    public Dao (Class < T > entityClass)
    {

        this.entityClass = entityClass;

    }

    /**

     Constructs a new Dao object without an entity
     class specified.
     */
    public Dao ()
    {
    }

    /**

     Finds all entities of type T in the database.
     @return An ArrayList of all entities of type T stored in
     the database.
     @throws SQLException If a database error occurs during
     the operation.
     @throws ReversoDaoException If a data access error occurs
     during the operation.
     @throws ReversoException If an error occurs during the
     operation.
     */
    public abstract ArrayList < T > findAll ()
            throws SQLException, ReversoDaoException,
            ReversoException;

    /**

     Finds an entity with the specified ID from the database.
     @param id The ID of the entity to find.
     @return The entity with the specified ID.
     @throws SQLException if there is an error accessing the database.
     @throws ReversoDaoException if there is an error in the data access object.
     @throws ReversoException if there is an error in the entity processing.
     */
    public abstract T find (int id) throws SQLException,
            ReversoDaoException,

            ReversoException;

    /**

     Saves an entity of type T to the database.
     @param t The entity to be saved.
     @param selectedCrud The selected CRUD operation to be
     performed.
     @throws SQLException If a database error occurs during
     the operation.
     @throws ReversoDaoException If a data access error occurs
     during the operation.
     @throws ReversoException If an error occurs during the
     operation.
     */
    public abstract void save (T t, CrudEnum selectedCrud)
            throws SQLException, ReversoDaoException,
            ReversoException;

    /**

     Deletes an entity of type T from the database.
     @param t The entity to be deleted.
     @throws SQLException If a database error occurs during
     the operation.
     @throws ReversoDaoException If a data access error occurs
     during the operation.
     @throwsReversoException If an error occurs during the
     operation.
     */
    public abstract void delete (T t)
            throws SQLException, ReversoDaoException,
            ReversoException;

}
