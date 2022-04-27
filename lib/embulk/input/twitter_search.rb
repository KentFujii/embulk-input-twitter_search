Embulk::JavaPlugin.register_input(
  "twitter_search", "org.embulk.input.twitter_search.TwitterSearchInputPlugin",
  File.expand_path('../../../../classpath', __FILE__))