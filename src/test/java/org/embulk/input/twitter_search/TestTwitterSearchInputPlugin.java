package org.embulk.input.twitter_search;

import org.embulk.test.TestingEmbulk;
import org.embulk.spi.InputPlugin;
import org.embulk.config.ConfigSource;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestTwitterSearchInputPlugin
{

    @Rule
    public TestingEmbulk embulk = TestingEmbulk.builder()
            .registerPlugin(InputPlugin.class, "twitter_search", TwitterSearchInputPlugin.class)
            .build();

    @Test
    public void testGetConfig()
    {
        ConfigSource config = embulk.loadYamlResource("java/org/embulk/input/twitter_search/test.yml");
        TwitterSearchInputPlugin.PluginTask task = config.loadConfig(TwitterSearchInputPlugin.PluginTask.class);
        assertEquals("sample_consumer_key", task.getAuth().get("consumer_key"));
    }
}
