package com.ephemeraltopic.data.bot;

public class ServerData
{
    private static final ServerData instance = new ServerData();

    private ServerData()
    {

    }

    public static ServerData getInstance()
    {
        return instance;
    }
}
