package org.embulk.input.twitter_search;

import org.embulk.EmbulkTestRuntime;
import org.embulk.config.ConfigLoader;
import org.embulk.config.ConfigSource;
//import org.embulk.config.ConfigException;
import org.embulk.spi.Exec;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

public class TestTwitterSearchInputPlugin
{
    @Rule
    public EmbulkTestRuntime runtime = new EmbulkTestRuntime();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ConfigSource getConfigFromYaml(String yaml)
    {
        ConfigLoader loader = new ConfigLoader(Exec.getModelManager());
        return loader.fromYamlString(yaml);
    }

    @Test
    public void testThrowExceptionAbsentJsonColumnName()
    {
        String configYaml = "" +
                "type: expand_json\n" +
                "expanded_columns:\n" +
                "  - {name: _c1, type: string}";
        ConfigSource config = getConfigFromYaml(configYaml);

        //exception.expect(ConfigException.class);
        //exception.expectMessage("Field 'json_column_name' is required but not set");
        //config.loadConfig(TwitterSearchInputPlugin.PluginTask.class);
    }
}
