package com.ephemeraltopic.util;

import java.util.Arrays;

public class ArgsSplitter
{
    /**
     * getting values of augment
     * @param name value name
     * @param args value
     * @return argument value to match name, null if no match
     */
    public static String getAugmentValue( String name, String[] args )
    {
        return Arrays.stream(args)
                .map( s -> s.split(":") )
                .filter( arr -> arr[0].equals(name) )
                .map( arr -> arr[1] )
                .findFirst()
                .orElse(null);
    }
}