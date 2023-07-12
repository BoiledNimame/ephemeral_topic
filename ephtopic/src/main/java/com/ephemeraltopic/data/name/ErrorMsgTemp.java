package com.ephemeraltopic.data.name;

public enum ErrorMsgTemp
{
    AUGMENT_IS_NULL("augment contains null!"),
    LOGIN_EXCEPTION("LoginException occurred. The token may not exist or may be incorrect.");

    private final String message;
    ErrorMsgTemp ( String message )
    {
        this.message = message;
    }

    public String get()
    {
        return this.message;
    }
}
