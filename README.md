# rss-reader

Simple RSS feed reader, written just for fun.

## Usage

### Build

```
$ git clone https://github.com/swajeet01/rss-reader.git
$ cd rss-reader
$ mvn package
$ java -cp target/rssreader-0.0.3.jar com.sg.rssreader.Main
$ # or with options
$ # java -cp target/rssreader-0.0.3.jar com.sg.rssreader.Main [-d <delay in ms>] [-u <rss feed link>]
```

### Grab a release

Check [releases](https://github.com/swajeet01/rss-reader/releases) for latest build, download a `jar`, then-

```
$ java -cp path-to-your-jar/rssreader-0.0.3.jar com.sg.rssreader.Main
$ # or with options
$ # java -cp path-to-your-jar/rssreader-0.0.3.jar com.sg.rssreader.Main [-d <delay in ms>] [-u <rss feed link>]
```
