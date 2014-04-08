import com.twitter.scalding._
import com.twitter.scalding.avro.UnpackedAvroSource

class WordCountJobAvroGeneric(args : Args) extends Job(args) {
  UnpackedAvroSource( args("input") )
    .flatMap('tweet -> 'word) { line : String => line.split("""\s+""") }
    .groupBy('word) { _.size }
    .write( Tsv( args("output") ) )
}