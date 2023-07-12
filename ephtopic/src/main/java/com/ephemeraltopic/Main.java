package com.ephemeraltopic;


import com.ephemeraltopic.data.name.ErrorMsgTemp;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.security.auth.login.LoginException;

public class Main
{
    private final JDA jda;
    private static final Main ephemeral_topic = null;
    public static Guild guild;
    public static Logger logger;

    private Main( String[] args ) throws LoginException
    {
        String token = ArgsSplitter.getAugmentValue("-token", args);
        String guildId = ArgsSplitter.getAugmentValue("-guild", args);

        if ( token==null )
        {
            exit(1, ErrorMsgTemp.AUGMENT_IS_NULL.get());
        }

        jda = JDABuilder.createDefault(token)
                .setEnabledIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.SCHEDULED_EVENTS,
                        GatewayIntent.MESSAGE_CONTENT
                )
                .setMemberCachePolicy(MemberCachePolicy.DEFAULT)
                .setEventPassthrough(true)
                .build();

        guild = jda.getGuildById(guildId);
    }

    public static void main ( String[] args )
    {
        logger = LogManager.getLogger();
        if ( args==null || args.length==0 )
        {
            exit(1, ErrorMsgTemp.AUGMENT_IS_NULL.get());
        }
        else
        {
            try
            {
                Main ephemeral_topic = new Main( args );
                ephemeral_topic.jda.awaitReady();
                // TODO configLoader
            }
            catch ( LoginException e )
            {
                logger.fatal(ErrorMsgTemp.LOGIN_EXCEPTION.get());
                e.printStackTrace();
            } catch ( InterruptedException ignore )
            {
                // This exception is not raised except during awaitReady, but it is not raised by this code, so ignore
            }
        }
    }

    public static Main getInstance()
    {
        return ephemeral_topic;
    }

    public static void exit (int code, String msg )
    {
        // TODO shutdown jda object
        getInstance().jda.shutdown();
        if ( code < 1 )
        {
            logger .info(msg);
        }
        else
        {
            logger.error(msg);
        }
        System.exit(code);
    }
}