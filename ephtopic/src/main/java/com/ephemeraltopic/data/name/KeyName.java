package com.ephemeraltopic.data.name;

public enum KeyName
{
    GUILD_ID("ServerID"),
    CATEGORY_NAME("BotCategoryName"),
    CHANNEL_NAME("BotUseToGenerateVoiceChannel"),
    VOICE_CHANNEL_SUFFIX("GeneratedVoiceChannelNameSuffix");
    private final String name;
    KeyName ( String name )
    {
        this.name = name;
    }

    public String get()
    {
        return this.name;
    }
}
