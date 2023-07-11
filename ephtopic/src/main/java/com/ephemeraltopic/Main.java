package com.ephemeraltopic;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{
    public static Logger logger;

    private Main( String[] args )
    {
        logger = LogManager.getLogger();
        //TODO split and get token by args[]
    }

    public static void main ( String[] args )
    {
        if ( args==null || args.length==0 )
        {
            exit(1);
        }
        else
        {
            Main BotInstance = new Main( args );
        }
    }

    public static void exit ( int code )
    {
        // TODO shutdown jda object
        System.exit(code);
    }
}