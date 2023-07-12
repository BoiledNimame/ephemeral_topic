package com.ephemeraltopic.util;

import com.ephemeraltopic.data.bot.ConfigLoader;
import com.ephemeraltopic.data.bot.ServerData;
import net.dv8tion.jda.api.JDA;

import java.util.concurrent.CountDownLatch;

public class Initialize
{
    public static void initialize (JDA jda) throws InterruptedException
    {
        final ServerData dataHolder = ServerData.getInstance();

        CountDownLatch latch = new CountDownLatch(1);
        ConfigLoader thread = new ConfigLoader(latch);
        thread.start();

        // await for Loading Config
        latch.await();
    }
}
