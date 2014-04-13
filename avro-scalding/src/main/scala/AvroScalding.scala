/**
 * Scalding example word count implementations for avro-serialized data.
 */

import avrotests.SimpleTweet
import com.twitter.scalding._
import com.twitter.scalding.avro.UnpackedAvroSource
import com.twitter.scalding.avro.PackedAvroSource


/**
 * Computes WordCount using the Avro Generic API and the Scalding Fields API.
 */
class WordCountJobAvroGeneric(args: Args) extends Job(args) {
  UnpackedAvroSource( args("input") )
    .flatMap('tweet -> 'word) { line : String => line.split("""\s+""") }
    .groupBy('word) { _.size }
    .write( Tsv( args("output") ) )
}


/**
 * Computes WordCount using the Avro Specific API and the Scalding Typed API.
 */
class WordCountJobAvroSpecific(args: Args) extends Job(args) {
  TypedPipe.from(PackedAvroSource[SimpleTweet]( args("input") ))
    .flatMap{ tweet: SimpleTweet => tweet.tweet.toString.split("""\s+""") }
    .groupBy{ identity }
    .size
    .toPipe('word, 'count)
    .write( Tsv( args("output") ) )
}