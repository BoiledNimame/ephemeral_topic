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
import java.util.Arrays;

public class Main
{
    private final JDA jda;
    private static Main ephemeral_topic;
    public static Guild guild;
    public static Logger logger;

    private Main( String[] args ) throws LoginException
    {
        String token = Arrays.stream(args)
                .map( s -> s.split(":") )
                .filter( arr -> arr[0].equals("-token") )
                .map( arr -> arr[1] )
                .findFirst()
                .orElse(null);

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

    public static void exit ( int code, String msg )
    {
        // TODO shutdown jda object
        ephemeral_topic.jda.shutdown();
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