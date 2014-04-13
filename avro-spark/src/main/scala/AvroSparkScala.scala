import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import org.apache.avro.generic.GenericRecord
import org.apache.avro.mapred.AvroKey
import org.apache.avro.mapreduce.AvroKeyInputFormat
import org.apache.hadoop.io.NullWritable
import org.apache.commons.lang.StringEscapeUtils.escapeCsv

object WordCountJobAvroGenericSpark {
  def main(args: Array[String]) {
    val sc = new SparkContext("local", "Avro Generic Spark Scala",
      System.getenv().get("SPARK_HOME"), List("target/scala-2.10/avro-spark_2.10-1.0.jar"))

    val avroRdd = sc.newAPIHadoopFile("file://%s".format(args(0),
    								  classOf[AvroKeyInputFormat[GenericRecord]],
    								  classOf[AvroKey[GenericRecord]],
    								  classOf[NullWritable])

  	val genericRecords = avroRdd.map{case (ak, _) => ak.datum()}

    val wordCounts = genericRecords.map(gr => gr.get("tweet").asInstanceOf[String])
    	.flatMap{tweet: String => tweet.split(" ")}
    	.map(word => (word, 1))
    	.reduceByKey((a, b) => a + b)

    val wordCountsFormatted = wordCounts.map{case (word, count) => (escapeCsv(word), count)}
    	.map{case (word, count) => s"$word,$count"}

  	wordCountsFormatted.saveAsTextFile("file:///tmp/twitter-wordcount-scala-spark.tsv")    
  }
}

object WordCountJobAvroSpecificSpark {
  def main(args: Array[String]) {
    val sc = new SparkContext("local", "Avro Specific Spark Scala",
      System.getenv().get("SPARK_HOME"), List("target/scala-2.10/avro-spark_2.10-1.0.jar"))

    val avroRdd = sc.newAPIHadoopFile("file:///Users/joe/Code/avro-cli-examples/twitter.avro",
                      classOf[AvroKeyInputFormat[GenericRecord]],
                      classOf[AvroKey[GenericRecord]],
                      classOf[NullWritable])

    val genericRecords = avroRdd.map{case (ak, _) => ak.datum()}

    val wordCounts = genericRecords.map(gr => gr.get("tweet").asInstanceOf[String])
      .flatMap{tweet: String => tweet.split(" ")}
      .map(word => (word, 1))
      .reduceByKey((a, b) => a + b)

    val wordCountsFormatted = wordCounts.map{case (word, count) => (escapeCsv(word), count)}
      .map{case (word, count) => s"$word,$count"}

    wordCountsFormatted.saveAsTextFile("file:///tmp/twitter-wordcount-scala-spark.tsv")    
  }
}