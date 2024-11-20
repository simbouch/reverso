package com.bouchaib.reversoTests;

import static org.junit.jupiter.api.Assertions.*;

import com.bouchaib.entity.Client;
import com.bouchaib.entity.entityException.ReversoException;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Client} class.
 * 
 * This class verifies the behavior of the {@link Client} methods, including:
 * - Validating the annual turnover (chiffre d'affaires) logic.
 * - Validating the street name (nom rue) input rules.
 * - Validating the email address (adresse mail) formatting rules.
 * 
 * @version 1.0
 * @author Bouchaib Khribech
 */
class ClientTest {

    private Client client;

    /**
     * Tests that a valid annual turnover value does not throw an exception.
     */
    @Test
    void testSetChiffreAffairesValid() {
        assertDoesNotThrow(() -> new Client().setChiffreAffaires(600000));
    }

    /**
     * Tests that an annual turnover equal to the minimum threshold (200) is valid.
     */
    @Test
    void testSetChiffreAffairesEqualTo200() {
        assertDoesNotThrow(() -> new Client().setChiffreAffaires(200));
    }

    /**
     * Tests that an annual turnover below the minimum threshold (200) throws an exception.
     */
    @Test
    void testSetChiffreAffairesLessThan200() {
        assertThrows(ReversoException.class, () -> new Client().setChiffreAffaires(199));
    }

    // -----------------------------------------
    // Tests for "nomRue" (Street Name)
    // -----------------------------------------

    /**
     * Tests that a valid street name does not throw an exception.
     */
    @Test
    void testSetNomRueValid() {
        String validNomRue = "Main Street";
        assertDoesNotThrow(() -> new Client().setNomRue(validNomRue));
    }

    /**
     * Tests that a null street name throws an exception.
     */
    @Test
    void testSetNomRueNull() {
        assertThrows(ReversoException.class, () -> new Client().setNomRue(null));
    }

    /**
     * Tests that an empty street name throws an exception.
     */
    @Test
    void testSetNomRueEmpty() {
        assertThrows(ReversoException.class, () -> new Client().setNomRue(""));
    }

    /**
     * Tests that a street name containing only whitespace throws an exception.
     */
    @Test
    void testSetNomRueWhiteSpace() {
        assertThrows(ReversoException.class, () -> new Client().setNomRue("   "));
    }

    /**
     * Tests that a street name containing special characters throws an exception.
     */
    @Test
    void testSetNomRueSpecialCharacters() {
        assertThrows(ReversoException.class, () -> new Client().setNomRue("\n"));
        assertThrows(ReversoException.class, () -> new Client().setNomRue("\r"));
    }

    // -----------------------------------------
    // Tests for "adresseMail" (Email Address)
    // -----------------------------------------

    /**
     * Tests that a valid email address does not throw an exception.
     */
    @Test
    void testSetAdresseMailValid() {
        String validAdresseMail = "bouchaib@example.com";
        assertDoesNotThrow(() -> new Client().setAdresseMail(validAdresseMail));
    }

    /**
     * Tests that an email address containing numbers is valid.
     */
    @Test
    void testSetAdresseMailWithNumbers() {
        String validAdresseMail = "john123.doe456@example789.com";
        assertDoesNotThrow(() -> new Client().setAdresseMail(validAdresseMail));
    }

    /**
     * Tests that an email address containing special characters is valid.
     */
    @Test
    void testSetAdresseMailWithSpecialCharacters() {
        String validAdresseMail = "john_doe+test@example.com";
        assertDoesNotThrow(() -> new Client().setAdresseMail(validAdresseMail));
    }

    /**
     * Tests that a null email address throws an exception.
     */
    @Test
    void testSetAdresseMailNull() {
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail(null));
    }

    /**
     * Tests that an empty email address throws an exception.
     */
    @Test
    void testSetAdresseMailEmpty() {
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail(""));
    }

    /**
     * Tests that an email address containing only whitespace throws an exception.
     */
    @Test
    void testSetAdresseMailWhiteSpace() {
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail("   "));
    }

    /**
     * Tests that an email address missing the "@" symbol throws an exception.
     */
    @Test
    void testSetAdresseMailNoAtSymbol() {
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail("bouchaibkhribech.example.com"));
    }

    /**
     * Tests that an email address containing special characters throws an exception.
     */
    @Test
    void testSetAdresseMailSpecialCharacters() {
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail("\n"));
        assertThrows(ReversoException.class, () -> new Client().setAdresseMail("\r"));
    }
}
