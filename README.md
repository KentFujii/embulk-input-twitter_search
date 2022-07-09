# Twitter Search input plugin for Embulk

Input Twitter Search API plugin for Embulk.

## Overview

* **Plugin type**: input
* **Resume supported**: no
* **Cleanup supported**: no
* **Guess supported**: no

## Example

```yaml
input:
  type: twitter_search
  auth:
    consumer_key: "sample_consumer_key"
    consumer_secret: "sample_consumer_secret"
    access_token: "sample_access_token"
    access_secret: "sample_access_secret"
  queries:
    - "from:@nishiogi_now exclude:retweets"
```

## TODO

- exp backeff and full-jitter
- Resume and Cleanup