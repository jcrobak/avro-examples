name := "Avro Spark"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "0.9.0-incubating"

libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.3"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.2.1"

// see: http://apache-spark-user-list.1001560.n3.nabble.com/SparkContext-startup-time-out-td1753.html

// libraryDependencies += "com.typesafe.akka" % "akka-cluster_2.10" % "2.2.4"

// see: https://issues.apache.org/jira/browse/SPARK-1138