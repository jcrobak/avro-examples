Introduction
------------

This project contains two sub-projects for computing the classic WordCount from a
corpus of [Apache Avro](http://avro.apache.org/)-encoded SimpleTweets using Scalding
and Spark. In both cases, there are examples using the Avro Specific and Avro Generic
records. The Specific record

The SimpleTweet schema can be found in `twitter.asvc`.


Scalding
--------

Requirements
============

* Java 1.7+ 
* Apache Maven
* Hadoop (tested with Hadoop 1.2.1) installed and `hadoop` on the `PATH`.

Building
========

To build the `avro-scalding` project:

	cd avro-scalding
	mvn package

To run the version of the WordCount job that uses the Generic records (and the Scalding
Fields API), run:

	hadoop jar target/avro-scalding-1.0.0.jar WordCountJobAvroGeneric --local --input ../../avro-cli-examples/twitter.avro --output /tmp/twitter-wordcount-generic



Refs:

https://groups.google.com/forum/#!topic/cascading-user/Kk6krXYgMEc
http://avro.apache.org/docs/1.7.6/gettingstartedjava.html#download_install
https://github.com/twitter/scalding/wiki/Type-safe-api-references

https://github.com/Tapad/scalding-avro-integration/blob/master/first-party/src/main/scala/jobs/SpecificExample.scala
