/**
 FormatterLog is a custom formatter class for the
 log records. It formats the log records in a
 specific format, which includes date, level,
 message, source class name and source method
 name. The format of the log records can be
 customized as per the requirement.
 @version 1.0
 @author Bouchaib
 @since 2023-03-26
 */
package com.bouchaib.entity.loggers;


import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.logging.Formatter;

import java.util.logging.Level;

import java.util.logging.LogRecord;


public class FormatterLog extends Formatter
{

    /**
     Overrides the format method of the Formatter
     class.
     It formats the log records in a specific format,
     which includes date, level, message, source class
     name and source method name.
     The format of the log records can be customized as
     per the requirement.
     @param record The LogRecord that needs to be
     formatted.
     @return The formatted log record as a string.
     */
    @Override
    public String format (LogRecord record)
    {

        if (record.getLevel ().intValue ()
                <Level.SEVERE.intValue ())
        {

            return "";

        }


        DateFormat format = new SimpleDateFormat (
                "dd/MM/yyyy HH:mm:ss");

        StringBuilder result = new StringBuilder ();

        result.append (format.format (new Date ()));

        result.append (" Level : ");

        result.append (record.getLevel ());

        result.append (" / Message : ");

        result.append (record.getMessage ());

        result.append (" / Classe : ");

        result.append (record.getSourceClassName ());

        result.append ("Méthode : ");

        result.append (record.getSourceMethodName ());

        result.append ("\n");

        return result.toString ();

    }

}


