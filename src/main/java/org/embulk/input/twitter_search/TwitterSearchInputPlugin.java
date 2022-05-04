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
import org.embulk.spi.SchemaConfig;
import org.embulk.spi.PageBuilder;

public class TwitterSearchInputPlugin implements InputPlugin {
    public interface PluginTask extends Task {
        @Config("auth")
        Map<String, String> getAuth();

        @Config("queries")
        List<String> getQueries();

        @Config("columns")
        SchemaConfig getColumns();
    }

    @Override
    public ConfigDiff transaction(ConfigSource config, InputPlugin.Control control) {
        PluginTask task = config.loadConfig(PluginTask.class);

        Schema schema = task.getColumns().toSchema();
        int taskCount = 1;  // number of run() method calls

        return resume(task.dump(), schema, taskCount, control);
    }

    @Override
    public ConfigDiff resume(TaskSource taskSource, Schema schema, int taskCount, InputPlugin.Control control) {
        control.run(taskSource, schema, taskCount);
        return Exec.newConfigDiff();
    }

    @Override
    public void cleanup(TaskSource taskSource, Schema schema, int taskCount, List<TaskReport> successTaskReports) {
    }

    @Override
    public TaskReport run(TaskSource taskSource, Schema schema, int taskIndex, PageOutput output) {
        PluginTask task = taskSource.loadTask(PluginTask.class);
        System.out.println(task.getAuth());
        System.out.println(task.getQueries());


        PageBuilder pagebuilder =
                new PageBuilder(Exec.getBufferAllocator(), schema, output);

        System.out.println(schema.getColumn(0));
        System.out.println(schema.getColumn(1));
        pagebuilder.setString(schema.getColumn(0), "111111111");
        pagebuilder.setString(schema.getColumn(1), "222222222");
        pagebuilder.addRecord();

        pagebuilder.setString(schema.getColumn(0), "333333333");
        pagebuilder.setString(schema.getColumn(1), "444444444");
        pagebuilder.addRecord();

        pagebuilder.finish();

        return Exec.newTaskReport();
    }

    @Override
    public ConfigDiff guess(ConfigSource config)
    {
        return Exec.newConfigDiff();
    }
}
