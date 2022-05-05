package org.embulk.input.twitter_search;

//import java.util.List;
import java.util.Map;
import org.embulk.EmbulkTestRuntime;
import org.embulk.config.ConfigLoader;
import org.embulk.config.ConfigSource;
//import org.embulk.config.ConfigException;
import org.embulk.spi.Exec;
//import org.embulk.spi.SchemaConfig;
import org.junit.Rule;
//import org.junit.rules.ExpectedException;
import org.junit.Test;
//import org.junit.Ignore;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;

public class TestTwitterSearchInputPlugin
{
    @Rule
    public EmbulkTestRuntime runtime = new EmbulkTestRuntime();

    private ConfigSource getConfigFromYaml(String yaml)
    {
        ConfigLoader loader = new ConfigLoader(Exec.getModelManager());
        return loader.fromYamlString(yaml);
    }

    @Test
    public void testGetConfig()
    {
        String configYaml = "" +
            "type: twitter_search\n" +
            "auth:\n" +
            "  consumer_key: \"sample_consumer_key\"\n" +
            "  consumer_secret: \"sample_consumer_secret\"\n" +
            "  access_token: \"sample_access_token\"\n" +
            "  access_secret: \"sample_access_secret\"\n" +
            "queries:\n" +
            "  - \"from:@nishiogi_now exclude:retweets\"\n" +
            "columns:\n" +
            "  - {name: id, type: long}\n" +
            "  - {name: text, type: string}\n" +
            "  - {name: created_at, type: timestamp}";
        ConfigSource config = getConfigFromYaml(configYaml);
        System.out.println(config.toString());

        ConfigSource auth = config.getNested("auth");
        String consumerKey = auth.get(String.class, "consumer_key");
        System.out.println(consumerKey);

        //System.out.println(config.getObjectNode());
        //config.loadConfig(TwitterSearchInputPlugin.PluginTask.class);

        //assertEquals("twitter_type", config);


        //exception.expect(ConfigException.class);
        //assertThrows
        //assertThrows(5, calc.plus(3));
        //assertThrows
        //exception.expectMessage("Field 'json_column_name' is required but not set");
        //config.loadConfig(TwitterSearchInputPlugin.PluginTask.class);
    }
}
