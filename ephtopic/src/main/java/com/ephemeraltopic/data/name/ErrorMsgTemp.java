package com.ephemeraltopic.data.name;

public enum ErrorMsgTemp
{
    AUGMENT_IS_NULL("augment contains null!"),
    LOGIN_EXCEPTION("login_exception has occurred!");

    private final String message;
    private ErrorMsgTemp ( String message )
    {
        this.message = message;
    }

    public String get()
    {
        return this.message;
    }
}
