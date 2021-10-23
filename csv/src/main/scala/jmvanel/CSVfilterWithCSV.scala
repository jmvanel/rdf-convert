package jmvanel

import org.apache.commons.csv._
import java.io._
import collection.JavaConverters._

/** Filter Lines in CSV matching one of the lines in another CSV
 *  Use case: tract rare plants scepies from a list of observations
 *  How to adapt: change:
 *  - idColumnName
 *  - calls to removeRenameKeys */
object CSVfilterWithCSV extends App {
  val nameColumn = "nom_valide"

  val csvFormatMain = CSVFormat. 
    RFC4180. withFirstRecordAsHeader(). withIgnoreSurroundingSpaces().withDelimiter(';')
  /*Reader*/ val in = new FileReader(args(0))
  val csvParser = CSVParser.parse( in, csvFormatMain)
  val recordsMain0 = csvParser iterator() asScala
  val recordsMain = recordsMain0.toList
  System.err.println( s"recordsMain Collection ${recordsMain.size}")
  val recordsFilterCollection = {
    val csvFormatFilter = CSVFormat. 
      RFC4180. withFirstRecordAsHeader(). withIgnoreSurroundingSpaces().withDelimiter(',')
      /*Reader*/ val in = new FileReader(args(1))
    val csvParserFilter = CSVParser.parse( in, csvFormatFilter)
    val recordsFilter = csvParserFilter iterator() asScala ;
    for( record <- recordsFilter ) yield {
      record.toMap().asScala
    }
  } . toList
  System.err.println(s"recordsFilterCollection ${recordsFilterCollection.getClass()} ${recordsFilterCollection.size}")

  var recordCount = 0
  var recordCountOutput = 0

  val printer = new CSVPrinter(new FileWriter("csv.txt"), CSVFormat.RFC4180 .withDelimiter(';') )
  printer.printRecord( csvParser.getHeaderNames() )
  for( record <- recordsMain ) {
    recordCount = recordCount + 1
    val key2value = record.toMap().asScala
    // System.err.println("recordCount:" + recordCount + " " + key2value.mkString(";"))
    val valuesCollection = filter(key2value, recordsFilterCollection)
    /*
     */
    for( values <- valuesCollection ) {
      printer.printRecord( values.values  asJava )
      recordCountOutput = recordCountOutput + 1
    }
  }
  System.err.println( "\ngetHeaderNames() " + csvParser.getHeaderNames() + "\n")
  System.err.println( s"RecordCount = $recordCount (getRecordNumber ${csvParser.getRecordNumber}) => output $recordCountOutput" )
  
  /** Filter keys */
  def filter(key2value: scala.collection.Map[String, String],
    recordsFilter: Iterable[scala.collection.mutable.Map[String,String]]
    ) : Iterable[scala.collection.Map[String, String]] = {
    
    val scientificNameWithAutor = key2value.get("nom_valide").getOrElse("")
    //System.err.println(s"scientificNameWithAutor $scientificNameWithAutor")
    for ( recordFilter <- recordsFilter ; 
      //_ = System.err.println(s"recordFilter ${recordFilter.mkString(";")}") ;
      taxonFromFilter <- recordFilter.get("Taxon") ;
      //_ = System.err.println(s"taxonFromFilter $taxonFromFilter") ;
      if( scientificNameWithAutor.startsWith(taxonFromFilter) )
      ) yield {
        System.err.println(s"MATCH: $scientificNameWithAutor")
        key2value
      }
  }
}

