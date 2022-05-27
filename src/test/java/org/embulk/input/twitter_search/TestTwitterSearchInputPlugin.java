package org.embulk.input.twitter_search;

import java.util.List;
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
import static org.junit.Assert.assertEquals;
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

        ConfigSource auth = config.getNested("auth");
        String consumerKey = auth.get(String.class, "consumer_key");
        String consumerSecret = auth.get(String.class, "consumer_secret");
        String accessToken = auth.get(String.class, "access_token");
        String accessSecret = auth.get(String.class, "access_secret");
        assertEquals("sample_consumer_key", consumerKey);
        assertEquals("sample_consumer_secret", consumerSecret);
        assertEquals("sample_access_token", accessToken);
        assertEquals("sample_access_secret", accessSecret);

        String[] queries = config.get(String[].class, "queries");
        assertEquals("from:@nishiogi_now exclude:retweets", queries[0]);

        ConfigSource[] columns = config.get(ConfigSource[].class, "columns");
        assertEquals("id", columns[0].get(String.class, "name"));
        assertEquals("long", columns[0].get(String.class, "type"));
    }
}
