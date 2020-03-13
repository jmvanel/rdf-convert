import scala.io.Source
  
object PlainTextBibliography {
  def preProcess(s: String): String = {
    // le pré-traitement pour e. g. et sp. :
    s . replaceAll("""e\. g\.""", "e\u00B7 g\u00B7" ) . replaceAll("""sp\.""", "sp\u00B7" )
    // L'expression "p." se traite de même.
    . replaceAll(""" p\.""", " p\u00B7" )
    // le pré-traitement pour les initiales avec 3 lettres:
    . replaceAll("""([A-Z])\. ([A-Z])\.""", """$1\u00B7\u00A0$2\u00B7""")
    // problème des points dans l'abstract ABS: . On les repère car ils sont précédés de 2 minuscule (au moins)
    . replaceAll("""(?<=[a-z][a-z])\. """, "\u00B7\u00A0" )
  }

  def processLine(s: String): Seq[String] = {
    s.split("""(?<!\([A-Z])\. """)
  }

  def processFields(parts: Seq[String]) : Map[String, String] = {
    val res = scala.collection.mutable.Map[String, String]().withDefaultValue("")
    res("bibo:authorList") = parts.lift(0).getOrElse("")
    res("dc:title") = parts.lift(1).getOrElse("")
    res("dct:publisher") = parts.lift(2).getOrElse("")
    def processUnorderedField( field: String, property: String) = {
      val part = parts . filter{ part => part . startsWith(field) } . headOption . getOrElse("")
      if(part != "") res(property) = res(property) + ". " + part
    }
    // les champs nommés facultatifs  In: , DOI: , ABS: , RES: , RÉS: , SUM: sont dans un ordre variable
    processUnorderedField("In:", "bibo:reproducedIn")
    processUnorderedField("DOI:", "url:doi")
    processUnorderedField("ABS:", "dc:abstract")
    processUnorderedField("RES:", "dc:abstract")
    processUnorderedField("RÉS:", "dc:abstract")
    processUnorderedField("SUM:", "dc:abstract")

    // toujours à la fin, les champs  facultatifs PDF et un hyperlien.
    processUnorderedField("PDF", "dc:pdf")
    processUnorderedField("http", "rdfs:seeAlso")

    res . toMap
  }

  def main(args:Array[String]): Unit = {
  val filename = args(0)
  val properties = Seq( "dc:title", "bibo:authorList", "dct:publisher", "bibo:reproducedIn", "url:doi", "dc:abstract", "dc:pdf", "rdfs:seeAlso")
  for ( property <- properties ) { print( property + "\t" ) } ; println
  for (line <- Source.fromFile(filename).getLines)  {
    Console.err.println(line)
    val parts = processLine(preProcess(line))
    // create tab separated string // val tabSeparatedLine = parts . foldLeft("") ((accum, element) => s"$accum	$element") ; println(tabSeparatedLine)
    val fieldsMap = processFields(parts)

    for ( property <- properties ) {
      val value = fieldsMap.lift(property).getOrElse("")
      print( value + "\t" )
    }
    println
  }
}
}
