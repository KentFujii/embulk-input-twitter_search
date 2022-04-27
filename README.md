# Twitter Search input plugin for Embulk

TODO: Write short description here and build.gradle file.

## Overview

* **Plugin type**: input
* **Resume supported**: yes
* **Cleanup supported**: yes
* **Guess supported**: no

## Configuration

- **option1**: description (integer, required)
- **option2**: description (string, default: `"myvalue"`)
- **option3**: description (string, default: `null`)

## Example

```yaml
in:
  type: twitter_search
  option1: example1
  option2: example2
```


## Build

```
$ ./gradlew gem  # -t to watch change of files and rebuild continuously
```
# embulk-input-twitter_search

## memo

publish with maven
https://zenn.dev/hiroysato/articles/e693bc483a67de

embulk-input-http
https://github.com/takumakanari/embulk-input-http

embulk-output-bigquery_java
https://github.com/trocco-io/embulk-output-bigquery_java

https://qiita.com/engabesi/items/9d08342241405378a8f6
https://gist.github.com/serihiro/6da0392bf3d94a9b673562370259d355
https://docs.google.com/document/u/0/d/1oKpvgstKlgmgUUja8hYqTqWxtwsgIbONoUaEj8lO0FE/mobilebasic

`embulk run -L ./ example/config.yml`