import org.apache.commons.csv._
import java.io._
import collection.JavaConverters._

/** splits Lines in CSV */
object CSVsplitLine extends App {
/*
  DEFAULT. // RFC4180. 
  withDelimiter(';'). 
  withQuote('"') . withRecordSeparator("\r\n") . withIgnoreEmptyLines(false) .
*/
  val csvFormat = CSVFormat. 
    RFC4180. withFirstRecordAsHeader(). withIgnoreSurroundingSpaces()
  /*Reader*/ val in = new FileReader(args(0))
  // /*Iterable<CSVRecord>*/ val records = csvFormat.parse(in).asScala
  val csvParser = CSVParser.parse( in, csvFormat)
  val records = csvParser iterator() asScala
  var badRecordCount = 0
  var recordCount = 0
  var recordCountOutput = 0
  val printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.RFC4180) 

  System.err.println( "getHeaderNames() " + csvParser.getHeaderNames() )
  printer.printRecord( csvParser.getHeaderNames() )
  for( record <- records ) {
    recordCount = recordCount + 1
    val key2value = record.toMap().asScala

    // System.err.println( key2value . mkString(",") + " ; rec " + record. getRecordNumber() )
    if( record.size() <= 1 ) {
      badRecordCount = badRecordCount + 1
    }
    val scientificName = record.get("scientificName") ; System.err.println( scientificName )
    removeRenameKeys( key2value, Map(),
      List("eventDate2","verbatimLocality2", "eventDate3","verbatimLocality3", "eventDate4","verbatimLocality4"),
      printer )
    removeRenameKeys( key2value,
      Map("eventDate2" -> "eventDate", "verbatimLocality2"-> "verbatimLocality"),
      List("eventDate","verbatimLocality", "eventDate3","verbatimLocality3", "eventDate4","verbatimLocality4"),
      printer )
    removeRenameKeys( key2value,
      Map("eventDate3" -> "eventDate", "verbatimLocality3"-> "verbatimLocality"),
      List("eventDate","verbatimLocality", "eventDate2","verbatimLocality2", "eventDate4","verbatimLocality4"),
      printer )
    removeRenameKeys( key2value,
      Map("eventDate4" -> "eventDate", "verbatimLocality4"-> "verbatimLocality"),
      List("eventDate","verbatimLocality", "eventDate2","verbatimLocality2", "eventDate3","verbatimLocality3"),
      printer )
  }
  System.err.println( s"\nbadRecordCount = $badRecordCount" )
  System.err.println( s"RecordCount = $recordCount (getRecordNumber ${csvParser.getRecordNumber}) => output $recordCountOutput" )

  /** remove and Rename keys, and print Record 
   *  do not print record if keysToRename matches nothing */
  def removeRenameKeys(key2value: scala.collection.Map[String, String],
     keysToRename: Map[String,String],
     keysToRemove: List[String],
     printer: CSVPrinter ): Unit = {
     val mapAfterRemoval = key2value -- keysToRemove
     val mapAfterRename =
       mapAfterRemoval . map {
         e=>(keysToRename.getOrElse(e._1, e._1), e._2)
       }
     // print with original column order
     val headerNames = csvParser.getHeaderNames()
     val values = for ( headerName <- headerNames asScala ) yield
     mapAfterRename.getOrElse(headerName, "")
     val firstKeyTorename = keysToRename.keys.headOption.getOrElse("")
     if( keysToRename . size == 0 ||
         key2value .getOrElse(firstKeyTorename, "") != "" ) {
       printer.printRecord( values. toIterable asJava )
       recordCountOutput = recordCountOutput + 1
       }
  }

  private def removeRenameKeysDraft(key2value: scala.collection.Map[String, String],
     keysToRename: Map[String,String],
     keysToRemove: List[String],
     printer: CSVPrinter ): Unit = {
    val basisForSplitting = List("eventDate2", "verbatimLocality2")
    val keysReplacedForWriting = List("eventDate", "verbatimLocality")
    val valueForSplitting = key2value(basisForSplitting(0))
    if( valueForSplitting != "" ) {
      System.err.println( basisForSplitting(0) + " " + valueForSplitting )
      // print record 1
      val m2 = key2value -- keysReplacedForWriting
      // trim record after last in basisForSplitting
      val ind:Int = m2 . values.toList.indexOf(keysReplacedForWriting.last)
      // list.subList(4, list.size()).clear();
      val keys = m2 . keys
      val keysAfterRemoval = keys . toList . slice ( ind, keys.size )
      val m3 = m2 -- keysAfterRemoval
      printer.printRecord( m3 . values )
    }
  }
}
