package org.embulk.input.twitter_search;

import java.util.List;
import java.util.Map;
import org.embulk.config.Config;
import org.embulk.config.ConfigDiff;
import org.embulk.config.ConfigSource;
import org.embulk.config.Task;
import org.embulk.config.TaskReport;
import org.embulk.config.TaskSource;
import org.embulk.spi.Exec;
import org.embulk.spi.InputPlugin;
import org.embulk.spi.PageOutput;
import org.embulk.spi.Schema;
import org.embulk.spi.PageBuilder;
import org.embulk.spi.json.JsonParser;
import org.embulk.spi.type.Types;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

public class TwitterSearchInputPlugin implements InputPlugin
{
    public interface PluginTask extends Task
    {
        @Config("auth")
        Map<String, String> getAuth();

        @Config("queries")
        List<String> getQueries();
    }

    @Override
    public ConfigDiff transaction(ConfigSource config, InputPlugin.Control control)
    {
        PluginTask task = config.loadConfig(PluginTask.class);

        Schema schema = Schema.builder().add("status", Types.JSON).build();
        int taskCount = task.getQueries().size();

        return resume(task.dump(), schema, taskCount, control);
    }

    @Override
    public ConfigDiff resume(TaskSource taskSource, Schema schema, int taskCount, InputPlugin.Control control)
    {
        control.run(taskSource, schema, taskCount);
        return Exec.newConfigDiff();
    }

    @Override
    public void cleanup(TaskSource taskSource, Schema schema, int taskCount, List<TaskReport> successTaskReports)
    {
    }

    @Override
    public TaskReport run(TaskSource taskSource, Schema schema, int taskIndex, PageOutput output)
    {
        PluginTask task = taskSource.loadTask(PluginTask.class);

        PageBuilder pagebuilder =
                new PageBuilder(Exec.getBufferAllocator(), schema, output);

        TwitterSearch twitter = new TwitterSearch(
                task.getAuth().get("consumer_key"),
                task.getAuth().get("consumer_secret"),
                task.getAuth().get("access_token"),
                task.getAuth().get("access_secret")
        );

        twitter.search(task.getQueries().get(taskIndex));
        while (twitter.hasNext()) {
            Status status = twitter.next();
            String statusJson = TwitterObjectFactory.getRawJSON(status);
            pagebuilder.setJson(
                    schema.getColumn(0),
                    new JsonParser().parse(statusJson));
            pagebuilder.addRecord();
        }

        pagebuilder.finish();

        return Exec.newTaskReport();
    }

    @Override
    public ConfigDiff guess(ConfigSource config)
    {
        return Exec.newConfigDiff();
    }
}
