package com.ephemeraltopic.data.bot;

import com.ephemeraltopic.Main;
import com.ephemeraltopic.data.name.KeyName;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ConfigLoader extends Thread
{
    private CountDownLatch latch;
    public ConfigLoader( CountDownLatch latch )
    {
        this.latch = latch;
    }

    @Override
    public  void run()
    {
        Yaml yaml = new Yaml();
        try
        {
            InputStream inputStream = new FileInputStream(Paths.get("./config.yaml").toFile());
            // yamlファイルからデータを抽出する
            @SuppressWarnings("unchecked")
            Map<String, String> Map = (Map<String, String>)((Map<String, Object>)yaml.load(inputStream)).get("config");

            // 試しにダンプさせる
            Main.logger.info("config.yaml has been successfully loaded.");
            Main.logger.info(connect("ServerID: ", Map.get(KeyName.GUILD_ID.get())));
            Main.logger.info(connect("CategoryName: ", Map.get(KeyName.CATEGORY_NAME.get())));

            // データ保持クラスに格納
                //TODO ServerDataへ格納

            // ストリームを閉じる
            inputStream.close();
        }
        catch ( IOException e ) {
            throw new RuntimeException(e);
        }
        finally
        {
            // initialize再開
            latch.countDown();
        }
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String connect(String arg1, String arg2 )
    {
        return new StringBuilder().append(arg1).append(arg2).toString();
    }
}